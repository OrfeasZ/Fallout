package gr.fallout.Net.Contexts;

import gr.fallout.Controllers.SupplierConfirmSupplyOrderItemController;
import gr.fallout.Controllers.SupplierDashboardController;
import gr.fallout.Controllers.SupplierLoginController;
import gr.fallout.Controllers.SupplierLogoutController;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class SupplyContext extends ContextBase
{
    public SupplyContext()
    {
        super();

        m_ContextBase = "/supplier/";

        RegisterRoute("/supplier", SupplierDashboardController.class);
        RegisterRoute("/supplier/login", SupplierLoginController.class);
        RegisterRoute("/supplier/logout", SupplierLogoutController.class);
        RegisterRoute("/supplier/item/:item_id/confirm", SupplierConfirmSupplyOrderItemController.class);
    }
}
