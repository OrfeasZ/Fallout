package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.SupplyOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.StorageSupplyOrderValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:15 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class StorageSupplyOrderController extends ProtectedController<StorageManager>
{
    private StorageManager m_Manager;

    private SupplyOrder m_SupplyOrder;

    public StorageSupplyOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_storage_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("GET"))
            return new AjaxErrorResponse("Invalid method.");

        StorageSupplyOrderValidator s_Validator = new StorageSupplyOrderValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));

        SupplyOrder s_Order = RecordManager.GetInstance().SupplyOrders.Get(s_OrderID);

        if (s_Order == null)
            return new AjaxErrorResponse("The specified order could not be found.");

        return new Response(new Gson().toJson(s_Order.Items()), 200, "application/json");
    }
}
