package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:38 μμ
 *
 * @author OrfeasZ
 */
public abstract class Controller
{
    protected HttpExchange m_Exchange;

    protected HashMap<String, List<String>> m_RouteParams;

    public Controller(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        m_Exchange = p_Exchange;
        m_RouteParams = p_Params;
    }

    public abstract Response Execute();
}
