package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

import java.util.Date;

/**
 * Date: 18/11/2013
 * Time: 12:08 πμ
 *
 * @author NikosF
 */

public class RobotControllerOrder extends Identifiable
{
    private Date m_AssemblyCompletionDate;

    private Date m_AssemblyInitiationDate;

    private float m_HourlyRate;

    private Integer m_SupplyOrder;

    private Integer m_Controller;

    private Integer m_Assembler;

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

    public float HourlyRate()
    {
        return m_HourlyRate;
    }

    public boolean HourlyRate(float p_HourlyRate)
    {
        m_HourlyRate = p_HourlyRate;
        return true;
    }

    public SupplyOrder SupplyOrder()
    {
        if (m_SupplyOrder == null)
            return null;

        return RecordManager.GetInstance().SupplyOrders.Get(m_SupplyOrder);
    }

    public boolean SupplyOrder(SupplyOrder p_SuppplyOrder)
    {
        if (p_SuppplyOrder == null)
            m_SupplyOrder = null;
        else
            m_SupplyOrder = p_SuppplyOrder.m_ID;

        RecordManager.GetInstance().RobotControllerOrders.Update(this);

        return true;
    }

    public RobotController Controller()
    {
        if (m_Controller == null)
            return null;

        return RecordManager.GetInstance().RobotControllers.Get(m_Controller);
    }

    public boolean Controller(RobotController p_Controller)
    {
        if (p_Controller == null)
            m_Controller = null;
        else
            m_Controller = p_Controller.m_ID;

        RecordManager.GetInstance().RobotControllerOrders.Update(this);

        return true;
    }

    public Assembler Assembler()
    {
        if (m_Assembler == null)
            return null;

        return RecordManager.GetInstance().Assemblers.Get(m_Assembler);
    }

    public boolean Assembler(Assembler p_Assembler)
    {
        if (p_Assembler == null)
            m_Assembler = null;
        else
            m_Assembler = p_Assembler.m_ID;

        RecordManager.GetInstance().RobotControllerOrders.Update(this);

        return true;
    }
}
