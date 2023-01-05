// https://www.acmicpc.net/problem/23290
// **** 

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class WizardSharkAndClone {

    private static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static final int[] sdx = {-1, 0, 1, 0};
    private static final int[] sdy = {0, -1, 0, 1};
    private static final List<Pair> pairs = new LinkedList<>();
    private static boolean[][] visited = new boolean[4][4];

    class Pair {
        int count;
        String path;

        public Pair(int count, String path) {
            this.count = count;
            this.path = path;
        }
    }

    class Shark {
        int x;
        int y;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Queue<Integer>[][] cloningMagicCasting(Queue<Integer>[][] graph) {
        Queue<Integer>[][] cloned = makeGraph();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int d: graph[i][j])
                    cloned[i][j].add(d);
            }
        }
        return cloned;
    }

    private void movingFish(Queue<Integer>[][] graph, int[][] smell, Shark shark) {
        boolean move;
        int nx, ny, d;
        Queue<Integer>[][] temp = makeGraph();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                while (!graph[i][j].isEmpty()) {
                    d = graph[i][j].poll();
                    move = false;

                    for (int k = 0; k < 8; ++k) {
                        nx = i + dx[(d - k + 8) % 8];
                        ny = j + dy[(d - k + 8) % 8];

                        if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                            continue;
                        if (smell[nx][ny] > 0)
                            continue;
                        if (nx == shark.x && ny == shark.y)
                            continue;

                        temp[nx][ny].add((d - k + 8) % 8);
                        move = true;
                        break;
                    }
                    if (!move)
                        temp[i][j].add(d);
                }
            }
        }

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                graph[i][j] = temp[i][j];
            }
        }
    }

    private void dfs(Queue<Integer>[][] graph, int sx, int sy, int depth, String path, int count) {
        if (depth == 3) {
            pairs.add(new Pair(count, path));
            return;
        }

        int nx, ny;
        for (int i = 0; i < 4; ++i) {
            nx = sx + sdx[i];
            ny = sy + sdy[i];

            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                continue;

            // **** 상어가 방문한 곳을 또 방문할 수 있음
            // 단, 물고기 수는 카운트하지 않음.
            if (visited[nx][ny]) {
                dfs(graph, nx, ny, depth + 1, path + i, count);
                continue;
            } else {
                visited[nx][ny] = true;
                dfs(graph, nx, ny, depth + 1, path + i, count + graph[nx][ny].size());
                visited[nx][ny] = false;
            }
        }
    }

    private String getPathString(Queue<Integer>[][] graph, Shark shark) {
        // find path
        pairs.clear();
        dfs(graph, shark.x, shark.y, 0, "", 0);
        pairs.sort((a, b) -> {
            int diff = b.count - a.count;
            if (diff > 0) {
                return 1;
            } else if (diff == 0) {
                return a.path.compareTo(b.path);
            } else {
                return -1;
            }
        });

        return pairs.get(0).path;
    }

    private Shark movingShark(Queue<Integer>[][] graph, int[][] smell, Shark shark) {
        int x = shark.x;
        int y = shark.y;

        // move
        String path = getPathString(graph, shark);
        for (int i = 0; i < 3; ++i) {
            int dir = Integer.parseInt(path.substring(i, i + 1));
            x += sdx[dir];
            y += sdy[dir];

            if (!graph[x][y].isEmpty()) {
                smell[x][y] = 3;
                graph[x][y].clear();
            }
        }
        return new Shark(x, y);
    }

    private void removeSmell(int[][] smell) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (smell[i][j] > 0)
                    --smell[i][j];
            }
        }
    }

    private Queue<Integer>[][] makeGraph() {
        Queue<Integer>[][] graph = new LinkedList[4][4];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                graph[i][j] = new LinkedList<>();
            }
        }
        return graph;
    }

    private void cloningMagic(Queue<Integer>[][] graph, Queue<Integer>[][] cloned) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                while (!cloned[i][j].isEmpty()) {
                    graph[i][j].add(cloned[i][j].poll());
                }
            }
        }
    }

    private int getTotalFishes(Queue<Integer>[][] graph) {
        int fishes = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                fishes += graph[i][j].size();
            }
        }
        return fishes;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Queue<Integer>[][] graph = makeGraph();

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            graph[x][y].add(d);
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;
        Shark shark = new Shark(sx, sy);

        int[][] smell = new int[4][4];

        for (int i = 0; i < s; ++i) {
            Queue<Integer>[][] cloned = cloningMagicCasting(graph);
            movingFish(graph, smell, shark);
            shark = movingShark(graph, smell, shark);
            removeSmell(smell);
            cloningMagic(graph, cloned);
        }

        System.out.println(getTotalFishes(graph));
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new WizardSharkAndClone().solution();
    }
}
