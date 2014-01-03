package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:58 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminDashboardController extends Controller
{
    private Administrator m_Administrator;

    private List<AccountingManager> m_AccountingManagers;
    private List<Assembler> m_Assemblers;
    private List<SalesManager> m_SalesManagers;
    private List<StorageManager> m_StorageManagers;

    public AdminDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
