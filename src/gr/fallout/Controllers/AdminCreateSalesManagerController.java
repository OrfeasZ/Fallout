package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Net.Response;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminCreateSalesManagerController extends Controller
{
    private Administrator m_Administrator;

    public AdminCreateSalesManagerController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}