package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:26 μμ
 *
 * @author OrfeasZ
 * @author NikosF
 */
public class CustomerPayOrderValidator implements IValidator
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
            s_Errors.add("order id is of an invalid format.");
        }

        if (!p_Parameters.containsKey("pay_sum") || p_Parameters.get("pay_sum").get(0) == null)
            s_Errors.add("Please fill in a Sum to pay.");

        try
        {
            Float.parseFloat(p_Parameters.get("pay_sum").get(0));
        }
        catch(Exception e)
        {
            s_Errors.add("Payment sum is of an invalid format.");
        }

        return s_Errors;
    }
}
