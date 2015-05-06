package by.NGWeb.RestJava.Test.Serializer.JsonConverters;

import by.NGWeb.RestJava.JSonSerializer.JsonConverters.IntConverter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class IntConverterTest {

    @Test
    public void testCanConvert() throws Exception {

        IntConverter converter = new IntConverter();
        Assert.assertTrue(converter.canConvert(int.class));
        Assert.assertTrue(converter.canConvert(Integer.class));

    }

    @Test
    public void testConvert() throws Exception {
        IntConverter converter = new IntConverter();
        Assert.assertEquals((Integer) 9, converter.convert("9"));
     }
}