package gr.fallout.Controllers;

import gr.fallout.Models.*;

import java.util.List;

/**
 * Date: 7/12/2013
 * Time: 11:42 μμ
 *
 * @author OrfeasZ, NikosF
 */
public class AccountingReportController
{
    private AccountingManager m_Manager;

    private Report m_Report;

    private List<SupplyOrder> m_SupplyOrders;

    private List<RobotControllerOrder> m_ControllerOrders;

    private List<CustomerOrder> m_CustomerOrders;
}
