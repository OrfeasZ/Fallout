package gr.fallout.Models;

/**
 * Date: 17/11/2013
 * Time: 11:58 μμ
 *
 * @author NikosF
 */

public class StorageManager
{
    private String m_Password;

    private String m_Username;

    public String Password()
    {
        return m_Password;
    }

    public boolean Password(String p_Password)
    {
        m_Password = p_Password;
        return true;
    }

    public String Username()
    {
        return m_Username;
    }

    public boolean Username(String p_Username)
    {
        m_Password = p_Username;
        return true;
    }
}