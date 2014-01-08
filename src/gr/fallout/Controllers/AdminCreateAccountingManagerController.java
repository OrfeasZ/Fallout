package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.AccountingManager;
import gr.fallout.Models.Administrator;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AdminCreateAccountingManagerValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class AdminCreateAccountingManagerController extends ProtectedController<Administrator>
{
    public AdminCreateAccountingManagerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
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

        AdminCreateAccountingManagerValidator s_Validator = new AdminCreateAccountingManagerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        String s_Username = m_Params.get("username").get(0);
        String s_Password = m_Params.get("password").get(0);

        Collection<AccountingManager> s_AccountingManagers = RecordManager.GetInstance().AccountingManagers.GetAll();

        for (AccountingManager s_Manager : s_AccountingManagers)
            if (s_Manager.Username().equalsIgnoreCase(s_Username))
                return new AjaxErrorResponse("The specified username is in use.");

        AccountingManager s_Manager = new AccountingManager();
        s_Manager.Username(s_Username);
        s_Manager.Password(s_Password);

        RecordManager.GetInstance().AccountingManagers.Insert(s_Manager);

        //return new Response(new Gson().toJson(s_Manager));
        return new AjaxRedirectResponse(m_ContextBase);
    }
}
