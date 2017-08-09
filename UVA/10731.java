package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class _10731_Test
{
	
	static ArrayList<Integer>[] adjList;
	static int V, counter, SCC, dfs_num[], dfs_low[];
	static boolean[] inSCC;
	static Stack<Integer> stack;
	static ArrayList<ArrayList<Integer>> comps;
	
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
			comps.add(new ArrayList<>());
			while(true)
			{
				int v = stack.pop();
				inSCC[v] = true;
				comps.get(comps.size()-1).add(v);
				if(v == u)
					break;
			}
			SCC++;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		boolean fst = true;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			if(fst)
				fst = false;
			else
				pw.println();
			V = 26;
			dfs_num = new int[V];
			dfs_low = new int[V];
			inSCC = new boolean[V];
			stack = new Stack<>();
			comps = new ArrayList<>();
			counter = 0;
			SCC = 0;
			adjList = new ArrayList[V];
			int[] vis = new int[26];
			for(int i=0;i<V;i++)
				adjList[i] = new ArrayList<>();
			for (int i = 0; i < n; i++)
			{
				int[] arr = new int[5];
				for (int j = 0; j < arr.length; j++)
				{
					arr[j] = sc.next().charAt(0)-'A';
					vis[arr[j]]++;
				}
				int u = sc.next().charAt(0)-'A';
				for(int v : arr)
					if(v != u)
						adjList[u].add(v);
			}
			tarjanSCC();
			ArrayList<String> ans = new ArrayList<>();
			for(ArrayList<Integer> x : comps)
			{
				Collections.sort(x);
				if(vis[x.get(0)] > 0)
				{
					StringBuilder sb = new StringBuilder();
					for(int i=0;i<x.size();i++)
					{
						if(i != 0)
							sb.append(' ');
						sb.append((char)('A'+x.get(i)));
					}
					ans.add(sb.toString());
				}
			}
			Collections.sort(ans);
			for(String x : ans)
				pw.println(x);
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
