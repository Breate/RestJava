package by.NGWeb.RestJava.JSonSerializer.JsonConverters;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class DoubleConverter implements IConverter {
    @Override
    public boolean canConvert(Class t) {
        if(t==double.class)
            return true;
        if(t==Double.class)
            return true;
        return false;
    }

    @Override
    public Object convert(String str) {
        try {
            return Double.parseDouble(str);
        }catch (Exception ex){
            return (Double)0.0D;
        }
    }
}
