package gr.fallout.Models;

public class SupplyOrderItem
{
    private boolean m_Arrived;

    private boolean m_PaymentConfirmed;

    private int m_Quantity;

    private Supplier m_Supplier;

    private RobotControllerPart m_Part;

    public boolean Arrived()
    {
        return m_Arrived;
    }

    public boolean Arrived(boolean p_Arrived)
    {
        m_Arrived = p_Arrived;
        return true;
    }

    public boolean PaymentConfirmed()
    {
        return m_Arrived;
    }

    public boolean PaymentConfirmed(boolean p_PaymentConfirmed)
    {
        m_PaymentConfirmed = p_PaymentConfirmed;
        return true;
    }

    public int Quantity()
    {
        return m_Quantity;
    }

    public boolean Quantity(int p_Quantity)
    {
        m_Quantity = p_Quantity;
        return true;
    }

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
