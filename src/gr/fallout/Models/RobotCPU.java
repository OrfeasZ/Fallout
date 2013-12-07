package gr.fallout.Models;

/**
 * Created with IntelliJ IDEA.
 * User: nikosf
 * Date: 12/7/13
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RobotCPU extends RobotControllerPart
{
    private int m_SocketType;

    public int SocketType(){
        return m_SocketType;
    }

    public boolean SocketType(int p_SocketType)
    {
       m_SocketType=p_SocketType;
       return true ;
    }

}
