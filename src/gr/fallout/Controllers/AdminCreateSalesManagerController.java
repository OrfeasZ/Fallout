package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Config;
import gr.fallout.Models.Administrator;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;
import gr.fallout.Responses.ErrorResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AdminCreateSalesManagerValidator;
import gr.fallout.Validators.StandardLoginValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminCreateSalesManagerController extends ProtectedController<Administrator>
{
    public AdminCreateSalesManagerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
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
            return new ErrorResponse("Invalid method.");

        AdminCreateSalesManagerValidator s_Validator = new AdminCreateSalesManagerValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new ErrorResponse(s_Errors.get(0));

        String s_Username = m_Params.get("username").get(0);
        String s_Password = m_Params.get("password").get(0);

        Collection<SalesManager> s_SalesManagers = RecordManager.GetInstance().SalesManagers.GetAll();

        for (SalesManager s_Manager : s_SalesManagers)
            if (s_Manager.Username().equalsIgnoreCase(s_Username))
                return new ErrorResponse("The specified username is in use.");

        SalesManager s_Manager = new SalesManager();
        s_Manager.Username(s_Username);
        s_Manager.Password(s_Password);

        RecordManager.GetInstance().SalesManagers.Insert(s_Manager);

        //return new Response(new Gson().toJson(s_Manager));
        return new RedirectResponse(m_ContextBase);
    }
}
