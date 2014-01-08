package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Config;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AppViewResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Util;
import gr.fallout.Validators.AccountingReportValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Date: 7/12/2013
 * Time: 11:42 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class AccountingReportController extends ProtectedController<AccountingManager>
{
    private Collection<SupplyOrder> m_SupplyOrders;

    private Collection<RobotControllerOrder> m_ControllerOrders;

    private Collection<CustomerOrder> m_CustomerOrders;

    public class ReportTotals
    {
        public float Supply;
        public float Sales;
        public float Assembly;
        public float Suppliers;
        public float Customers;
    }

    public AccountingReportController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_acct_sid");

        m_SupplyOrders = new ArrayList<SupplyOrder>();
        m_ControllerOrders = new ArrayList<RobotControllerOrder>();
        m_CustomerOrders = new ArrayList<CustomerOrder>();
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        AccountingReportValidator s_Validator = new AccountingReportValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_ReportID = Integer.parseInt(m_Params.get("report_id").get(0));

        Report s_Report = RecordManager.GetInstance().Reports.Get(s_ReportID);

        if (s_Report == null)
            return new AjaxErrorResponse("The specified Report doesn't exist.");

        Report s_PreviousReport = RecordManager.GetInstance().Reports.Get(s_ReportID - 1);

        // Get the date of the previous report
        Date s_StartDate = null;

        if (s_PreviousReport == null)
            s_StartDate = new Date(0);
        else
            s_StartDate = s_PreviousReport.Date();

        Collection<SupplyOrder> s_SupplyOrders = RecordManager.GetInstance().SupplyOrders.GetAll();
        Collection<RobotControllerOrder> s_ControllerOrders = RecordManager.GetInstance().RobotControllerOrders.GetAll();
        Collection<CustomerOrder> s_CustomerOrders = RecordManager.GetInstance().CustomerOrders.GetAll();

        final Collection<Supplier> s_Suppliers = RecordManager.GetInstance().Suppliers.GetAll();
        final Collection<Customer> s_Customers = Util.FilterPasswords(RecordManager.GetInstance().Customers.GetAll());

        final HashMap<Integer, Float> s_SupplyOrderPrices = new HashMap<Integer, Float>();
        final HashMap<Integer, Float> s_ControllerOrderPrices = new HashMap<Integer, Float>();
        final HashMap<Integer, Float> s_CustomerOrderPrices = new HashMap<Integer, Float>();

        final HashMap<Integer, Float> s_SupplierDebts = new HashMap<Integer, Float>();
        final HashMap<Integer, Float> s_CustomerDebts = new HashMap<Integer, Float>();

        final ReportTotals s_Totals = new ReportTotals();
        s_Totals.Assembly = 0.f;
        s_Totals.Customers = 0.f;
        s_Totals.Sales = 0.f;
        s_Totals.Suppliers = 0.f;
        s_Totals.Supply = 0.f;

        // Filter orders based on report span
        for (SupplyOrder s_Order : s_SupplyOrders)
        {
            if (s_Order.SubmissionDate().after(s_StartDate) && s_Order.SubmissionDate().before(s_Report.Date()))
            {
                m_SupplyOrders.add(s_Order);
                s_SupplyOrderPrices.put(s_Order.m_ID, s_Order.Price());

                s_Totals.Supply += s_Order.Price();

                for (SupplyOrderItem s_Item : s_Order.Items())
                {
                    if (s_Item.PaymentConfirmed())
                        continue;

                    if (!s_SupplierDebts.containsKey(s_Item.Supplier().m_ID))
                        s_SupplierDebts.put(s_Item.Supplier().m_ID, 0.f);

                    s_SupplierDebts.put(s_Item.Supplier().m_ID, s_SupplierDebts.get(s_Item.Supplier().m_ID) + s_Item.Price());
                    s_Totals.Suppliers += s_Item.Price();
                }
            }
        }

        for (RobotControllerOrder s_Order : s_ControllerOrders)
        {
            if (s_Order.SubmissionDate().after(s_StartDate) && s_Order.SubmissionDate().before(s_Report.Date()))
            {
                m_ControllerOrders.add(s_Order);
                s_ControllerOrderPrices.put(s_Order.m_ID, s_Order.Price());

                s_Totals.Assembly += s_Order.Price();
            }
        }

        for (CustomerOrder s_Order : s_CustomerOrders)
        {
            if (s_Order.SubmissionDate().after(s_StartDate) && s_Order.SubmissionDate().before(s_Report.Date()))
            {
                m_CustomerOrders.add(s_Order);
                s_CustomerOrderPrices.put(s_Order.m_ID, s_Order.Price());

                s_Totals.Sales += s_Order.Price();

                if (s_Order.PaidSum() < s_Order.Price())
                {
                    if (!s_CustomerDebts.containsKey(s_Order.Customer().m_ID))
                        s_CustomerDebts.put(s_Order.Customer().m_ID, 0.f);

                    s_CustomerDebts.put(s_Order.Customer().m_ID, s_CustomerDebts.get(s_Order.Customer().m_ID) + (s_Order.Price() - s_Order.PaidSum()));
                    s_Totals.Customers += (s_Order.Price() - s_Order.PaidSum());
                }
            }
        }

        // Do we need to generate a new report file?
        File f = new File(Config.ReportsPath + "report_" + s_ReportID + ".txt");
        if (!f.exists())
        {
            try
            {
                PrintWriter s_Writer = new PrintWriter(Config.ReportsPath + "report_" + s_ReportID + ".txt");

                s_Writer.println("Supply Costs");
                s_Writer.println("------------");

                for (SupplyOrder s_Order : m_SupplyOrders)
                {
                    s_Writer.println("Order #" + s_Order.m_ID + " - " + s_Order.Items().size() + " items - " + s_Order.SubmissionDate().toString() + " - Price: $" + s_SupplyOrderPrices.get(s_Order.m_ID));
                }

                s_Writer.println();
                s_Writer.println("Assembly Costs");
                s_Writer.println("--------------");

                for (RobotControllerOrder s_Order : m_ControllerOrders)
                {
                    s_Writer.println("Order #" + s_Order.m_ID + " - Completed on " + s_Order.SubmissionDate().toString() + " - Cost: $" + s_ControllerOrderPrices.get(s_Order.m_ID));
                }

                s_Writer.println();
                s_Writer.println("Sales");
                s_Writer.println("-----");

                for (CustomerOrder s_Order : m_CustomerOrders)
                {
                    s_Writer.println("Order #" + s_Order.m_ID + " - " + s_Order.ControllerOrders().size() + " controllers - Price: $" + s_CustomerOrderPrices.get(s_Order.m_ID));
                }

                s_Writer.println();
                s_Writer.println("Supplier Debts");
                s_Writer.println("--------------");

                for (Supplier s_Supplier : s_Suppliers)
                {
                    float s_Debt = s_SupplierDebts.containsKey(s_Supplier.m_ID) ? s_SupplierDebts.get(s_Supplier.m_ID) : 0.f;
                    s_Writer.println(s_Supplier.Name() + " - Debt: $" + s_Debt);
                }

                s_Writer.println();
                s_Writer.println("Customer Debts");
                s_Writer.println("--------------");

                for (Customer s_Customer : s_Customers)
                {
                    float s_Debt = s_CustomerDebts.containsKey(s_Customer.m_ID) ? s_CustomerDebts.get(s_Customer.m_ID) : 0.f;
                    s_Writer.println(s_Customer.Name() + " - Debt: $" + s_Debt);
                }

                s_Writer.println();
                s_Writer.println("Totals");
                s_Writer.println("------");

                s_Writer.println("Supply Orders: $" + s_Totals.Supply);
                s_Writer.println("Assembly: $" + s_Totals.Assembly);
                s_Writer.println("Sales: $" + s_Totals.Sales);
                s_Writer.println("Supplier Debts: $" + s_Totals.Suppliers);
                s_Writer.println("Customer Debts: $" + s_Totals.Customers);

                s_Writer.flush();
                s_Writer.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        final Gson s_Gson = new Gson();

        return new AppViewResponse("AccountingReport", new HashMap<String, String>() {{
            put("supply_orders", s_Gson.toJson(m_SupplyOrders));
            put("supply_orders_prices", s_Gson.toJson(s_SupplyOrderPrices));
            put("controller_orders", s_Gson.toJson(m_ControllerOrders));
            put("controller_orders_prices", s_Gson.toJson(s_ControllerOrderPrices));
            put("customer_orders", s_Gson.toJson(m_CustomerOrders));
            put("customer_orders_prices", s_Gson.toJson(s_CustomerOrderPrices));
            put("suppliers", s_Gson.toJson(s_Suppliers));
            put("customers", s_Gson.toJson(s_Customers));
            put("supplier_debts", s_Gson.toJson(s_SupplierDebts));
            put("customer_debts", s_Gson.toJson(s_CustomerDebts));
            put("totals", s_Gson.toJson(s_Totals));
        }}, "Fallout - Accounting Report");
    }
}
