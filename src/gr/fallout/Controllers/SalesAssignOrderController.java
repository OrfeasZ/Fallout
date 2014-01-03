package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:13 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesAssignOrderController extends Controller
{
    private SalesManager m_Manager;

    private CustomerOrder m_Order;

    public SalesAssignOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
