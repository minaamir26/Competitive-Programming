import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LCPC12F
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int tc = sc.nextInt();
		int k = 1;
		while(tc-- > 0)
		{
			int x = sc.nextInt();
			int n = sc.nextInt();
			int[] arr = new int[100001];
			long ans = 0;
			while(n-->0)
			{
				int cur = sc.nextInt();
				if(cur <= x)
					ans+=arr[x-cur];
				arr[cur]++;
			}
			pw.printf("%d. %d\n",k++,ans);
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
