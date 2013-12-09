package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.SupplyOrder;
import gr.fallout.Net.Response;

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

    public StorageSupplyOrderController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
