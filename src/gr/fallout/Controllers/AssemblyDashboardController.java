package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Assembler;
import gr.fallout.Models.RobotController;
import gr.fallout.Models.RobotControllerOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Store.RecordManager;

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
    private Assembler m_Assembler;

    private List<RobotControllerOrder> m_PendingOrders;

    private List<RobotControllerOrder> m_InProgressOrders;

    public AssemblyDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_assembly_sid");

        m_Assembler = m_User;
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        Collection<RobotControllerOrder> s_Orders = m_Assembler.AssignedOrders();

        // Get pending orders
        final Collection<RobotControllerOrder> s_PendingOrders = new ArrayList<RobotControllerOrder>();
        final Collection<RobotControllerOrder> s_InProgressOrders = new ArrayList<RobotControllerOrder>();

        for (RobotControllerOrder s_Order : s_Orders)
        {
            if (s_Order.AssemblyInitiationDate() != null && s_Order.AssemblyCompletionDate() == null)
                s_InProgressOrders.add(s_Order);
            else if (s_Order.AssemblyInitiationDate() == null && s_Order.AssemblyCompletionDate() == null)
                s_PendingOrders.add(s_Order);
        }

        final Gson s_Gson = new Gson();

        return new AppViewResponse("AssemblyDashboard", new HashMap<String, String>() {{
            put("pending_orders", s_Gson.toJson(s_PendingOrders));
            put("in_progress_orders", s_Gson.toJson(s_InProgressOrders));
        }}, "Fallout - Assembler Dashboard");
    }
}
