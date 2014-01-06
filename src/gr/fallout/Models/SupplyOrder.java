package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SupplyOrder extends Identifiable
{
    private Date m_SubmissionDate;

    private float m_Tax;

    private Collection<Integer> m_Items;

    private Integer m_ControllerOrder;

    public SupplyOrder()
    {
        m_Items = new ArrayList<Integer>();
    }

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

    public RobotControllerOrder ControllerOrder()
    {
        if (m_ControllerOrder == null)
            return null;

        return RecordManager.GetInstance().RobotControllerOrders.Get(m_ControllerOrder);
    }

    public boolean ControllerOrder(RobotControllerOrder p_ControllerOrder)
    {
        if (p_ControllerOrder == null)
            m_ControllerOrder = null;
        else
            m_ControllerOrder = p_ControllerOrder.m_ID;

        RecordManager.GetInstance().SupplyOrders.Update(this);
        return true;
    }

    public void UpdateControllerOrder()
    {
        for (SupplyOrderItem s_Item : Items())
            if (!s_Item.Arrived())
                return;

        // ControllerOrder is now ready for assembly
        ControllerOrder().SupplyOrder(null);
    }

}
