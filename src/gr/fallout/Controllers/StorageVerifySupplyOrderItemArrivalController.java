package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.SupplyOrderItem;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:15 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class StorageVerifySupplyOrderItemArrivalController extends Controller
{
    private StorageManager m_Manager;

    private SupplyOrderItem m_Item;

    public StorageVerifySupplyOrderItemArrivalController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
