package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 1/6/14
 * Time: 2:06 AM
 *
 * @author OrfeasZ
 */

public class StorageSupplyOrderValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {
        List<String> s_Errors = new ArrayList<String>();

        if (!p_Parameters.containsKey("order_id") || p_Parameters.get("order_id").get(0) == null)
            s_Errors.add("Please fill in an order id.");

        try
        {
            Integer.parseInt(p_Parameters.get("order_id").get(0));
        }
        catch(NumberFormatException e)
        {
            s_Errors.add("Order id is of an invalid format.");
        }

        return s_Errors;    }
}
