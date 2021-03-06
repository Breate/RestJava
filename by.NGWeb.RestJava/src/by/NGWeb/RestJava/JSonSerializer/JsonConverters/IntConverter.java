package by.NGWeb.RestJava.JSonSerializer.JsonConverters;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class IntConverter implements IConverter {
    @Override
    public boolean canConvert(Class t) {
        if(t==int.class)
            return true;
        if(t==Integer.class)
            return true;
        return false;
    }

    @Override
    public Object convert(String str) {
        try {
            return Integer.parseInt(str);
        }catch (Exception ex){
            return 0;
        }
    }
}
