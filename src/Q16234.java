import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair{
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Q16234 {
    static int n, l, r, board[][], dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1}, cnt = 0;
    static boolean visited[][], flag = true;
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        while(flag) {
            flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j])
                        bfs(i, j);
                }
            }
            for (int i = 0; i < n; i++)
                Arrays.fill(visited[i], false);
            if(flag) cnt += 1;
        }
        System.out.println(cnt);
    }

    private static void bfs(int x, int y) {
        int avg = board[x][y];
        List<Pair> population = new ArrayList<>();
        population.add(new Pair(x, y));
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;
        while(!q.isEmpty()){
            Pair here = q.poll(); x = here.x; y = here.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if(isBorder(nx, ny) && !visited[nx][ny] && isCross(board[x][y], board[nx][ny]))
                {
                    q.add(new Pair(nx, ny));
                    population.add(new Pair(nx, ny));
                    avg += board[nx][ny];
                    visited[nx][ny] = true;
                    if(!flag) flag = true;
                }
            }
        }
        avg /= population.size();
        for(Pair pos: population) board[pos.x][pos.y] = avg;
    }

    private static boolean isCross(int src, int dst) {
        int diff =Math.abs(src - dst);
        return (diff >= l && diff <= r);
    }

    private static boolean isBorder(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
            Arrays.fill(visited[i], false);
        }
    }
}