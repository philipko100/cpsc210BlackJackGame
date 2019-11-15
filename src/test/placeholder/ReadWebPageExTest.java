package placeholder;

import network.ReadWebPageEx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class ReadWebPageExTest {
    ReadWebPageEx readWeb;

    @BeforeEach
    void beforeEach(){
        readWeb = new ReadWebPageEx();
    }

    /* Connection from site not set up yet
    @Test
    void testSetConnection() {
        try {
            readWeb.setConnection();
            assertEquals("GET", readWeb.connection.getRequestMethod());
        } catch (IOException e) {
            fail();
        }
    }
    */

    @Test
    void testParse() {
        assertEquals("Clear", readWeb.parse("{\"coord\": { \"lon\": 139,\"lat\": 35},\n" +
                    "  \"weather\": [\n" +
                    "    {\n" +
                    "      \"id\": 800,\n" +
                    "      \"main\": \"Clear\",\n" +
                    "      \"description\": \"clear sky\",\n" +
                    "      \"icon\": \"01n\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"base\": \"stations\",\n" +
                    "  \"main\": {\n" +
                    "    \"temp\": 289.92,\n" +
                    "    \"pressure\": 1009,\n" +
                    "    \"humidity\": 92,\n" +
                    "    \"temp_min\": 288.71,\n" +
                    "    \"temp_max\": 290.93\n" +
                    "  },\n" +
                    "  \"wind\": {\n" +
                    "    \"speed\": 0.47,\n" +
                    "    \"deg\": 107.538\n" +
                    "  },\n" +
                    "  \"clouds\": {\n" +
                    "    \"all\": 2\n" +
                    "  },\n" +
                    "  \"dt\": 1560350192,\n" +
                    "  \"sys\": {\n" +
                    "    \"type\": 3,\n" +
                    "    \"id\": 2019346,\n" +
                    "    \"message\": 0.0065,\n" +
                    "    \"country\": \"JP\",\n" +
                    "    \"sunrise\": 1560281377,\n" +
                    "    \"sunset\": 1560333478\n" +
                    "  },\n" +
                    "  \"timezone\": 32400,\n" +
                    "  \"id\": 1851632,\n" +
                    "  \"name\": \"Shuzenji\",\n" +
                    "  \"cod\": 200\n" +
                    "}"));
    }
}
