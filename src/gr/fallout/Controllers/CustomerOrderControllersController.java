package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Net.Response;

/**
 * Date: 7/12/2013
 * Time: 11:58 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerOrderControllersController extends Controller
{
    private Customer m_Customer;

    private CustomerOrder m_Order;

    public CustomerOrderControllersController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
