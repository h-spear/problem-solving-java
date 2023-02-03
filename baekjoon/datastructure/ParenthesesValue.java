// https://www.acmicpc.net/problem/2504

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class ParenthesesValue {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<String> stack = new Stack<>();

        boolean isValid = true;
        for (int i = 0; i < input.length(); ++i) {
            String s = input.substring(i, i + 1);

            if (s.equals("(") || s.equals("[")) {
                stack.push(s);
            } else if (stack.isEmpty()) {
                isValid = false;
                break;
            } else if (s.equals(")")) {
                int score = 0;
                String item;
                while (stack.isEmpty() == false && stack.peek().equals("(") == false) {
                    item = stack.pop();
                    if (item.equals("[")) {
                        isValid = false;
                        break;
                    } else {
                        score += Integer.parseInt(item);
                    }
                }
                if (isValid == false) {
                    break;
                }
                if (stack.isEmpty()) {
                    isValid = false;
                    break;
                }
                stack.pop();
                if (score == 0) {
                    stack.push("2");
                } else {
                    stack.push(String.valueOf(score * 2));
                }
            } else if (s.equals("]")) {
                int score = 0;
                String item;
                while (stack.isEmpty() == false && stack.peek().equals("[") == false) {
                    item = stack.pop();
                    if (item.equals("(")) {
                        isValid = false;
                        break;
                    } else {
                        score += Integer.parseInt(item);
                    }
                }
                if (isValid == false) {
                    break;
                }
                if (stack.isEmpty()) {
                    isValid = false;
                    break;
                }
                stack.pop();
                if (score == 0) {
                    stack.push("3");
                } else {
                    stack.push(String.valueOf(score * 3));
                }
            }
        }

        if (isValid == false) {
            System.out.println(0);
            return;
        }

        try {
            int answer = 0;
            while (!stack.isEmpty()) {
                answer += Integer.parseInt(stack.pop());
            }
            System.out.println(answer);
        } catch (Exception e) {
            System.out.println(0);
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new ParenthesesValue().solution();
    }
}
