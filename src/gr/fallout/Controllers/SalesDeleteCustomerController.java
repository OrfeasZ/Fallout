package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;

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

    public SalesDeleteCustomerController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
