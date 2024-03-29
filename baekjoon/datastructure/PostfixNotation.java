// https://www.acmicpc.net/problem/1918

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class PostfixNotation {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] expr = br.readLine().toCharArray();

		Deque<Character> stack = new ArrayDeque<>(100);
		StringBuilder sb = new StringBuilder();
		for (char c: expr) {
			switch (c) {
				case '*':
				case '/':
					while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
						Character op = stack.pop();
						sb.append(op);
					}
					stack.push(c);
					break;
				case '+':
				case '-':
					while (!stack.isEmpty() && stack.peek() != '(') {
						Character op = stack.pop();
						sb.append(op);
					}
					stack.push(c);
					break;
				case ')':
					while (!stack.isEmpty()) {
						Character op = stack.pop();
						if (op == '(')
							break;
						sb.append(op);
					}
					break;
				case '(':
					stack.push(c);
					break;
				default:
					sb.append(c);
					break;
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb.toString());
		br.close();
	}
}