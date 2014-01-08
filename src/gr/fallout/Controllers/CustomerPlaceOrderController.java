package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.CustomerPlaceOrderValidator;

import java.util.*;

/**
 * Date: 8/12/2013
 * Time: 12:08 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class CustomerPlaceOrderController extends ProtectedController<Customer>
{
    public CustomerPlaceOrderController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_cust_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("POST"))
            return new AjaxErrorResponse("Invalid method.");

        CustomerPlaceOrderValidator s_Validator = new CustomerPlaceOrderValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Gson s_Gson = new Gson();

        Collection<RobotControllerOrderTemplate> s_Controllers = s_Gson.fromJson(m_Params.get("controllers").get(0), new TypeToken<Collection<RobotControllerOrderTemplate>>(){}.getType());

        CustomerOrder s_Order = new CustomerOrder();
        s_Order.Delivered(false);
        s_Order.Customer(m_User);
        s_Order.SubmissionDate(new Date());
        s_Order.Status(CustomerOrder.OrderStatus.Pending);

        // Create controller Orders
        for (RobotControllerOrderTemplate s_Template : s_Controllers)
        {
            RobotControllerOrder s_ControllerOrder = new RobotControllerOrder();
            RecordManager.GetInstance().RobotControllerOrders.Insert(s_ControllerOrder);

            RobotController s_Controller = new RobotController();

            s_Controller.Case(FindCase(s_Template.Case));
            s_Controller.CPU(FindCPU(s_Template.CPU));
            s_Controller.RAM(FindRAM(s_Template.RAM, s_Template.RAMCap));
            s_Controller.Motherboard(FindMotherboard(s_Template.Mobo));

            RecordManager.GetInstance().RobotControllers.Insert(s_Controller);

            s_ControllerOrder.Controller(s_Controller);
            s_ControllerOrder.SubmissionDate(new Date());

            // One (or more) items are out of stock
            if (s_Controller.Case() == null || s_Controller.CPU() == null || s_Controller.RAM() == null || s_Controller.Motherboard() == null)
            {
                // Create a SupplyOrder
                SupplyOrder s_SupplyOrder = new SupplyOrder();
                s_SupplyOrder.SubmissionDate(new Date());

                RecordManager.GetInstance().SupplyOrders.Insert(s_SupplyOrder);

                if (s_Controller.Case() == null)
                    s_SupplyOrder.AddItem(CreateCaseSupplyOrderItem(s_SupplyOrder, s_Template.Case));

                if (s_Controller.CPU() == null)
                    s_SupplyOrder.AddItem(CreateCPUSupplyOrderItem(s_SupplyOrder, s_Template.CPU));

                if (s_Controller.RAM() == null)
                    s_SupplyOrder.AddItem(CreateRAMSupplyOrderItem(s_SupplyOrder, s_Template.RAM, s_Template.RAMCap));

                if (s_Controller.Motherboard() == null)
                    s_SupplyOrder.AddItem(CreateMotherboardSupplyOrderItem(s_SupplyOrder, s_Template.Mobo));

                s_ControllerOrder.SupplyOrder(s_SupplyOrder);
                s_SupplyOrder.ControllerOrder(s_ControllerOrder);
            }

            s_Order.AddControllerOrder(s_ControllerOrder);
        }

        RecordManager.GetInstance().CustomerOrders.Insert(s_Order);

        m_User.AddOrder(s_Order);

        return new AjaxRedirectResponse(m_ContextBase);
    }

    private RobotCase FindCase(RobotCase.CaseSize p_Size)
    {
        Collection<RobotCase> s_Cases = RecordManager.GetInstance().RobotCases.GetAll();

        for (RobotCase s_Case : s_Cases)
        {
            if (!s_Case.Used() && s_Case.Size() == p_Size)
            {
                s_Case.Used(true);
                return s_Case;
            }
        }

        return null;
    }

    private RobotCPU FindCPU(RobotCPU.CPUSocket s_Socket)
    {
        Collection<RobotCPU> s_CPUs = RecordManager.GetInstance().RobotCPUs.GetAll();

        for (RobotCPU s_CPU : s_CPUs)
        {
            if (!s_CPU.Used() && s_CPU.SocketType() == s_Socket)
            {
                s_CPU.Used(true);
                return s_CPU;
            }
        }

        return null;
    }

    private RobotRAM FindRAM(RobotRAM.RAMType p_Type, RobotRAM.RAMCapacity p_Capacity)
    {
        Collection<RobotRAM> s_RAMs = RecordManager.GetInstance().RobotRAMs.GetAll();

        for (RobotRAM s_RAM : s_RAMs)
        {
            if (!s_RAM.Used() && s_RAM.Type() == p_Type && s_RAM.Capacity() == p_Capacity)
            {
                s_RAM.Used(true);
                return s_RAM;
            }
        }

        return null;
    }

    private RobotMotherboard FindMotherboard(RobotMotherboard.MotherboardModel p_Model)
    {
        Collection<RobotMotherboard> s_Motherboards = RecordManager.GetInstance().RobotMotherboards.GetAll();

        for (RobotMotherboard s_Motherboard : s_Motherboards)
        {
            if (!s_Motherboard.Used() && s_Motherboard.Model() == p_Model)
            {
                s_Motherboard.Used(true);
                return s_Motherboard;
            }
        }

        return null;
    }

    private SupplyOrderItem CreateCaseSupplyOrderItem(SupplyOrder p_Order, RobotCase.CaseSize p_Size)
    {
        // Find Supplier
        Collection<Supplier> s_Suppliers = RecordManager.GetInstance().Suppliers.GetAll();

        Supplier s_Supplier = null;

        for (Supplier s_Candidate : s_Suppliers)
            if (s_Candidate.AvailableType() == Supplier.PartType.Case)
                s_Supplier = s_Candidate;

        // Create template
        RobotCase s_Template = new RobotCase();
        s_Template.Size(p_Size);

        RecordManager.GetInstance().CaseTemplates.Insert(s_Template);

        // Create SupplyOrderItem
        SupplyOrderItem s_OrderItem = new SupplyOrderItem();

        s_OrderItem.Arrived(false);
        s_OrderItem.PaymentConfirmed(false);
        s_OrderItem.PartType(Supplier.PartType.Case);
        s_OrderItem.PartTemplate(s_Template);
        s_OrderItem.Supplier(s_Supplier);
        s_OrderItem.Quantity(1);
        s_OrderItem.SupplyOrder(p_Order);

        RecordManager.GetInstance().SupplyOrderItems.Insert(s_OrderItem);

        s_Supplier.AddItem(s_OrderItem);

        return s_OrderItem;
    }

    private SupplyOrderItem CreateCPUSupplyOrderItem(SupplyOrder p_Order, RobotCPU.CPUSocket p_Socket)
    {
        // Find Supplier
        Collection<Supplier> s_Suppliers = RecordManager.GetInstance().Suppliers.GetAll();

        Supplier s_Supplier = null;

        for (Supplier s_Candidate : s_Suppliers)
            if (s_Candidate.AvailableType() == Supplier.PartType.CPU)
                s_Supplier = s_Candidate;

        // Create template
        RobotCPU s_Template = new RobotCPU();
        s_Template.SocketType(p_Socket);

        RecordManager.GetInstance().CPUTemplates.Insert(s_Template);

        // Create SupplyOrderItem
        SupplyOrderItem s_OrderItem = new SupplyOrderItem();

        s_OrderItem.Arrived(false);
        s_OrderItem.PaymentConfirmed(false);
        s_OrderItem.PartType(Supplier.PartType.CPU);
        s_OrderItem.PartTemplate(s_Template);
        s_OrderItem.Supplier(s_Supplier);
        s_OrderItem.Quantity(1);
        s_OrderItem.SupplyOrder(p_Order);

        RecordManager.GetInstance().SupplyOrderItems.Insert(s_OrderItem);

        s_Supplier.AddItem(s_OrderItem);

        return s_OrderItem;
    }

    private SupplyOrderItem CreateRAMSupplyOrderItem(SupplyOrder p_Order, RobotRAM.RAMType p_Type, RobotRAM.RAMCapacity p_Capacity)
    {
        // Find Supplier
        Collection<Supplier> s_Suppliers = RecordManager.GetInstance().Suppliers.GetAll();

        Supplier s_Supplier = null;

        for (Supplier s_Candidate : s_Suppliers)
            if (s_Candidate.AvailableType() == Supplier.PartType.RAM)
                s_Supplier = s_Candidate;

        // Create template
        RobotRAM s_Template = new RobotRAM();
        s_Template.Type(p_Type);
        s_Template.Capacity(p_Capacity);

        RecordManager.GetInstance().RAMTemplates.Insert(s_Template);

        // Create SupplyOrderItem
        SupplyOrderItem s_OrderItem = new SupplyOrderItem();

        s_OrderItem.Arrived(false);
        s_OrderItem.PaymentConfirmed(false);
        s_OrderItem.PartType(Supplier.PartType.RAM);
        s_OrderItem.PartTemplate(s_Template);
        s_OrderItem.Supplier(s_Supplier);
        s_OrderItem.Quantity(1);
        s_OrderItem.SupplyOrder(p_Order);

        RecordManager.GetInstance().SupplyOrderItems.Insert(s_OrderItem);

        s_Supplier.AddItem(s_OrderItem);

        return s_OrderItem;
    }

    private SupplyOrderItem CreateMotherboardSupplyOrderItem(SupplyOrder p_Order, RobotMotherboard.MotherboardModel p_Model)
    {
        // Find Supplier
        Collection<Supplier> s_Suppliers = RecordManager.GetInstance().Suppliers.GetAll();

        Supplier s_Supplier = null;

        for (Supplier s_Candidate : s_Suppliers)
            if (s_Candidate.AvailableType() == Supplier.PartType.Motherboard)
                s_Supplier = s_Candidate;

        // Create template
        RobotMotherboard s_Template = new RobotMotherboard();
        s_Template.Model(p_Model);

        RecordManager.GetInstance().MotherboardTemplates.Insert(s_Template);

        // Create SupplyOrderItem
        SupplyOrderItem s_OrderItem = new SupplyOrderItem();

        s_OrderItem.Arrived(false);
        s_OrderItem.PaymentConfirmed(false);
        s_OrderItem.PartType(Supplier.PartType.Motherboard);
        s_OrderItem.PartTemplate(s_Template);
        s_OrderItem.Supplier(s_Supplier);
        s_OrderItem.Quantity(1);
        s_OrderItem.SupplyOrder(p_Order);

        RecordManager.GetInstance().SupplyOrderItems.Insert(s_OrderItem);

        s_Supplier.AddItem(s_OrderItem);

        return s_OrderItem;
    }
}
