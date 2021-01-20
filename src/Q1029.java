import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1029 {
    static int n, arr[][], cache[][][] = new int[10][15][(1 << 15) + 1];
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(0, 0, 0));
    }

    private static int solution(int buyers, int last, int price) {
        if (cache[price][last][buyers] != -1) return cache[price][last][buyers];
        buyers |= (1 << last);
        int ret = 0;
        for (int man = 0; man < n; ++man) {
            if (((1 << man) & buyers) != 0 || arr[last][man] < price) continue;
            ret = Math.max(ret, solution(buyers, man, arr[last][man]));
        }
        return cache[price][last][buyers] = ret + 1;
    }

    private static void init() throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++)
                arr[i][j] = line.charAt(j) - '0';
        }
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 15; ++j) {
                Arrays.fill(cache[i][j], -1);
            }
        }
    }
}
