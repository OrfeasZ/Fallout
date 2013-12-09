package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Models.Assembler;
import gr.fallout.Net.Response;

/**
 * Date: 9/12/2013
 * Time: 3:59 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AdminDeleteAssemblerController extends Controller
{
    private Administrator m_Administrator;

    private Assembler m_Assembler;

    public AdminDeleteAssemblerController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
