package gr.fallout.Models;

import java.util.Date;
import java.util.List;

/**
 * Date: 18/11/2013
 * Time: 12:55 πμ
 *
 * @author NikosF
 */

public class CustomerOrder
{

    private int m_OrderID;

    private int m_PaidSum;

    private int m_Status;

    private Date m_SubmissionDate;

    private Customer m_Customer;

    private List<RobotControllerOrder> m_ControllerOrders;

    public int OrderID()
    {
        return m_OrderID;
    }

    public boolean OrderID(int p_OrderID)
    {
        m_OrderID = p_OrderID;
        return true;
    }

    public int PaidSum()
    {
        return m_PaidSum;
    }

    public boolean PaidSum(int p_PaidSum)
    {
        m_PaidSum = p_PaidSum;
        return true;
    }

    public int Status()
    {
        return m_Status;
    }

    public boolean Status(int p_Status)
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
        return m_Customer;
    }

    public boolean Customer(Customer p_Customer)
    {
        m_Customer = p_Customer;
        return true;
    }

    public List<RobotControllerOrder> ControllerOrders()
    {
        return m_ControllerOrders;
    }

    public boolean ControllerOrders(List<RobotControllerOrder> p_ControllerOrders)
    {
        m_ControllerOrders = p_ControllerOrders;
        return true;
    }
}

