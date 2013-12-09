package gr.fallout.Controllers;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;

import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:11 πμ
 *
 * @author OrfeasZ, NikosF
 */
public class StorageStockController extends Controller
{
    private StorageManager m_Manager;

    private List<RobotCase> m_RobotCases;
    private List<RobotCPU> m_RobotCPUs;
    private List<RobotMotherboard> m_RobotMotherboards;
    private List<RobotRAM> m_RobotRAMs;

    public StorageStockController(HttpExchange p_Exchange)
    {
        super(p_Exchange);
    }

    @Override
    public Response Execute()
    {
        return null;
    }
}
