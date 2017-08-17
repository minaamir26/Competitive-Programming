import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1137_BusRoutes
{
	static Queue<Integer> adj[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		adj = new LinkedList[10001];
		for(int i=0;i<adj.length;i++)
			adj[i] = new LinkedList<>();
		int[] indeg = new int[10001];
		int[] outdeg = new int[10001];

		boolean[] in = new boolean[adj.length];
		while(n-->0)
		{
			int k = sc.nextInt();
			int lst = sc.nextInt()-1;
			in[lst] = true;
			while(k-->0)
			{
				int x = sc.nextInt()-1;
				indeg[lst]++;
				outdeg[x]++;
				in[x] = true;
				adj[lst].add(x);
				lst = x;
			}
		}

		for(int i=0;i<adj.length;i++)
			if(in[i])
				if(indeg[i]!=outdeg[i])
				{
					System.out.println(0);
					return;
				}
		vis = new boolean[10001];
		int nodes = 0;
		int fst = -1;
		for(int i=0;i<in.length;i++)
			if(in[i])
			{
				if(fst < 0)
					fst = i;
				nodes++;
			}
		dfs(fst);
		if(cnt != nodes)
		{
			System.out.println(0);
			return;
		}
		euler = new ArrayList<>();
		vis = new boolean[10001];
		euler(fst);
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(euler.size()-1);
		for(int i=euler.size()-1;i>=0;i--)
		{
			pw.print(euler.get(i));
			if(i > 0)
				pw.print(" ");
		}
		pw.flush();
	}
	static boolean[] vis;
	static int cnt;
	static void dfs(int u){
		vis[u] = true;
		cnt++;
		for(int v : adj[u])
			if(!vis[v])
				dfs(v);
	}
	static ArrayList<Integer> euler;
	
	static void euler(int u)
	{
		while(!adj[u].isEmpty())
			euler(adj[u].poll());
		euler.add(u+1);
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
