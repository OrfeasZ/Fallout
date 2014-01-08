package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.AccountingManager;
import gr.fallout.Models.Report;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Store.RecordManager;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.*;

/**
 * Date: 7/12/2013
 * Time: 11:35 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */

public class AccountingDashboardController extends ProtectedController<AccountingManager>
{
    private AccountingManager m_Mannager;

    private Collection<Report> m_Reports;

    private int m_DaysToNextReport;

    public AccountingDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_acct_sid");

        if (!m_LoggedIn)
            return;

        m_Reports = RecordManager.GetInstance().Reports.GetAll();

        if (m_Reports == null)
            m_Reports = new ArrayList<Report>();

        // If this is the first time visiting the accounting dashboard, generate a new report.
        if (m_Reports.isEmpty())
        {
            Report s_Report = new Report();
            s_Report.Date(new Date());
            RecordManager.GetInstance().Reports.Insert(s_Report);
            m_Reports.add(s_Report);

            m_DaysToNextReport = 30;
            return;
        }

        Report s_LatestReport = null;

        // Find the latest report so we can calculate the remaining days for the generation of the next report
        for (Report s_Report : m_Reports)
        {
            if (s_LatestReport == null)
                s_LatestReport = s_Report;

            if (s_Report.Date().after(s_LatestReport.Date()))
                s_LatestReport = s_Report;
        }

        m_DaysToNextReport = 30 - Days.daysBetween(new DateTime(s_LatestReport.Date()), new DateTime(new Date())).getDays();

        if (m_DaysToNextReport <= 0)
        {
            Report s_Report = new Report();
            s_Report.Date(new Date());
            RecordManager.GetInstance().Reports.Insert(s_Report);
            m_Reports.add(s_Report);

            m_DaysToNextReport = 30;
        }
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        return new AppViewResponse("AccountingDashboard", new HashMap<String, String>() {{
            put("reports", new Gson().toJson(m_Reports));
            put("days", Integer.toString(m_DaysToNextReport));
        }}, "Fallout - Accounting Dashboard");
    }
}
