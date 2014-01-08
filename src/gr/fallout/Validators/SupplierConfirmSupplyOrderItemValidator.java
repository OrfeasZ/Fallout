package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:32 μμ
 *
 * @author OrfeasZ
 */
public class SupplierConfirmSupplyOrderItemValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {
        List<String> s_Errors = new ArrayList<String>();

        if (!p_Parameters.containsKey("item_id") || p_Parameters.get("item_id").get(0) == null)
            s_Errors.add("Please fill in an item id.");

        try
        {
            Integer.parseInt(p_Parameters.get("item_id").get(0));
        }
        catch(Exception e)
        {
            s_Errors.add("Item id is of an invalid format.");
        }

        return s_Errors;
    }
}
