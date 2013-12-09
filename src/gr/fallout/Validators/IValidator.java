package gr.fallout.Validators;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:20 μμ
 *
 * @author OrfeasZ
 */

public interface IValidator
{
    public List<String> Validate(HashMap<String, String> p_Parameters);
}
