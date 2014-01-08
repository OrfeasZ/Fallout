package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:31 μμ
 *
 * @author OrfeasZ
 */
public class SupplierLoginValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {
        List<String> s_Errors = new ArrayList<String>();


        if (!p_Parameters.containsKey("taxid") || p_Parameters.get("taxid").get(0) == null)
            s_Errors.add("Please fill in a Tax id.");

        try
        {
            Integer.parseInt(p_Parameters.get("taxid").get(0));
        }
        catch(NumberFormatException e)
        {
            s_Errors.add("Tax id is of an invalid format.");
        }

        if (p_Parameters.containsKey("taxid") && p_Parameters.get("taxid").get(0) != null && p_Parameters.get("taxid").get(0).length() != 9)
            s_Errors.add("Tax id is of an invalid format.");

        return s_Errors;
    }

 }
