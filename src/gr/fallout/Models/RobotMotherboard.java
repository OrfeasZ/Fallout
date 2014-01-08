package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

/**
 * Date: 12/7/13
 * Time: 10:51 PM
 *
 * @author NikosF
 * @author OrfeasZ
 */

public class RobotMotherboard extends RobotControllerPart
{
    public enum MotherboardModel
    {
        StandardATX,
        MicroATX,
        MiniATX
    }

    private MotherboardModel m_Model;

    public MotherboardModel Model()
    {
        return m_Model;
    }

    public boolean Model(MotherboardModel p_Model)
    {
        m_Model = p_Model;

        switch (m_Model)
        {
            case StandardATX:
                m_PurchaseCost = 12.f;
                break;
            case MicroATX:
                m_PurchaseCost = 18.f;
                break;
            case MiniATX:
                m_PurchaseCost = 26.f;
                break;
        }

        RecordManager.GetInstance().RobotMotherboards.Update(this);

        return true;
    }
}
