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

public class TJU_2241
{
	static ArrayList<Integer> adj[];
	static boolean[] vis;
	static int leave[], enter[];
	static int t;

	static void dfs(int u)
	{
		vis[u] = true;
		enter[u] = t++;
		for(int v : adj[u])
			if(!vis[v])
				dfs(v);
		leave[u] = t++;
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		PrintWriter pw = new PrintWriter(System.out);
		while(tc-->0)
		{
			int n = sc.nextInt();
			t = 0;
			adj = new ArrayList[n];
			for(int i=0;i<n;i++)
				adj[i] = new ArrayList<Integer>();
			vis = new boolean[n];
			enter = new int[n];
			leave = new int[n];
			for(int i=0;i<n-1;i++)
			{
				int u = sc.nextInt();
				int v = sc.nextInt();
				adj[v].add(u);
			}
			dfs(0);
			int q = sc.nextInt();
			while(q-->0)
			{
				int a = sc.nextInt();
				int b = sc.nextInt();

				if(enter[b] > enter[a] && enter[b] < leave[a])
					pw.println("Yes");
				else
					pw.println("No");
			}
			pw.println();
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
