package SerializerDemoClasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Siarhei Shchypanau on 5/6/2015.
 */
public class PhoneNumber {

    private String type;
    private String number;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The number
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * @param number
     * The number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}