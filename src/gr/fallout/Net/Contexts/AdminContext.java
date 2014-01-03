package gr.fallout.Net.Contexts;

import gr.fallout.Controllers.*;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class AdminContext extends ContextBase
{
    public AdminContext()
    {
        super();

        RegisterRoute("/admin", AdminDashboardController.class);
        RegisterRoute("/admin/login", AdminLoginController.class);
        RegisterRoute("/admin/logout", AdminLogoutController.class);
        RegisterRoute("/admin/accounting/create", AdminCreateAccountingManagerController.class);
        RegisterRoute("/admin/assembler/create", AdminCreateAssemblerController.class);
        RegisterRoute("/admin/sales/create", AdminCreateSalesManagerController.class);
        RegisterRoute("/admin/storage/create", AdminCreateStorageManagerController.class);
        RegisterRoute("/admin/accounting/:user_id/delete", AdminDeleteAccountingManagerController.class);
        RegisterRoute("/admin/assembler/:user_id/delete", AdminDeleteAssemblerController.class);
        RegisterRoute("/admin/sales/:user_id/delete", AdminDeleteSalesManagerController.class);
        RegisterRoute("/admin/storage/:user_id/delete", AdminDeleteStorageManagerController.class);
    }
}
