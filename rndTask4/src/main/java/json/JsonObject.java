package json;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonObject {

    private Map<String, String> fields;

    public JsonObject(String input) {
        this.fields = new LinkedHashMap<>();

        int objectStart = input.indexOf('{');
        int endOfObject = addField(input, objectStart);
        while (endOfObject != input.length()) {
            endOfObject = addField(input, endOfObject);
        }
    }

    private int addField(String input, int objectStart) {
        int endOfObject = getObjectEndPosition(input, objectStart);
        int colonPlace = input.indexOf(':', objectStart);
        String field = input.substring(objectStart + 1, colonPlace).trim();
        if (endOfObject == -1) {
            endOfObject = input.length();
        }
        this.fields.put(field, input.substring(colonPlace + 1, endOfObject - 1));
        return endOfObject;
    }

    private int getObjectEndPosition(String input, int objectStart) {
        for (int i = objectStart, innerness = 0; i < input.length(); ++i) {
            char symbol = input.charAt(i);
            if (symbol == '{') {
                innerness++;
            } else if (symbol == '}') {
                innerness--;
            }
            if (innerness == 0 && symbol == ',') {
                return i;
            }
        }
        return -1;
    }

    public String getField(String field) {
        return fields.get(field);
    }
}
