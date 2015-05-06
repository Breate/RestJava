package by.NGWeb.RestJava.JSonSerializer.JsonConverters;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public interface IConverter {
    public boolean canConvert(Class t);
    public Object convert(String str );
}
