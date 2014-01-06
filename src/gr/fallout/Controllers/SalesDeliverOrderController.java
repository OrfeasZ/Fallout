package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Models.SalesManager;
import gr.fallout.Models.SupplyOrderItem;
import gr.fallout.Net.Response;
import gr.fallout.Responses.ErrorResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.SalesDeliverOrderValidator;
import gr.fallout.Validators.SupplierConfirmSupplyOrderItemValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:13 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesDeliverOrderController extends ProtectedController<SalesManager>
{
    public SalesDeliverOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_sales_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("GET"))
            return new ErrorResponse("Invalid method.");

        SalesDeliverOrderValidator s_Validator = new SalesDeliverOrderValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new ErrorResponse(s_Errors.get(0));

        Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));

        CustomerOrder s_Item = RecordManager.GetInstance().CustomerOrders.Get(s_OrderID);

        if (s_Item == null)
            return new ErrorResponse("The specified order could not be found.");

        if (s_Item.Delivered())
            return new ErrorResponse("The specified order is already delivered.");

        s_Item.Delivered(true);

        return new RedirectResponse(m_ContextBase);
    }
}
