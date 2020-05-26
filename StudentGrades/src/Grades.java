import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Grades {

    public static Map<String, Integer> results = new HashMap<>();


    /**
     * check whether mark is valid
     * for later fill
     * @param type
     * @param mark
     * @throws Exception
     */
    public static void putMark(String type, int mark) throws Exception{
        Set<String> keys = results.keySet();
        for (String k: keys) {
            if (type.equals(k)) {
                int range = results.get(k);
                if (mark < 0 || mark > range) {
                    throw new InvalidMarkException("Invalid mark");
                } else {
                    results.put(type, range);
                }
            }
        }

    }

    public Grades(String path) {
        results = getKeyValue(path);
    }

    /**
     * to fill result with json file
     * @return
     */
    public static Map<String, Integer> getKeyValue(String path) {

        System.out.println(path);
        try {
            JsonObject root = ((JsonObject) Jsoner.deserialize(new FileReader(path)));
            for (String key : root.keySet()) {
                results.put(key, ((BigDecimal) root.get(key)).intValue());
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}

