import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
class Pair{
    int x, y;
    public Pair(int x, int y){
        this.x=  x;
        this.y = y;
    }
}
class Coord{
    Pair endPoint;
    int gen;
    List<Pair> coord;

    public Coord(Pair p1, Pair p2, int gen) {
        this.gen = gen;
        this.endPoint = p2;
        this.coord = new ArrayList<>();
        this.coord.add(p1);
        this.coord.add(p2);
    }
}

public class Q15685 {
    static int n, command[][], dir[][] = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    static boolean board[][] = new boolean[101][101];
    static Coord coords[];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        // evolve generation of each dragon curve
        for (int i = 0; i < n; i++) {
            Coord here = coords[i]; // start, end, dir, gen
            for (int j = 0; j < here.gen; j++) {
                // Rotate degree of 90.
                List<Pair> changed = rotate(here.coord, here.endPoint);
                boolean first = true;
                for(Pair p: changed){
                    if(first) {
                        here.endPoint = new Pair(p.x, p.y);
                        first = false;
                    }
                    here.coord.add(new Pair(p.x, p.y));
                }
            }
        }
        // count the number of squares enclosing all angles with dragon curve
        for (int i = 0; i < n; i++) {
            for(Pair p: coords[i].coord)
                board[p.y][p.x] = true;
        }
        int cnt = 0;
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                if(board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1])
                    cnt += 1;
        System.out.println(cnt);
    }

    private static List<Pair> rotate(List<Pair> coord, Pair end) {
        List<Pair> ret = new ArrayList<>();
        for (int i = 0; i < coord.size(); i++) {
            Pair point = coord.get(i), rotated = new Pair(end.x + (end.y - point.y), end.y - (end.x - point.x));
            if(point.x == end.x && point.y == end.y) continue;
            ret.add(rotated);
        }
        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x, y, d, gen;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        command = new int[n][n];
        coords = new Coord[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            gen = Integer.parseInt(st.nextToken());
            coords[i] = new Coord(new Pair(x, y), new Pair(x + dir[d][0], y + dir[d][1]), gen);
            Arrays.fill(board[i], false);
        }
    }
    /* 더 간단한 방법. 이전까지의 모든 선들이 90도 회전해서 다시 추가되는 것이기 때문.
    simulation(y, x, g, new StringBuilder(String.valueOf(d)));
    public static void simulation(int sy, int sx, int generation, StringBuilder sb) {
        if (generation < 0) return;
        StringBuilder temp = new StringBuilder(sb);
        int nx = sx, ny = sy;
        for (int i = sb.length() /2; i < sb.length(); i++) {
            int d = sb.charAt(i) - '0';
            nx += dir[d][0];
            ny += dir[d][1];
            map[nx][ny] = 1; // count 출력하기 위함.
        }
        for (int i = sb.length() - 1; i >= 0 ; i--) {
            int d = sb.charAt(i) - '0';
            temp.append(String.valueOf((d + 1) % 4));
        }
        simulation(nx, ny, generation - 1, temp);
    }*/
}
