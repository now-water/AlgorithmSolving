import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q14499 {
    static int n, m, px, py, k, board[][], command[], dice[] = new int[7];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }
    private static void solution() {
        int x = px, y = py;
        int dx[] = {0, 0, 0, -1, 1}, dy[] = {0, 1, -1, 0, 0};
        for (int i = 0; i < k; i++) {
            boolean isMoved = false;
            int com = command[i];
            int one = dice[1], two = dice[2], three = dice[3], four = dice[4], five = dice[5], six = dice[6];
            if(com <= 2){
                if(com == 1){ // 오른쪽
                    if(y + 1 < m){
                        dice[1] = four; dice[3] = one;
                        dice[6] = three; dice[4] = six;
                        y += 1;
                        isMoved = true;
                    }
                }
                else{ // 왼쪽
                    if(y - 1 >= 0) {
                        dice[1] = three; dice[3] = six;
                        dice[6] = four; dice[4] = one;
                        y -= 1;
                        isMoved = true;
                    }
                }
            }
            else if(com >= 3){
                if(com == 3) { // 위
                    if(x - 1 >= 0){
                        dice[1] = five; dice[2] = one;
                        dice[5] = six; dice[6] = two;
                        x -= 1;
                        isMoved = true;
                    }
                }
                else { // 아래
                    if(x + 1 < n){
                        dice[1] = two; dice[2] = six;
                        dice[5] = one; dice[6] = five;
                        x += 1;
                        isMoved = true;
                    }
                }
            }
            if(!isMoved) continue;
            // 좌표 이동 후
            if(board[x][y] == 0){
                board[x][y] = dice[6];
            } else{
                dice[6] = board[x][y];
                board[x][y] = 0;
            }
            System.out.println(dice[1]);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        px = Integer.parseInt(st.nextToken());
        py = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        command = new int[k];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dice, 0);
    }
}

