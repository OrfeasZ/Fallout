package gr.fallout.Models;

/**
 * Date: 12/7/13
 * Time: 10:51 PM
 *
 * @author NikosF
 */

public class RobotMotherboard
{
    public enum MotherboardModel
    {
        Default
    }

    private MotherboardModel m_Model;

    public MotherboardModel Model()
    {
        return m_Model;
    }

    public boolean Model(MotherboardModel p_Model)
    {
        m_Model = p_Model;
        return true;
    }
}
