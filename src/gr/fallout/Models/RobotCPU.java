package gr.fallout.Models;

/**
 * Date: 12/7/13
 * Time: 10:39 PM
 *
 * @author NikosF
 */

public class RobotCPU extends RobotControllerPart
{
    public enum CPUSocket
    {
        Socket1156,
        Socket1155,
        Socket1150
    }

    private int m_SocketType;

    public int SocketType()
    {
        return m_SocketType;
    }

    public boolean SocketType(int p_SocketType)
    {
        m_SocketType = p_SocketType;
        return true;
    }

}
