package findcommonancestor;

/**
 * Created by v.chibrikov on 11.12.2014.
 */
public class Main {
    public static void main(String[] args) {
        String[] commits = {"G", "F", "E", "D", "C", "B", "A"};
        String[][] parents ={{"F","D"},{"E"}, {"B"}, {"C"}, {"B"}, {"A"}, null};
        //String[] commits = {"N", "G", "J", "F", "D", "K", "L", "A"};
        //String[][] parents = {{"G", "J"}, {"D", "F"}, {"K", "L"}, {"A"}, {"A"}, {"A"}, {"A"}, null};

        //String[] commits = {"N", "G", "J", "F", "D", "K", "E", "C", "L", "B", "A"};
        //String[][] parents = {{"D", "J"}, {"D", "F"}, {"K"}, {"E"}, {"C"}, {"L"}, {"B"}, {"B"}, {"C"}, {"A"}, null};


        String commit1 = "D";
        String commit2 = "F";

        String result = (new MyFindCommonAncestor().findCommmonAncestor(commits, parents, commit1, commit2));
        System.out.print(result);
    }
}
