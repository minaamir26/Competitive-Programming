package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BlockTower_327D
{
	static int n,m;
	static char[][] grid;
	static int[] dx = new int[]{0,0,1,-1};
	static int[] dy = new int[]{1,-1,0,0};
	static StringBuilder sb;
	static int k;
	static void dfs(int x, int y){
		vis[x][y] = true;
		sb.append("B ").append(x+1).append(" ").append(y+1).append("\n");
		k++;
		for(int i=0;i<4;i++)
		{
			int x2 = x + dx[i];
			int y2 = y + dy[i];
			if(valid(x2,y2) && !vis[x2][y2])
				dfs(x2,y2);
		}
		sb.append("D ").append(x+1).append(" ").append(y+1).append("\n");
		sb.append("R ").append(x+1).append(" ").append(y+1).append("\n");
		k+=2;
	}


	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		sb = new StringBuilder();
		grid = new char[n][m];
		for(int i=0;i<n;i++)
		{
			String s = sc.next();
			for(int j=0;j<m;j++)
				grid[i][j] = s.charAt(j);
		}
		vis = new boolean[n][m];
		k = 0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(!vis[i][j] && grid[i][j] != '#')
				{
					vis[i][j] = true;
					sb.append("B ").append(i+1).append(" ").append(j+1).append("\n");
					k++;
					for(int i2=0;i2<4;i2++)
					{
						int x2 = i + dx[i2];
						int y2 = j + dy[i2];
						if(valid(x2,y2) && !vis[x2][y2])
							dfs(x2,y2);
					}
				}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(k);
		pw.print(sb);
		pw.flush();
	}

	static boolean[][] vis;
	static boolean valid(int x, int y)
	{
		return x >= 0 && y >= 0 && x < n && y < m && grid[x][y] != '#';
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
