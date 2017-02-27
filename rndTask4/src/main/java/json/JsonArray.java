package json;

import java.util.List;

public class JsonArray {

    List<JsonObject> objects;

    public JsonArray(String input) {
        int objectStart = input.indexOf('{');
        int endOfObject = addObject(input, objectStart);
        while (endOfObject != input.length()) {
            endOfObject = addObject(input, endOfObject);
        }
    }

    /**
     * This method doesn't work properly. So, this class is not present in tests.
     */
    private int addObject(String input, int objectStart) {
        int endOfObject = getObjectEndPosition(input, objectStart);
        this.objects.add(new JsonObject(input.substring(objectStart, endOfObject + 1 )));
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
            if (innerness == 0) {
                return i;
            }
        }
        return -1;
    }
}
