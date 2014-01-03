package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;

import java.util.HashMap;

/**
 * Date: 7/12/2013
 * Time: 11:56 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerLoginController extends Controller
{
    public CustomerLoginController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        // TODO: This is actually presented by the dashboard.
        // The LoginController just performs the login using provided data.
        return new AppViewResponse("CustomerLogin", null, "Fallout - Customer Login");
    }
}
