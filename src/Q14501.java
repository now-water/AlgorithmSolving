import java.util.Scanner;

public class Q14501 {
    static int days[], costs[], n, ans = 0;
    public static void main(String[] args) {
        init();
        solution(-1, 0);
        System.out.println(ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        int ans = 0;
        n = sc.nextInt();
        days = new int[n];
        costs = new int[n];
        for (int i = 0; i < n; i++) {
            days[i] = sc.nextInt();
            costs[i] = sc.nextInt();
        }
    }

    private static void solution(int idx, int cost) {
        ans = Math.max(ans, cost);
        if(idx == -1) idx = 0;
        for (int i = idx; i < n; i++)
            if(i + days[i] <= n)
                solution(i + days[i], cost + costs[i]);
    }
}

/*
10
5 50
4 40
3 30
2 20
1 10
1 10
2 20
3 30
4 40
5 50

3 30
2 20
1 10
3 30 해서 90
 */