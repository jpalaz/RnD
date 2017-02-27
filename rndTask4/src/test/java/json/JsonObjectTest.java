package json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonObjectTest {

    @Test
    public void parseFile() throws Exception {
        JsonObject object = new JsonObject(input);

        assertEquals(output, object.getField("assets"));
    }

    private String input = "{\n" +
            "\tassets: [\n" +
            "\t\t{\n" +
            "\t\t\ttype: \"book\", name: \"Book 1\", pages: 190\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\ttype: \"book\", name: \"Book 2\", pages: 50\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";

    private String output = " [\n" +
            "\t\t{\n" +
            "\t\t\ttype: \"book\", name: \"Book 1\", pages: 190\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\ttype: \"book\", name: \"Book 2\", pages: 50\n" +
            "\t\t}\n" +
            "\t]\n";
}