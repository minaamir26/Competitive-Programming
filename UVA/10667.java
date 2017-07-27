package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _10667 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		PrintWriter pw  = new PrintWriter(System.out);
		while(tc-->0)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[][] grid = new int[n][n];
			while(m-->0)
			{
				int r1 = sc.nextInt()-1;
				int c1 = sc.nextInt()-1;
				int r2 = sc.nextInt()-1;
				int c2 = sc.nextInt()-1;
				
				for(int i=r1;i<=r2;i++)
				{
					grid[i][c1]++;
					if(c2 < n-1)
						grid[i][c2+1]--;
				}
			}
			for(int i=0;i<n;i++)
				for(int j=1;j<n;j++)
					grid[i][j] = grid[i][j]+ grid[i][j-1];
			boolean[][] arr = new boolean[n][n];
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(grid[i][j] > 0)
						arr[i][j] = true;
			int right[][] = new int[n][n];
			for(int i=0;i<n;i++)
			{
				int last = n;
				int j = n-1;
				while(true)
				{
					while(j>=0 && !arr[i][j])
					{
						right[i][j] = last;
						j--;
					}
					if(j<0)
						break;
					right[i][j] = j;
					last = j;
					j--;
				}
			}
			int ans = 0;
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
				{
					int min = n;
					for(int k=i;k<n;k++)
					{
						if(right[k][j] == j)
							break;
						min = Math.min(min, right[k][j]);
						ans = Math.max(ans, (k-i+1) * (min-j));
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
