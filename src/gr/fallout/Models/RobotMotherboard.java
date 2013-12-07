package gr.fallout.Models;

/**
 * Created with IntelliJ IDEA.
 * User: nikosf
 * Date: 12/7/13
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class RobotMotherboard {

    private int m_Model ;

    public int Model(){
        return m_Model;
    }
    public boolean Model(int p_Model){
        m_Model=p_Model;
        return true;
    }
}
