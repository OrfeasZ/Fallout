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
        // TODO
    }

    private int m_OrderID;

    private float m_PaidSum;

    private OrderStatus m_Status;

    private Date m_SubmissionDate;

    private Integer m_Customer;

    private Collection<Integer> m_ControllerOrders;

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
        return true;
    }

    public float PaidSum()
    {
        return m_PaidSum;
    }

    public boolean PaidSum(float p_PaidSum)
    {
        m_PaidSum = p_PaidSum;
        return true;
    }

    public OrderStatus Status()
    {
        return m_Status;
    }

    public boolean Status(OrderStatus p_Status)
    {
        m_Status = p_Status;
        return true;
    }

    public Date SubmissionDate()
    {
        return m_SubmissionDate;
    }

    public boolean SubmissionDate(Date p_SubmissionDate)
    {
        m_SubmissionDate = p_SubmissionDate;
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
}

