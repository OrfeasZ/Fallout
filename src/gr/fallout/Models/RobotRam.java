package gr.fallout.Models;

/**
 * Date: 12/7/13
 * Time: 10:45 PM
 *
 * @author NikosF
 */

public class RobotRAM extends RobotControllerPart
{
    private int m_Capacity;
    private int m_Type;

    public int Capacity()
    {
        return m_Capacity;
    }

    public boolean Capacity(int p_Capacity)
    {
        m_Capacity = p_Capacity;
        return true;
    }

    public int Type()
    {
        return m_Type;
    }

    public boolean Type(int p_Type)
    {
        m_Type = p_Type;
        return true;
    }
}