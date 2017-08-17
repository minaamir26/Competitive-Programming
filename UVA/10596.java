package v105;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _10596_MorningWalk {
	
	static ArrayList<Integer> adj[];
	
	static void dfs(int u)
	{
		vis[u] = true;
		for(int v : adj[u])
			if(!vis[v])
				dfs(v);
	}
	static boolean[] vis;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		outer : while(sc.ready())
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int Deg[] = new int[n];
			adj = new ArrayList[n];
			for(int i=0;i<n;i++)
				adj[i] = new ArrayList<>();
			while(m-->0)
			{
				int a = sc.nextInt();
				int b = sc.nextInt();
				Deg[a]++;
				Deg[b]++;
				adj[a].add(b);
				adj[b].add(a);
			}
			vis = new boolean[n];
			int comp = 0;
			for(int i=0;i<n;i++)
				if(!vis[i] && Deg[i] > 0)
				{
					comp++;
					dfs(i);
				}
			
			if(comp != 1)
			{
				pw.println("Not Possible");
				continue outer;
			}
			for(int i=0;i<n;i++)
				if(Deg[i] % 2 != 0)
				{
					pw.println("Not Possible");
					continue outer;
				}
			pw.println("Possible");
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
