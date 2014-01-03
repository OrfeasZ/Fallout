package gr.fallout.Net;

import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 1/3/14
 * Time: 7:00 PM
 *
 * @author OrfeasZ
 */

public class Util
{
    public static HashMap<String, String> GetCookies(HttpExchange p_Exchange)
    {
        HashMap<String, String> s_CookieContainer = new HashMap<String, String>();

        List<String> s_CookieHeader = p_Exchange.getRequestHeaders().get("Cookie");

        if (s_CookieHeader == null || s_CookieHeader.isEmpty())
            return s_CookieContainer;

        String[] s_Cookies = s_CookieHeader.get(0).split(";");

        for (String s_Cookie : s_Cookies)
        {
            String[] s_CookieData = s_Cookie.trim().split("=");
            s_CookieContainer.put(s_CookieData[0], s_CookieData[1]);
        }

        return s_CookieContainer;
    }

    public static void SetCookie(HttpExchange p_Exchange, String p_Key, String p_Value)
    {
        List<String> s_CookieHeader = p_Exchange.getRequestHeaders().get("Set-Cookie");

        if (s_CookieHeader == null)
            s_CookieHeader = new ArrayList<String>() {{ add(""); }};

        String s_NewCookies = s_CookieHeader.get(0) + p_Key + "=" + p_Value + "; ";
        s_CookieHeader.set(0, s_NewCookies);

        p_Exchange.getResponseHeaders().put("Set-Cookie", s_CookieHeader);
    }
}
