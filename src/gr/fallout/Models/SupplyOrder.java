package gr.fallout.Models;

import java.util.Date;
import java.util.List;

public class SupplyOrder extends Identifiable
{
    private Date m_SubmissionDate;

    private float m_Tax;

    private List<SupplyOrderItem> m_Items;

    public Date SubmissionDate()
    {
        return m_SubmissionDate;
    }

    public boolean SubmissionDate(Date p_SubmissionDate)
    {
        m_SubmissionDate = p_SubmissionDate;
        return true;
    }

    public double Tax()
    {
        return m_Tax;
    }

    public boolean Tax(float p_Tax)
    {
        m_Tax = p_Tax;
        return true;
    }

    public List<SupplyOrderItem> Items()
    {
        return m_Items;
    }

    public boolean Items(List<SupplyOrderItem> p_Items)
    {
        m_Items = p_Items;
        return true;
    }
}
