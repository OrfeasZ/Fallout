package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Models.Assembler;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AdminDeleteAssemblerValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:59 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class AdminDeleteAssemblerController extends ProtectedController<Administrator>
{
    public AdminDeleteAssemblerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_admin_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        AdminDeleteAssemblerValidator s_Validator = new AdminDeleteAssemblerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_UserID = Integer.parseInt(m_Params.get("user_id").get(0));

        Assembler s_Assembler = RecordManager.GetInstance().Assemblers.Get(s_UserID);

        if (s_Assembler == null)
            return new AjaxErrorResponse("The specified Assembler doesn't exist.");

        RecordManager.GetInstance().Assemblers.Remove(s_Assembler);

        return new AjaxRedirectResponse(m_ContextBase);
    }
}
