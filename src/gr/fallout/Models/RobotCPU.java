package gr.fallout.Models;

import gr.fallout.Store.RecordManager;

/**
 * Date: 12/7/13
 * Time: 10:39 PM
 *
 * @author NikosF
 * @author OrfeasZ
 */

public class RobotCPU extends RobotControllerPart
{
    public enum CPUSocket
    {
        Socket1156,
        Socket1155,
        Socket1150
    }

    private CPUSocket m_SocketType;

    public CPUSocket SocketType()
    {
        return m_SocketType;
    }

    public boolean SocketType(CPUSocket p_SocketType)
    {
        m_SocketType = p_SocketType;

        switch (m_SocketType)
        {
            case Socket1156:
                m_PurchaseCost = 20.f;
                break;
            case Socket1155:
                m_PurchaseCost = 50.f;
                break;
            case Socket1150:
                m_PurchaseCost = 120.f;
                break;
        }

        RecordManager.GetInstance().RobotCPUs.Update(this);

        return true;
    }

}
