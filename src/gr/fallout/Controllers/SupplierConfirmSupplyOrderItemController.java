package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;
import gr.fallout.Responses.ErrorResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.StorageVerifySupplyOrderItemArrivalValidator;
import gr.fallout.Validators.SupplierConfirmSupplyOrderItemValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:22 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class SupplierConfirmSupplyOrderItemController extends ProtectedController<Supplier>
{
    public SupplierConfirmSupplyOrderItemController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_supply_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("GET"))
            return new ErrorResponse("Invalid method.");

        SupplierConfirmSupplyOrderItemValidator s_Validator = new SupplierConfirmSupplyOrderItemValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new ErrorResponse(s_Errors.get(0));

        Integer s_ItemID = Integer.parseInt(m_Params.get("item_id").get(0));

        SupplyOrderItem s_Item = RecordManager.GetInstance().SupplyOrderItems.Get(s_ItemID);

        if (s_Item == null || s_Item.PaymentConfirmed())
            return new ErrorResponse("The payment for the specified item is already confirmed.");

        s_Item.PaymentConfirmed(true);

        return new RedirectResponse(m_ContextBase);
    }
}
