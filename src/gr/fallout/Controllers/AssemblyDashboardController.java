package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:24 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class AssemblyDashboardController extends ProtectedController<Assembler>
{
    public AssemblyDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_assembly_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        Collection<RobotControllerOrder> s_Orders = m_User.AssignedOrders();

        // Get pending orders
        final Collection<RobotControllerOrder> s_PendingOrders = new ArrayList<RobotControllerOrder>();
        final Collection<RobotControllerOrder> s_InProgressOrders = new ArrayList<RobotControllerOrder>();
        final HashMap<Integer, RobotControllerParts> s_Parts = new HashMap<Integer, RobotControllerParts>();

        for (RobotControllerOrder s_Order : s_Orders)
        {
            // We don't want Orders pending supply
            if (s_Order.SupplyOrder() != null)
                continue;

            if (s_Order.AssemblyInitiationDate() != null && s_Order.AssemblyCompletionDate() == null)
                s_InProgressOrders.add(s_Order);
            else if (s_Order.AssemblyInitiationDate() == null && s_Order.AssemblyCompletionDate() == null)
                s_PendingOrders.add(s_Order);

            RobotControllerParts s_ControllerParts = new RobotControllerParts();

            s_ControllerParts.Case = s_Order.Controller().Case();
            s_ControllerParts.CPU = s_Order.Controller().CPU();
            s_ControllerParts.RAM = s_Order.Controller().RAM();
            s_ControllerParts.Motherboard = s_Order.Controller().Motherboard();

            s_Parts.put(s_Order.m_ID, s_ControllerParts);
        }

        final Gson s_Gson = new Gson();

        return new AppViewResponse("AssemblyDashboard", new HashMap<String, String>() {{
            put("pending_orders", s_Gson.toJson(s_PendingOrders));
            put("in_progress_orders", s_Gson.toJson(s_InProgressOrders));
            put("order_parts", s_Gson.toJson(s_Parts));
        }}, "Fallout - Assembler Dashboard");
    }
}
