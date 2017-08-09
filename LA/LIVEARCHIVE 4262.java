import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class _4262_RoadNetworks
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-- > 0)
		{
			V = sc.nextInt();
			if(V == -1)
				break;
			dfs_num = new int[V];
			dfs_low = new int[V];
			SCC = 0;
			inSCC = new boolean[V];
			stack = new Stack<>();
			adjList = new ArrayList[V];
			for(int i=0;i<V;i++)
				adjList[i] = new ArrayList<>();
			int m = sc.nextInt();
			while(m-- > 0)
			{
				int u = sc.nextInt();
				int v = sc.nextInt();
				u--;v--;
				adjList[u].add(v);
			}
			sc.nextInt();
			tarjanSCC();
			pw.println(SCC);
		}
		pw.flush();
	}
	
	static ArrayList<Integer>[] adjList;
	static int V, counter, SCC, dfs_num[], dfs_low[];
	static boolean[] inSCC;
	static Stack<Integer> stack;
	
	static void tarjanSCC()	 	//O(V + E)
	{
		for(int i = 0; i < V; ++i)
			if(dfs_num[i] == 0)
				tarjanSCC(i);
	}
	
	static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		
		for(int v: adjList[u])
		{
			if(dfs_num[v] == 0)
				tarjanSCC(v);
			if(!inSCC[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);	
		}
		if(dfs_num[u] == dfs_low[u])
		{
			//SCC found
			while(true)
			{
				int v = stack.pop();
				inSCC[v] = true;
				if(v == u)
					break;
			}
			SCC++;
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
