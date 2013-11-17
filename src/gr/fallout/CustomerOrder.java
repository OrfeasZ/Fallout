package gr.fallout;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: NoFaTe
 * Date: 18/11/2013
 * Time: 12:55 πμ
 * To change this template use File | Settings | File Templates.
 */
public class CustomerOrder {

    private int m_OrderID;

    private int m_PaidSum;

    private int m_Status;

    private Date m_SubmissionDate;

    private Customer m_Customer;


    public int OrderID() {
        return m_OrderID;
    }

    public boolean OrderID(int p_OrderID) {
        m_OrderID = p_OrderID;
        return true;
    }

    public int PaidSum() {
        return m_PaidSum;
    }

    public boolean PaidSum(int p_PaidSum) {
        m_PaidSum = p_PaidSum;
        return true;}

    public int Status() {
        return m_PaidSum;
    }

    public boolean Status(int p_Status) {
        m_Status = p_Status;
        return true;
    }

    public Date SubmissionDate() {
        return m_SubmissionDate;
    }

    public boolean SubmissionDate(Date p_SubmissionDate) {
        m_SubmissionDate = p_SubmissionDate;
        return true;

    }

    public Customer Customer() {
        return m_Customer;
    }

    public boolean Customer (Customer p_Customer) {
        m_Customer = p_Customer;
        return true;
    }
}

