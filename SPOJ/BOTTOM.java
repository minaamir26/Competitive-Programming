import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOTTOM
{
	static ArrayList<Integer>[] adjList;
	static int V, counter, SCC, dfs_num[], dfs_low[];
	static int[] inSCC;
	static int[] st;
	static int s;
	static int sp;
	static void tarjanSCC()
	{
		for(int i = 0; i < V; ++i)
			if(dfs_num[i] == 0)
				tarjanSCC(i);
	}

	static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		st[sp++] = u;
		for(int v: adjList[u])
		{
			if(dfs_num[v] == 0)
				tarjanSCC(v);
			if(inSCC[v] == 0)
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);	
		}
		if(dfs_num[u] == dfs_low[u])
		{
			while(true)
			{
				int v = st[--sp];
				inSCC[v] = s;
				if(v == u)
					break;
			}
			s++;
			SCC++;
		}
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		st = new int[5010];
		while(true)
		{
			sp = 0;
			int n = sc.nextInt();
			if(n == 0) break;
			int m = sc.nextInt();
			adjList = new ArrayList[n];
			for(int i=0;i<n;i++)
				adjList[i] = new ArrayList<>();
			int[] from = new int[m];
			int[] to = new int[m];
			int k = 0;
			int mm = m;
			while(mm-- > 0)
			{
				int u = sc.nextInt()-1;
				int v = sc.nextInt()-1;
				adjList[u].add(v);
				from[k] = u;
				to[k++] = v;
			}
			inSCC = new int[n];
			dfs_low = new int[n];
			dfs_num = new int[n];
			V = n;
			counter = SCC = s = 0;
			++s;
			tarjanSCC();
			int[] outdeg = new int[s];
			for(int i=0;i<m;i++)
			{
				if(inSCC[from[i]] != inSCC[to[i]])
					outdeg[inSCC[from[i]]]++;
			}
			boolean f = true;
			for(int i=0;i<n;i++)
			{
				if(outdeg[inSCC[i]] == 0)
				{
					if(!f)
						sb.append(" ");
					else
						f = false;
					sb.append(i+1);
				}
			}
			sb.append("\n");
		}
		pw.print(sb);
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
