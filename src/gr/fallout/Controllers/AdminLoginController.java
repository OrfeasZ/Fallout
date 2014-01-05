package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Managers.SessionManager;
import gr.fallout.Models.Administrator;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Responses.ErrorResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.StandardLoginValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:58 μμ
 *
 * @author OrfeasZ, NikosF
 */

public class AdminLoginController extends ProtectedController<Administrator>
{
    public AdminLoginController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_admin_sid");
    }

    @Override
    public Response Execute()
    {
        // We should be redirected to the dashboard if we're already logged in.
        if (m_LoggedIn)
            return new RedirectResponse(m_ContextBase);

        if (m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
        {
            StandardLoginValidator s_Validator = new StandardLoginValidator();
            List<String> s_Errors = s_Validator.Validate(m_Params);

            // Always return the first error
            if (s_Errors != null && !s_Errors.isEmpty())
                return new ErrorResponse(s_Errors.get(0));

            String s_Username = m_Params.get("username").get(0);
            String s_Password = m_Params.get("password").get(0);

            Collection<Administrator> s_Admins = RecordManager.GetInstance().Administrators.GetAll();

            for (Administrator s_Admin : s_Admins)
            {
                if (s_Admin.Username().equalsIgnoreCase(s_Username) && s_Admin.Password().equals(s_Password))
                {
                    String s_SessionID = SessionManager.GetInstance().CreateUserSession(s_Admin);
                    Util.SetCookie(m_Exchange, "fo_admin_sid", s_SessionID);
                    return new RedirectResponse("/admin");
                }
            }

            return new ErrorResponse("User not found.");
        }

        return new AppViewResponse("AdminLogin", null, "Fallout - Admin Login");
    }
}
