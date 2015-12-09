package iteration;

import java.util.Queue;

public class MyFolder<T, U> implements Folder<T, U> {
    public U fold(U u, Queue<T> ts, Function2<T, U, U> function) {
        if (u == null || ts == null || function == null)
            throw new IllegalArgumentException();

        U currentU = u;
        while (ts.peek() != null) {
            currentU = function.apply(ts.poll(), currentU);
        }
        return currentU;
    }
}
