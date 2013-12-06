package gr.fallout.Models;

/**
 * Date: 18/11/2013
 * Time: 1:24 πμ
 *
 * @author NikosF
 */

public class AccountingManager
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
        m_Username = p_Username;
        return true;
    }
}
