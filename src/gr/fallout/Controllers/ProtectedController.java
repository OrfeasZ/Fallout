package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Managers.SessionManager;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Responses.RedirectResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 1/3/14
 * Time: 6:55 PM
 *
 * @author OrfeasZ
 */

public abstract class ProtectedController<T> extends Controller
{
    protected T m_User;

    protected boolean m_LoggedIn;

    public ProtectedController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase, String p_CookieName)
    {
        super(p_Exchange, p_Params, p_ContextBase);
        m_LoggedIn = false;

        HashMap<String, String> s_Cookies = Util.GetCookies(p_Exchange);

        if (!s_Cookies.containsKey(p_CookieName))
            return;

        if (!SessionManager.GetInstance().HasSession(s_Cookies.get(p_CookieName)))
            return;

        m_LoggedIn = true;
        m_User = (T)SessionManager.GetInstance().GetSessionUser(s_Cookies.get(p_CookieName));
    }

    @Override
    public Response Execute()
    {
        if (!m_LoggedIn)
            return new RedirectResponse(m_ContextBase + "login/");
        return null;
    }
}
