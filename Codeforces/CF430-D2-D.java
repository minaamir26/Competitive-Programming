package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WorkingOut_430D
{
	static int n,m;
	static int[] grid[];
	
	static int[][] memo1 , memo2, memo4,memo3;
	static int[][] curMemo;
	static int inf = (int)1e9;
	static int dx1,dy1,dx2,dy2;
	static int dp(int x, int y)
	{
		if(curMemo[x][y] != -1)
			return curMemo[x][y];
		int ans = grid[x][y];
		if(valid(x+dx1,y+dy1))
			ans = Math.max(ans, grid[x][y]+dp(x+dx1,y+dy1));
		if(valid(x+dx2,y+dy2))
			ans = Math.max(ans, grid[x][y]+dp(x+dx2,y+dy2));
		return curMemo[x][y] = ans;
	}
	
	static boolean valid(int x, int y)
	{
		return x>=0 && y>=0 && x<n && y<m;
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		grid = new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				grid[i][j] = sc.nextInt();
		
		memo1 = new int[n][m];
		memo2 = new int[n][m];
		memo3 = new int[n][m];
		memo4 = new int[n][m];
		for(int i=0;i<n;i++)
			Arrays.fill(memo1[i], -1);
		for(int i=0;i<n;i++)
			Arrays.fill(memo2[i], -1);
		for(int i=0;i<n;i++)
			Arrays.fill(memo4[i], -1);
		for(int i=0;i<n;i++)
			Arrays.fill(memo3[i], -1);
		
		curMemo = memo1;
		dx1 = 0; dy1 = 1; dx2 = 1; dy2 = 0;
		dp(0,0);
		curMemo = memo2;
		dx1 = -1; dy1 = 0; dx2 = 0; dy2 = 1;
		dp(n-1,0);
		curMemo = memo3;
		dx1 = 0; dy1 = -1; dx2 = 1; dy2 = 0;
		dp(0,m-1);
		curMemo = memo4;
		dx1 = 0; dy1 = -1; dx2 = -1; dy2 = 0;
		dp(n-1,m-1);
		int max = 0;
		for(int i=1;i<n-1;i++)
			for(int j=1;j<m-1;j++)
			{
				max = Math.max(max, memo4[i][j-1] + memo1[i][j+1]+memo3[i+1][j]+ memo2[i-1][j]);
				max = Math.max(max, memo4[i-1][j] + memo1[i+1][j]+memo3[i][j-1]+ memo2[i][j+1]);
			}
		System.out.println(max);
		
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
