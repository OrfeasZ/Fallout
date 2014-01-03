package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;

import java.util.HashMap;

/**
 * Date: 8/12/2013
 * Time: 12:09 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class StorageLoginController extends Controller
{
    public StorageLoginController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
