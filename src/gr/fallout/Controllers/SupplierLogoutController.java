package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:19 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class SupplierLogoutController extends Controller
{
    public SupplierLogoutController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
