package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Managers.SessionManager;
import gr.fallout.Models.Supplier;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.SupplierLoginValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:18 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class SupplierLoginController extends ProtectedController<Supplier>
{
    public SupplierLoginController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_supply_sid");
    }

    @Override
    public Response Execute()
    {
        if (m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
        {
            // We should be redirected to the dashboard if we're already logged in.
            if (m_LoggedIn)
                return new AjaxRedirectResponse(m_ContextBase);

            SupplierLoginValidator s_Validator = new SupplierLoginValidator();
            List<String> s_Errors = s_Validator.Validate(m_Params);

            // Always return the first error
            if (s_Errors != null && !s_Errors.isEmpty())
                return new AjaxErrorResponse(s_Errors.get(0));

            Integer s_TaxID = Integer.parseInt(m_Params.get("taxid").get(0));

            Collection<Supplier> s_Suppliers = RecordManager.GetInstance().Suppliers.GetAll();

            for (Supplier s_Supplier : s_Suppliers)
            {
                if (s_Supplier.TaxID() == s_TaxID)
                {
                    String s_SessionID = SessionManager.GetInstance().CreateUserSession(s_Supplier);
                    Util.SetCookie(m_Exchange, "fo_supply_sid", s_SessionID);
                    return new AjaxRedirectResponse(m_ContextBase);
                }
            }

            return new AjaxErrorResponse("User not found.");
        }

        // We should be redirected to the dashboard if we're already logged in.
        if (m_LoggedIn)
            return new RedirectResponse(m_ContextBase);

        return new AppViewResponse("SupplierLogin", null, "Fallout - Supplier Login");
    }
}
