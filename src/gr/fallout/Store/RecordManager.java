package gr.fallout.Store;

import com.google.gson.reflect.TypeToken;
import gr.fallout.Models.*;

import java.io.IOException;
import java.util.Collection;

/**
 * Date: 1/3/14
 * Time: 8:46 PM
 *
 * @author OrfeasZ, NikosF
 */

public class RecordManager
{
    private static RecordManager m_Instance = null;

    public static RecordManager GetInstance()
    {
        if (m_Instance == null)
            m_Instance = new RecordManager();
        return m_Instance;
    }

    //

    public RecordStore<AccountingManager> AccountingManagers;
    public RecordStore<Administrator> Administrators;
    public RecordStore<Assembler> Assemblers;
    public RecordStore<Customer> Customers;
    public RecordStore<CustomerOrder> CustomerOrders;
    public RecordStore<Report> Reports;
    public RecordStore<RobotCase> RobotCases;
    public RecordStore<RobotController> RobotControllers;
    public RecordStore<RobotControllerOrder> RobotControllerOrders;
    public RecordStore<RobotCPU> RobotCPUs;
    public RecordStore<RobotMotherboard> RobotMotherboards;
    public RecordStore<RobotRAM> RobotRAMs;
    public RecordStore<SalesManager> SalesManagers;
    public RecordStore<StorageManager> StorageManagers;
    public RecordStore<Supplier> Suppliers;
    public RecordStore<SupplyOrder> SupplyOrders;
    public RecordStore<SupplyOrderItem> SupplyOrderItems;

    RecordManager()
    {
        try
        {
            AccountingManagers = new RecordStore<AccountingManager>("acct", new TypeToken<Collection<AccountingManager>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            Administrators = new RecordStore<Administrator>("admin", new TypeToken<Collection<Administrator>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            Assemblers = new RecordStore<Assembler>("assm", new TypeToken<Collection<Assembler>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            Customers = new RecordStore<Customer>("cust", new TypeToken<Collection<Customer>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            CustomerOrders = new RecordStore<CustomerOrder>("cust_ord", new TypeToken<Collection<CustomerOrder>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            Reports = new RecordStore<Report>("repo", new TypeToken<Collection<Report>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            RobotCases = new RecordStore<RobotCase>("rob_case", new TypeToken<Collection<RobotCase>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            RobotControllers = new RecordStore<RobotController>("rob_ctr", new TypeToken<Collection<RobotController>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            RobotControllerOrders = new RecordStore<RobotControllerOrder>("rob_ctr_ord", new TypeToken<Collection<RobotControllerOrder>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            RobotCPUs = new RecordStore<RobotCPU>("rob_cpu", new TypeToken<Collection<RobotCPU>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            RobotMotherboards = new RecordStore<RobotMotherboard>("rob_mobo", new TypeToken<Collection<RobotMotherboard>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            RobotRAMs = new RecordStore<RobotRAM>("rob_ram", new TypeToken<Collection<RobotRAM>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            SalesManagers = new RecordStore<SalesManager>("sales", new TypeToken<Collection<SalesManager>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            StorageManagers = new RecordStore<StorageManager>("store", new TypeToken<Collection<StorageManager>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            Suppliers = new RecordStore<Supplier>("supp", new TypeToken<Collection<Supplier>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            SupplyOrders = new RecordStore<SupplyOrder>("supp_ord", new TypeToken<Collection<SupplyOrder>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            SupplyOrderItems = new RecordStore<SupplyOrderItem>("supp_ord_itm", new TypeToken<Collection<SupplyOrderItem>>(){}.getType());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
