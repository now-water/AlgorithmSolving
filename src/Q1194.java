import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos{
    int x, y, cnt, key;
    public Pos(int x, int y, int cnt, int key){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.key = key;
    }
}
public class Q1194 {
    static int n, m, dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
    static char board[][];
    static Pos start;
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        boolean visited[][][] = new boolean[64][n][m];
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], false);
            }
        }
        Queue<Pos> q = new LinkedList<>();
        q.offer(start);
        visited[start.key][start.x][start.y] = true;
        while(!q.isEmpty()){
            Pos here = q.poll();
            if(board[here.x][here.y] == '1') {
                System.out.println(here.cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = here.x + dx[i], ny = here.y + dy[i];
                if(!isBorder(nx, ny)) continue;
                if(board[nx][ny] != '#' && !visited[here.key][nx][ny])
                {
                    if(board[nx][ny] == '.' || board[nx][ny] == '0' || board[nx][ny] == '1'){
                        visited[here.key][nx][ny] = true;
                        q.offer(new Pos(nx, ny, here.cnt + 1, here.key));
                    }
                    else if('a' <= board[nx][ny] && board[nx][ny] <= 'f'){
                        int newKey = here.key | (1 << (board[nx][ny] - 'a'));
                        visited[here.key][nx][ny] = true;
                        visited[newKey][nx][ny] = true;
                        q.offer(new Pos(nx, ny, here.cnt + 1, newKey));
                    }
                    else if('A' <= board[nx][ny] && board[nx][ny] <= 'F'){
                        if((here.key & (1 << (board[nx][ny] - 'A'))) > 0){
                            visited[here.key][nx][ny] = true;
                            q.offer(new Pos(nx ,ny, here.cnt + 1, here.key));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
    private static boolean isBorder(int x, int y){
        return (x >= 0 && x < n && y >= 0 && y < m);
    }
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
                if(board[i][j] == '0')
                    start = new Pos(i, j, 0, 0);

            }
        }
    }
}
