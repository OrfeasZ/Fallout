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
        m_Server.createContext("/", new CustomerContext()).getFilters().add(new ParameterFilter());
        m_Server.createContext("/supplier", new SupplyContext()).getFilters().add(new ParameterFilter());
        m_Server.createContext("/accounting", new AccountingContext()).getFilters().add(new ParameterFilter());
        m_Server.createContext("/sales", new SalesContext()).getFilters().add(new ParameterFilter());
        m_Server.createContext("/assembly", new AssemblyContext()).getFilters().add(new ParameterFilter());
        m_Server.createContext("/storage", new StorageContext()).getFilters().add(new ParameterFilter());
        m_Server.createContext("/admin", new AdminContext()).getFilters().add(new ParameterFilter());
        m_Server.createContext("/assets", new AssetContext()).getFilters().add(new ParameterFilter());
    }

    public void Start()
    {
        m_Server.setExecutor(null);
        m_Server.start();
    }
}
