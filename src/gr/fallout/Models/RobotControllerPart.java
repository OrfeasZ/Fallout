package gr.fallout.Models;

/**
 * Date: 17/11/2013
 * Time: 11:35 μμ
 *
 * @author NikosF
 */

public abstract class RobotControllerPart extends Identifiable
{
    protected float m_AssemblyCost;

    protected float m_PurchaseCost;

    protected boolean m_Used;

    public float AssemblyCost()
    {
        return m_AssemblyCost;
    }

    public boolean AssemblyCost(float p_AssemblyCost)
    {
        m_AssemblyCost = p_AssemblyCost;
        return true;

    }

    public float PurchaseCost()
    {
        return m_PurchaseCost;
    }

    public boolean PurchaseCost(float p_PurchaseCost)
    {
        m_PurchaseCost = p_PurchaseCost;
        return true;
    }

    public boolean Used()
    {
        return m_Used;
    }

    public boolean Used(boolean p_Used)
    {
        m_Used = p_Used;
        return true;
    }
}