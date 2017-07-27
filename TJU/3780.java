import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TJU_3780 {
	static int n , m;
	static int[][] grid;

	static int[] dx = new int[]{0,0,1,1,1,-1,-1,-1};
	static int[] dy = new int[]{1,-1,0,1,-1,-1,1,0};
	
	static boolean[][] vis;
	static void dfs(int x, int y)
	{
		vis[x][y] = true;
			for(int j=0;j<dx.length;j++)
				{
					int x2 = x + dx[j];
					int y2 = y + dy[j];
					if(valid(x2 , y2) && !vis[x2][y2])
						dfs(x2,y2);
				}
	}

	static boolean valid(int x, int y){
		return x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1;
	}


	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			m = sc.nextInt();
			n = sc.nextInt();
			if(n == 0 && m == 0)
				break;
			grid = new int[n][m];
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					grid[i][j] = sc.nextInt();
			int cnt = 0;
			vis = new boolean[n][m];
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
				{
					if(!vis[i][j] && grid[i][j] == 1)
					{
						cnt++;
						dfs(i , j);
					}
				}
			pw.println(cnt);
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
