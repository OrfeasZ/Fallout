package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Managers.SessionManager;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.StandardLoginValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:08 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */

public class SalesLoginController extends ProtectedController<SalesManager>
{
    public SalesLoginController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_sales_sid");
    }

    @Override
    public Response Execute()
    {
        if (m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
        {
            // We should be redirected to the dashboard if we're already logged in.
            if (m_LoggedIn)
                return new AjaxRedirectResponse(m_ContextBase);

            StandardLoginValidator s_Validator = new StandardLoginValidator();
            List<String> s_Errors = s_Validator.Validate(m_Params);

            // Always return the first error
            if (s_Errors != null && !s_Errors.isEmpty())
                return new AjaxErrorResponse(s_Errors.get(0));

            String s_Username = m_Params.get("username").get(0);
            String s_Password = m_Params.get("password").get(0);

            Collection<SalesManager> s_SalesManagers = RecordManager.GetInstance().SalesManagers.GetAll();

            for (SalesManager s_SalesManager : s_SalesManagers)
            {
                if (s_SalesManager.Username().equalsIgnoreCase(s_Username) && s_SalesManager.Password().equals(s_Password))
                {
                    String s_SessionID = SessionManager.GetInstance().CreateUserSession(s_SalesManager);
                    Util.SetCookie(m_Exchange, "fo_sales_sid", s_SessionID);
                    return new AjaxRedirectResponse(m_ContextBase);
                }
            }

            return new AjaxErrorResponse("User not found.");
        }

        // We should be redirected to the dashboard if we're already logged in.
        if (m_LoggedIn)
            return new RedirectResponse(m_ContextBase);

        return new AppViewResponse("SalesLogin", null, "Fallout - Sales Login");
    }
}
