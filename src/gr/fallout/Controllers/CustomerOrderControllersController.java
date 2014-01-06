package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Customer;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Models.RobotControllerOrder;
import gr.fallout.Models.SupplyOrderItem;
import gr.fallout.Net.Response;
import gr.fallout.Responses.ErrorResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.CustomerOrderControllersValidator;
import gr.fallout.Validators.SupplierConfirmSupplyOrderItemValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:58 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class CustomerOrderControllersController extends ProtectedController<Customer>
{
    public CustomerOrderControllersController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_cust_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("GET"))
            return new ErrorResponse("Invalid method.");

        CustomerOrderControllersValidator s_Validator = new CustomerOrderControllersValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new ErrorResponse(s_Errors.get(0));

        Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));

        CustomerOrder s_Order = RecordManager.GetInstance().CustomerOrders.Get(s_OrderID);

        Gson s_Gson = new Gson();

        if (s_Order == null)
            return new Response("[]", 200, "application/json");

        Collection<HashMap<String, String>> s_Controllers = new ArrayList<HashMap<String, String>>();

        for (RobotControllerOrder s_ControllerOrder : s_Order.ControllerOrders())
        {
            HashMap<String, String> s_Controller = new HashMap<String, String>();

            s_Controller.put("order", s_Gson.toJson(s_ControllerOrder));
            s_Controller.put("controller", s_Gson.toJson(s_ControllerOrder.Controller()));

            s_Controllers.add(s_Controller);
        }

        return new Response(s_Gson.toJson(s_Controllers), 200, "application/json");
    }
}
