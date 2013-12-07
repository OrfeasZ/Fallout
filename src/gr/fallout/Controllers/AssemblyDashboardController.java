package gr.fallout.Controllers;

import gr.fallout.Models.Assembler;
import gr.fallout.Models.RobotControllerOrder;

import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:24 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class AssemblyDashboardController
{
    private Assembler m_Assembler;

    private List<RobotControllerOrder> m_PendingOrders;

    private List<RobotControllerOrder> m_InProgressOrders;
}
