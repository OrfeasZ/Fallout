package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;

/**
 * Date: 9/12/2013
 * Time: 3:58 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminLogoutController extends Controller
{
    public AdminLogoutController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
