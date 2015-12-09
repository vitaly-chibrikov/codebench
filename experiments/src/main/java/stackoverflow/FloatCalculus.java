package stackoverflow;

/**
 * @author v.chibrikov
 */
public class FloatCalculus {
    public static void main(String[] args) {
        float base = 0.49f;
        float result = 0f;
        for (int i = 0; i < 1000; i++) {
          result += base;
        }
        System.out.println(result);
    }
}
