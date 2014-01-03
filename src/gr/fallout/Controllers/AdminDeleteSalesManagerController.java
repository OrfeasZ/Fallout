package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;

import java.util.HashMap;

/**
 * Date: 9/12/2013
 * Time: 4:00 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminDeleteSalesManagerController extends Controller
{
    private Administrator m_Administrator;

    private SalesManager m_SalesManager;

    public AdminDeleteSalesManagerController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
