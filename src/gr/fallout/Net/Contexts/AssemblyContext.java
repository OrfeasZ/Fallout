package gr.fallout.Net.Contexts;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class AssemblyContext implements HttpHandler
{
    public void handle(HttpExchange t) throws IOException
    {
        String response = "AssemblyContext";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
