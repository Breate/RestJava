package by.NGWeb.RestJava.JSonSerializer.JsonConverters;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class FloatConverter implements IConverter {
    @Override
    public boolean canConvert(Class t) {
        if(t==float.class)
            return true;
        if(t==Float.class)
            return true;
        return false;
    }

    @Override
    public Object convert(String str) {
        try {
            return Float.parseFloat(str);
        }catch (Exception ex){
            return (Float)0.0F;
        }
    }
}
