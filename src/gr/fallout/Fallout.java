package gr.fallout;

import gr.fallout.Net.WebServer;

import java.io.IOException;

/**
 * Date: 6/12/2013
 * Time: 10:41 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class Fallout
{
    // region Singleton

    private static Fallout m_Instance = null;

    public static Fallout GetInstance()
    {
        if (m_Instance == null)
            m_Instance = new Fallout();
        return m_Instance;
    }

    // endregion

    private WebServer m_WebServer;

    public Fallout()
    {
    }

    public void Init(int p_WebPort) throws IOException
    {
        m_WebServer = new WebServer(p_WebPort);
        m_WebServer.Start();
    }

}
