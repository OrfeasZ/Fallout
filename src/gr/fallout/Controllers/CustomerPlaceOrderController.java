package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Net.Response;

import java.util.HashMap;

/**
 * Date: 8/12/2013
 * Time: 12:08 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerPlaceOrderController extends Controller
{
    private Customer m_Customer;

    public CustomerPlaceOrderController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }


    @Override
    public Response Execute()
    {
        return null;
    }
}
