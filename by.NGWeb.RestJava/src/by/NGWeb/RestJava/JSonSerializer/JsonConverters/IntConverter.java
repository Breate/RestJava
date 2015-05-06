package by.NGWeb.RestJava.JSonSerializer.JsonConverters;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class IntConverter implements IConverter<Integer> {
    @Override
    public boolean canConvert(Class<Integer> t) {
        if(t==int.class)
            return true;
        if(t==Integer.class)
            return true;
        return false;
    }

    @Override
    public Integer convert(String str) {
        try {
            return Integer.parseInt(str);
        }catch (Exception ex){
            return 0;
        }
    }
}
