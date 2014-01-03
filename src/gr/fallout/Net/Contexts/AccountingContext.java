package gr.fallout.Net.Contexts;

import gr.fallout.Controllers.AccountingDashboardController;
import gr.fallout.Controllers.AccountingLoginController;
import gr.fallout.Controllers.AccountingLogoutController;
import gr.fallout.Controllers.AccountingReportController;

/**
 * Date: 6/12/2013
 * Time: 10:40 πμ
 *
 * @author OrfeasZ
 */

public class AccountingContext extends ContextBase
{
    public AccountingContext()
    {
        super();

        RegisterRoute("/accounting", AccountingDashboardController.class);
        RegisterRoute("/accounting/login", AccountingLoginController.class);
        RegisterRoute("/accounting/logout", AccountingLogoutController.class);
        RegisterRoute("/accounting/report/:report_id", AccountingReportController.class);
    }
}
