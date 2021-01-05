import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Snap{
    int rx, ry, bx, by;
    char shot[][];

    public Snap(int rx, int ry, int bx, int by, char snapshot[][]){
        this.rx = rx; this.ry = ry;
        this.bx = bx; this.by = by;
        shot = new char[snapshot.length][snapshot[0].length];
        for (int i = 0; i < snapshot.length; i++) {
            for (int j = 0; j < snapshot[0].length; j++) {
                shot[i][j] = snapshot[i][j];
            }
        }
    }
}

public class Q13459 {
    static int n, m, dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
    static Snap start;
    static boolean visited[][][][] = new boolean[10][10][10][10];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static int solution() {
        int count = 0;
        Queue<Snap> q = new LinkedList<>();
        q.offer(start);
        visited[start.rx][start.ry][start.bx][start.by] = true;
        while(!q.isEmpty()){
            int size = q.size();
            count += 1;
            for (int round = 0; round < size; round++) {
                Snap here = q.poll();
                for (int i = 0; i < 4; i++) {
                    boolean rflag = false, bflag = false;
                    char snapshot[][] = here.shot;
                    // red bead -> go in hole
                    int rnx = here.rx + dx[i], rny = here.ry + dy[i];
                    // blue bead -> can't go in hole
                    int bnx = here.bx + dx[i], bny = here.by + dy[i];
                    int rmove = 1, bmove = 1;
                    // red bead move
                    while (snapshot[rnx][rny] != '#') {
                        if (snapshot[rnx][rny] == 'O') {
                            rflag = true;
                            break;
                        }
                        rmove += 1;
                        rnx += dx[i]; rny += dy[i];
                    }
                    if (!rflag) {
                        rnx -= dx[i]; rny -= dy[i];
                    }

                    // blue bead move
                    while (snapshot[bnx][bny] != '#') {
                        if (snapshot[bnx][bny] == 'O') {
                            bflag = true;
                            break;
                        }
                        bmove += 1;
                        bnx += dx[i]; bny += dy[i];
                    }
                    if(bflag) continue;
                    bnx -= dx[i];
                    bny -= dy[i];

                    // 빨간색만 구멍에 빠졌으면 게임 끝
                    if (rflag) {
                        if (count <= 10) return 1;
                        else return 0;
                    }
                    // 빨간색 구슬과 파란색 구슬이 같은 위치일 경우 위치 조정
                    if(rnx == bnx && rny == bny){
                        if(rmove > bmove){ // 더 많이 이동 = 더 뒤에 있었다.
                            rnx -= dx[i];
                            rny -= dy[i];
                        }
                        else{
                            bnx -= dx[i];
                            bny -= dy[i];
                        }
                    }
                    if (visited[rnx][rny][bnx][bny]) continue;
                    snapshot[here.rx][here.ry] = '.';
                    snapshot[here.bx][here.by] = '.';
                    snapshot[rnx][rny] = 'R';
                    snapshot[bnx][bny] = 'B';

                    visited[rnx][rny][bnx][bny] = true;
                    q.offer(new Snap(rnx, rny, bnx, bny, snapshot));
                    snapshot[rnx][rny] = '.';
                    snapshot[bnx][bny] = '.';
                }
            }
        }
        return 0;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char board[][] = new char[n][m];

        int rx = 0, ry = 0, bx = 0, by = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                // board initialize
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'R') {
                    rx = i; ry = j;
                }
                else if(board[i][j] == 'B') {
                    bx = i ; by = j;
                }
                // visited initialize
                for (int k = 0; k < n; k++)
                    for (int l = 0; l < m; l++)
                        visited[i][j][k][l] = false;
            }
        }
        start = new Snap(rx, ry, bx, by, board);
    }
}