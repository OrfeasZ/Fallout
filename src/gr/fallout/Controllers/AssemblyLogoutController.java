package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Assembler;
import gr.fallout.Net.Response;
import gr.fallout.Net.Util;
import gr.fallout.Responses.RedirectResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:24 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class AssemblyLogoutController extends ProtectedController<Assembler>
{
    public AssemblyLogoutController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_assembly_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        Util.SetCookie(m_Exchange, "fo_assembly_sid", "expired");

        return new RedirectResponse(m_ContextBase + "login/");
    }
}
