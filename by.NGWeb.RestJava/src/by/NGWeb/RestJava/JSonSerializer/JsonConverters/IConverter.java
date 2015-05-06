package by.NGWeb.RestJava.JSonSerializer.JsonConverters;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public interface IConverter<T> {
    public boolean canConvert(Class<T> t);
    public T convert(String str );
}
