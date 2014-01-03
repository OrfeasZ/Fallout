package gr.fallout.Store;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gr.fallout.Config;
import gr.fallout.Models.Identifiable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Date: 1/3/14
 * Time: 7:47 PM
 *
 * @author OrfeasZ, NikosF
 */

public class RecordStore<T>
{
    private String m_SourceName;
    private TreeMap<Integer, Identifiable> m_Records;
    private Gson m_Gson;

    public RecordStore(String p_SourceName, Type p_CollectionType) throws IOException
    {
        m_SourceName = p_SourceName;
        m_Records = new TreeMap<Integer, Identifiable>();
        m_Gson = new Gson();

        String s_ViewContent = "[]";

        try
        {
            s_ViewContent =  new String(Files.readAllBytes(Paths.get(Config.DataPath + m_SourceName + ".json")), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            Files.createFile(Paths.get(Config.DataPath + m_SourceName + ".json"));
            Files.write(Paths.get(Config.DataPath + m_SourceName + ".json"), "[]".getBytes());
        }

        Collection<T> s_Records = m_Gson.fromJson(s_ViewContent, p_CollectionType);

        for (T s_Record : s_Records)
        {
            m_Records.put(((Identifiable) s_Record).m_ID, (Identifiable) s_Record);
        }
    }

    public T Get(Integer p_ID)
    {
        return (T)m_Records.get(p_ID);
    }

    public Integer Insert(T p_Record)
    {
        Integer s_NewID = m_Records.isEmpty() ? 0 : m_Records.lastKey() + 1;
        ((Identifiable)p_Record).m_ID = s_NewID;
        Update(p_Record);
        return s_NewID;
    }

    public void Update(T p_Record)
    {
        m_Records.put(((Identifiable)p_Record).m_ID, (Identifiable)p_Record);
        Persist();
    }

    public void Remove(T p_Record)
    {
        m_Records.remove(((Identifiable)p_Record).m_ID);
        Persist();
    }

    public void Remove(Integer p_RecordID)
    {
        m_Records.remove(p_RecordID);
        Persist();
    }

    public Collection<T> GetAll()
    {
        return (Collection<T>)m_Records.values();
    }

    private void Persist()
    {
        Collection<T> s_Records = (Collection<T>)m_Records.values();
        String s_EncodedData = m_Gson.toJson(s_Records);

        try
        {
            Files.write(Paths.get(Config.DataPath + m_SourceName + ".json"), s_EncodedData.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
