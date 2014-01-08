package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.Supplier;
import gr.fallout.Models.SupplyOrderItem;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AppViewResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:20 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class SupplierDashboardController extends ProtectedController<Supplier>
{    public SupplierDashboardController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_supply_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        return new AppViewResponse("SupplierDashboard", new HashMap<String, String>() {{
            put("items", new Gson().toJson(m_User.Items()));
        }}, "Fallout - Supplier Dashboard");
    }
}
