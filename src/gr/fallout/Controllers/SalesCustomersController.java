package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;

import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:10 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesCustomersController extends Controller
{
    private SalesManager m_Manager;

    private List<Customer> m_Customers;

    public SalesCustomersController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}