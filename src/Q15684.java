import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q15684 {
    static int n, m ,h;
    static int ladder[][];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        for (int i = 0; i <= 3; i++) {
            if(dfs(1, 0, i))
            {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static boolean dfs(int level, int cnt, int maxCnt) {
        if(cnt == maxCnt){
            if(check()) return true;
            else return false;
        }

        for (int i = level; i <= h; i++) {
            for (int j = 1; j < n ; j++) {
                if(ladder[i][j] == 0 && ladder[i][j + 1]==0){
                    ladder[i][j] = 1;
                    ladder[i][j+1] = 2;
                    if(dfs(i, cnt + 1, maxCnt))
                        return true;
                    ladder[i][j] = ladder[i][j+1] = 0;
                }
            }
        }
        return false;
    }

    private static boolean check() {
        for (int i = 1; i <= n; i++) {
            int y = i;
            for (int x = 1; x <= h; x++) {
                if(ladder[x][y] == 1) y += 1;
                else if(ladder[x][y] == 2) y -= 1;
            }
            if(y != i) return false;
        }
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        ladder = new int[h + 1][n + 1];

        for (int i = 0; i < h + 1; i++)
            Arrays.fill(ladder[i], 0);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            ladder[h][v] = 1; // right
            ladder[h][v+1] = 2; // left
        }
    }
}