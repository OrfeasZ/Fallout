package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Assembler;
import gr.fallout.Models.RobotControllerOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.ErrorResponse;
import gr.fallout.Responses.RedirectResponse;
import gr.fallout.Validators.AssemblyEndAssemblyValidator;
import gr.fallout.Validators.AssemblyStartAssemblyValidator;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:28 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class AssemblyEndControllerOrderAssemblyController extends ProtectedController<Assembler>
{
    private Assembler m_Assembler;

    private RobotControllerOrder m_Order;

    public AssemblyEndControllerOrderAssemblyController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
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

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
            return new ErrorResponse("Invalid method.");

        AssemblyEndAssemblyValidator s_Validator = new AssemblyEndAssemblyValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new ErrorResponse(s_Errors.get(0));

        Integer s_OrderID = Integer.parseInt(m_Params.get("order_id").get(0));

        Collection<RobotControllerOrder> s_Orders = m_Assembler.AssignedOrders();

        RobotControllerOrder s_Order = null;

        for (RobotControllerOrder s_AssignedOrder : s_Orders)
            if (s_AssignedOrder.m_ID.equals(s_OrderID))
                s_Order = s_AssignedOrder;

        if (s_Order == null)
            return new ErrorResponse("The specified order could not be found.");

        if (s_Order.AssemblyInitiationDate() == null)
            return new ErrorResponse("The specified order is not being assembled.");

        if (s_Order.AssemblyCompletionDate() != null)
            return new ErrorResponse("The specified order has already been assembled.");

        s_Order.AssemblyCompletionDate(new Date());

        return new RedirectResponse(m_ContextBase);
    }
}
