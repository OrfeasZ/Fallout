package gr.fallout.Net.Contexts;

import gr.fallout.Controllers.*;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class AssemblyContext extends ContextBase
{
    public AssemblyContext()
    {
        super();

        m_ContextBase = "/assembly/";

        RegisterRoute("/assembly", AssemblyDashboardController.class);
        RegisterRoute("/assembly/login", AssemblyLoginController.class);
        RegisterRoute("/assembly/logout", AssemblyLogoutController.class);
        RegisterRoute("/assembly/order/:order_id/start", AssemblyStartControllerOrderAssemblyController.class);
        RegisterRoute("/assembly/order/:order_id/end", AssemblyEndControllerOrderAssemblyController.class);
    }
}
