package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Date: 17/11/2013
 * Time: 10:34 μμ
 *
 * @author NikosF
 */

public class Supplier extends Identifiable
{
    public enum PartType
    {
        Case,
        CPU,
        Motherboard,
        RAM
    }

    private String m_Address;

    private boolean m_EUBusiness;

    private int m_TaxID;

    private String m_Name;

    private Collection<Integer> m_Items;

    private PartType m_AvailableType;

    public Supplier()
    {
        m_Items = new ArrayList<Integer>();
    }

    public String Address()
    {
        return m_Address;
    }

    public boolean Address(String p_Address)
    {
        m_Address = p_Address;
        RecordManager.GetInstance().Suppliers.Update(this);
        return true;
    }

    public boolean EUBusiness()
    {
        return m_EUBusiness;
    }

    public boolean EUBusiness(boolean p_EUBusiness)
    {
        m_EUBusiness = p_EUBusiness;
        RecordManager.GetInstance().Suppliers.Update(this);
        return true;
    }

    public int TaxID()
    {
        return m_TaxID;
    }

    public boolean TaxID(int p_TaxID)
    {
        m_TaxID = p_TaxID;
        RecordManager.GetInstance().Suppliers.Update(this);
        return true;
    }

    public String Name()
    {
        return m_Name;
    }

    public boolean Name(String p_Name)
    {
        m_Name = p_Name;
        RecordManager.GetInstance().Suppliers.Update(this);
        return true;
    }

    public PartType AvailableType()
    {
        return m_AvailableType;
    }

    public boolean AvailableType(PartType p_AvailableType)
    {
        m_AvailableType = p_AvailableType;
        RecordManager.GetInstance().Suppliers.Update(this);
        return true;
    }

    public Collection<SupplyOrderItem> Items()
    {
        return RecordManager.GetInstance().SupplyOrderItems.Get(m_Items);
    }

    public boolean AddItem(SupplyOrderItem p_Item)
    {
        p_Item.Supplier(this);

        if (m_Items.contains(p_Item.m_ID))
            return false;

        m_Items.add(p_Item.m_ID);
        RecordManager.GetInstance().Suppliers.Update(this);

        return true;
    }

    public boolean RemoveItem(SupplyOrderItem p_Item)
    {
        p_Item.Supplier(null);

        if (!m_Items.contains(p_Item.m_ID))
            return false;

        m_Items.remove(p_Item.m_ID);
        RecordManager.GetInstance().Suppliers.Update(this);

        return true;
    }
}
