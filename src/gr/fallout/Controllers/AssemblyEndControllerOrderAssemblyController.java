package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Assembler;
import gr.fallout.Models.RobotControllerOrder;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:28 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class AssemblyEndControllerOrderAssemblyController extends Controller
{
    private Assembler m_Assembler;

    private RobotControllerOrder m_Order;

    public AssemblyEndControllerOrderAssemblyController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
