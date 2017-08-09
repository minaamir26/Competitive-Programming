import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class CAPCITY
{
	static LinkedList<Integer>[] adjList;
	static int V, counter, SCC, dfs_num[], dfs_low[];
	static int[] inSCC;
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
			if(inSCC[v] == -1)
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);	
		}
		if(dfs_num[u] == dfs_low[u])
		{
			//SCC found
			while(true)
			{
				int v = stack.pop();
				inSCC[v] = SCC;
				if(v == u)
					break;
			}
			SCC++;
		}
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		int m = sc.nextInt();
		adjList = new LinkedList[V];
		for(int i=0;i<adjList.length;i++)
			adjList[i] = new LinkedList<>();
		dfs_num = new int[V];
		dfs_low = new int[V];
		inSCC = new int[V];
		Arrays.fill(inSCC, -1);
		while(m-->0)
		{
			int u = sc.nextInt()-1;
			int v = sc.nextInt()-1;
			adjList[v].add(u);
		}
		stack = new Stack<>();
		tarjanSCC();
		int[] indeg = new int[SCC+2];
		for(int i=0;i<V;i++)
			for (Integer v : adjList[i])
				if(inSCC[i] != inSCC[v])
					indeg[inSCC[v]]++;
		LinkedList<Integer> ans = new LinkedList<>();
		for(int i=0;i<SCC;i++)
		{
			if(indeg[i] == 0)
				ans.add(i);
		}
		PrintWriter pw = new PrintWriter(System.out);
		if(ans.size() != 1)
			pw.println(0);
		else
		{
			LinkedList<Integer> ans2 = new LinkedList<>();
			for(int i=0;i<V;i++)
				if(inSCC[i] == ans.get(0))
					ans2.add(i);
			Collections.sort(ans2);
			pw.println(ans2.size());
			for(int x : ans2)
			{
				pw.print(x+1);
				pw.print(" ");
			}
		}
		pw.flush();
	}
	static class Scanner{
		StringTokenizer st;BufferedReader br;
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		public Scanner(String s) throws FileNotFoundException{	br = new BufferedReader(new FileReader(new File(s)));}
		public String next() throws IOException 
		{while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());return st.nextToken();}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		public long nextLong() throws IOException {return Long.parseLong(next());}
		public String nextLine() throws IOException {return br.readLine();}
		public double nextDouble() throws IOException {return Double.parseDouble(next());}
		public boolean ready() throws IOException {return br.ready();}
	}
}
