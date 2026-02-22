import java.util.*;

public class InputParser {
    public Map<String, String> parse(String raw) {
        Map<String, String> fields = new LinkedHashMap<>();
        for (String part : raw.split(";")) {
            String[] kv = part.split("=", 2);
            if (kv.length == 2) {
                fields.put(kv[0].trim(), kv[1].trim());
            }
        }
        return fields;
    }
}
