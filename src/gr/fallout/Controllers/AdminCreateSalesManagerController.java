package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminCreateSalesManagerController extends Controller
{
    private Administrator m_Administrator;

    public AdminCreateSalesManagerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
