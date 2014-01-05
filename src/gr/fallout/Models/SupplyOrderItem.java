package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

public class SupplyOrderItem extends Identifiable
{
    private boolean m_Arrived;

    private boolean m_PaymentConfirmed;

    private int m_Quantity;

    private Integer m_Supplier;

    private Integer m_Part;

    private Supplier.PartType m_PartType;

    public boolean Arrived()
    {
        return m_Arrived;
    }

    public boolean Arrived(boolean p_Arrived)
    {
        m_Arrived = p_Arrived;
        return true;
    }

    public boolean PaymentConfirmed()
    {
        return m_PaymentConfirmed;
    }

    public boolean PaymentConfirmed(boolean p_PaymentConfirmed)
    {
        m_PaymentConfirmed = p_PaymentConfirmed;
        return true;
    }

    public int Quantity()
    {
        return m_Quantity;
    }

    public boolean Quantity(int p_Quantity)
    {
        m_Quantity = p_Quantity;
        return true;
    }

    public Supplier Supplier()
    {
        if (m_Supplier == null)
            return null;

        return RecordManager.GetInstance().Suppliers.Get(m_Supplier);
    }

    public boolean Supplier(Supplier p_Supplier)
    {
        if (p_Supplier == null)
            m_Supplier = null;
        else
            m_Supplier = p_Supplier.m_ID;

        RecordManager.GetInstance().SupplyOrderItems.Update(this);
        return true;
    }

    public RobotControllerPart RobotControllerPart()
    {
        if (m_Part == null)
            return null;

        switch (m_PartType)
        {
            case Case:
                return RecordManager.GetInstance().RobotCases.Get(m_Part);
            case CPU:
                return RecordManager.GetInstance().RobotCPUs.Get(m_Part);
            case Motherboard:
                return RecordManager.GetInstance().RobotMotherboards.Get(m_Part);
            case RAM:
                return RecordManager.GetInstance().RobotRAMs.Get(m_Part);
            default:
                return null;
        }
    }

    public boolean RobotControllerPart(RobotControllerPart p_Part)
    {
        if (p_Part == null)
            m_Supplier = null;
        else
            m_Supplier = p_Part.m_ID;

        RecordManager.GetInstance().SupplyOrderItems.Update(this);
        return true;
    }
}
