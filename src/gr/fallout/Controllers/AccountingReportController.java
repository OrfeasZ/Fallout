package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:42 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AccountingReportController extends Controller
{
    private AccountingManager m_Manager;

    private Report m_Report;

    private List<SupplyOrder> m_SupplyOrders;

    private List<RobotControllerOrder> m_ControllerOrders;

    private List<CustomerOrder> m_CustomerOrders;

    public AccountingReportController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase);
        System.out.println("Viewing report with id " + p_Params.get("id"));
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
