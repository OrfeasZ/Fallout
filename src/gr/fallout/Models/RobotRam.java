package gr.fallout.Models;

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

    public RAMCapacity Capacity()
    {
        return m_Capacity;
    }

    public boolean Capacity(RAMCapacity p_Capacity)
    {
        m_Capacity = p_Capacity;
        return true;
    }

    public RAMType Type()
    {
        return m_Type;
    }

    public boolean Type(RAMType p_Type)
    {
        m_Type = p_Type;
        return true;
    }
}