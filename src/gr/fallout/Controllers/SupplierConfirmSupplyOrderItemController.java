package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Supplier;
import gr.fallout.Models.SupplyOrderItem;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:22 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class SupplierConfirmSupplyOrderItemController extends Controller
{
    private Supplier m_Supplier;

    private SupplyOrderItem m_Item;

    public SupplierConfirmSupplyOrderItemController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
