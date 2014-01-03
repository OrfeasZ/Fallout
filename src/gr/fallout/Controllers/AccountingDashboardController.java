package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.AccountingManager;
import gr.fallout.Models.Report;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:35 μμ
 *
 * @author OrfeasZ, NikosF
 */

public class AccountingDashboardController extends Controller
{
    private AccountingManager m_Manager;

    private List<Report> m_Reports;

    private int m_DaysToNextReport;

    public AccountingDashboardController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    private List<Report> GetAllReports()
    {
        return null;
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
