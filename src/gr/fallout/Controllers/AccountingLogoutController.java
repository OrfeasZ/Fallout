package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.AccountingManager;
import gr.fallout.Net.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:55 μμ
 *
 * @author OrfeasZ, NikosF
 */

public class AccountingLogoutController extends ProtectedController<AccountingManager>
{
    public AccountingLogoutController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params)
    {
        super(p_Exchange, p_Params, "/accounting/login", "fo_acct_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        return null;
    }
}
