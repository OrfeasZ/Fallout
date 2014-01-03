package gr.fallout.Net;

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

    public Response(String p_Content)
    {
        Content = p_Content.getBytes();
        StatusCode = 200;
        ContentType = "text/html";
    }

    public Response(byte[] p_Content)
    {
        Content = p_Content;
        StatusCode = 200;
        ContentType = "text/html";
    }

    public Response(String p_Content, int p_StatusCode)
    {
        Content = p_Content.getBytes();
        StatusCode = p_StatusCode;
        ContentType = "text/html";
    }

    public Response(byte[] p_Content, int p_StatusCode)
    {
        Content = p_Content;
        StatusCode = p_StatusCode;
        ContentType = "text/html";
    }

    public Response(byte[] p_Content, int p_StatusCode, String p_ContentType)
    {
        Content = p_Content;
        StatusCode = p_StatusCode;
        ContentType = p_ContentType;
    }

    public Response(String p_Content, int p_StatusCode, String p_ContentType)
    {
        Content = p_Content.getBytes();
        StatusCode = p_StatusCode;
        ContentType = p_ContentType;
    }
}
