package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

/**
 * Date: 12/7/13
 * Time: 10:45 PM
 *
 * @author NikosF
 */

public class RobotRAM extends RobotControllerPart
{
    public enum RAMCapacity
    {
        TwoGB,
        FourGB
    }

    public enum RAMType
    {
        DDR2,
        DDR3
    }

    private RAMCapacity m_Capacity;
    private RAMType m_Type;

    public RobotRAM()
    {
        m_Capacity = RAMCapacity.TwoGB;
        m_Type = RAMType.DDR2;
    }

    public RAMCapacity Capacity()
    {
        return m_Capacity;
    }

    public boolean Capacity(RAMCapacity p_Capacity)
    {
        m_Capacity = p_Capacity;

        CalculatePrice();

        return true;
    }

    public RAMType Type()
    {
        return m_Type;
    }

    public boolean Type(RAMType p_Type)
    {
        m_Type = p_Type;

        CalculatePrice();

        return true;
    }

    private void CalculatePrice()
    {
        switch (m_Capacity)
        {
            case TwoGB:
                m_PurchaseCost = 8.f;
                break;
            case FourGB:
                m_PurchaseCost = 16.f;
                break;
        }

        if (m_Type == RAMType.DDR2)
            m_PurchaseCost *= 0.8f;

        RecordManager.GetInstance().RobotRAMs.Update(this);
    }
}