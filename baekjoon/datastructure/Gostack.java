// https://www.acmicpc.net/problem/3425

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Gostack {

    StringTokenizer st;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N;
        long V;
        String input;
        boolean running = true;

        while (running) {
            List<String> program = new LinkedList<>();
            while (true) {
                input = br.readLine();
                if (input.equals("END")) {
                    break;
                } else if (input.equals("QUIT")) {
                    running = false;
                    break;
                } else {
                    program.add(input);
                }
            }

            if (running) {
                N = Integer.parseInt(br.readLine());
                for (int i = 0; i < N; ++i) {
                    V = Integer.parseInt(br.readLine());
                    try {
                        long result = execute(program, V);
                        bw.write("" + result);
                        bw.newLine();
                    } catch (Exception e) {
                        bw.write("ERROR");
                        bw.newLine();
                    }
                }
                br.readLine();
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private long execute(List<String> program, long v) throws Exception {
        List<Long> stack = new ArrayList<>(1001);
        stack.add(v);

        for (String input: program) {
            st = new StringTokenizer(input);
            String cmd = st.nextToken();

            if (cmd.equals("NUM")) {
                long X = Integer.parseInt(st.nextToken());
                stack.add(X);
            } else if (cmd.equals("POP")) {
                stack.remove(stack.size() - 1);
            } else if (cmd.equals("INV")) {
                stack.add(-stack.remove(stack.size() - 1));
            } else if (cmd.equals("DUP")) {
                stack.add(stack.get(stack.size() - 1).longValue());
            } else if (cmd.equals("SWP")) {
                long one = stack.remove(stack.size() - 1);
                long two = stack.remove(stack.size() - 1);
                stack.add(one);
                stack.add(two);
            } else if (cmd.equals("ADD")) {
                long one = stack.remove(stack.size() - 1);
                long two = stack.remove(stack.size() - 1);
                stack.add(one + two);
            } else if (cmd.equals("SUB")) {
                long one = stack.remove(stack.size() - 1);
                long two = stack.remove(stack.size() - 1);
                stack.add(two - one);
            } else if (cmd.equals("MUL")) {
                long one = stack.remove(stack.size() - 1);
                long two = stack.remove(stack.size() - 1);
                stack.add(one * two);
            } else if (cmd.equals("DIV")) {
                long one = stack.remove(stack.size() - 1);
                long two = stack.remove(stack.size() - 1);
                stack.add(divMod(two, one)[0]);
            } else if (cmd.equals("MOD")) {
                long one = stack.remove(stack.size() - 1);
                long two = stack.remove(stack.size() - 1);
                stack.add(divMod(two, one)[1]);
            }
        }
        if (stack.size() != 1 || Math.abs(stack.get(0)) > 1e9) {
            throw new Exception();
        }
        return stack.remove(0);
    }

    private long[] divMod(long a, long b) {
        long q = Math.abs(a) / Math.abs(b);
        long r = Math.abs(a) - Math.abs(b) * q;
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
            q *= -1;
        }
        if (a < 0) {
            r *= -1;
        }
        return new long[]{q, r};
    }

    public static void main(String[] args) throws Exception {
        new Gostack().solution();
    }
}
