import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Q5373 {
    static char cube[][] = new char[9][12];
    static char copy[][] = new char[9][12];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();
        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0){
            int n = Integer.parseInt(br.readLine());
            String s[] = br.readLine().split(" ");
            copyArr(copy, cube);
            for (int i = 0; i < n; i++) play(copy, s[i]);
            printHead(copy);
        }
    }
    static void play(char[][] copy, String s) {
        char dir = s.charAt(0), clock = s.charAt(1);
        char temp[] = new char[3];
        if (dir == 'L') {
            if (clock == '-') {
                for (int i = 0; i < 3; i++) temp[i] = copy[i][0];
                for (int i = 0; i < 3; i++) copy[i][0] = copy[i + 3][0];
                for (int i = 0; i < 3; i++) copy[i + 3][0] = copy[i + 6][0];
                for (int i = 0; i < 3; i++) copy[i + 6][0] = copy[5 - i][8];
                for (int i = 0; i < 3; i++) copy[5 - i][8] = temp[i];
                rotatePlane(3, 9, 6, 12, 3);
            } else {
                for (int i = 0; i < 3; i++) temp[i] = copy[i + 3][8];
                for (int i = 0; i < 3; i++) copy[i + 3][8] = copy[8 - i][0];
                for (int i = 0; i < 3; i++) copy[8 - i][0] = copy[5 - i][0];
                for (int i = 0; i < 3; i++) copy[5 - i][0] = copy[2 - i][0];
                for (int i = 0; i < 3; i++) copy[2 - i][0] = temp[i];
                rotatePlane(3, 9, 6, 12, 1);
            }

        }
        else if (dir == 'R') {
            if (clock == '-') {
                for (int i = 0; i < 3; i++) temp[i] = copy[2 - i][2];
                for (int i = 0; i < 3; i++) copy[2 - i][2] = copy[3 + i][6];
                for (int i = 0; i < 3; i++) copy[3 + i][6] = copy[8 - i][2];
                for (int i = 0; i < 3; i++) copy[8 - i][2] = copy[5 - i][2];
                for (int i = 0; i < 3; i++) copy[5 - i][2] = temp[i];
                rotatePlane(3, 3, 6, 6, 3);
            } else {
                for (int i = 0; i < 3; i++) temp[i] = copy[i][2];
                for (int i = 0; i < 3; i++) copy[i][2] = copy[i + 3][2];
                for (int i = 0; i < 3; i++) copy[i + 3][2] = copy[i + 6][2];
                for (int i = 0; i < 3; i++) copy[i + 6][2] = copy[5 - i][6];
                for (int i = 0; i < 3; i++) copy[5 - i][6] = temp[i];
                rotatePlane(3, 3, 6, 6, 1);
            }

        }
        else if (dir == 'F') {
            if (clock == '-') {
                for (int i = 0; i < 3; i++) temp[i] = copy[2][i];
                for (int i = 0; i < 3; i++) copy[2][i] = copy[i + 3][3];
                for (int i = 0; i < 3; i++) copy[i + 3][3] = copy[6][2 - i];
                for (int i = 0; i < 3; i++) copy[6][2 - i] = copy[5 - i][11];
                for (int i = 0; i < 3; i++) copy[5 - i][11] = temp[i];
                rotatePlane(3, 0, 6, 3, 3);
            } else {
                for (int i = 0; i < 3; i++) temp[i] = copy[i + 3][11];
                for (int i = 0; i < 3; i++) copy[i + 3][11] = copy[6][i];
                for (int i = 0; i < 3; i++) copy[6][i] = copy[5 - i][3];
                for (int i = 0; i < 3; i++) copy[5 - i][3] = copy[2][2 - i];
                for (int i = 0; i < 3; i++) copy[2][2 - i] = temp[i];
                rotatePlane(3, 0, 6, 3, 1);
            }

        }
        else if (dir == 'B') {
            if (clock == '-') {
                for (int i = 0; i < 3; i++) temp[i] = copy[3 + i][9];
                for (int i = 0; i < 3; i++) copy[3 + i][9] = copy[8][i];
                for (int i = 0; i < 3; i++) copy[8][i] = copy[5 - i][5];
                for (int i = 0; i < 3; i++) copy[5 - i][5] = copy[0][2 - i];
                for (int i = 0; i < 3; i++) copy[0][2 - i] = temp[i];
                rotatePlane(3, 6, 6, 9, 3);
            } else {
                for (int i = 0; i < 3; i++) temp[i] = copy[0][i];
                for (int i = 0; i < 3; i++) copy[0][i] = copy[i + 3][5];
                for (int i = 0; i < 3; i++) copy[i + 3][5] = copy[8][2 - i];
                for (int i = 0; i < 3; i++) copy[8][2 - i] = copy[5 - i][9];
                for (int i = 0; i < 3; i++) copy[5 - i][9] = temp[i];
                rotatePlane(3, 6, 6, 9, 1);
            }
        }
        else if (dir == 'U') {
            if (clock == '-') {
                for (int i = 0; i < 3; i++) temp[i] = copy[3][11 - i];
                for (int i = 0; i < 3; i++) copy[3][11 - i] = copy[3][8 - i];
                for (int i = 0; i < 3; i++) copy[3][8 - i] = copy[3][5 - i];
                for (int i = 0; i < 3; i++) copy[3][5 - i] = copy[3][2 - i];
                for (int i = 0; i < 3; i++) copy[3][2 - i] = temp[i];
                rotatePlane(0, 0, 3, 3, 3);
            } else {
                for (int i = 0; i < 3; i++) temp[i] = copy[3][i];
                for (int i = 0; i < 3; i++) copy[3][i] = copy[3][i + 3];
                for (int i = 0; i < 3; i++) copy[3][i + 3] = copy[3][i + 6];
                for (int i = 0; i < 3; i++) copy[3][i + 6] = copy[3][i + 9];
                for (int i = 0; i < 3; i++) copy[3][i + 9] = temp[i];
                rotatePlane(0, 0, 3, 3, 1);
            }
        }
        else if (dir == 'D') {
            if (clock == '-') {
                for (int i = 0; i < 3; i++) temp[i] = copy[5][i];
                for (int i = 0; i < 3; i++) copy[5][i] = copy[5][i + 3];
                for (int i = 0; i < 3; i++) copy[5][i + 3] = copy[5][i + 6];
                for (int i = 0; i < 3; i++) copy[5][i + 6] = copy[5][i + 9];
                for (int i = 0; i < 3; i++) copy[5][i + 9] = temp[i];
                rotatePlane(6, 0, 9, 3, 3);
            } else {
                for (int i = 0; i < 3; i++) temp[i] = copy[5][11 - i];
                for (int i = 0; i < 3; i++) copy[5][11 - i] = copy[5][8 - i];
                for (int i = 0; i < 3; i++) copy[5][8 - i] = copy[5][5 - i];
                for (int i = 0; i < 3; i++) copy[5][5 - i] = copy[5][2 - i];
                for (int i = 0; i < 3; i++) copy[5][2 - i] = temp[i];
                rotatePlane(6, 0, 9, 3, 1);
            }
        }
    }
    static void rotatePlane(int sx, int sy, int ex, int ey, int cnt) {
        char temp[][] = new char[3][3], temp2[][] = new char[3][3];
        for (int i = sx; i < ex; i++)
            for (int j = sy; j < ey; j++)
                temp[i-sx][j-sy] = copy[i][j];
        while(cnt-- >0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--)
                    temp2[j][2-i] = temp[i][j];
            }
            copyArr(temp, temp2);
        }
        for (int i = sx; i < ex; i++)
            for (int j = sy; j < ey; j++)
                copy[i][j] = temp[i-sx][j-sy];
    }

    static void copyArr(char dst[][], char src[][]){
        for (int i = 0; i < src.length; i++)
            for (int j = 0; j < src[0].length; j++){
                dst[i][j] = src[i][j];
            }
    }
    static void printHead(char[][] copy) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(copy[i][j]);
            System.out.println();
        }
    }
    static void init() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
            {
                cube[i][j] = 'w';
                cube[i+6][j] = 'y';
            }
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) cube[i][j] = 'r';
            for (int j = 3; j < 6; j++) cube[i][j] = 'b';
            for (int j = 6; j < 9; j++) cube[i][j] = 'o';
            for (int j = 9; j < 12; j++) cube[i][j] = 'g';
        }
    }
}