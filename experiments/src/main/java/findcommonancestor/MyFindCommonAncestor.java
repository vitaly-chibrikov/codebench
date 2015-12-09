package findcommonancestor;

import java.util.*;

public class MyFindCommonAncestor implements FindCommonAncestor {
    public String findCommmonAncestor(String[] commitHashes,
                                      String[][] parentHashes,
                                      String commitHash1,
                                      String commitHash2) {
        if (commitHash1 == null || commitHash2 == null || commitHashes == null || parentHashes == null) {
            throw new IllegalArgumentException();
        }

        if (commitHash1.equals(commitHash2)) {
            return commitHash1;
        }

        //O(N)
        final Map<String, String[]> hashToAncestors = getHashesToAncestors(commitHashes, parentHashes);

        //O(N)
        final Set<String> hash1ancestors = new HashSet<String>();
        traverse(commitHash1, hashToAncestors, new Function() {
            @Override
            public String apply(String hash) {
                hash1ancestors.add(hash);
                return null;
            }
        });

        //O(N)
        return traverse(commitHash2, hashToAncestors, new Function() {
            @Override
            public String apply(String hash) {
                if (hash1ancestors.contains(hash)) {
                    return hash;
                }
                return null;
            }
        });
    }

    public interface Function {
        String apply(String hash);
    }

    private String traverse(String startHash, Map<String, String[]> hashToAncestors, Function function) {
        Queue<String> traversalQueue = new LinkedList<String>();
        traversalQueue.add(startHash);
        while (!traversalQueue.isEmpty()) {
            String hash = traversalQueue.poll();

            String common = function.apply(hash);
            if (common != null) {
                return common;
            }

            String[] ancestors = hashToAncestors.get(hash);
            if (ancestors != null)
                Collections.addAll(traversalQueue, ancestors);
        }
        return null;
    }

    private Map<String, String[]> getHashesToAncestors(String[] commitHashes, String[][] parentHashes) {
        Map<String, String[]> hashToAncestors = new HashMap<String, String[]>();
        for (int i = 0; i < commitHashes.length; ++i) {
            hashToAncestors.put(commitHashes[i], parentHashes[i]);
        }
        return hashToAncestors;
    }
}
