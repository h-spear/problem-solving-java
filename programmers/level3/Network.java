// https://school.programmers.co.kr/learn/courses/30/lessons/43162?language=java

package programmers.level3;

class Network {
    private void dfs(int[][] computers, boolean[] visited, int n, int x) {
        visited[x] = true;
        for (int i = 0; i < n; ++i) {
            if (computers[x][i] == 0)
                continue;
            if (visited[i])
                continue;
            dfs(computers, visited, n, i);
        }
    }

    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; ++i) {
            if (visited[i])
                continue;
            dfs(computers, visited, n, i);
            answer += 1;
        }

        return answer;
    }
}