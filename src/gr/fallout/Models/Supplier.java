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
    private String m_Address;

    private boolean m_EUBusiness;

    private int m_TaxID;

    private String m_Name;

    private List<SupplyOrderItem> m_Orders;

    public String Address()
    {
        return m_Address;
    }

    public boolean Address(String p_Address)
    {
        m_Address = p_Address;
        return true;
    }

    public boolean EUBusiness()
    {
        return m_EUBusiness;
    }

    public boolean EUBusiness(boolean p_EUBusiness)
    {
        m_EUBusiness = p_EUBusiness;
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
