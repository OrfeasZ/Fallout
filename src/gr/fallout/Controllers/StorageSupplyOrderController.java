package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.SupplyOrder;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:15 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class StorageSupplyOrderController extends Controller
{
    private StorageManager m_Manager;

    private SupplyOrder m_SupplyOrder;

    public StorageSupplyOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
