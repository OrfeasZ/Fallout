package gr.fallout.Net;

import com.sun.net.httpserver.HttpServer;
import gr.fallout.Net.Contexts.*;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Date: 6/12/2013
 * Time: 10:37 πμ
 *
 * @author OrfeasZ
 */

public class WebServer
{
    private HttpServer m_Server;

    public WebServer(int p_Port) throws IOException
    {
        m_Server = HttpServer.create(new InetSocketAddress(p_Port), 0);
        RegisterContexts();
    }

    private void RegisterContexts()
    {
        m_Server.createContext("/", new CustomerContext());
        m_Server.createContext("/supply", new SupplyContext());
        m_Server.createContext("/accounting", new AccountingContext());
        m_Server.createContext("/sales", new SalesContext());
        m_Server.createContext("/assembly", new AssemblyContext());
        m_Server.createContext("/storage", new StorageContext());
        m_Server.createContext("/admin", new AdminContext());
        m_Server.createContext("/assets", new AssetContext());
    }

    public void Start()
    {
        m_Server.setExecutor(null);
        m_Server.start();
    }
}
