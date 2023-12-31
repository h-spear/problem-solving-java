// https://www.acmicpc.net/problem/27500

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class WhoIsTopInThisGame {

	private static final String ATTACK = "attack";
	private static final String COUNTER_STRIKE = "counter strike";
	private static final String RIPOSTE = "riposte";

	private static final int FIORA_GUARD = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int hp1 = Integer.parseInt(st.nextToken());
		int hp2 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int d1 = Integer.parseInt(st.nextToken());
		int d2 = Integer.parseInt(st.nextToken());
		int d3 = Integer.parseInt(st.nextToken());
		int d4 = Integer.parseInt(st.nextToken());

		// x = -1 : guard
		// x > 0  : attack damage
		int[] fioraAction = new int[333];

		int m = Integer.parseInt(br.readLine());
		while (m-- > 0){
			st = new StringTokenizer(br.readLine());
			int frame = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			if (name.equals(ATTACK)) {
				fioraAction[frame + 4] = d3;
			} else {
				for (int j = 0; j < 9; ++j) {
					fioraAction[frame + j] = FIORA_GUARD;
				}
				fioraAction[frame + 9] = d4;
			}
		}

		// dp element : prev status
		Status[][][] dp = new Status[333][301][301];
		dp[0][hp1][hp2] = new Status();
		for (int frame = 0; frame < 301; ++frame) {
			for (int jaxHp = 1; jaxHp <= hp1; ++jaxHp) {
				for (int fioraHp = 1; fioraHp <= hp2; ++fioraHp) {
					if (dp[frame][jaxHp][fioraHp] == null)
						continue;

					int damaged, nextJaxHp, nextFioraHp;

					// no action
					damaged = getFioraAttackDamage(fioraAction, frame, frame + 1);
					nextJaxHp = Math.max(jaxHp - damaged, 0);
					nextFioraHp = fioraHp;
					dp[frame + 1][nextJaxHp][nextFioraHp] = new Status(frame, jaxHp, fioraHp, Status.NO_ACTION);

					// attack
					damaged = getFioraAttackDamage(fioraAction, frame, frame + 5);
					nextJaxHp = Math.max(jaxHp - damaged, 0);
					nextFioraHp = Math.max(fioraHp - (fioraAction[frame + 4] != -1 ? d1 : 0), 0);
					dp[frame + 5][nextJaxHp][nextFioraHp] = new Status(frame, jaxHp, fioraHp, Status.ATTACK);

					// counterstrike
					damaged = getFioraAttackDamage(fioraAction, frame + 14, frame + 15);
					nextJaxHp = Math.max(jaxHp - damaged, 0);
					nextFioraHp = Math.max(fioraHp - (fioraAction[frame + 14] != -1 ? d2 : 0), 0);
					dp[frame + 15][nextJaxHp][nextFioraHp] = new Status(frame, jaxHp, fioraHp, Status.COUNTER_STRIKE);
				}
			}
		}

		int bestFrame = -1;
		int bestJaxHp = -1;
		for (int frame = 0; frame < 302; ++frame) {
			for (int jaxHp = hp1; jaxHp > 0; --jaxHp) {
				if (dp[frame][jaxHp][0] != null) {
					bestFrame = frame;
					bestJaxHp = jaxHp;
					frame = 301;
					break;
				}
			}
		}

		if (bestFrame == -1) {
			System.out.println("NO");
		} else {
			Deque<String> stack = new ArrayDeque<>();
			Status status = new Status(bestFrame, bestJaxHp, 0, Status.NO_ACTION);

			while (status.frame > 0) {
				Status prevStatus = dp[status.frame][status.hp1][status.hp2];
				switch (prevStatus.type) {
					case Status.ATTACK:
						stack.push(prevStatus.frame + " " + ATTACK); break;
					case Status.COUNTER_STRIKE:
						stack.push(prevStatus.frame + " " + COUNTER_STRIKE); break;
					default:
						break;
				}
				status = prevStatus;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("YES\n");
			while (!stack.isEmpty()) {
				sb.append(stack.pop()).append("\n");
			}
			System.out.println(sb.toString());
		}
		br.close();
	}

	private static int getFioraAttackDamage(int[] fioraAction, int s, int e) {
		int res = 0;
		for (int i = s; i < e; ++i) {
			res += Math.max(0, fioraAction[i]);
		}
		return res;
	}

	private static class Status {
		static final int NO_ACTION = 0;
		static final int ATTACK = 1;
		static final int COUNTER_STRIKE = 2;

		int hp1, hp2, frame, type;

		Status() {}

		Status(int frame, int hp1, int hp2, int type) {
			this.frame = frame;
			this.hp1 = hp1;
			this.hp2 = hp2;
			this.type = type;
		}
	}
}