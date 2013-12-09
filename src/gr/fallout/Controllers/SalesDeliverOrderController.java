package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;

/**
 * Date: 9/12/2013
 * Time: 4:13 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesDeliverOrderController extends Controller
{
    private SalesManager m_Manager;

    private CustomerOrder m_Order;

    public SalesDeliverOrderController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
