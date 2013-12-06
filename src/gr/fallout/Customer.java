package gr.fallout;

import java.util.List;

/**
 * Date: 18/11/2013
 * Time: 1:03 πμ
 *
 * @author NikosF
 */

public class Customer
{

    private String m_Address;

    private String m_Email;

    private String m_Fax;

    private String m_Name;

    private String m_Password;

    private int m_Phone;

    private int m_TaxID;

    private List<CustomerOrder> m_Orders;

    public String Address()
    {
        return m_Address;
    }

    public boolean Address(String p_Address)
    {
        m_Address = p_Address;
        return true;
    }

    public String Email()
    {
        return m_Email;
    }

    public boolean Email(String p_Email)
    {
        m_Email = p_Email;
        return true;
    }

    public String Fax()
    {
        return m_Fax;
    }

    public boolean Fax(String p_Fax)
    {
        m_Fax = p_Fax;
        return true;
    }

    public String Name()
    {
        return m_Fax;
    }

    public boolean Name(String p_Name)
    {
        m_Name = p_Name;
        return true;
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

    public int Phone()
    {
        return m_Phone;
    }

    public boolean Phone(int p_Phone)
    {
        m_Phone = p_Phone;
        return true;
    }

    public int TaxID()
    {
        return m_TaxID;
    }

    public boolean TaxID(int p_TaxID)
    {
        m_TaxID = p_TaxID;
        return true;
    }

    public List<CustomerOrder> Orders()
    {
        return m_Orders;
    }

    public boolean Orders(List<CustomerOrder> p_Orders)
    {
        m_Orders = p_Orders;
        return true;
    }
}
