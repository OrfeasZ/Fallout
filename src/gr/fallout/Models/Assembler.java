package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Date: 18/11/2013
 * Time: 12:48 πμ
 *
 * @author NikosF
 */

public class Assembler extends Identifiable
{
    private String m_Password;

    private String m_Username;

    private String m_Name;

    private Collection<Integer> m_AssignedOrders;

    public Assembler()
    {
        m_AssignedOrders = new ArrayList<Integer>();
    }

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

    public String Name()
    {
        return m_Name;
    }

    public boolean Name(String p_Name)
    {
        m_Name = p_Name;
        return true;
    }

    public Collection<RobotControllerOrder> AssignedOrders()
    {
        return RecordManager.GetInstance().RobotControllerOrders.Get(m_AssignedOrders);
    }

    public boolean AddOrder(RobotControllerOrder p_Order)
    {
        p_Order.Assembler(this);

        if (m_AssignedOrders.contains(p_Order.m_ID))
            return false;

        m_AssignedOrders.add(p_Order.m_ID);
        RecordManager.GetInstance().Assemblers.Update(this);

        return true;
    }

    public boolean RemoveOrder(RobotControllerOrder p_Order)
    {
        p_Order.Assembler(null);

        if (!m_AssignedOrders.contains(p_Order.m_ID))
            return false;

        m_AssignedOrders.remove(p_Order.m_ID);
        RecordManager.GetInstance().Assemblers.Update(this);

        return true;
    }

}
