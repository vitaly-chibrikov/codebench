package tp;

/**
 * @author v.chibrikov
 */
public class RPS {
    public static void main(String[] args) {
        String firstPlayerAction = "rock";
        String secondPlayerAction = "paper";
        Palm first = Palm.fromString(firstPlayerAction);
        Palm second = Palm.fromString(secondPlayerAction);

        System.out.println(first.fight(second));
    }
}
