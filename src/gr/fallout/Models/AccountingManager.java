package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

/**
 * Date: 18/11/2013
 * Time: 1:24 πμ
 *
 * @author NikosF
 * @author OrfeasZ
 */

public class AccountingManager extends Identifiable
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
        RecordManager.GetInstance().AccountingManagers.Update(this);
        return true;
    }

    public String Username()
    {
        return m_Username;
    }

    public boolean Username(String p_Username)
    {
        m_Username = p_Username;
        RecordManager.GetInstance().AccountingManagers.Update(this);
        return true;
    }
}
