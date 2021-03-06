package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AdminDeleteSalesManagerValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class AdminDeleteSalesManagerController extends ProtectedController<Administrator>
{
    public AdminDeleteSalesManagerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_admin_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        AdminDeleteSalesManagerValidator s_Validator = new AdminDeleteSalesManagerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_UserID = Integer.parseInt(m_Params.get("user_id").get(0));

        SalesManager s_SalesManager = RecordManager.GetInstance().SalesManagers.Get(s_UserID);

        if (s_SalesManager == null)
            return new AjaxErrorResponse("The specified Sales Manager doesn't exist.");

        RecordManager.GetInstance().SalesManagers.Remove(s_SalesManager);

        return new AjaxRedirectResponse(m_ContextBase);
    }
}
