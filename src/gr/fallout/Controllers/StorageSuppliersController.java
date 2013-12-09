package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.Supplier;
import gr.fallout.Net.Response;

import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:11 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class StorageSuppliersController extends Controller
{
    private StorageManager m_Manager;

    private List<Supplier> m_Suppliers;

    public StorageSuppliersController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
