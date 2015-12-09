package stackoverflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author v.chibrikov
 */
public class TestRuntime {
    public static void main(String[] args) {
        try {
            Process p = Runtime.getRuntime().exec("test.bat");
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
