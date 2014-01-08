package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.AccountingManager;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Responses.RedirectResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:55 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */

public class AccountingLogoutController extends ProtectedController<AccountingManager>
{
    public AccountingLogoutController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_acct_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        Util.SetCookie(m_Exchange, "fo_acct_sid", "expired");

        return new RedirectResponse(m_ContextBase + "login/");
    }
}
