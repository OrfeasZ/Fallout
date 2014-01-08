package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

/**
 * Date: 17/11/2013
 * Time: 11:58 μμ
 *
 * @author NikosF
 * @author OrfeasZ
 */

public class StorageManager extends Identifiable
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
        RecordManager.GetInstance().StorageManagers.Update(this);
        return true;
    }

    public String Username()
    {
        return m_Username;
    }

    public boolean Username(String p_Username)
    {
        m_Username = p_Username;
        RecordManager.GetInstance().StorageManagers.Update(this);
        return true;
    }
}
