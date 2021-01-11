import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14890 {
    static int board[][], n, L;
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        int cnt = 0;
        /*for (int i = 0; i < n; i++) {
            // Row check
            int pre = board[i][0], preCnt = 1;
            boolean flag = true;
            for (int j = 1; j < n; j++) {
                if(pre == board[i][j])
                    preCnt += 1;
                // 현재까지의 길에 경사로를 둘 수 있는 경우
                else if(board[i][j] - pre == 1) {
                    if (preCnt >= L) {
                        pre = board[i][j];
                        preCnt = 1;
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
                // 앞으로의 길에 경사로를 둘 수 있는지 확인
                else if(board[i][j] - pre == -1) {
                    boolean next = true;
                    for (int k = 1; k < L; k++) {
                        if(j + k >= n || board[i][j + k] != board[i][j])
                        {
                            next = false;
                            break;
                        }
                    }
                    if(next){
                        pre = board[i][j];
                        preCnt = 1 - L;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
                else {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt += 1;

            // Col check
            pre = board[0][i]; preCnt = 1;
            flag = true;
            for (int j = 1; j < n; j++) {
                if(pre == board[j][i])
                    preCnt += 1;
                    // 현재까지의 길에 경사로를 둘 수 있는 경우
                else if(board[j][i] - pre == 1) {
                    if (preCnt >= L) {
                        pre = board[j][i];
                        preCnt = 1;
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
                // 앞으로의 길에 경사로를 둘 수 있는지 확인
                else if(board[j][i] - pre == -1) {
                    boolean next = true;
                    for (int k = 1; k < L; k++) {
                        if(j + k >= n || board[j + k][i] != board[j][i])
                        {
                            next = false;
                            break;
                        }
                    }
                    if(next){
                        pre = board[j][i];
                        preCnt = 1 - L;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
                else {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt += 1;
        }*/
        for (int i = 0; i < n; i++) {
            int hori[] = new int[n];
            int vert[] = new int[n];
            for (int j = 0; j < n; j++) {
                hori[j] = board[i][j];
                vert[j] = board[j][i];
            }
            if(isAvailable(hori)) cnt++;
            if(isAvailable(vert)) cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean isAvailable(int[] path) {
        int len = path.length, num = path[0], cont = 1;
        for (int i = 1; i < len; i++) {
            if(num == path[i]) cont++;
            else{
                if(path[i] - num == 1){ // 위로 올라가는 경사로
                    if(cont >= L) {
                        num = path[i];
                        cont = 1;
                    }
                    else return false;
                } else if(path[i] - num == -1){ // 아래로 내려가는 경사로
                    num = path[i];
                    for (int j = 1; j < L; j++) {
                        if(i + j >= n || num != path[i + j])
                            return false;
                    }
                    cont = 1 - L;
                } else{
                    return false;
                }
            }
        }
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
    }
}
