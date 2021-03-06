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

    protected HashMap<String, List<String>> m_Params;

    protected String m_ContextBase;

    public Controller(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        m_Exchange = p_Exchange;
        m_Params = p_Params;
        m_ContextBase = p_ContextBase;
    }

    public abstract Response Execute();
}
