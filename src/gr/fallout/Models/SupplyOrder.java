package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SupplyOrder extends Identifiable
{
    private Date m_SubmissionDate;

    private float m_Tax;

    private Collection<Integer> m_Items;

    public Date SubmissionDate()
    {
        return m_SubmissionDate;
    }

    public boolean SubmissionDate(Date p_SubmissionDate)
    {
        m_SubmissionDate = p_SubmissionDate;
        RecordManager.GetInstance().SupplyOrders.Update(this);
        return true;
    }

    public double Tax()
    {
        return m_Tax;
    }

    public boolean Tax(float p_Tax)
    {
        m_Tax = p_Tax;
        RecordManager.GetInstance().SupplyOrders.Update(this);
        return true;
    }

    public Collection<SupplyOrderItem> Items()
    {
        return RecordManager.GetInstance().SupplyOrderItems.Get(m_Items);
    }

    public boolean AddItem(SupplyOrderItem p_Item)
    {
        if (m_Items.contains(p_Item.m_ID))
            return false;

        m_Items.add(p_Item.m_ID);
        RecordManager.GetInstance().SupplyOrders.Update(this);

        return true;
    }

    public boolean RemoveItem(SupplyOrderItem p_Item)
    {
        if (!m_Items.contains(p_Item.m_ID))
            return false;

        m_Items.remove(p_Item.m_ID);
        RecordManager.GetInstance().SupplyOrders.Update(this);

        return true;
    }
}
