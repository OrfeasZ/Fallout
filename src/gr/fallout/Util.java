package gr.fallout;

import com.rits.cloning.Cloner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Date: 8/1/2014
 * Time: 12:46 μμ
 *
 * @author OrfeasZ
 */
public class Util
{
    public static <T> Collection<T> FilterPasswords(Collection<T> p_Items)
    {
        Collection<T> s_Items = new ArrayList<T>();

        Cloner s_Cloner = new Cloner();

        for (T s_Item : p_Items)
        {
            try
            {
                // Create item clone
                T s_Clone = s_Cloner.deepClone(s_Item);

                Field s_Field = s_Clone.getClass().getDeclaredField("m_Password");
                s_Field.setAccessible(true);
                s_Field.set(s_Clone, "");

                s_Items.add(s_Clone);
            }
            catch (Exception e)
            {
            }
        }

        return s_Items;
    }
}
