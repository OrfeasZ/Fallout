package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 1/7/14
 * Time: 1:27 AM
 *
 * @author OrfeasZ
 * @author NikosF
 */

public class AccountingReportValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {
        List<String> s_Errors = new ArrayList<String>();

        if (!p_Parameters.containsKey("report_id") || p_Parameters.get("report_id").get(0) == null)
            s_Errors.add("No report id specified.");

        try
        {
            Integer.parseInt(p_Parameters.get("report_id").get(0));
        }
        catch(Exception e)
        {
            s_Errors.add("Report id is of an invalid format.");
        }

        return s_Errors;
    }
}
