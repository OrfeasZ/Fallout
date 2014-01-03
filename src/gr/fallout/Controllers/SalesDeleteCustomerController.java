package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:09 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesDeleteCustomerController extends Controller
{
    private SalesManager m_Manager;

    private Customer m_Customer;

    public SalesDeleteCustomerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
