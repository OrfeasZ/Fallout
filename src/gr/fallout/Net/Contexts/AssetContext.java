package gr.fallout.Net.Contexts;

import com.sun.net.httpserver.HttpExchange;
import gr.fallout.Config;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Date: 1/3/14
 * Time: 5:34 PM
 *
 * @author OrfeasZ
 */

public class AssetContext extends ContextBase
{
    public AssetContext()
    {
        super();
    }

    @Override
    public void handle(HttpExchange p_Exchange) throws IOException
    {
        try
        {
            byte[] s_AssetData = Files.readAllBytes(Paths.get(Config.DocsPath + p_Exchange.getRequestURI()));

            p_Exchange.getResponseHeaders().add("Content-Type", GetContentType(p_Exchange));

            p_Exchange.sendResponseHeaders(200, s_AssetData.length);
            OutputStream s_Stream = p_Exchange.getResponseBody();
            s_Stream.write(s_AssetData);
            s_Stream.close();
        }
        catch (Exception p_Exception)
        {
            NotFoundResponse(p_Exchange);
        }
    }

    private String GetContentType(HttpExchange p_Exchange)
    {
        String s_Extension = "";

        int i = p_Exchange.getRequestURI().toString().lastIndexOf('.');
        if (i > 0)
            s_Extension = p_Exchange.getRequestURI().toString().substring(i + 1);

        if (s_Extension.equalsIgnoreCase("css"))
            return "text/css";

        if (s_Extension.equalsIgnoreCase("js"))
            return "text/javascript";

        if (s_Extension.equalsIgnoreCase("png"))
            return "image/png";

        if (s_Extension.equalsIgnoreCase("jpg") || s_Extension.equalsIgnoreCase("jpeg"))
            return "image/jpeg";

        if (s_Extension.equalsIgnoreCase("svg"))
            return "image/svg+xml";

        return "application/octet-stream";
    }
}