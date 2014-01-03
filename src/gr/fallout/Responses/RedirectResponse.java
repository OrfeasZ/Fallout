package gr.fallout.Responses;

import gr.fallout.Config;
import gr.fallout.Net.Response;

/**
 * Date: 1/3/14
 * Time: 7:18 PM
 *
 * @author OrfeasZ
 */

public class RedirectResponse extends Response
{
    public RedirectResponse(String p_Location)
    {
        super("", 302);
        Headers.put("Location", Config.BaseURL + p_Location);
    }
}
