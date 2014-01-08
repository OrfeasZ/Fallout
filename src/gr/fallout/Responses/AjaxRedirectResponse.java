package gr.fallout.Responses;

import gr.fallout.Config;
import gr.fallout.Net.Response;

/**
 * Date: 1/3/14
 * Time: 7:18 PM
 *
 * @author OrfeasZ
 */

public class AjaxRedirectResponse extends Response
{
    public AjaxRedirectResponse(String p_Location)
    {
        super("<script type=\"text/javascript\">window.location.href = '" + Config.BaseURL + p_Location + "';</script>");
    }
}
