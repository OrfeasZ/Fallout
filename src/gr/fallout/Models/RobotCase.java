package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

/**
 * Date: 18/11/2013
 * Time: 1:24 πμ
 *
 * @author NikosF
 * @author OrfeasZ
 */

public class RobotCase extends RobotControllerPart
{
    public enum CaseSize
    {
        Mini,
        Mid,
        Full
    }

    private CaseSize m_Size;

    private float m_WarrantyCost;

    public CaseSize Size()
    {
        return m_Size;
    }

    public boolean Size(CaseSize p_Size)
    {
        m_Size = p_Size;

        switch (m_Size)
        {
            case Mini:
                m_PurchaseCost = 10.f;
                break;
            case Mid:
                m_PurchaseCost = 20.f;
                break;
            case Full:
                m_PurchaseCost = 30.f;
                break;
        }

        RecordManager.GetInstance().RobotCases.Update(this);

        return true;
    }

    public float WarrantyCost()
    {
        return m_WarrantyCost;
    }

    public boolean WarrantyCost(float p_WarrantyCost)
    {
        m_WarrantyCost = p_WarrantyCost;
        RecordManager.GetInstance().RobotCases.Update(this);
        return true;
    }
}