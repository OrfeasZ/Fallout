package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Models.Assembler;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AdminCreateAssemblerValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:59 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class AdminCreateAssemblerController extends ProtectedController<Administrator>
{
    public AdminCreateAssemblerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_admin_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
            return new AjaxErrorResponse("Invalid method.");

        AdminCreateAssemblerValidator s_Validator = new AdminCreateAssemblerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        String s_Name = m_Params.get("name").get(0);
        String s_Username = m_Params.get("username").get(0);
        String s_Password = m_Params.get("password").get(0);

        Collection<Assembler> s_Assemblers = RecordManager.GetInstance().Assemblers.GetAll();

        for (Assembler s_Manager : s_Assemblers)
            if (s_Manager.Username().equalsIgnoreCase(s_Username))
                return new AjaxErrorResponse("The specified username is in use.");

        Assembler s_Manager = new Assembler();
        s_Manager.Name(s_Name);
        s_Manager.Username(s_Username);
        s_Manager.Password(s_Password);

        RecordManager.GetInstance().Assemblers.Insert(s_Manager);

        //return new Response(new Gson().toJson(s_Manager));
        return new AjaxRedirectResponse(m_ContextBase);
    }
}
