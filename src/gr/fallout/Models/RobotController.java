package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

/**
 * Date: 18/11/2013
 * Time: 12:33 πμ
 *
 * @author NikosF
 */

public class RobotController extends Identifiable
{
    private Integer m_Case;

    private Integer m_CPU;

    private Integer m_Motherboard;

    private Integer m_RAM;

    public RobotCase Case()
    {
        return RecordManager.GetInstance().RobotCases.Get(m_Case);
    }

    public boolean Case(RobotCase p_Case)
    {
        m_Case = p_Case.m_ID;
        RecordManager.GetInstance().RobotControllers.Update(this);
        return true;
    }

    public RobotCPU CPU()
    {
        return RecordManager.GetInstance().RobotCPUs.Get(m_CPU);
    }

    public boolean CPU(RobotCPU p_CPU)
    {
        m_CPU = p_CPU.m_ID;
        RecordManager.GetInstance().RobotControllers.Update(this);
        return true;
    }

    public RobotMotherboard Motherboard()
    {
        return RecordManager.GetInstance().RobotMotherboards.Get(m_Motherboard);
    }

    public boolean Motherboard(RobotMotherboard p_Motherboard)
    {
        m_Motherboard = p_Motherboard.m_ID;
        RecordManager.GetInstance().RobotControllers.Update(this);
        return true;
    }

    public RobotRAM RAM()
    {
        return RecordManager.GetInstance().RobotRAMs.Get(m_RAM);
    }

    public boolean RAM(RobotRAM p_RAM)
    {
        m_RAM = p_RAM.m_ID;
        RecordManager.GetInstance().RobotControllers.Update(this);
        return true;
    }
}
