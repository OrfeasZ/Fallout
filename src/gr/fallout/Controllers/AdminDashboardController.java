package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:58 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class AdminDashboardController extends ProtectedController<Administrator>
{
    private Collection<AccountingManager> m_AccountingManagers;
    private Collection<Assembler> m_Assemblers;
    private Collection<SalesManager> m_SalesManagers;
    private Collection<StorageManager> m_StorageManagers;

    public AdminDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_admin_sid");

        m_AccountingManagers = Util.FilterPasswords(RecordManager.GetInstance().AccountingManagers.GetAll());
        m_Assemblers = Util.FilterPasswords(RecordManager.GetInstance().Assemblers.GetAll());
        m_SalesManagers = Util.FilterPasswords(RecordManager.GetInstance().SalesManagers.GetAll());
        m_StorageManagers = Util.FilterPasswords(RecordManager.GetInstance().StorageManagers.GetAll());
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        Gson s_Gson = new Gson();

        HashMap<String, String> s_Params = new HashMap<String, String>();
        s_Params.put("acct_managers", s_Gson.toJson(m_AccountingManagers));
        s_Params.put("assemblers", s_Gson.toJson(m_Assemblers));
        s_Params.put("sales_managers", s_Gson.toJson(m_SalesManagers));
        s_Params.put("storage_managers", s_Gson.toJson(m_StorageManagers));

        return new AppViewResponse("AdminDashboard", s_Params, "Admin - Dashboard");
    }
}
