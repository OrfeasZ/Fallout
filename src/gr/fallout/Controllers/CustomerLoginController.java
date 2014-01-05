package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:56 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerLoginController extends Controller
{
    public CustomerLoginController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase);
    }

    @Override
    public Response Execute()
    {
        return new AppViewResponse("CustomerLogin", null, "Fallout - Customer Login");
    }
}
