package gr.fallout.Net.Contexts;

import gr.fallout.Controllers.*;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class StorageContext extends ContextBase
{
    public StorageContext()
    {
        super();

        m_ContextBase = "/storage/";

        RegisterRoute("/storage", StorageDashboardController.class);
        RegisterRoute("/storage/login", StorageLoginController.class);
        RegisterRoute("/storage/logout", StorageLogoutController.class);
        RegisterRoute("/storage/stock", StorageStockController.class);
        RegisterRoute("/storage/suppliers", StorageSuppliersController.class);
        RegisterRoute("/storage/orders", StorageSupplyOrdersController.class);
        RegisterRoute("/storage/order/:order_id", StorageSupplyOrderController.class);
        RegisterRoute("/storage/order/:order_id/item/:item_id/verify", StorageVerifySupplyOrderItemArrivalController.class);
    }
}
