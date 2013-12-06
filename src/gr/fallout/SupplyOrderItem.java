package gr.fallout;

public class SupplyOrderItem
{
    private Supplier m_Supplier;

    private RobotControllerPart m_Part;

    public Supplier Supplier()
    {
        return m_Supplier;
    }

    public boolean Supplier(Supplier p_Supplier)
    {
        m_Supplier = p_Supplier;
        return true;
    }

    public RobotControllerPart RobotControllerPart()
    {
        return m_Part;
    }

    public boolean RobotControllerPart(RobotControllerPart p_Part)
    {
        m_Part = p_Part;
        return true;
    }
}
