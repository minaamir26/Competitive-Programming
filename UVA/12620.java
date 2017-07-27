package v126;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _12620 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = 300;
		int[] fib = new int[n];
		fib[0] = fib[1] = 1;
		for(int i=2;i<n;i++)
			fib[i] = (fib[i-1] + fib[i-2])%100;
		int sum = 0;
		for(int i=0;i<300;i++)
			sum+=fib[i];
		PrintWriter pw = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			long l = sc.nextLong()-1;
			long r = sc.nextLong()-1;
			long ans = 0;
			long st = l  / 300;
			long end = r / 300;
			if(end-st <= 1)
			{
				for(long i=l;i<=r;i++)
					ans+=fib[(int)(i%300)];
			}
			else
			{
				ans = 1l*sum*(end-st-1);
				for(long i=l;i<(st+1)*300;i++)
					ans+=fib[(int)(i%300)];
				for(long i=end*300;i<=r;i++)
					ans+=fib[(int)(i%300)];
			}
			pw.println(ans);
		}
		pw.flush();
	}
	static class Scanner{
		StringTokenizer st;BufferedReader br;
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		public String next() throws IOException 
		{while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());return st.nextToken();}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		public long nextLong() throws IOException {return Long.parseLong(next());}
		public String nextLine() throws IOException {return br.readLine();}
		public double nextDouble() throws IOException {return Double.parseDouble(next());}
		public boolean ready() throws IOException {return br.ready();}
	}

}
