package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Assembler;
import gr.fallout.Models.RobotControllerOrder;
import gr.fallout.Net.Response;

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

    public AssemblyEndControllerOrderAssemblyController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
