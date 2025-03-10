// https://www.acmicpc.net/problem/1760

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class N_Rook {

	private static int n, m;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) {
		input();
		System.out.println(solve());
	}

	private static int solve() {
		int count = 0;
		for (int i = 1; i <= n; ++i) {
			++call;
			if (dfs(i))
				++count;
		}
		return count;
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == 0 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static void input() {
		FastIO io = new FastIO();

		int N = io.nextInt();
		int M = io.nextInt();

		int[][] grid = new int[N][M];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				grid[i][j] = io.nextInt();
			}
		}

		int group1 = 0;
		int group2 = 0;
		int[][] indexer1 = new int[N][M];
		int[][] indexer2 = new int[N][M];

		for (int i = 0; i < N; ++i) {
			boolean flag = false;
			for (int j = 0; j < M; ++j) {
				if (grid[i][j] <= 1) {
					if (!flag) {
						flag = true;
						++group1;
					}
					indexer1[i][j] = group1;
				}
				else flag = false;
			}
		}

		for (int j = 0; j < M; ++j) {
			boolean flag = false;
			for (int i = 0; i < N; ++i) {
				if (grid[i][j] <= 1) {
					if (!flag) {
						flag = true;
						++group2;
					}
					indexer2[i][j] = group2;
				}
				else flag = false;
			}
		}

		n = group1;
		m = group2;
		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}
		match = new int[m + 1];
		visited = new int[n + 1];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (grid[i][j] == 0)
					graph.get(indexer1[i][j]).add(indexer2[i][j]);
			}
		}
	}

	private static class FastIO {
		private static final int EOF = -1;

		private static final byte ASCII_dot = 46;
		private static final byte ASCII_minus = 45;
		private static final byte ASCII_space = 32;
		private static final byte ASCII_n = 10;
		private static final byte ASCII_0 = 48;
		private static final byte ASCII_9 = 57;

		private static final byte ASCII_A = 65;
		private static final byte ASCII_Z = 90;
		private static final byte ASCII_a = 97;
		private static final byte ASCII_z = 122;

		private final DataInputStream din;
		private final DataOutputStream dout;

		private byte[] inbuffer;
		private int inbufferpointer, bytesread;
		private byte[] outbuffer;
		private int outbufferpointer;

		private byte[] bytebuffer;

		private FastIO() {
			this.din = new DataInputStream(System.in);
			this.dout = new DataOutputStream(System.out);

			this.inbuffer = new byte[10000];
			this.inbufferpointer = 0;
			this.bytesread = 0;
			this.outbuffer = new byte[20];
			this.outbufferpointer = 0;

			this.bytebuffer = new byte[20];
		}

		private byte read() {
			if (inbufferpointer == bytesread)
				fillBuffer();
			return bytesread == EOF ? EOF : inbuffer[inbufferpointer++];
		}

		private void fillBuffer() {
			try {
				bytesread = din.read(inbuffer, inbufferpointer = 0, inbuffer.length);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		private void write(byte b) {
			if (outbufferpointer == outbuffer.length)
				flushBuffer();
			outbuffer[outbufferpointer++] = b;
		}

		private void flushBuffer() {
			if (outbufferpointer != 0) {
				try {
					dout.write(outbuffer, 0, outbufferpointer);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				outbufferpointer = 0;
			}
		}

		private boolean hasNext() {
			return inbufferpointer == bytesread;
		}

		private char[] convertCharArray(byte[] arr, int length) {
			char[] ret = new char[length];
			for (int i = 0; i < length; i++) {
				ret[i] = (char) arr[i];
			}
			return ret;
		}

		private int nextInt() {
			boolean isMinus = false;
			byte b;

			while(isSpace(b = read()))
				;

			if (b == ASCII_minus) {
				isMinus = true;
				b = read();
			}

			int result = b - '0';
			while (isDigit(b = read()))
				result = result * 10 + b - '0';
			return isMinus ? -result : result;
		}

		private float nextFloat() {
			boolean isMinus = false;

			byte b;
			while(isSpace(b = read()))
				;

			if (b == ASCII_minus) {
				isMinus = true;
				b = read();
			}

			float result = b - '0';
			while (isDigit(b = read()))
				result = result * 10 + b - '0';

			if (b == ASCII_dot) {
				float div = 1;
				while (isDigit(b = read())) {
					result = result * 10 + b - '0';
					div *= 10;
				}
				result /= div;
			}
			return isMinus ? -result : result;
		}

		private char[] nextCharArray() {
			byte b;
			int index = 0;

			while (isChar(b = read()))
				bytebuffer[index++] = b;

			return convertCharArray(bytebuffer, index);
		}

		private String nextString() {
			StringBuilder sb = new StringBuilder();

			byte b;
			while (isChar(b = read())) {
				sb.append((char) b);
			}

			return sb.toString();
		}

		private void println() {
			write(ASCII_n);
		}

		private void print(String s) {
			for (int i = 0; i < s.length(); i++) {
				write((byte) s.charAt(i));
			}
		}

		private void println(String s) {
			print(s);
			write(ASCII_n);
		}

		private void print(int i) {
			if (i == 0) {
				write(ASCII_0);
			} else {
				if (i < 0) {
					write(ASCII_minus);
					i = -i;
				}
				int index = 0;
				while (i > 0) {
					bytebuffer[index++] = (byte) ((i % 10) + ASCII_0);
					i /= 10;
				}
				while (index-- > 0) {
					write(bytebuffer[index]);
				}
			}
		}

		private void println(int i) {
			print(i);
			write(ASCII_n);
		}

		private void printls(int i) {
			print(i);
			write(ASCII_space);
		}

		private void print(long i) {
			if (i == 0) {
				write(ASCII_0);
			} else {
				if (i < 0) {
					write(ASCII_minus);
					i = -i;
				}
				int index = 0;
				while (i > 0) {
					bytebuffer[index++] = (byte) ((i % 10) + ASCII_0);
					i /= 10;
				}
				while (index-- > 0) {
					write(bytebuffer[index]);
				}
			}
		}

		private void println(long i) {
			print(i);
			write(ASCII_n);
		}

		private void printls(long i) {
			print(i);
			write(ASCII_space);
		}

		private boolean isSpace(byte b) {
			return b <= ASCII_space && b > 0;
		}

		private boolean isDigit(byte b) {
			return b >= ASCII_0 && b <= ASCII_9;
		}

		private boolean isChar(byte b) {
			return (ASCII_A <= b && b <= ASCII_Z) || (ASCII_a <= b && b <= ASCII_z);
		}
	}
}
