package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.SalesDeleteCustomerValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:09 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesDeleteCustomerController extends ProtectedController<SalesManager>
{
    public SalesDeleteCustomerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_sales_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        SalesDeleteCustomerValidator s_Validator = new SalesDeleteCustomerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_UserID = Integer.parseInt(m_Params.get("user_id").get(0));

        Customer s_Customer = RecordManager.GetInstance().Customers.Get(s_UserID);

        if (s_Customer == null)
            return new AjaxErrorResponse("The specified Customer doesn't exist.");

        RecordManager.GetInstance().Customers.Remove(s_Customer);

        return new AjaxRedirectResponse(m_ContextBase);
    }
}
