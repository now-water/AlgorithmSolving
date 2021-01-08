import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14888 {
    static int n, arr[], gihoCnt[] = {0, 0, 0, 0}, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static char giho[] = {'+', '-', '*', '/'};
    static List<Integer> numList = new ArrayList<Integer>();
    public static void main(String[] args) throws IOException {
        init();
        solution(gihoCnt, new ArrayList<Character>());
        System.out.println(max);
        System.out.println(min);
    }

    private static void solution(int gihoCnt[], List<Character> gihoList) {
        if(gihoList.size() == n - 1){
            // 최대 최소 갱신
            updateAnswer(gihoList);
            return;
        }
        int copyCnt[] = new int[4];
        for (int i = 0; i < 4; i++)
            copyCnt[i] = gihoCnt[i];

        for (int i = 0; i < 4; i++) {
            if(gihoCnt[i] > 0) {
                List copyGiho = new ArrayList<Character>();
                copyGiho.addAll(gihoList);
                copyGiho.add(giho[i]);
                copyCnt[i] -= 1;
                solution(copyCnt, copyGiho);
                copyCnt[i] += 1;
            }
        }
    }

    private static void updateAnswer(List<Character> list) {
        int gidx = 0, head = 0;
        for (int nidx = 0; nidx < numList.size(); nidx++) {
            if(nidx == 0){
                head = calculate(numList.get(0), list.get(gidx++), numList.get(1));
                nidx += 1;
                continue;
            }
            head = calculate(head, list.get(gidx++), numList.get(nidx));
        }
        max = Math.max(max, head);
        min = Math.min(min, head);
    }

    private static int calculate(int a, char c, int b) {
        switch(c){
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
            numList.add(arr[i]);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            gihoCnt[i] = Integer.parseInt(st.nextToken());
    }
}
