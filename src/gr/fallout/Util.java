package gr.fallout;

import java.lang.reflect.Field;
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
        for (T s_Item : p_Items)
        {
            try
            {
                Field s_Field = s_Item.getClass().getDeclaredField("m_Password");
                s_Field.setAccessible(true);
                s_Field.set(s_Item, "");
            }
            catch (Exception e)
            {
                continue;
            }
        }

        return p_Items;
    }
}
