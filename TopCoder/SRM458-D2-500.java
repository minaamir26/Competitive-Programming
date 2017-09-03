import java.util.Arrays;
public class BouncingBalls{
	public static double expectedBounces(int[] x, int T){
		int n = x.length;
		Arrays.sort(x);
		int cnt= 0;
		for(int i=0;i<(1<<n);i++){
			for(int a=0;a<n;a++)
				for(int b=a+1;b<n;b++)
					if((i & (1<<a)) != 0 && (i & (1<<b)) == 0 && x[b] - x[a] <= 2 * T)
						cnt++;
		}
		System.out.println(cnt);
		return 1.0*cnt / (1<<n);
	}
}