package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:03 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerPayOrderController extends Controller
{
    private Customer m_Customer;

    private CustomerOrder m_Order;

    public CustomerPayOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
