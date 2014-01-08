package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:09 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesDashboardController extends ProtectedController<SalesManager>
{
    private Collection<Customer> m_Customers;

    private Collection<CustomerOrder> m_UnassignedOrders;
    private Collection<CustomerOrder> m_UndeliveredOrders;
    private Collection<CustomerOrder> m_PendingOrders;
    private Collection<CustomerOrder> m_DeliveredOrders;
    private HashMap<Integer, Integer> m_PaymentStatus;

    public SalesDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_sales_sid");

        m_Customers = Util.FilterPasswords(RecordManager.GetInstance().Customers.GetAll());

        m_UnassignedOrders = new ArrayList<CustomerOrder>();
        m_UndeliveredOrders = new ArrayList<CustomerOrder>();
        m_PendingOrders = new ArrayList<CustomerOrder>();
        m_DeliveredOrders = new ArrayList<CustomerOrder>();
        m_PaymentStatus = new HashMap<Integer, Integer>();

        Collection<CustomerOrder> s_Orders = RecordManager.GetInstance().CustomerOrders.GetAll();

        for (CustomerOrder s_Order : s_Orders)
        {
            int s_PaymentStatus = -1;

            if (s_Order.Price() == s_Order.PaidSum())
                s_PaymentStatus = 1;
            else if (s_Order.PaidSum() > 0)
                s_PaymentStatus = 0;

            // Update order status
            s_Order.Status();

            if (s_Order.Assembler() == null)
            {
                m_UnassignedOrders.add(s_Order);
            }
            else if (s_Order.Assembler() != null && !s_Order.IsAssembled())
            {
                m_PendingOrders.add(s_Order);
            }
            else if (s_Order.Assembler() != null && s_Order.IsAssembled() && !s_Order.Delivered())
            {
                m_UndeliveredOrders.add(s_Order);
                m_PaymentStatus.put(s_Order.m_ID, s_PaymentStatus);
            }
            else
            {
                m_DeliveredOrders.add(s_Order);
                m_PaymentStatus.put(s_Order.m_ID, s_PaymentStatus);
            }
        }
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        final Gson s_Gson = new Gson();

        return new AppViewResponse("SalesDashboard", new HashMap<String, String>() {{
            put("customers", s_Gson.toJson(m_Customers));
            put("unassigned_orders", s_Gson.toJson(m_UnassignedOrders));
            put("pending_orders", s_Gson.toJson(m_PendingOrders));
            put("undelivered_orders", s_Gson.toJson(m_UndeliveredOrders));
            put("delivered_orders", s_Gson.toJson(m_DeliveredOrders));
            put("payment_status", s_Gson.toJson(m_PaymentStatus));
        }}, "Fallout - Sales Dashboard");
    }
}
