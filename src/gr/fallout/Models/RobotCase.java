package gr.fallout.Models;

public class RobotCase extends RobotControllerPart
{
    private int m_Size;

    private int m_WarrantyCost;

    public int Size()
    {
        return m_Size;
    }

    public boolean Size(int p_Size)
    {
        m_Size = p_Size;
        return true;
    }

    public int WarrantyCost()
    {
        return m_WarrantyCost;
    }

    public boolean WarrantyCost(int p_WarrantyCost)
    {
        m_WarrantyCost = p_WarrantyCost;
        return true;
    }
}