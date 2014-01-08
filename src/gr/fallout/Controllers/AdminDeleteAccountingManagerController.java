package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.AccountingManager;
import gr.fallout.Models.Administrator;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AdminDeleteAccountingManagerValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminDeleteAccountingManagerController extends ProtectedController<Administrator>
{
    public AdminDeleteAccountingManagerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_admin_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        AdminDeleteAccountingManagerValidator s_Validator = new AdminDeleteAccountingManagerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_UserID = Integer.parseInt(m_Params.get("user_id").get(0));

        AccountingManager s_AccountingManager = RecordManager.GetInstance().AccountingManagers.Get(s_UserID);

        if (s_AccountingManager == null)
            return new AjaxErrorResponse("The specified Accounting Manager doesn't exist.");

        RecordManager.GetInstance().AccountingManagers.Remove(s_AccountingManager);

        return new AjaxRedirectResponse(m_ContextBase);
    }
}
