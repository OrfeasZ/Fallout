package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Date: 18/11/2013
 * Time: 12:55 πμ
 *
 * @author NikosF
 */

public class CustomerOrder extends Identifiable
{
    public enum OrderStatus
    {
        Pending,
        Assembled,
        Delivered
    }

    private int m_OrderID;

    private float m_PaidSum;

    private OrderStatus m_Status;

    private Date m_SubmissionDate;

    private Integer m_Customer;

    private Collection<Integer> m_ControllerOrders;

    private Integer m_Assembler;

    private boolean m_Delivered;

    public CustomerOrder()
    {
        m_ControllerOrders = new ArrayList<Integer>();
    }

    public int OrderID()
    {
        return m_OrderID;
    }

    public boolean OrderID(int p_OrderID)
    {
        m_OrderID = p_OrderID;
        RecordManager.GetInstance().CustomerOrders.Update(this);
        return true;
    }

    public float PaidSum()
    {
        return m_PaidSum;
    }

    public boolean PaidSum(float p_PaidSum)
    {
        m_PaidSum = p_PaidSum;
        RecordManager.GetInstance().CustomerOrders.Update(this);
        return true;
    }

    public OrderStatus Status()
    {
        if (Delivered())
            Status(OrderStatus.Delivered);
        else if (IsAssembled())
            Status(OrderStatus.Assembled);
        else
            Status(OrderStatus.Pending);

        return m_Status;
    }

    public boolean Status(OrderStatus p_Status)
    {
        m_Status = p_Status;
        RecordManager.GetInstance().CustomerOrders.Update(this);
        return true;
    }

    public Date SubmissionDate()
    {
        return m_SubmissionDate;
    }

    public boolean SubmissionDate(Date p_SubmissionDate)
    {
        m_SubmissionDate = p_SubmissionDate;
        RecordManager.GetInstance().CustomerOrders.Update(this);
        return true;
    }

    public boolean Delivered()
    {
        return m_Delivered;
    }

    public boolean Delivered(boolean p_Delivered)
    {
        m_Status = OrderStatus.Delivered;
        m_Delivered = p_Delivered;
        RecordManager.GetInstance().CustomerOrders.Update(this);
        return true;
    }

    public Customer Customer()
    {
        if (m_Customer == null)
            return null;

        return RecordManager.GetInstance().Customers.Get(m_Customer);
    }

    public boolean Customer(Customer p_Customer)
    {
        if (p_Customer == null)
            m_Customer = null;
        else
            m_Customer = p_Customer.m_ID;

        RecordManager.GetInstance().CustomerOrders.Update(this);

        return true;
    }

    public Assembler Assembler()
    {
        if (m_Assembler == null)
            return null;

        return RecordManager.GetInstance().Assemblers.Get(m_Assembler);
    }

    public boolean Assembler(Assembler p_Assembler)
    {
        if (p_Assembler == null)
            m_Assembler = null;
        else
            m_Assembler = p_Assembler.m_ID;

        for (RobotControllerOrder s_Order : ControllerOrders())
            s_Order.Assembler(p_Assembler);

        RecordManager.GetInstance().CustomerOrders.Update(this);

        return true;
    }

    public Collection<RobotControllerOrder> ControllerOrders()
    {
        return RecordManager.GetInstance().RobotControllerOrders.Get(m_ControllerOrders);
    }

    public boolean AddControllerOrder(RobotControllerOrder p_Order)
    {
        if (m_ControllerOrders.contains(p_Order.m_ID))
            return false;

        m_ControllerOrders.add(p_Order.m_ID);
        RecordManager.GetInstance().CustomerOrders.Update(this);

        return true;
    }

    public boolean RemoveControllerOrder(RobotControllerOrder p_Order)
    {
        if (!m_ControllerOrders.contains(p_Order.m_ID))
            return false;

        m_ControllerOrders.remove(p_Order.m_ID);
        RecordManager.GetInstance().CustomerOrders.Update(this);

        return true;
    }

    public boolean IsAssembled()
    {
        for (RobotControllerOrder s_Order : ControllerOrders())
            if (s_Order.AssemblyCompletionDate() == null)
                return false;

        return true;
    }
}

