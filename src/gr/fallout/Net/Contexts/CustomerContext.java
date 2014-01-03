package gr.fallout.Net.Contexts;

import gr.fallout.Controllers.*;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class CustomerContext extends ContextBase
{
    public CustomerContext()
    {
        super();

        RegisterRoute("/", CustomerDashboardController.class);
        RegisterRoute("/login", CustomerLoginController.class);
        RegisterRoute("/logout", CustomerLogoutController.class);
        RegisterRoute("/order", CustomerPlaceOrderController.class);
        RegisterRoute("/order/:order_id", CustomerOrderControllersController.class);
        RegisterRoute("/order/:order_id/pay", CustomerPayOrderController.class);
    }
}
