package gr.fallout.Models;

import java.util.Date;

/**
 * Date: 18/11/2013
 * Time: 1:25 πμ
 *
 * @author NikosF
 */

public class Report extends Identifiable
{
    private Date m_Date;

    public Date Date()
    {
        return m_Date;
    }

    public boolean Date(Date p_Date)
    {
        m_Date = p_Date;
        return true;
    }
}
