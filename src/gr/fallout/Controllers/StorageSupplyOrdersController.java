package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.SupplyOrder;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:11 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class StorageSupplyOrdersController extends Controller
{
    private StorageManager m_Manager;

    private List<SupplyOrder> m_SupplyOrders;

    public StorageSupplyOrdersController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
