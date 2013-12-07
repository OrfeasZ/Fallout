package gr.fallout.Models;

/**
 * Date: 17/11/2013
 * Time: 11:35 μμ
 *
 * @author NikosF
 */

public abstract class RobotControllerPart
{
    protected int m_AssemblyCost;

    protected int m_PurchaseCost;

    public int AssemblyCost()
    {
        return m_AssemblyCost;
    }

    public boolean AssemblyCost(int p_AssemblyCost)
    {
        m_AssemblyCost = p_AssemblyCost;
        return true;

    }

    public int PurchaseCost()
    {
        return m_PurchaseCost;
    }

    public boolean PurchaseCost(int p_PurchaseCost)
    {
        m_PurchaseCost = p_PurchaseCost;
        return true;

    }
}