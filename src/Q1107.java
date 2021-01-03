import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Q1107 {
    static int m, minValue = Integer.MAX_VALUE, arr[];
    static String n;
    static ArrayList<Integer> num;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.next();
        m = sc.nextInt();
        arr = new int[10]; Arrays.fill(arr, 1);
        num = new ArrayList<>();
        for (int i = 0; i < m; i++)
            arr[sc.nextInt()] = 0;

        solution();
        System.out.println(minValue);
    }
    public static void solution(){
        minValue = Math.abs(toNumber(n) - 100);
        for (int i = 0; i <= 999999; i++) {
            String number = String.valueOf(i);
            boolean isOkay = true;
            for (int j = 0; j < number.length(); j++) {
                if(arr[number.charAt(j) - '0'] == 0)
                {
                    isOkay = false;
                    break;
                }
            }
            if(!isOkay) continue;

            int diff = Math.abs(toNumber(n) - toNumber(number)) + number.length();
            minValue = Math.min(minValue, diff);
        }
    }

    private static int toNumber(String n) {
        int pos = 1, ret = 0;
        for (int i = n.length() - 1; i >= 0 ; i--, pos *= 10) {
            ret += (n.charAt(i) - '0') * pos;
        }
        return ret;
    }
}
/*
11
8
1 3 4 5 6 7 8 9
정답: 10
-----------------
12
9
0 1 3 4 5 6 7 8 9
정답  : 11

-> 2에서 12만들기
 */