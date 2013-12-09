package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Net.Response;

/**
 * Date: 9/12/2013
 * Time: 3:38 μμ
 *
 * @author OrfeasZ
 */
public abstract class Controller
{
    protected HttpExchange m_Exchange;

    public Controller(HttpExchange p_Exchange)
    {
        m_Exchange = p_Exchange;
    }

    public abstract Response Execute();
}
