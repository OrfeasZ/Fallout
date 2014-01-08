package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Validators.CustomerPayOrderGetValidator;
import gr.fallout.Validators.CustomerPayOrderValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:03 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class CustomerPayOrderController extends ProtectedController<Customer>
{
    public CustomerPayOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_cust_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
        {
            CustomerPayOrderValidator s_Validator = new CustomerPayOrderValidator();
            List<String> s_Errors = s_Validator.Validate(m_Params);

            // Always return the first error
            if (s_Errors != null && !s_Errors.isEmpty())
                return new AjaxErrorResponse(s_Errors.get(0));

            Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));
            Float s_Sum = Float.parseFloat(m_Params.get("pay_sum").get(0));

            Collection<CustomerOrder> s_Orders = m_User.Orders();

            CustomerOrder s_Order = null;

            for (CustomerOrder s_CustomerOrder : s_Orders)
                if (s_CustomerOrder.m_ID.equals(s_OrderID))
                    s_Order = s_CustomerOrder;

            if (s_Order == null)
                return new AjaxErrorResponse("The specified order could not be found.");

            if (s_Order.PaidSum() == s_Order.Price())
                return new AjaxErrorResponse("The specified order is already paid.");

            if (s_Order.PaidSum() + s_Sum > s_Order.Price())
                return new AjaxErrorResponse("The specified sum exceeds the price of the order.");

            s_Order.PaidSum(s_Order.PaidSum() + s_Sum);

            return new AjaxRedirectResponse(m_ContextBase);
        }

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("GET"))
            return new AjaxErrorResponse("Invalid method.");

        CustomerPayOrderGetValidator s_Validator = new CustomerPayOrderGetValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));

        Collection<CustomerOrder> s_Orders = m_User.Orders();

        CustomerOrder s_Order = null;

        for (CustomerOrder s_CustomerOrder : s_Orders)
            if (s_CustomerOrder.m_ID.equals(s_OrderID))
                s_Order = s_CustomerOrder;

        if (s_Order == null)
            return new AjaxErrorResponse("The specified order could not be found.");

        HashMap<String, Float> s_Response = new HashMap<String, Float>();

        s_Response.put("paid", s_Order.PaidSum());
        s_Response.put("price", s_Order.Price());

        return new Response(new Gson().toJson(s_Response), 200, "application/json");
    }
}
