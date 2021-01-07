import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Snapshot{
    int rx, ry, bx, by;
    char shot[][];
    public Snapshot(int rx, int ry, int bx, int by, char snap[][]) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        shot = new char[snap.length][snap[0].length];
        for (int i = 0; i < snap.length; i++)
            for (int j = 0; j < snap[0].length; j++)
                this.shot[i][j] = snap[i][j];
    }
}
public class Q13460 {
    static int n, m;
    static char board[][];
    static Snapshot start;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static int solution() {
        int min = 0, dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0 , -1};
        boolean visited[][][][] = new boolean[10][10][10][10];
        char shot[][] = new char[n][m];
        Queue<Snapshot> q = new LinkedList<>();
        q.offer(start);
        visited[start.rx][start.ry][start.bx][start.by] = true;
        while(!q.isEmpty()){
            int size = q.size();
            min += 1;
            for (int i = 0; i < size; i++) {
                Snapshot here = q.poll();
                for (int j = 0; j < 4; j++) {
                    boolean rflag = false, bflag = false;
                    int rmove = 1, bmove = 1;
                    int rnx = here.rx + dx[j], rny = here.ry + dy[j];
                    int bnx = here.bx + dx[j], bny = here.by + dy[j];
                    arrCopy(shot, here.shot);
                    while(shot[rnx][rny] != '#'){
                        if(shot[rnx][rny] == 'O'){
                            rflag = true;
                            break;
                        }
                        rnx += dx[j];
                        rny += dy[j];
                        rmove += 1;
                    }
                    if(!rflag){
                        rnx -= dx[j];
                        rny -= dy[j];
                    }
                    while(shot[bnx][bny] != '#'){
                        if(shot[bnx][bny] == 'O'){
                            bflag = true;
                            break;
                        }
                        bnx += dx[j];
                        bny += dy[j];
                        bmove += 1;
                    }
                    if(!bflag){
                        bnx -= dx[j];
                        bny -= dy[j];
                    }
                    else continue;

                    if(rflag){
                        if(min > 10) return -1;
                        return min;
                    }
                    if(rnx == bnx && rny == bny){
                        if(rmove > bmove){
                            rnx -= dx[j];
                            rny -= dy[j];
                        } else{
                            bnx -= dx[j];
                            bny -= dy[j];
                        }
                    }

                    if(visited[rnx][rny][bnx][bny]) continue;
                    shot[here.rx][here.ry] = shot[here.bx][here.by] = '.';
                    shot[rnx][rny] = 'R';
                    shot[bnx][bny] = 'B';
/*                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < m; l++) {
                            System.out.print(shot[k][l]);
                        }
                        System.out.println("");
                    }
                    System.out.println("");*/
                    visited[rnx][rny][bnx][bny] = true;
                    q.offer(new Snapshot(rnx, rny, bnx, bny, shot));
                }
            }
        }
        return -1;
    }

    private static void arrCopy(char[][] dst, char[][] src) {
        for (int i = 0; i < src.length; i++)
            for (int j = 0; j < src[0].length; j++) {
                char ch = src[i][j];
                dst[i][j] = ch;
            }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        int rx = 0, ry = 0, bx = 0, by = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++)
            {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'R'){
                    rx = i; ry = j;
                }
                else if(board[i][j] == 'B'){
                    bx = i; by = j;
                }
            }
        }
        start = new Snapshot(rx, ry, bx, by, board);
    }
    private static boolean isBorder(int x, int y){
        return (x >= 0 && x < n && y >= 0 && y < m);
    }
}
/*
8 5
#####
#O.##
#B.##
#..##
#..##
##.R#
#...#
#####
답 : 5

10 6
######
#O##.#
#.#.##
#..#.#
#B...#
###R##
#..#.#
###..#
#....#
######
답 : 4
 */