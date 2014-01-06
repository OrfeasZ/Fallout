package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

public class SupplyOrderItem extends Identifiable
{
    private boolean m_Arrived;

    private boolean m_PaymentConfirmed;

    private int m_Quantity;

    private Integer m_Supplier;

    private Supplier.PartType m_PartType;

    private Integer m_PartTemplate;

    public boolean Arrived()
    {
        return m_Arrived;
    }

    public boolean Arrived(boolean p_Arrived)
    {
        m_Arrived = p_Arrived;
        RecordManager.GetInstance().SupplyOrderItems.Update(this);
        return true;
    }

    public boolean PaymentConfirmed()
    {
        return m_PaymentConfirmed;
    }

    public boolean PaymentConfirmed(boolean p_PaymentConfirmed)
    {
        m_PaymentConfirmed = p_PaymentConfirmed;
        RecordManager.GetInstance().SupplyOrderItems.Update(this);
        return true;
    }

    public int Quantity()
    {
        return m_Quantity;
    }

    public boolean Quantity(int p_Quantity)
    {
        m_Quantity = p_Quantity;
        RecordManager.GetInstance().SupplyOrderItems.Update(this);
        return true;
    }

    public Supplier.PartType PartType()
    {
        return m_PartType;
    }

    public boolean PartType(Supplier.PartType p_PartType)
    {
        m_PartType = p_PartType;
        RecordManager.GetInstance().SupplyOrderItems.Update(this);
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

    public RobotControllerPart PartTemplate()
    {
        if (m_PartTemplate == null)
            return null;

        switch (m_PartType)
        {
            case Case:
                return RecordManager.GetInstance().CaseTemplates.Get(m_PartTemplate);
            case CPU:
                return RecordManager.GetInstance().CPUTemplates.Get(m_PartTemplate);
            case Motherboard:
                return RecordManager.GetInstance().MotherboardTemplates.Get(m_PartTemplate);
            case RAM:
                return RecordManager.GetInstance().RAMTemplates.Get(m_PartTemplate);
            default:
                return null;
        }
    }

    public boolean PartTemplate(RobotControllerPart p_Part)
    {
        if (p_Part == null)
            m_PartTemplate = null;
        else
            m_PartTemplate = p_Part.m_ID;

        RecordManager.GetInstance().SupplyOrderItems.Update(this);
        return true;
    }
}
