package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Supplier;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Responses.RedirectResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:19 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class SupplierLogoutController extends ProtectedController<Supplier>
{
    public SupplierLogoutController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_supply_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        Util.SetCookie(m_Exchange, "fo_supply_sid", "expired");

        return new RedirectResponse(m_ContextBase + "login/");
    }
}
