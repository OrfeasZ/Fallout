package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:25 μμ
 *
 * @author OrfeasZ
 */
public class AssemblyStartAssemblyValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {
        List<String> s_Errors = new ArrayList<String>();

        if (!p_Parameters.containsKey("order_id") || p_Parameters.get("order_id").get(0) == null)
            s_Errors.add("No order id specified.");

        try
        {
            Integer.parseInt(p_Parameters.get("order_id").get(0));
        }
        catch(Exception e)
        {
            s_Errors.add("Order id is of an invalid format.");
        }

        if (!p_Parameters.containsKey("warranty") || p_Parameters.get("warranty").get(0) == null)
            s_Errors.add("Please fill in a case warranty.");

        try
        {
            Float.parseFloat(p_Parameters.get("warranty").get(0));
        }
        catch(Exception e)
        {
            s_Errors.add("The cae warranty is of an invalid format.");

        }

        if (!p_Parameters.containsKey("rate") || p_Parameters.get("rate").get(0) == null)
            s_Errors.add("Please fill in an hourly rate.");

        try
        {
            Float.parseFloat(p_Parameters.get("rate").get(0));
        }
        catch(Exception e)
        {
            s_Errors.add("The hourly rate is of an invalid format.");
        }
        
        return s_Errors;
    }
}
