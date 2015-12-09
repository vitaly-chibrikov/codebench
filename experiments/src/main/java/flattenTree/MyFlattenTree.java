package flattenTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MyFlattenTree<T> implements FlattenTree<T> {
    public List<T> flattenInOrder(Tree<T> tree) {
        if (tree == null)
            throw new IllegalArgumentException();

        final LinkedList<T> flattenTree = new LinkedList<T>();
        final Stack<Tree<T>> traverseQueue = new Stack<Tree<T>>();
        traverseQueue.add(tree);
        while (!traverseQueue.isEmpty()) {
            Either<T, Triple<Tree<T>>> either = traverseQueue.pop().get();
            if (either.isLeft()) {
                either.ifLeft(new Function<T, T>() {
                    @Override
                    public T apply(T leaf) {
                        flattenTree.add(leaf);
                        return leaf;
                    }
                });
            } else {
                either.ifRight(new Function<Triple<Tree<T>>, Triple<Tree<T>>>() {
                    @Override
                    public Triple<Tree<T>> apply(Triple<Tree<T>> triple) {
                        traverseQueue.add(triple.right());
                        traverseQueue.add(triple.middle());
                        traverseQueue.add(triple.left());
                        return triple;
                    }
                });
            }
        }
        return flattenTree;
    }
}
