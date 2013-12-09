package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;

import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:10 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesOrdersController extends Controller
{
    private SalesManager m_Manager;

    private List<CustomerOrder> m_UnassignedOrders;
    private List<CustomerOrder> m_UndeliveredOrders;
    private List<CustomerOrder> m_PendingOrders;
    private List<CustomerOrder> m_DeliveredOrders;

    public SalesOrdersController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
