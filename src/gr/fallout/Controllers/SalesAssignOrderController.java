package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Assembler;
import gr.fallout.Models.CustomerOrder;
import gr.fallout.Models.RobotControllerOrder;
import gr.fallout.Models.SalesManager;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.AssemblyEndAssemblyValidator;
import gr.fallout.Validators.SalesAssignOrderValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 4:13 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class SalesAssignOrderController extends ProtectedController<SalesManager>
{
    public SalesAssignOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_sales_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
        {
            SalesAssignOrderValidator s_Validator = new SalesAssignOrderValidator();
            List<String> s_Errors = s_Validator.Validate(m_Params);

            // Always return the first error
            if (s_Errors != null && !s_Errors.isEmpty())
                return new AjaxErrorResponse(s_Errors.get(0));

            Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));
            Integer s_AssemblerID = Integer.parseInt(m_Params.get("assembler_id").get(0));

            CustomerOrder s_Order = RecordManager.GetInstance().CustomerOrders.Get(s_OrderID);
            Assembler s_Assembler = RecordManager.GetInstance().Assemblers.Get(s_AssemblerID);

            if (s_Order == null)
                return new AjaxErrorResponse("The specified order could not be found.");

            if (s_Assembler == null)
                return new AjaxErrorResponse("The specified assembler could not be found.");

            if (s_Order.Assembler() != null)
                return new AjaxErrorResponse("The specified order is already being assembled.");

            s_Order.Assembler(s_Assembler);

            return new AjaxRedirectResponse(m_ContextBase);
        }

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("GET"))
            return new AjaxErrorResponse("Invalid method.");

        // Get all Assemblers and the count of their assigned orders
        Collection<Assembler> s_Assemblers = RecordManager.GetInstance().Assemblers.GetAll();
        HashMap<Integer, Integer> s_AssemblyCount = new HashMap<Integer, Integer>();

        for (Assembler s_Assembler : s_Assemblers)
        {
            int s_OrderCount = 0;

            for (RobotControllerOrder s_Order : s_Assembler.AssignedOrders())
                if (s_Order.AssemblyCompletionDate() == null)
                    ++s_OrderCount;

            s_AssemblyCount.put(s_Assembler.m_ID, s_OrderCount);
        }

        Gson s_Gson = new Gson();

        HashMap<String, String> s_ResponseContent = new HashMap<String, String>();
        s_ResponseContent.put("assemblers", s_Gson.toJson(s_Assemblers));
        s_ResponseContent.put("orders", s_Gson.toJson(s_AssemblyCount));

        return new Response(s_Gson.toJson(s_ResponseContent), 200, "application/json");
    }
}
