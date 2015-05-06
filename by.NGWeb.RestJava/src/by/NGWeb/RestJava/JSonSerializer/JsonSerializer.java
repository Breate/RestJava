package by.NGWeb.RestJava.JSonSerializer;

import by.NGWeb.RestJava.JSonSerializer.Annotation.Generic;
import by.NGWeb.RestJava.JSonSerializer.JsonConverters.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class JsonSerializer {

    public static List<IConverter> converters = new ArrayList<IConverter>();

    static {
        converters.add(new IntConverter());
        converters.add(new FloatConverter());
        converters.add(new DoubleConverter());
        converters.add(new StringConverter());
    }

    public <T> T Deserialize(Class<T> clazz, String json) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        T elem = clazz.newInstance();

            Map m = parseJSonObject(json);
            Apply(elem, m);

        return elem;
    }

    private void Apply(Object oo, Map map) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Method m[] = oo.getClass().getMethods();

        for (int i = 0; i < m.length; i++)
            if (m[i].getName().startsWith("set")) {
                String name = m[i].getName().replace("set", "");
                if (map.containsKey(name)) {
                    Object value = map.get(name);
                    Class cz = m[i].getParameterTypes()[0];
                    if (Collection.class.isAssignableFrom(cz)) {
                        Collection<Object> instance = new ArrayList<>();
                        ApplyList(instance, (Iterable) value,m[i].getAnnotation(Generic.class).Type());
                        m[i].invoke(oo, instance);
                        continue;
                    }
                    boolean converted = false;
                    for (IConverter cv : converters) {

                        if (cv.canConvert(cz)) {
                            converted = true;
                            m[i].invoke(oo, cv.convert((String) value));
                        }

                    }
                    if (converted == false) {
                        Object ooo = cz.newInstance();
                        Apply(ooo, (Map) value);
                        m[i].invoke(oo, ooo);
                    }
                }
            }
    }

    private void ApplyList(Collection<Object> elem, Iterable c,Class persistentClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Object o : c) {

                Object instanse = persistentClass.newInstance();
                Apply(instanse, (Map) o);
                elem.add(instanse);
            }
    }

    private Object parseJSon(String json) {
        json = json.replaceAll("\n", "");
        if (json.startsWith("{") && json.endsWith("}"))
            return parseJSonObject(json);
        if (json.startsWith("[") && json.endsWith("]"))
            parseArray(json);
        return json;

    }

    private List parseArray(String json) {
        Pattern p = Pattern.compile("[ :]+((?=\\[)\\[[^\\]]*\\]|(?=\\{)\\{[^\\}]*\\}|(?=[0-9])[^,\\}]*|(?=\\\")\\\"[^\"]*\\\")");
        Matcher m = p.matcher(json);
        List<Object> lst = new ArrayList<>();
        while (m.find()) {
            String value = m.group();

            value = GroomString(value);

            if (value.startsWith("{")) {
                lst.add(parseJSonObject(value));
                continue;
            }
            if (value.startsWith("[")) {
                lst.add(parseArray(value));
                continue;
            }
            lst.add(value);
        }
        return lst;
    }

    private Map parseJSonObject(String json) {
        Map map = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);

        Pattern p = Pattern.compile("[ :]+((?=\\[)\\[[^\\]]*\\]|(?=\\{)\\{[^\\}]*\\}|(?=[0-9])[^,\\}]*|(?=\\\")\\\"[^\"]*\\\")");
        Matcher m = p.matcher(json);
        List<String> lst = new ArrayList<>();
        while (m.find()) {
            String key = m.group();
            m.find();
            String value = m.group();

            key = GroomString(key);

            value = GroomString(value);

            if (value.startsWith("{")) {
                map.put(key, parseJSonObject(value));
                continue;
            }
            if (value.startsWith("[")) {
                map.put(key, parseArray(value));
                continue;
            }
            map.put(key, value);
        }

        return map;
    }

    private String GroomString(String str) {
        return trimWhitespaces(TrimFirstQuote(RemoveQuotes(str)));
    }

    private String RemoveQuotes(String str) {
        Pattern p = Pattern.compile("^\\\"[^\"\\{\\[]*: |\"$|^:[^:]|^ ");
        return p.matcher(trimWhitespaces(str)).replaceAll("");
    }

    private String TrimFirstQuote(String str) {
        Pattern p = Pattern.compile("^\\\"");
        return p.matcher(str).replaceAll("");
    }

    private String trimWhitespaces(String str) {

        Pattern p = Pattern.compile("^\\s+|\\s+$");
        return p.matcher(str).replaceAll("");
    }

}
