package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.Administrator;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AdminCreateStorageManagerValidator;

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
public class AdminCreateStorageManagerController extends ProtectedController<Administrator>
{
    public AdminCreateStorageManagerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
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

        AdminCreateStorageManagerValidator s_Validator = new AdminCreateStorageManagerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        String s_Username = m_Params.get("username").get(0);
        String s_Password = m_Params.get("password").get(0);

        Collection<StorageManager> s_StorageManagers = RecordManager.GetInstance().StorageManagers.GetAll();

        for (StorageManager s_Manager : s_StorageManagers)
            if (s_Manager.Username().equalsIgnoreCase(s_Username))
                return new AjaxErrorResponse("The specified username is in use.");

        StorageManager s_Manager = new StorageManager();
        s_Manager.Username(s_Username);
        s_Manager.Password(s_Password);

        RecordManager.GetInstance().StorageManagers.Insert(s_Manager);

        //return new Response(new Gson().toJson(s_Manager));
        return new AjaxRedirectResponse(m_ContextBase);
    }
}
