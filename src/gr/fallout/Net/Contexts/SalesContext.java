package gr.fallout.Net.Contexts;

import gr.fallout.Controllers.*;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class SalesContext extends ContextBase
{
    public SalesContext()
    {
        super();

        m_ContextBase = "/sales/";

        RegisterRoute("/sales", SalesDashboardController.class);
        RegisterRoute("/sales/login", SalesLoginController.class);
        RegisterRoute("/sales/logout", SalesLogoutController.class);
        RegisterRoute("/sales/order/:order_id/assign", SalesAssignOrderController.class);
        RegisterRoute("/sales/order/:order_id/deliver", SalesDeliverOrderController.class);
        RegisterRoute("/sales/customer/create", SalesCreateCustomerController.class);
        RegisterRoute("/sales/customer/:user_id/delete", SalesDeleteCustomerController.class);
    }
}
