package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Net.Response;

import java.util.HashMap;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminCreateStorageManagerController extends Controller
{
    private Administrator m_Administrator;

    public AdminCreateStorageManagerController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
