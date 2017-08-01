package Codeforces;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KseninaAndPawns_382D {
	static int[][] sol;
	static String s = "^v<>";
	static int[] dy = new int[]{0,0,-1,1};
	static int[] dx = new int[]{-1,1,0,0};
	static char[][] grid;
	static int sx[] , sy[];
	static int kk = 0;
	static void dfs(int x ,int y)
	{
		while(grid[x][y] != '#')
		{
			if(sol[x][y] != 0)
				break;
			sx[kk] = x;
			sy[kk] = y;
			kk++;
			int x2 = x + dx[dir[grid[x][y]]];
			int y2 = y + dy[dir[grid[x][y]]];
			x = x2;
			y = y2;
		}
		kk--;
		while(kk >= 0)
		{
			x = sx[kk];
			y = sy[kk];
			kk--;
			sol[x][y] = 1 + sol[x+dx[dir[grid[x][y]]]][ y + dy[dir[grid[x][y]]]];
		}
		kk++;
	}
	static int[][] vis;
	static int k;
	static boolean inf;
	static void check(int x ,int y)
	{
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		qx.add(x);
		qy.add(y);
		while(!qx.isEmpty())
		{
			int xx = qx.poll();
			int yy = qy.poll();
			vis[xx][yy] = k;
			if(grid[xx][yy] == '#')
				continue ;
			if(vis[xx + dx[s.indexOf(grid[xx][yy])]][yy + dy[s.indexOf(grid[xx][yy])]] == k)
			{
				inf = true;
				return;
			}
			if(vis[xx + dx[s.indexOf(grid[xx][yy])]][yy + dy[s.indexOf(grid[xx][yy])]] == 0)
			{
				qx.add(xx + dx[s.indexOf(grid[xx][yy])]);
				qy.add(yy + dy[s.indexOf(grid[xx][yy])]);
			}
		}
	}
	static int[] dir = new int[1000];
	static void dfs3(int x, int y)
	{
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		qx.add(x);
		qy.add(y);
		while(!qx.isEmpty())
		{
			int xx = qx.poll();
			int yy = qy.poll();
			if(grid[xx][yy] == '#')
				continue;
			int x2 = xx + dx[s.indexOf(grid[xx][yy])];
			int y2 = yy + dy[s.indexOf(grid[xx][yy])];
			grid[xx][yy] = '#';
			qx.add(x2);
			qy.add(y2);
		}
	}


	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		grid = new char[n][m];
		sx = new int[4100009];
		sy = new int[4100009];
		for(int i=0;i<n;i++)
			grid[i] = sc.next().toCharArray();
		inf = false;
		dir['^'] = s.indexOf('^');
		dir['v'] = s.indexOf('v');
		dir['>'] = s.indexOf('>');
		dir['<'] = s.indexOf('<');
//		n = 2000;
//		m = 2000;
//		grid = new char[n][m];
//		for(int i=0;i<n;i++)
//			for(int j=0;j<m;j++)
//			{
//				if(i == 0 || i == n-1 || j == m-1 || j == 0)
//					grid[i][j] = '#';
//				else
//					if(i == 1 && j%2 == 0)
//						grid[i][j] = '>';
//					else
//						if(i == n-2 && j%2 == 1)
//							grid[i][j] = '>';
//						else
//							if(j%2 == 1)
//								grid[i][j] = 'v';
//							else
//								grid[i][j]= '^';
//			}

		k = 1;
		vis = new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(vis[i][j] == 0)
				{
					check(i,j);
					k++;
				}
		if(inf)
			System.out.println(-1);
		else
		{
			sol = new int[n][m];
			for(int i=0;i<n;i++)
				Arrays.fill(sol[i], 0);
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if(grid[i][j] != '#' && sol[i][j] == 0)
						dfs(i,j);
			int max = 0;
			int maxx = 0 , maxy = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if(max < sol[i][j])
					{
						max = Math.max(max, sol[i][j]);
						maxx = i;
						maxy = j;
					}
				}
			}
			dfs3(maxx, maxy);
			sol = new int[n][m];
			for(int i=0;i<n;i++)
				Arrays.fill(sol[i], 0);
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if(grid[i][j] != '#' && sol[i][j] == 0)
						dfs(i,j);
			int max2 = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					max2 = Math.max(max2, sol[i][j]);
				}
			}
			System.out.println(Math.max(2*max-1 , max + max2));
		}
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