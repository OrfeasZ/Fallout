package gr.fallout;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: NoFaTe
 * Date: 18/11/2013
 * Time: 1:25 πμ
 * To change this template use File | Settings | File Templates.
 */
public class Report {
    private Date m_Date;

    public Date Date(){

       return m_Date;
    }

    public boolean Date(Date p_Date){
        m_Date=p_Date;
        return true;
    }
}
