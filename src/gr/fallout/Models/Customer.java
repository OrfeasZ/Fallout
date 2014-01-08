package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Date: 18/11/2013
 * Time: 1:03 πμ
 *
 * @author NikosF
 * @author OrfeasZ
 */

public class Customer extends Identifiable
{

    private String m_Address;

    private String m_Email;

    private int m_Fax;

    private String m_Name;

    private String m_Username;

    private String m_Password;

    private int m_Phone;

    private int m_TaxID;

    private Collection<Integer> m_Orders;

    public Customer()
    {
        m_Orders = new ArrayList<Integer>();
    }

    public String Address()
    {
        return m_Address;
    }

    public boolean Address(String p_Address)
    {
        m_Address = p_Address;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public String Email()
    {
        return m_Email;
    }

    public boolean Email(String p_Email)
    {
        m_Email = p_Email;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public int Fax()
    {
        return m_Fax;
    }

    public boolean Fax(int p_Fax)
    {
        m_Fax = p_Fax;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public String Name()
    {
        return m_Name;
    }

    public boolean Name(String p_Name)
    {
        m_Name = p_Name;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public String Username()
    {
        return m_Username;
    }

    public boolean Username(String p_Username)
    {
        m_Username = p_Username;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public String Password()
    {
        return m_Password;
    }

    public boolean Password(String p_Password)
    {
        m_Password = p_Password;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public int Phone()
    {
        return m_Phone;
    }

    public boolean Phone(int p_Phone)
    {
        m_Phone = p_Phone;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public int TaxID()
    {
        return m_TaxID;
    }

    public boolean TaxID(int p_TaxID)
    {
        m_TaxID = p_TaxID;
        RecordManager.GetInstance().Customers.Update(this);
        return true;
    }

    public Collection<CustomerOrder> Orders()
    {
        return RecordManager.GetInstance().CustomerOrders.Get(m_Orders);
    }

    public boolean AddOrder(CustomerOrder p_Order)
    {
        p_Order.Customer(this);

        if (m_Orders.contains(p_Order.m_ID))
            return false;

        m_Orders.add(p_Order.m_ID);
        RecordManager.GetInstance().Customers.Update(this);

        return true;
    }

    public boolean RemoveOrder(CustomerOrder p_Order)
    {
        p_Order.Customer(null);

        if (!m_Orders.contains(p_Order.m_ID))
            return false;

        m_Orders.remove(p_Order.m_ID);
        RecordManager.GetInstance().Customers.Update(this);

        return true;
    }

    public float Debt()
    {
        float s_Debt = 0.f;

        for (CustomerOrder s_Order : Orders())
            if (s_Order.PaidSum() < s_Order.Price())
                s_Debt += s_Order.Price() - s_Order.PaidSum();

        return s_Debt;
    }
}
