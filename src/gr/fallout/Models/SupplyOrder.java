package gr.fallout.Models;

import java.util.Date;
import java.util.List;

public class SupplyOrder
{
    private int m_ID;

    private Date m_SubmissionDate;

    private int m_Tax;

    private List<SupplyOrderItem> m_Items;

    public int ID()
    {
        return m_ID;
    }

    public boolean ID(int p_ID)
    {
        m_ID = p_ID;
        return true;
    }

    public Date SubmissionDate()
    {
        return m_SubmissionDate;
    }

    public boolean SubmissionDate(Date p_SubmissionDate)
    {
        m_SubmissionDate = p_SubmissionDate;
        return true;
    }

    public int Tax()
    {
        return m_Tax;
    }

    public boolean Tax(int p_Tax)
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
