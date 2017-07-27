package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KseninaAndPawns_382D {
	static int[][] sol;
	static String s = "^v<>";
	static int[] dy = new int[]{0,0,-1,1};
	static int[] dx = new int[]{-1,1,0,0};
	static char[][] grid;


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
			if(vis[xx + dx[dir[(grid[xx][yy])]]][yy + dy[dir[(grid[xx][yy])]]] == k)
			{
				inf = true;
				return;
			}
			if(vis[xx + dx[dir[(grid[xx][yy])]]][yy + dy[dir[(grid[xx][yy])]]] == 0)
			{
				qx.add(xx + dx[dir[(grid[xx][yy])]]);
				qy.add(yy + dy[dir[(grid[xx][yy])]]);
			}
		}
	}

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
			int x2 = xx + dx[dir[(grid[xx][yy])]];
			int y2 = yy + dy[dir[(grid[xx][yy])]];
			grid[xx][yy] = '#';
			qx.add(x2);
			qy.add(y2);
		}
	}

	static int V;
	static ArrayList<Integer> adjList[];
	//16
	//65535
	static ArrayList<Integer> sortedArray;
	static void toposort()
	{
		int[] p = new int[V];
		sortedArray = new ArrayList<Integer>(V);
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(grid[i][j] != '#')
			{
				int v = 1000* i + dx[dir[grid[i][j]]] + dy[dir[grid[i][j]]] ;
				++p[v];
			}
		Queue<Integer> roots = new LinkedList<Integer>();	//PriorityQueue??
		for(int i = 0; i < V; ++i)
			if(p[i] == 0)
			{
				int x = i/1000;
				int y = i%1000;
				sol[x][y] = 1 + sol[x+dx[dir[(grid[x][y])]]][y + dy[dir[(grid[x][y])]]];
				roots.add(i);
			}
		while(!roots.isEmpty())
		{
			int u = roots.remove();
			sortedArray.add(u);
			for(int v: adjList[u])
				if(--p[v] == 0)
				{
					int x = v/1000;
					int y = v%1000;
					sol[x][y] = 1 + sol[x+dx[dir[(grid[x][y])]]][y + dy[dir[(grid[x][y])]]];
					roots.add(v);
				}
		}
	}

	static int dir[];
	static int n,m;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		grid = new char[n][m];
		dir = new int[1000];
		for(int i=0;i<n;i++)
			grid[i] = sc.next().toCharArray();
		long t = System.currentTimeMillis();
		dir['^'] = s.indexOf('^');
		dir['v'] = s.indexOf('v');
		dir['>'] = s.indexOf('>');
		dir['<'] = s.indexOf('<');
		
		n = 2000;
		m = 2000;
		grid = new char[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				if(i == 0 || i == n-1 || j == m-1 || j == 0)
					grid[i][j] = '#';
				else
					if(i == 1 && j%2 == 0)
						grid[i][j] = '>';
					else
						if(i == n-2 && j%2 == 1)
							grid[i][j] = '>';
						else
							if(j%2 == 1)
								grid[i][j] = 'v';
							else
								grid[i][j]= '^';
			}
//		for(int i=0;i<n;i++)
//			System.out.println(new String(grid[i]));
		
		inf = false;
		k = 1;
		vis = new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(vis[i][j] == 0)
				{
					check(i,j);
					k++;
				}
		System.out.println("heree ");
		System.out.println(System.currentTimeMillis()-t);
		if(inf)
			System.out.println(-1);
		else
		{

			V = n * m;
			adjList = new ArrayList[V];
			
			int[] size = new int[V];
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if(grid[i][j]!='#')
					{
						int node = i*m+j;
						int x2 = i + dx[dir[(grid[i][j])]];
						int y2 = j + dy[dir[(grid[i][j])]];
						size[x2*m+y2]++;
//						System.out.println(adjList[x2*m+y2].size());
					}
			for(int i=0;i<V;i++)
				adjList[i] = new ArrayList<>(size[i]);
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if(grid[i][j]!='#')
					{
						int node = i*m+j;
						int x2 = i + dx[dir[(grid[i][j])]];
						int y2 = j + dy[dir[(grid[i][j])]];
						adjList[x2*m+y2].add(node);
//						System.out.println(adjList[x2*m+y2].size());
					}
			
			System.out.println("yes");
			sol = new int[n][m];
			toposort();
			for(int i=0;i<sortedArray.size();i++)
			{
				int x = sortedArray.get(i)/m;
				int y = sortedArray.get(i)%m;
				if(grid[x][y] != '#')
					sol[x][y] = 1 + sol[x+dx[dir[(grid[x][y])]]][y + dy[dir[(grid[x][y])]]];
			}
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
			adjList = new ArrayList[V];
			for(int i=0;i<V;i++)
				adjList[i] = new ArrayList<>(size[i]);
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if(grid[i][j]!='#')
					{
						int node = i*m+j;
						int x2 = i + dx[dir[(grid[i][j])]];
						int y2 = j + dy[dir[(grid[i][j])]];
						adjList[x2*m+y2].add(node);
					}
			toposort();
			sol = new int[n][m];

			for(int i=0;i<sortedArray.size();i++)
			{
				int x = sortedArray.get(i)/m;
				int y = sortedArray.get(i)%m;
				if(grid[x][y] != '#')
					sol[x][y] = 1 + sol[x+dx[dir[(grid[x][y])]]][y + dy[dir[(grid[x][y])]]];
			}
			int max2 = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					max2 = Math.max(max2, sol[i][j]);
				}
			}
			System.out.println(Math.max(2*max-1 , max + max2));
		}
		System.out.println("Time: " + (System.currentTimeMillis()-t));
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
