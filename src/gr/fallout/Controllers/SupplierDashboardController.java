package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Supplier;
import gr.fallout.Models.SupplyOrderItem;
import gr.fallout.Net.Response;

import java.util.HashMap;

import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:20 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class SupplierDashboardController extends Controller
{
    private Supplier m_Supplier;

    private List<SupplyOrderItem> m_Items;

    public SupplierDashboardController(HttpExchange p_Exchange, HashMap<String, String> p_Params)
    {
        super(p_Exchange, p_Params);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
