package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:56 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class CustomerDashboardController extends ProtectedController<Customer>
{
    class OrderPayment
    {
        public float Price;
        public float Paid;
    }

    private Collection<CustomerOrder> m_CustomerOrders;
    private HashMap<Integer, OrderPayment> m_OrderPayments;

    public CustomerDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_cust_sid");

        if (!m_LoggedIn)
            return;

        m_OrderPayments = new HashMap<Integer, OrderPayment>();
        m_CustomerOrders = m_User.Orders();

        // Update order status
        for (CustomerOrder s_Order : m_CustomerOrders)
        {
            s_Order.Status();

            OrderPayment s_Payment = new OrderPayment();
            s_Payment.Price = s_Order.Price();
            s_Payment.Paid = s_Order.PaidSum();

            m_OrderPayments.put(s_Order.m_ID, s_Payment);
        }
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        return new AppViewResponse("CustomerDashboard", new HashMap<String, String>() {{
            put("orders", new Gson().toJson(m_CustomerOrders));
            put("payments", new Gson().toJson(m_OrderPayments));
        }}, "Fallout - Customer Dashboard");
    }
}
