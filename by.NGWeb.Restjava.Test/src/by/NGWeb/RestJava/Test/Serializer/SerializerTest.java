package by.NGWeb.RestJava.Test.Serializer;

import SerializerDemoClasses.Example;
import by.NGWeb.RestJava.JSonSerializer.JsonSerializer;
import org.junit.Test;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class SerializerTest {


    @Test
    public void testDeserealize() throws InstantiationException, IllegalAccessException {

        JsonSerializer serializer = new JsonSerializer();
        Example ex =  serializer.Deserealize(Example.class,"{\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"age\": 25,\n" +
                "    \"address\": {\n" +
                "        \"streetAddress\": \"21 2nd Street\",\n" +
                "        \"city\": \"New York\",\n" +
                "        \"state\": \"NY\",\n" +
                "        \"postalCode\": 10021\n" +
                "    },\n" +
                "    \"phoneNumbers\": [\n" +
                "        {\n" +
                "            \"type\": \"home\",\n" +
                "            \"number\": \"212 555-1234\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"fax\",\n" +
                "            \"number\": \"646 555-4567\" \n" +
                "        }\n" +
                "    ] \n" +
                "}");
    }
}


