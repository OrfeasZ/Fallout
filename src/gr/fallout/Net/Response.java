package gr.fallout.Net;

import java.util.HashMap;

/**
 * Date: 9/12/2013
 * Time: 3:43 μμ
 *
 * @author OrfeasZ
 */
public class Response
{
    public byte[] Content;
    public String ContentType;
    public int StatusCode;
    public HashMap<String, String> Headers;

    public Response(String p_Content)
    {
        Headers = new HashMap<String, String>();
        Content = p_Content.getBytes();
        StatusCode = 200;
        ContentType = "text/html";
    }

    public Response(byte[] p_Content)
    {
        Headers = new HashMap<String, String>();
        Content = p_Content;
        StatusCode = 200;
        ContentType = "text/html";
    }

    public Response(String p_Content, int p_StatusCode)
    {
        Headers = new HashMap<String, String>();
        Content = p_Content.getBytes();
        StatusCode = p_StatusCode;
        ContentType = "text/html";
    }

    public Response(byte[] p_Content, int p_StatusCode)
    {
        Headers = new HashMap<String, String>();
        Content = p_Content;
        StatusCode = p_StatusCode;
        ContentType = "text/html";
    }

    public Response(byte[] p_Content, int p_StatusCode, String p_ContentType)
    {
        Headers = new HashMap<String, String>();
        Content = p_Content;
        StatusCode = p_StatusCode;
        ContentType = p_ContentType;
    }

    public Response(String p_Content, int p_StatusCode, String p_ContentType)
    {
        Headers = new HashMap<String, String>();
        Content = p_Content.getBytes();
        StatusCode = p_StatusCode;
        ContentType = p_ContentType;
    }
}
