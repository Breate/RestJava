package by.NGWeb.RestJava.JSonSerializer.JsonConverters;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class StringConverter implements IConverter {
    @Override
    public boolean canConvert(Class t) {
        if (String.class ==t)
            return true;
        return false;
    }

    @Override
    public Object convert(String str) {
       return str;
    }
}
