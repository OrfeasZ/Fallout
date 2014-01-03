package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;

import java.util.HashMap;

import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:56 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerDashboardController extends Controller
{
    private Customer m_Customer;

    private List<CustomerOrder> m_CustomerOrders;

    public CustomerDashboardController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
