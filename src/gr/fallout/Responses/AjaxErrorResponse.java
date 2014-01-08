package gr.fallout.Responses;

import com.google.gson.Gson;
import gr.fallout.Net.Response;

import java.util.Collection;

/**
 * Date: 1/3/14
 * Time: 9:56 PM
 *
 * @author OrfeasZ
 */

public class AjaxErrorResponse extends Response
{
    public AjaxErrorResponse(Collection<String> p_Errors)
    {
        super("<script type=\"text/javascript\">ShowErrors(" + new Gson().toJson(p_Errors) + ");</script>");
    }

    public AjaxErrorResponse(String p_Error)
    {
        super("<script type=\"text/javascript\">ShowErrors([ " + new Gson().toJson(p_Error) + " ]);</script>");
    }
}
