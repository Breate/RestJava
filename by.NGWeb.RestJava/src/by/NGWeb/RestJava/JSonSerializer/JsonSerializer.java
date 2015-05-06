package by.NGWeb.RestJava.JSonSerializer;

import by.NGWeb.RestJava.JSonSerializer.JsonConverters.*;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 5/6/2015.
 */
public class JsonSerializer {

    public static List<IConverter> converters = new ArrayList<IConverter>();
    static {
        converters.add(new IntConverter());
        converters.add(new FloatConverter());
        converters.add(new DoubleConverter());
        converters.add(new StringConverter());
    }

    public <T> T Deserealize(Class<T> clazz,String json) throws IllegalAccessException, InstantiationException {
     Object obj = parseJSon(json);
        T elem = clazz.newInstance();
        Method m[] = clazz.getMethods();

        for (int i = 0; i < m.length; i++)
            if (m[i].getName().startsWith("set")){
                System.out.println("instanceName."+m[i].getName()+"(\"valOne\");");
            }

        return elem;
    }

    private Object parseJSon(String json) {

        if (json.startsWith("{")&&json.endsWith("}"))
           return parseJSonObject(json);
        if (json.startsWith("[")&&json.endsWith("]"))
            parseArray(json);
       return json;

    }

    private Object parseArray(String json) {
        Pattern p = Pattern.compile("[ :]+((?=\\[)\\[[^]]*\\]|(?=\\{)\\{[^\\}]*\\}|(?=[0-9])[^,]*|(?=\\\")\\\"[^\"]*\\\")");
        Matcher m =  p.matcher(json);
        List<Object> lst = new ArrayList<>();
        while (m.find()) {
            String value = m.group();

            if(value.startsWith("{")){
                lst.add(parseJSonObject(value));
                continue;
            }
            if(value.startsWith("[")) {
                lst.add(parseArray(value));
                continue;
            }
            lst.add(value);
        }
        return lst;
    }

    private Object parseJSonObject(String json) {
        HashMap map = new HashMap<String,Object>();

        Pattern p = Pattern.compile("[ :]+((?=\\[)\\[[^]]*\\]|(?=\\{)\\{[^\\}]*\\}|(?=[0-9])[^,]*|(?=\\\")\\\"[^\"]*\\\")");
        Matcher m =  p.matcher(json);
        List<String> lst = new ArrayList<>();
        while (m.find()) {
             String key = m.group();
             m.find();
             String value = m.group();

            if(value.startsWith("{")){
                map.put(key, parseJSonObject(value));
                continue;
            }
            if(value.startsWith("[")){
                map.put(key, parseArray(value));
                continue;
            }
            map.put(key, value);
        }

        return map;
    }

}
