import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Q13458{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        long remain,n=Long.parseLong(st.nextToken()),b,c,arr[]=new long[(int)n],ans=n;
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) arr[i]=Long.parseLong(st.nextToken());
        st=new StringTokenizer(br.readLine());
        b=Long.parseLong(st.nextToken());
        c=Long.parseLong(st.nextToken());
        for(int i=0;i<n;i++){
            if(arr[i]-b<=0) continue;
            ans+=(arr[i]-b)/c;
            if((arr[i]-b)%c>0) ans+=1;
        }
        System.out.println(ans);
    }
}