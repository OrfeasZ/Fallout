package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Net.Response;

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

    public CustomerPayOrderController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
