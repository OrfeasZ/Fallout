package gr.fallout.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Models.*;
import gr.fallout.Net.Response;
import gr.fallout.Responses.AjaxErrorResponse;
import gr.fallout.Responses.AjaxRedirectResponse;
import gr.fallout.Store.RecordManager;
import gr.fallout.Validators.StorageVerifySupplyOrderItemArrivalValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 8/12/2013
 * Time: 12:15 πμ
 *
 * @author OrfeasZ
 * @author NikosF
 */

public class StorageVerifySupplyOrderItemArrivalController extends ProtectedController<StorageManager>
{
    public StorageVerifySupplyOrderItemArrivalController(HttpExchange p_Exchange, HashMap<String, List<String>> p_Params, String p_ContextBase)
    {
        super(p_Exchange, p_Params, p_ContextBase, "fo_storage_sid");
    }

    @Override
    public Response Execute()
    {
        Response s_Base = super.Execute();
        if (s_Base != null)
            return s_Base;

        if (!m_Exchange.getRequestMethod().equalsIgnoreCase("GET"))
            return new AjaxErrorResponse("Invalid method.");

        StorageVerifySupplyOrderItemArrivalValidator s_Validator = new StorageVerifySupplyOrderItemArrivalValidator();
        List<String> s_Errors = s_Validator.Validate(m_Params);

        // Always return the first error
        if (s_Errors != null && !s_Errors.isEmpty())
            return new AjaxErrorResponse(s_Errors.get(0));

        Integer s_ItemID = Integer.parseInt(m_Params.get("item_id").get(0));

        SupplyOrderItem s_Item = RecordManager.GetInstance().SupplyOrderItems.Get(s_ItemID);

        Gson s_Gson = new Gson();

        if (s_Item == null || s_Item.Arrived())
            return new AjaxErrorResponse("The specified item has already been marked as arrived.");

        s_Item.Arrived(true);

        // Generate quantity of controller parts in the order
        RobotControllerPart s_Template = s_Item.PartTemplate();

        for (int i = 0; i < s_Item.Quantity(); ++i)
        {
            switch (s_Item.PartType())
            {
                case Case:
                {
                    RobotCase s_Case = new RobotCase();
                    s_Case.Used(false);
                    s_Case.Size(((RobotCase)s_Template).Size());
                    RecordManager.GetInstance().RobotCases.Insert(s_Case);

                    s_Item.SupplyOrder().ControllerOrder().Controller().Case(s_Case);
                    s_Item.SupplyOrder().UpdateControllerOrder();
                    break;
                }
                case CPU:
                {
                    RobotCPU s_CPU = new RobotCPU();
                    s_CPU.Used(false);
                    s_CPU.SocketType(((RobotCPU) s_Template).SocketType());
                    RecordManager.GetInstance().RobotCPUs.Insert(s_CPU);

                    s_Item.SupplyOrder().ControllerOrder().Controller().CPU(s_CPU);
                    s_Item.SupplyOrder().UpdateControllerOrder();
                    break;
                }
                case Motherboard:
                {
                    RobotMotherboard s_Motherboard = new RobotMotherboard();
                    s_Motherboard.Used(false);
                    s_Motherboard.Model(((RobotMotherboard) s_Template).Model());
                    RecordManager.GetInstance().RobotMotherboards.Insert(s_Motherboard);

                    s_Item.SupplyOrder().ControllerOrder().Controller().Motherboard(s_Motherboard);
                    s_Item.SupplyOrder().UpdateControllerOrder();
                    break;
                }
                case RAM:
                {
                    RobotRAM s_RAM = new RobotRAM();
                    s_RAM.Used(false);
                    s_RAM.Capacity(((RobotRAM) s_Template).Capacity());
                    s_RAM.Type(((RobotRAM) s_Template).Type());
                    RecordManager.GetInstance().RobotRAMs.Insert(s_RAM);

                    s_Item.SupplyOrder().ControllerOrder().Controller().RAM(s_RAM);
                    s_Item.SupplyOrder().UpdateControllerOrder();
                    break;
                }
            }
        }

        return new AjaxRedirectResponse(m_ContextBase);
    }
}
