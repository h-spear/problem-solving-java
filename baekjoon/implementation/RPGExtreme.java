package baekjoon.implementation;// https://www.acmicpc.net/problem/17081

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RPGExtreme {

	public static void main(String[] args) throws Exception {
		Table table = new Table();
		table.input();
		table.simul();
		System.out.println(table);
	}

	public static class Table {

		private int passedTurns;
		private Map map;
		private Player player;
		private String reason;

		public void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] grid = new char[N][M];

			int K = 0;
			int L = 0;
			for (int i = 0; i < N; ++i) {
				String line = br.readLine();
				for (int j = 0; j < M; ++j) {
					grid[i][j] = line.charAt(j);
					if (grid[i][j] == '&' || grid[i][j] == 'M') {
						++K;
					} else if (grid[i][j] == 'B') {
						++L;
					} else if (grid[i][j] == '@') {
						this.player = new Player(i, j);
						grid[i][j] = '.';
					}
				}
			}

			this.player.setCommand(br.readLine());

			String[] monsterInfos = new String[K];
			for (int i = 0; i < K; ++i) {
				monsterInfos[i] = br.readLine();
			}

			String[] itemInfos = new String[L];
			for (int i = 0; i < L; ++i) {
				itemInfos[i] = br.readLine();
			}

			this.map = new Map(N, M, grid, player, monsterInfos, itemInfos);
			br.close();
		}

		public void simul() {
			passedTurns = 0;
			reason = Messages.CONTINUE;
			try {
				for (char c: player.command.toCharArray()) {
					++passedTurns;
					player.move(c);
				}
			} catch (Exceptions.GameEndException e) {
				reason = e.getMessage();
			}
		}

		@Override
		public String toString() {
			return map.toString() + "\n"
				+ String.format(Messages.TURN_PRINT_FORMAT, passedTurns) + "\n"
				+ player.toString() + "\n"
				+ reason;
		}

		private static class Map {
			final int n, m;
			final char[][] grid;
			final Monster[][] monsterInfo;
			final Item[][] itemInfo;
			final Player player;

			Map(int n, int m, char[][] grid, Player player, String[] monsterInfos, String[] itemInfos) {
				this.n = n;
				this.m = m;
				this.grid = grid;
				this.player = player;
				this.player.map = this;
				this.monsterInfo = getMonsterMap(n, m, grid, monsterInfos);
				this.itemInfo = getItemMap(n, m, grid, itemInfos);
			}

			private static Monster[][] getMonsterMap(int n, int m, char[][] grid, String[] monsterInfos) {
				StringTokenizer st;
				Monster[][] monster = new Monster[n][m];
				for (String monsterInfo: monsterInfos) {
					st = new StringTokenizer(monsterInfo);
					int R = Integer.parseInt(st.nextToken()) - 1;
					int C = Integer.parseInt(st.nextToken()) - 1;
					String S = st.nextToken();
					int W = Integer.parseInt(st.nextToken());
					int A = Integer.parseInt(st.nextToken());
					int H = Integer.parseInt(st.nextToken());
					int E = Integer.parseInt(st.nextToken());
					monster[R][C] = new Monster(S, W, A, H, E, grid[R][C] == 'M');
				}
				return monster;
			}

			private static Item[][] getItemMap(int n, int m, char[][] grid, String[] itemInfos) {
				StringTokenizer st;
				Item[][] item = new Item[n][m];
				for (String itemInfo: itemInfos) {
					st = new StringTokenizer(itemInfo);
					int R = Integer.parseInt(st.nextToken()) - 1;
					int C = Integer.parseInt(st.nextToken()) - 1;
					char T = st.nextToken().charAt(0);
					if (T == 'W') {
						int attack = Integer.parseInt(st.nextToken());
						item[R][C] = Item.Weapon.of(attack);
					} else if (T == 'A') {
						int defense = Integer.parseInt(st.nextToken());
						item[R][C] = Item.Armor.of(defense);
					} else {
						String type = st.nextToken();
						item[R][C] = Item.Accessory.of(type);
					}
				}
				return item;
			}

			void removeMonster(int r, int c) {
				grid[r][c] = '.';
				monsterInfo[r][c] = null;
			}

			void removeItem(int r, int c) {
				grid[r][c] = '.';
				itemInfo[r][c] = null;
			}

			boolean isWall(int r, int c) {
				return grid[r][c] == '#';
			}

			boolean isValid(int r, int c) {
				return 0 <= r && r < n && 0 <= c && c < m;
			}

			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < m; ++j) {
						sb.append((player.isAlive() && i == player.r && j == player.c) ? '@' : grid[i][j]);
					}
					sb.append("\n");
				}
				sb.setLength(sb.length() - 1);
				return sb.toString();
			}
		}

		private static class Player {
			int r, c;
			int lv, hp, att, def, exp;
			int maxHp;
			final int initialR, initialC;
			Item.Weapon weapon;
			Item.Armor armor;
			Item.Accessory[] accessories = new Item.Accessory[4];
			int countOfAccessory;
			String command;
			Map map;

			Player(int r, int c) {
				this.initialR = this.r = r;
				this.initialC = this.c = c;
				this.maxHp = this.hp = 20;
				this.lv = 1;
				this.att = 2;
				this.def = 2;
				this.exp = 0;
			}

			void move(char dir) {
				int nr = this.r;
				int nc = this.c;
				if (dir == 'L') --nc;
				else if (dir == 'R') ++nc;
				else if (dir == 'U') --nr;
				else if (dir == 'D') ++nr;

				if (!map.isValid(nr, nc) || map.isWall(nr, nc)) {
					nr = this.r;
					nc = this.c;
				}

				char c = map.grid[nr][nc];
				boolean catchBoss = false;
				try {
					if (c == '^') {
						this.stepSpikeTrap();
					} else if (c == '&' || c == 'M') {
						Monster monster = map.monsterInfo[nr][nc];
						this.battleWith(monster);
						map.removeMonster(nr, nc);
						if (monster.isBoss) {
							catchBoss = true;
						}
					} else if (c == 'B') {
						Item item = map.itemInfo[nr][nc];
						this.getItem(item);
						map.removeItem(nr, nc);
					}
					this.r = nr;
					this.c = nc;
					if (catchBoss) {
						throw new Exceptions.GameEndException(Messages.WIN);
					}
				} catch (Exceptions.PlayerReincarnationException e) {
					hillFullHp();
					this.r = initialR;
					this.c = initialC;
				}
			}

			boolean isAlive() {
				return this.hp > 0;
			}

			void increaseHp(int hp) {
				this.hp = Math.min(this.maxHp, this.hp + hp);
			}

			void hillFullHp() {
				this.hp = this.maxHp;
			}

			/**
			 * if true, player die.
			 */
			boolean decreaseHp(int hp) {
				this.hp = Math.max(0, this.hp - hp);
				if (this.hp == 0 && hasAccessoryType(Item.Accessory.Type.REINCARNATION)) {
					removeAccessory(Item.Accessory.Type.REINCARNATION);
					throw new Exceptions.PlayerReincarnationException();
				}
				return this.hp == 0;
			}

			void stepSpikeTrap() {
				int damaged = 5;
				if (hasAccessoryType(Item.Accessory.Type.DEXTERITY)) {
					damaged = 1;
				}
				boolean died = decreaseHp(damaged);
				if (died)
					throw new Exceptions.GameEndException(
						String.format(Messages.KILLED_BY, "SPIKE TRAP")
					);
			}

			void battleWith(Monster monster) {
				try {
					boolean firstAttack = hasAccessoryType(Item.Accessory.Type.COURAGE);
					boolean firstDamaged = false;
					if (monster.isBoss && hasAccessoryType(Item.Accessory.Type.HUNTER)) {
						firstDamaged = true;
						hillFullHp();
					}
					while (this.hp > 0 && monster.hp > 0) {
						int attack = Math.max(1, getAttack(firstAttack) - monster.def);
						monster.decreaseHp(attack);

						int damaged = Math.max(1, monster.att - getDefense());
						if (firstDamaged) {
							damaged = 0;
							firstDamaged = false;
						}
						boolean died = decreaseHp(damaged);
						if (died)
							throw new Exceptions.GameEndException(
								String.format(Messages.KILLED_BY, monster.name)
							);
						if (firstAttack) firstAttack = false;
					}
				} catch (Exceptions.MonsterDieException e) {
					increaseExp(monster.exp);
					if (hasAccessoryType(Item.Accessory.Type.HP_REGENERATION)) {
						increaseHp(3);
					}
				} catch (Exceptions.PlayerReincarnationException e) {
					monster.hillFullHp();
					throw new Exceptions.PlayerReincarnationException();
				}
			}

			void getItem(Item item) {
				int type = Item.typeOf(item);
				if (type == Item.TYPE_WEAPON) {
					weapon = (Item.Weapon) item;
				} else if (type == Item.TYPE_ARMOR) {
					armor = (Item.Armor) item;
				} else if (type == Item.TYPE_ACCESSORY) {
					Item.Accessory accessory = (Item.Accessory) item;
					if (!hasAccessoryType(accessory.type) && countOfAccessory < 4) {
						accessories[countOfAccessory++] = accessory;
					}
				}
			}

			boolean hasAccessoryType(Item.Accessory.Type type) {
				for (int i = 0; i < countOfAccessory; ++i) {
					if (accessories[i].type == type) {
						return true;
					}
				}
				return false;
			}

			void removeAccessory(Item.Accessory.Type type) {
				if (countOfAccessory <= 0)
					return;

				for (int i = 0; i < countOfAccessory; ++i) {
					if (accessories[i].type == type) {
						accessories[i] = accessories[--countOfAccessory];
						break;
					}
				}
			}

			void increaseExp(int exp) {
				if (hasAccessoryType(Item.Accessory.Type.EXPERIENCE)) {
					exp = (int) (exp * 1.2);
				}
				this.exp += exp;
				if (this.exp >= lv * 5) {
					++lv;
					this.exp = 0;
					maxHp += 5;
					att += 2;
					def += 2;
					hillFullHp();
				}
			}

			int getAttack() {
				return getAttack(false);
			}

			int getAttack(boolean isFirstAttack) {
				int attack = this.att + (weapon != null ? weapon.attack : 0);
				if (isFirstAttack) {
					if (hasAccessoryType(Item.Accessory.Type.COURAGE)) {
						attack *= (hasAccessoryType(Item.Accessory.Type.DEXTERITY) ? 3 : 2);
					}
				}
				return attack;
			}

			int getDefense() {
				return this.def + (armor != null ? armor.defense : 0);
			}

			public void setCommand(String command) {
				this.command = command;
			}

			@Override
			public String toString() {
				return "LV : " + lv
					+ "\nHP : " + hp + "/" + maxHp
					+ "\nATT : " + att
					+ "+" + (weapon != null ? weapon.attack : "0")
					+ "\nDEF : " + def
					+ "+" + (armor != null ? armor.defense : "0")
					+ "\nEXP : " + exp + "/" + (lv * 5);
			}
		}

		private static class Monster {
			String name;
			boolean isBoss;
			int att, def, hp, exp;
			int maxHp;

			public Monster(String name, int att, int def, int hp, int exp, boolean isBoss) {
				this.name = name;
				this.att = att;
				this.def = def;
				this.hp = hp;
				this.maxHp = hp;
				this.exp = exp;
				this.isBoss = isBoss;
			}

			void decreaseHp(int hp) {
				this.hp -= hp;
				if (this.hp <= 0)
					throw new Exceptions.MonsterDieException();
			}

			void hillFullHp() {
				this.hp = this.maxHp;
			}
		}

		private static class Item {

			static final int TYPE_WEAPON = 0;
			static final int TYPE_ARMOR = 1;
			static final int TYPE_ACCESSORY = 2;

			static int typeOf(Item item) {
				if (item instanceof Weapon) {
					return TYPE_WEAPON;
				} else if (item instanceof Armor) {
					return TYPE_ARMOR;
				} else if (item instanceof Accessory) {
					return TYPE_ACCESSORY;
				}
				throw new IllegalStateException("unknown item type.");
			}

			static class Weapon extends Item {
				int attack;

				static Weapon of(int attack) {
					Weapon weapon = new Weapon();
					weapon.attack = attack;
					return weapon;
				}
			}

			static class Armor extends Item {
				int defense;

				static Armor of(int defense) {
					Armor armor = new Armor();
					armor.defense = defense;
					return armor;
				}
			}

			static class Accessory extends Item {
				Type type;

				static Accessory of(String type) {
					for (Type t: Type.values()) {
						if (t.code.equals(type)) {
							Accessory accessory = new Accessory();
							accessory.type = t;
							return accessory;
						}
					}
					throw new IllegalStateException("unknown accessory type.");
				}

				enum Type {
					HP_REGENERATION("HR"),
					REINCARNATION("RE"),
					COURAGE("CO"),
					EXPERIENCE("EX"),
					DEXTERITY("DX"),
					HUNTER("HU"),
					CURSED("CU");

					private final String code;

					Type(String code) {
						this.code = code;
					}
				}
			}
		}

		private static class Exceptions {

			static class GameEndException extends RuntimeException {

				public GameEndException(String message) {
					super(message);
				}
			}

			static class MonsterDieException extends RuntimeException {
			}

			static class PlayerReincarnationException extends RuntimeException {
			}
		}

		private static class Messages {

			public static final String CONTINUE = "Press any key to continue.";
			public static final String TURN_PRINT_FORMAT = "Passed Turns : %d";
			public static final String KILLED_BY = "YOU HAVE BEEN KILLED BY %s..";
			public static final String WIN = "YOU WIN!";
		}
	}
}

