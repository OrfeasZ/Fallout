package gr.fallout.Models;

import java.util.List;

/**
 * Date: 17/11/2013
 * Time: 10:34 μμ
 *
 * @author NikosF
 */

public class Supplier
{
    private int m_Address;

    private boolean m_EUBussiness;

    private int m_TaxID;

    private String m_Name;

    private List<SupplyOrderItem> m_Orders;

    public int Adress()
    {
        return m_Address;
    }

    public boolean Address(int p_Address)
    {
        m_Address = p_Address;
        return true;

    }

    public boolean EUBussiness()
    {
        return m_EUBussiness;

    }

    public boolean EUBussiness(boolean p_EUBussiness)
    {
        m_EUBussiness = p_EUBussiness;
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

    public String Name()
    {
        return m_Name;
    }

    public boolean Name(String p_Name)
    {
        m_Name = p_Name;
        return true;
    }

    public List<SupplyOrderItem> Orders()
    {
        return m_Orders;
    }

    public boolean Orders(List<SupplyOrderItem> p_Orders)
    {
        m_Orders = p_Orders;
        return true;
    }
}
