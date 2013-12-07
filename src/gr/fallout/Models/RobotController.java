package gr.fallout.Models;

/**
 * Date: 18/11/2013
 * Time: 12:33 πμ
 *
 * @author NikosF
 */

public class RobotController
{
    private RobotControllerPart m_Case;

    private RobotControllerPart m_CPU;

    private RobotControllerPart m_Motherboard;

    private RobotControllerPart m_RAM;

    public RobotControllerPart Case()
    {
        return m_Case;
    }

    public boolean Case(RobotControllerPart p_Case)
    {
        m_Case = p_Case;
        return true;
    }

    public RobotControllerPart CPU()
    {
        return m_CPU;
    }

    public boolean CPU(RobotControllerPart p_CPU)
    {
        m_CPU = p_CPU;
        return true;
    }

    public RobotControllerPart Motherboard()
    {
        return m_Motherboard;
    }

    public boolean Motherboard(RobotControllerPart p_Motherboard)
    {
        m_Motherboard = p_Motherboard;
        return true;
    }

    public RobotControllerPart RAM()
    {
        return m_RAM;
    }

    public boolean RAM(RobotControllerPart p_RAM)
    {
        m_RAM = p_RAM;
        return true;
    }
}
