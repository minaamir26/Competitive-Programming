package v001;

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

public class _117_ThePostalWorkerRingsOnce
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(sc.ready())
		{
			int[] deg = new int[26];
			int[][] dist = new int[26][26];
			for(int i=0;i<26;i++)
				for(int j=0;j<26;j++)
					if(i != j)
						dist[i][j] = (int)1e7;
			int sum = 0;
			ArrayList<Integer> odds = new ArrayList<>();
			while(true)
			{
				String s = sc.next();
				if(s.equals("deadend"))
					break;
				int fst = s.charAt(0) - 'a';
				int scd = s.charAt(s.length()-1) - 'a';
				dist[fst][scd] = s.length();
				dist[scd][fst] = s.length();
				deg[fst]++;
				deg[scd]++;
				sum+=s.length();
			}
			int odd = 0;
			for(int i=0;i<26;i++)
				if(deg[i] % 2 == 1)
				{
					odd++;
					odds.add(i);
				}
			if(odd == 0)
				pw.println(sum);
			else
			{
				int V = 26;
				for(int k = 0; k < V; k++)
					for(int i = 0; i < V; i++)
						for(int j = 0; j < V; j++)
							if(dist[i][j] > dist[i][k] + dist[k][j])
								dist[i][j] = dist[i][k] + dist[k][j];
				pw.println(sum + dist[odds.get(0)][odds.get(1)]);
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