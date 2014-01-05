package gr.fallout.Responses;

import gr.fallout.Config;
import gr.fallout.Net.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: 1/3/14
 * Time: 5:16 PM
 *
 * @author OrfeasZ
 */

public class AppViewResponse extends Response
{
    public AppViewResponse(String p_ViewName, HashMap<String, String> p_Params, String s_Title)
    {
        super("");

        try
        {
            String s_ViewContent =  new String(Files.readAllBytes(Paths.get(Config.DocsPath + "views/" + p_ViewName + ".html")), StandardCharsets.UTF_8);
            String s_TemplateContent =  new String(Files.readAllBytes(Paths.get(Config.DocsPath + "views/templates/" + Config.BaseTemplateName + ".html")), StandardCharsets.UTF_8);

            // Replace View params
            if (p_Params != null)
            {
                for (Map.Entry<String, String> s_Entry : p_Params.entrySet())
                {
                    s_ViewContent = s_ViewContent.replaceAll("<%=\\s+" + s_Entry.getKey() + "\\s+%>", Matcher.quoteReplacement(s_Entry.getValue()));
                }
            }

            // Replace Template params
            s_TemplateContent = s_TemplateContent.replaceAll("<%=\\s+body\\s+%>", Matcher.quoteReplacement(s_ViewContent));
            s_TemplateContent = s_TemplateContent.replaceAll("<%=\\s+title\\s+%>", Matcher.quoteReplacement(s_Title));
            s_TemplateContent = s_TemplateContent.replaceAll("<%=\\s+base_url\\s+%>", Matcher.quoteReplacement(Config.BaseURL));

            Content = s_TemplateContent.getBytes();
        }
        catch (IOException e)
        {
        }
    }
}
