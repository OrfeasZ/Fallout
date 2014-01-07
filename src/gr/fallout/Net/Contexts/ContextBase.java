package gr.fallout.Net.Contexts;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import gr.fallout.Controllers.Controller;
import gr.fallout.Net.Response;

import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: 1/3/14
 * Time: 3:55 PM
 *
 * @author OrfeasZ
 */

public abstract class ContextBase implements HttpHandler
{
    private LinkedHashMap<String, Class<?>> m_Routes;
    private LinkedHashMap<String, String> m_RoutePatterns;

    protected String m_ContextBase;

    protected ContextBase()
    {
        m_Routes = new LinkedHashMap<String, Class<?>>();
        m_RoutePatterns = new LinkedHashMap<String, String>();
    }

    protected void RegisterRoute(String p_Route, Class<?> p_ControllerClass)
    {
        if (!p_Route.endsWith("/"))
            p_Route += "/";

        m_Routes.put(p_Route, p_ControllerClass);
        m_RoutePatterns.put(p_Route.replaceAll(":([a-zA-Z0-9_-]+)", "([a-zA-Z0-9_-]+)"), p_Route);
    }

    protected boolean HandleRequest(HttpExchange p_Exchange) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        String s_RequestURI = p_Exchange.getRequestURI().toString();

        int i = s_RequestURI.lastIndexOf('?');
        if (i > 0)
            s_RequestURI = s_RequestURI.substring(0, i);

        if (!s_RequestURI.endsWith("/"))
            s_RequestURI += "/";

        for (Map.Entry<String, String> s_Entry : m_RoutePatterns.entrySet())
        {
            // Our route pattern matches, dispatch request to the appropriate controller
            Pattern s_KeyPattern = Pattern.compile(s_Entry.getKey());

            if (s_KeyPattern.matcher(s_RequestURI).matches())
            {
                // Collect route, POST, and GET parameters
                HashMap<String, List<String>> s_Params = (HashMap<String, List<String>>)p_Exchange.getAttribute("parameters");

                Matcher s_ParamMatcher = Pattern.compile(":([a-zA-Z0-9_-]+)").matcher(s_Entry.getValue());
                Matcher s_RouteMatcher = s_KeyPattern.matcher(s_RequestURI);

                s_RouteMatcher.find();

                int s_Match = 0;
                while (s_ParamMatcher.find())
                {
                    s_Params.put(s_ParamMatcher.group(1), new ArrayList<String>(Arrays.asList(s_RouteMatcher.group(s_Match + 1))));
                    ++s_Match;
                }

                // Create our controller
                Controller s_Controller = (Controller)m_Routes.get(s_Entry.getValue()).getConstructor(HttpExchange.class, s_Params.getClass(), String.class).newInstance(p_Exchange, s_Params, m_ContextBase);
                Response s_Response = s_Controller.Execute();

                if (s_Response == null)
                {
                    NotImplementedResponse(p_Exchange);
                    return true;
                }

                p_Exchange.getResponseHeaders().add("Content-Type", s_Response.ContentType);

                for (Map.Entry<String, String> s_Header : s_Response.Headers.entrySet())
                    p_Exchange.getResponseHeaders().add(s_Header.getKey(), s_Header.getValue());

                p_Exchange.sendResponseHeaders(s_Response.StatusCode, s_Response.Content.length);
                OutputStream s_Stream = p_Exchange.getResponseBody();
                s_Stream.write(s_Response.Content);
                s_Stream.close();

                return true;
            }
        }

        return false;
    }

    public void handle(HttpExchange p_Exchange) throws IOException
    {
        try
        {
            if (!HandleRequest(p_Exchange))
                NotFoundResponse(p_Exchange);
        }
        catch (Exception p_Exception)
        {
            p_Exception.printStackTrace();
            InternalErrorResponse(p_Exchange);
        }
    }

    protected void NotFoundResponse(HttpExchange p_Exchange) throws IOException
    {
        String s_Response = "The specified resource could not be found.";
        p_Exchange.sendResponseHeaders(404, s_Response.length());
        OutputStream s_Stream = p_Exchange.getResponseBody();
        s_Stream.write(s_Response.getBytes());
        s_Stream.close();
    }

    protected void InternalErrorResponse(HttpExchange p_Exchange) throws IOException
    {
        String s_Response = "An internal error has occurred.";
        p_Exchange.sendResponseHeaders(500, s_Response.length());
        OutputStream s_Stream = p_Exchange.getResponseBody();
        s_Stream.write(s_Response.getBytes());
        s_Stream.close();
    }

    protected void NotImplementedResponse(HttpExchange p_Exchange) throws IOException
    {
        String s_Response = "Not implemented.";
        p_Exchange.sendResponseHeaders(501, s_Response.length());
        OutputStream s_Stream = p_Exchange.getResponseBody();
        s_Stream.write(s_Response.getBytes());
        s_Stream.close();
    }
}
