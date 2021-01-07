import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14889 {
    static int n, board[][];
    static boolean team[];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(0, 0));
    }

    private static int solution(int s, int memCount) {
        // 종료조건. 멤버가 반씩 나눠진 경우
        if (memCount == (n / 2)) {
            int sumStart = 0, sumLink = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (team[i] && team[j]) {
                        sumStart += board[i][j];
                        sumStart += board[j][i];
                    }
                    else if (!team[i] && !team[j]) {
                        sumLink += board[i][j];
                        sumLink += board[j][i];
                    }
                }
            }
            return Math.abs(sumStart - sumLink);
        }
        int ret = Integer.MAX_VALUE;

        for (int i = s; i < n; i++) {
            team[i] = true;
            ret = Math.min(ret, solution(i + 1, memCount + 1));
            team[i] = false;
        }
        /* 이러면 재귀가 너무 깊어져서 안됨.. 선택 안하는 경우는 재귀 x
        team[s] = true;
        ret = Math.min(ret, solution(s + 1, team,memCount + 1));
        team[s] = false;
        ret = Math.min(ret, solution(s + 1, team, memCount));
        */
        return ret;
    }
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        team = new boolean[n];
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
            team[i] = false;
        }
    }
}