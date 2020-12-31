import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q17135 {
    static class Pair<K, V> {

        public final K key;
        public final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    static class Monster{
        int dist;
        Pair<Integer, Integer> pos;

        public Monster(int dist, Pair pair) {
            this.dist = dist;
            this.pos = pair;
        }
    }

    static ArrayList<Monster> monster[];
    static int n, m, d;
    static int [][] board = new int[15][15];

    public static void main(String[] args) throws IOException {
        init();
        calc();
    }

    private static void calc() {
        int archerPos[] = new int[3], ans = 0;
        // 궁수 배치
        for (int i = 0; i < m - 2; i++) {
            for (int j = i + 1; j < m - 1; j++) {
                for (int k = j + 1; k < m; k++) {
                    archerPos[0] = i;
                    archerPos[1] = j;
                    archerPos[2] = k;
                    monsterGenerate(archerPos);
                    ans = Math.max(ans, start(archerPos));
                }
            }
        }
        System.out.println(ans);
        return;
    }

    private static int start(int archerPos[]) {
        int ans = 0, dist, archer;
        int temp[][] = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                temp[i][j] = board[i][j];
            }

        boolean isDead[][] = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(isDead[i], false);
        }

        for (int round = 0; round < n; round++) {
            for (archer = 0; archer < 3; archer++) {
                for(Monster mob : monster[archer]){
                    int x = mob.pos.key, y = mob.pos.value;
                    if(temp[x][y] == 1 && mob.dist - round <= d && x + round < n){
                        isDead[x][y] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(temp[i][j] == 1 && isDead[i][j])
                    {
                        ans += 1;
                        temp[i][j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    private static void monsterGenerate(int archerPos[]) {
        monster = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            monster[i] = new ArrayList<>();
        }
        int dist;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == 1){
                    for (int archer = 0; archer < 3; archer++) {
                        dist = Math.abs(i - n) + Math.abs(j - archerPos[archer]);
                        monster[archer].add( new Monster(dist, new Pair(i, j)));
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            Collections.sort(monster[i], new Comparator<Monster>() {
                @Override
                public int compare(Monster o1, Monster o2) {
                    if(o1.dist == o2.dist) {
                        /*if(o1.pos.value == o2.pos.value)
                            return o2.pos.key - o1.pos.key;*/
                        return o1.pos.value - o2.pos.value;
                    }
                    return o1.dist - o2.dist;
                }
            });
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        /*System.out.println("");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(board[i][j]);
            System.out.println("");
        }*/
    }
}
/*
5 5 3
1 1 1 0 1
0 1 1 0 0
1 1 1 0 0
0 1 1 0 0
1 1 1 0 0
출력 : 13
 */