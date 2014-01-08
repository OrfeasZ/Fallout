package gr.fallout.Responses;

import gr.fallout.Config;
import gr.fallout.Net.Response;

/**
 * Date: 8/1/2014
 * Time: 11:00 πμ
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
