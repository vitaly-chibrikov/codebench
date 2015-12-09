package flattenTree;

import java.util.List;

/**
 * Created by v.chibrikov on 11.12.2014.
 */
public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree.Node<Integer>(
                new Tree.Node<Integer>(
                        new Tree.Leaf<Integer>(1),
                        new Tree.Leaf<Integer>(2),
                        new Tree.Leaf<Integer>(3)
                ),
                new Tree.Node<Integer>(
                        new Tree.Leaf<Integer>(4),
                        new Tree.Leaf<Integer>(5),
                        new Tree.Node<Integer>(
                                new Tree.Leaf<Integer>(6),
                                new Tree.Leaf<Integer>(7),
                                new Tree.Leaf<Integer>(8)
                        )
                ),
                new Tree.Leaf<Integer>(9)
        );

        List<Integer> result = (new MyFlattenTree<Integer>()).flattenInOrder(tree);
        System.out.print(result.size());
    }
}
