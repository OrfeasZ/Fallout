package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Net.Response;

/**
 * Date: 8/12/2013
 * Time: 12:08 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerPlaceOrderController extends Controller
{
    private Customer m_Customer;

    public CustomerPlaceOrderController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }


    @Override
    public Response Execute()
    {
        return null;
    }
}
