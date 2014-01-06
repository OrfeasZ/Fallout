package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

public class Administrator extends Identifiable
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
        RecordManager.GetInstance().Administrators.Update(this);
        return true;
    }

    public String Username()
    {
        return m_Username;
    }

    public boolean Username(String p_Username)
    {
        m_Username = p_Username;
        RecordManager.GetInstance().Administrators.Update(this);
        return true;
    }
}
