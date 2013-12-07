package gr.fallout.Models;

/**
 * Created with IntelliJ IDEA.
 * User: nikosf
 * Date: 12/7/13
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class RobotRam extends RobotControllerPart{
    private int m_Capacity ;
    private int m_Type ;

    public int Capacity(){
        return m_Capacity;
    }

    public boolean Capacity(int p_Capacity){
        m_Capacity=p_Capacity;
        return true ;
    }

    public boolean Type(int p_Type){
        m_Type=p_Type;
        return true ;
    }
}