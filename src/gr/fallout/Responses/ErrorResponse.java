package gr.fallout.Responses;

import gr.fallout.Net.Response;

/**
 * Date: 1/3/14
 * Time: 9:56 PM
 *
 * @author OrfeasZ
 */

public class ErrorResponse extends Response
{

    public ErrorResponse(String p_Error)
    {
        super("Error: " + p_Error);
    }
}
