package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.CustomerOrderControllersValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:58 μμ
 *
 * @author OrfeasZ
 * @author NikosF
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
            return new AjaxErrorResponse("Invalid method.");

        CustomerOrderControllersValidator s_Validator = new CustomerOrderControllersValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));

        CustomerOrder s_Order = RecordManager.GetInstance().CustomerOrders.Get(s_OrderID);

        Gson s_Gson = new Gson();

        if (s_Order == null)
            return new Response("[]", 200, "application/json");

        Collection<RobotControllerParts> s_ControllerParts = new ArrayList<RobotControllerParts>();

        for (RobotControllerOrder s_ControllerOrder : s_Order.ControllerOrders())
        {
            RobotControllerParts s_Parts = new RobotControllerParts();

            s_Parts.Case = s_ControllerOrder.Controller().Case();
            s_Parts.CPU = s_ControllerOrder.Controller().CPU();
            s_Parts.RAM = s_ControllerOrder.Controller().RAM();
            s_Parts.Motherboard = s_ControllerOrder.Controller().Motherboard();

            s_ControllerParts.add(s_Parts);
        }

        return new Response(s_Gson.toJson(s_ControllerParts), 200, "application/json");
    }
}
