package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Administrator;
import gr.fallout.Models.Assembler;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

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

    public AdminDeleteAssemblerController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
