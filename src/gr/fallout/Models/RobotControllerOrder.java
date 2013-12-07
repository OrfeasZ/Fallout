package gr.fallout.Models;

import java.util.Date;

/**
 * Date: 18/11/2013
 * Time: 12:08 πμ
 *
 * @author NikosF
 */

public class RobotControllerOrder
{
    private Date m_AssemblyCompletionDate;

    private Date m_AssemblyInitiationDate;

    private int m_HourlyRate;

    private SupplyOrder m_SupplyOrder;

    private RobotController m_Controller;

    private Assembler m_Assembler;

    public Date AssemblyCompletionDate()
    {
        return m_AssemblyCompletionDate;
    }

    public boolean AssemblyCompletionDate(Date p_AssemblyCompletionDate)
    {
        m_AssemblyCompletionDate = p_AssemblyCompletionDate;
        return true;
    }

    public Date AssemblyInitiationDate()
    {
        return m_AssemblyInitiationDate;
    }

    public boolean AssemblyInitiationDate(Date p_AssemblyInitiationDate)
    {
        m_AssemblyInitiationDate = p_AssemblyInitiationDate;
        return true;
    }

    public int HourlyRate()
    {
        return m_HourlyRate;
    }

    public boolean HourlyRate(int p_HourlyRate)
    {
        m_HourlyRate = p_HourlyRate;
        return true;
    }

    public SupplyOrder SupplyOrder()
    {
        return m_SupplyOrder;
    }

    public boolean SupplyOrder(SupplyOrder p_SuppplyOrder)
    {
        m_SupplyOrder = p_SuppplyOrder;
        return true;
    }

    public RobotController Controller()
    {
        return m_Controller;
    }

    public boolean Controller(RobotController p_Controller)
    {
        m_Controller = p_Controller;
        return true;
    }

    public Assembler Assembler()
    {
        return m_Assembler;
    }

    public boolean Assembler(Assembler p_Assembler)
    {
        m_Assembler = p_Assembler;
        return true;
    }
}
