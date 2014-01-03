package gr.fallout.Managers;

import java.util.HashMap;
import java.util.UUID;

/**
 * Date: 1/3/14
 * Time: 7:06 PM
 *
 * @author OrfeasZ
 */

public class SessionManager
{
    private static SessionManager m_Instance = null;

    public static SessionManager GetInstance()
    {
        if (m_Instance == null)
            m_Instance = new SessionManager();
        return m_Instance;
    }

    //

    private HashMap<String, Object> m_SessionUsers;

    SessionManager()
    {
        m_SessionUsers = new HashMap<String, Object>();
    }

    public boolean HasSession(String p_SessionID)
    {
        return m_SessionUsers.containsKey(p_SessionID);
    }

    public Object GetSessionUser(String p_SessionID)
    {
        return m_SessionUsers.get(p_SessionID);
    }

    public String CreateUserSession(Object p_User)
    {
        String s_SessionID = UUID.randomUUID().toString();
        m_SessionUsers.put(s_SessionID, p_User);
        return s_SessionID;
    }

    public void RemoveSession(String p_SessionID)
    {
        m_SessionUsers.remove(p_SessionID);
    }
}
