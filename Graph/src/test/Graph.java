package test;

import java.util.*;


public final class Graph {
    private String[] vertexes;
    private int count;
    private HashMap<String, Integer> indexMap;
    private boolean[][] matrix;
    private ArrayList<String[]> verArr;
    private ArrayList<boolean[][]> matArr;
    private boolean isSetMatrix;

    public Graph(final int size) {
        this.vertexes = new String[size];
        this.indexMap = new HashMap<>(size);
        this.matrix = new boolean[size][size];
    }

    public void addVertex(final String vertex) {
        assert (vertex != null);

        vertexes[count] = vertex;
        indexMap.put(vertex, count++);
    }

    public void setEdge(final String vertex, final int count, final String... vertexes) {
        assert (count == vertexes.length);

        int iU = indexMap.get(vertex);

        for (final String v : vertexes) {
            assert (indexMap.containsKey(v));

            int iV = indexMap.get(v);

            matrix[iU][iV] = true;
            matrix[iV][iU] = true;
        }
    }

    public void setMatrixes() {
        verArr = new ArrayList<>();
        matArr = new ArrayList<>();

        setMatrixesRecursive(0);

        isSetMatrix = true;
    }

    private void setMatrixesRecursive(final int depth) {
        if (depth == vertexes.length) {
            verArr.add(vertexes.clone());
            boolean[][] copy = new boolean[count][count];
            for (int iC = 0; iC < count; ++iC) {
                for (int iR = 0; iR < count; ++iR) {
                    copy[iC][iR] = matrix[iC][iR];
                }
            }
            matArr.add(copy);
            return;
        }

        for (int i = depth; i < vertexes.length; ++i) {
            swap(depth, i);
            setMatrixesRecursive(depth + 1);
            swap(depth, i);
        }
    }

    private void swap(final int iA, final int iB) {
        String tempV = vertexes[iA];
        vertexes[iA] = vertexes[iB];
        vertexes[iB] = tempV;

        for (int iCol = 0; iCol < vertexes.length; ++iCol) {
            boolean temp = matrix[iCol][iA];
            matrix[iCol][iA] = matrix[iCol][iB];
            matrix[iCol][iB] = temp;
        }

        for (int iRow = 0; iRow < vertexes.length; ++iRow) {
            boolean temp = matrix[iA][iRow];
            matrix[iA][iRow] = matrix[iB][iRow];
            matrix[iB][iRow] = temp;
        }
    }

    public void printBfs(final String start) {
        assert (isSetMatrix) : "You first set matrix!!";
        assert (start != null && verArr != null && matArr != null);

        System.out.println(String.format("Start: %s, BFS", start));

        SortedSet<String> bfsSet = new TreeSet<>();

        for (int iA = 0; iA < verArr.size(); ++iA) {
            String[] tVertexes = verArr.get(iA);
            boolean[][] tMatrix = matArr.get(iA);
            HashMap<String, Integer> tIndexMap = new HashMap<>(count);

            for (int iV = 0; iV < count; ++iV) {
                tIndexMap.put(tVertexes[iV], iV);
            }

            boolean[] discovered = new boolean[count];
            ArrayList<String> bfsList = new ArrayList<>(count);
            Queue<Integer> queue = new ArrayDeque<>(count);

            bfsList.add(start);
            queue.add(tIndexMap.get(start));
            discovered[tIndexMap.get(start)] = true;

            while (!!!queue.isEmpty()) {
                int next = queue.poll();

                for (int i = 0; i < count; ++i) {
                    if (!!!discovered[i] && tMatrix[next][i]) {
                        bfsList.add(tVertexes[i]);
                        queue.add(i);
                        discovered[i] = true;
                    }
                }
            }

            bfsSet.add(String.join(" ", bfsList));
        }

        for (final String bfs : bfsSet) {
            System.out.println(bfs);
        }
    }

    public void printDfsPreOrder(final String start) {
        assert (isSetMatrix) : "You first set matrix!!";
        assert (start != null && verArr != null && matArr != null);

        System.out.println(String.format("Start: %s, DFS - PreOrder", start));

        SortedSet<String> dfsSet = new TreeSet<>();

        for (int iA = 0; iA < verArr.size(); ++iA) {
            String[] tVertexes = verArr.get(iA);
            boolean[][] tMatrix = matArr.get(iA);
            HashMap<String, Integer> tIndexMap = new HashMap<>(count);

            for (int iV = 0; iV < count; ++iV) {
                tIndexMap.put(tVertexes[iV], iV);
            }

            ArrayList<String> dfsList = new ArrayList<>(count);
            boolean[] discovered = new boolean[count];

            dfsPreOrderRecursive(tIndexMap.get(start), tVertexes, tMatrix, tIndexMap, discovered, dfsList);

            dfsSet.add(String.join(" ", dfsList));
        }

        for (final String dfs : dfsSet) {
            System.out.println(dfs);
        }
    }

    private void dfsPreOrderRecursive(int index, String[] tempVertexes, boolean[][] matrix,
                                      HashMap<String, Integer> map, boolean[] discovered, ArrayList<String> dfsList) {
        discovered[index] = true;

        dfsList.add(tempVertexes[index]);

        for (int i = 0; i < count; ++i) {
            if (!!!discovered[i] && matrix[index][i]) {
                dfsPreOrderRecursive(i, tempVertexes, matrix, map, discovered, dfsList);
            }
        }
    }

    public void printDfsPostOrder(final String start) {
        assert (isSetMatrix) : "You first set matrix!!";
        assert (start != null && verArr != null && matArr != null);

        assert (start != null && verArr != null && matArr != null);

        System.out.println(String.format("Start: %s, DFS - PostOrder", start));

        SortedSet<String> dfsSet = new TreeSet<>();

        for (int iA = 0; iA < verArr.size(); ++iA) {
            String[] tVertexes = verArr.get(iA);
            boolean[][] tMatrix = matArr.get(iA);
            HashMap<String, Integer> tIndexMap = new HashMap<>(count);

            for (int iV = 0; iV < count; ++iV) {
                tIndexMap.put(tVertexes[iV], iV);
            }

            ArrayList<String> dfsList = new ArrayList<>(count);
            boolean[] discovered = new boolean[count];

            dfsPostOrderRecursive(tIndexMap.get(start), tVertexes, tMatrix, tIndexMap, discovered, dfsList);

            dfsSet.add(String.join(" ", dfsList));
        }

        for (final String dfs : dfsSet) {
            System.out.println(dfs);
        }
    }

    private void dfsPostOrderRecursive(int index, String[] tempVertexes, boolean[][] matrix,
                                       HashMap<String, Integer> map, boolean[] discovered, ArrayList<String> dfsList) {
        discovered[index] = true;

        for (int i = 0; i < count; ++i) {
            if (!!!discovered[i] && matrix[index][i]) {
                dfsPostOrderRecursive(i, tempVertexes, matrix, map, discovered, dfsList);
            }
        }

        dfsList.add(tempVertexes[index]);
    }
}