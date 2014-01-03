package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;

import java.util.HashMap;

/**
 * Date: 7/12/2013
 * Time: 11:55 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AccountingLogoutController extends Controller
{
    public AccountingLogoutController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
