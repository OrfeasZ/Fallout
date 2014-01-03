package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:24 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class AssemblyLogoutController extends Controller
{
    public AssemblyLogoutController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
