package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Assembler;
import gr.fallout.Models.RobotControllerOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Validators.AssemblyStartAssemblyValidator;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:27 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class AssemblyStartControllerOrderAssemblyController extends ProtectedController<Assembler>
{

    public AssemblyStartControllerOrderAssemblyController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_assembly_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
            return new AjaxErrorResponse("Invalid method.");

        AssemblyStartAssemblyValidator s_Validator = new AssemblyStartAssemblyValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));
        Float s_WarrantyCost = Float.parseFloat(m_Params.get("warranty").get(0));
        Float s_HourlyRate = Float.parseFloat(m_Params.get("rate").get(0));

        Collection<RobotControllerOrder> s_Orders = m_User.AssignedOrders();

        RobotControllerOrder s_Order = null;

        for (RobotControllerOrder s_AssignedOrder : s_Orders)
            if (s_AssignedOrder.m_ID.equals(s_OrderID))
                s_Order = s_AssignedOrder;

        if (s_Order == null)
            return new AjaxErrorResponse("The specified order could not be found.");

        if (s_Order.AssemblyInitiationDate() != null)
            return new AjaxErrorResponse("The specified order is already being assembled.");

        s_Order.HourlyRate(s_HourlyRate);
        s_Order.Controller().Case().WarrantyCost(s_WarrantyCost);
        s_Order.AssemblyInitiationDate(new Date());

        return new AjaxRedirectResponse(m_ContextBase);
    }
}
