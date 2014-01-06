package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.StorageManager;
import gr.fallout.Models.Supplier;
import gr.fallout.Models.SupplyOrder;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Store.RecordManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:10 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class StorageDashboardController extends ProtectedController<StorageManager>
{
    private Collection<Supplier> m_Suppliers;
    private Collection<SupplyOrder> m_SupplyOrders;

    private Integer m_CaseStock;
    private Integer m_CPUStock;
    private Integer m_MotherboardStock;
    private Integer m_RAMStock;

    public StorageDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_storage_sid");

        m_Suppliers = RecordManager.GetInstance().Suppliers.GetAll();
        m_SupplyOrders = RecordManager.GetInstance().SupplyOrders.GetAll();

        // TODO: Don't count parts that are in-use
        m_CaseStock = RecordManager.GetInstance().RobotCases.Count();
        m_CPUStock = RecordManager.GetInstance().RobotCPUs.Count();
        m_MotherboardStock = RecordManager.GetInstance().RobotMotherboards.Count();
        m_RAMStock = RecordManager.GetInstance().RobotRAMs.Count();
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        final Gson s_Gson = new Gson();

        return new AppViewResponse("StorageDashboard", new HashMap<String, String>() {{
            put("suppliers", s_Gson.toJson(m_Suppliers));
            put("supply_orders", s_Gson.toJson(m_SupplyOrders));
            put("case_stock", s_Gson.toJson(m_CaseStock));
            put("cpu_stock", s_Gson.toJson(m_CPUStock));
            put("motherboard_stock", s_Gson.toJson(m_MotherboardStock));
            put("ram_stock", s_Gson.toJson(m_RAMStock));
        }}, "Fallout - Storage Dashboard");
    }
}
