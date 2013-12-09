package gr.fallout.Models;

/**
 * Date: 18/11/2013
 * Time: 12:33 πμ
 *
 * @author NikosF
 */

public class RobotController
{
    private RobotCase m_Case;

    private RobotCPU m_CPU;

    private RobotMotherboard m_Motherboard;

    private RobotRAM m_RAM;

    public RobotCase Case()
    {
        return m_Case;
    }

    public boolean Case(RobotCase p_Case)
    {
        m_Case = p_Case;
        return true;
    }

    public RobotCPU CPU()
    {
        return m_CPU;
    }

    public boolean CPU(RobotCPU p_CPU)
    {
        m_CPU = p_CPU;
        return true;
    }

    public RobotMotherboard Motherboard()
    {
        return m_Motherboard;
    }

    public boolean Motherboard(RobotMotherboard p_Motherboard)
    {
        m_Motherboard = p_Motherboard;
        return true;
    }

    public RobotRAM RAM()
    {
        return m_RAM;
    }

    public boolean RAM(RobotRAM p_RAM)
    {
        m_RAM = p_RAM;
        return true;
    }
}
