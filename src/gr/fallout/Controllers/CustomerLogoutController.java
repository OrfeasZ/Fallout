package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Responses.RedirectResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:09 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerLogoutController extends ProtectedController<Customer>
{
    public CustomerLogoutController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_cust_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        Util.SetCookie(m_Exchange, "fo_cust_sid", "expired");

        return new RedirectResponse(m_ContextBase + "login/");
    }
}
