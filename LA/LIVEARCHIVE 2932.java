import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _3932 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			if(n == 0 && m == 0 && k == 0)
				break;
			int[] grid[] = new int[n][m];
			for(int i=0;i<n;i++)
			{
				String s = sc.next();
				for(int j=0;j<m;j++)
					grid[i][j] = s.charAt(j) == '.' ? 1 : 0;
			}
			int[] cum[] = new int[n+2][m+2];
			for(int i=1;i<=n;i++)
			{
				for(int j=1;j<=m;j++)
				{
					cum[i][j] = grid[i-1][j-1];
					if(i > 0)
						cum[i][j]+=cum[i-1][j];
					if(j > 0)
						cum[i][j]+=cum[i][j-1];
					if(i > 0 && j > 0)
						cum[i][j]-=cum[i-1][j-1];
				}
			}
			for(int i=0;i<n+2;i++)
				cum[i][m+1] = cum[i][m];
			for(int i=0;i<m+2;i++)
				cum[n+1][i] = cum[n][i];

			int ans = n * m;
			for(int r1 = 1 ; r1<=n;r1++)
				for(int r2 = r1 ; r2<=n;r2++)
				{
					int i = 1 , j = 1;
					while(i<=m && j<=m)
					{
						while(j <= m && cum[r2][j] - cum[r2][i-1] - cum[r1-1][j] + cum[r1-1][i-1] < k)
							j++;
						while(i <= m && cum[r2][j] - cum[r2][i-1] - cum[r1-1][j] + cum[r1-1][i-1] >= k)
						{
							ans = Math.min(ans, (j-i+1) * (r2-r1+1));
							i++;
						}
					}
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
