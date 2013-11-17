import gr.fallout.RobotControllerOrder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NoFaTe
 * Date: 18/11/2013
 * Time: 12:48 πμ
 * To change this template use File | Settings | File Templates.
 */
public class Assembler {
    private String m_Password;

    private String m_Name;

    private String m_Username;

    private List<RobotControllerOrder> m_AssignedOrders;

    public String Password() {
        return m_Password;
    }

    public boolean Password(String p_Password) {
        m_Password = p_Password;
        return true;
    }

    public String Username() {
        return m_Username;
    }

    public boolean Username(String p_Username) {
        m_Password = p_Username;
        return true;
    }

    public String Name() {
        return m_Name;
    }

    public boolean Name(String p_Name) {
        m_Password = p_Name;
        return true;
    }

    public List<RobotControllerOrder> AssignedOrders()
    {
        return m_AssignedOrders;
    }

    public boolean Items(List<RobotControllerOrder> p_AssignedOrders)
    {
        m_AssignedOrders = p_AssignedOrders;
        return true;
    }

}
