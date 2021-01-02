import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q15683 {
    static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static char board[][];
    static List<Pair> cctvPos = new ArrayList<>();
    static List<List<Pair>> cctvs[];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc(0, board));
    }

    private static void apply(Pair here, List<Pair> way, char temp[][]) {
        for (Pair w: way)
        {
            int nx = here.x + w.x, ny = here.y + w.y;
            while(isBorder(nx, ny) && temp[nx][ny] != '6')
            {
                if(temp[nx][ny] == '0')
                    temp[nx][ny] = '#';
                nx += w.x; ny += w.y;
            }
        }
    }

    private static boolean isBorder(int nx, int ny) {
        return (nx >= 0 && nx < n && ny >= 0 && ny < m);
    }

    public static void copyArr(char a[][], char b[][])
    {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = b[i][j];
    }

    public static int calc(int idx, char board[][]) {
        if (idx >= cctvPos.size())
            return checkSquare(board);

        int ret = 987654321;
        Pair here = cctvPos.get(idx);
        int num = board[here.x][here.y] - '0';
        for (List<Pair> pair : cctvs[num]) {
            char temp[][] = new char[n][m];
            copyArr(temp, board);
            apply(here, pair, temp);
            ret = Math.min(ret, calc(idx + 1, temp));
        }
        return ret;
    }

    private static int checkSquare(char[][] board) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == '0')
                    ans += 1;
            }
        }
        return ans;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        cctvs = new ArrayList[7];

        for (int i = 0; i < 7; i++) {
            cctvs[i] = new ArrayList<>();
        }

        Pair up = new Pair(-1, 0), right = new Pair(0, 1), down = new Pair(1, 0), left = new Pair(0, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
            {
                board[i][j] = st.nextToken().charAt(0);
                if(board[i][j] != '0' && board[i][j] != '6')
                {
                    int idx = board[i][j] - '0';
                    cctvPos.add(new Pair(i, j));
                }
            }
        }

        cctvs[1].add(List.of(up)); cctvs[1].add(List.of(right)); cctvs[1].add(List.of(down)); cctvs[1].add(List.of(left));
        cctvs[2].add(List.of(up, down)); cctvs[2].add(List.of(left, right));
        cctvs[3].add(List.of(up, right)); cctvs[3].add(List.of(right, down)); cctvs[3].add(List.of(down, left)); cctvs[3].add(List.of(up, left));
        cctvs[4].add(List.of(up, left, right)); cctvs[4].add(List.of(up, right, down)); cctvs[4].add(List.of(right, down, left)); cctvs[4].add(List.of(up, left, down));
        cctvs[5].add(List.of(up, right, down, left));
    }
}
