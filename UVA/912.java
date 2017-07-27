package v009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _912 {

	static ArrayList<Integer> adj[];

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				pw.println();
			int n = sc.nextInt();
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<n;i++)
				sb.append(sc.next());
			String s1 = sb.toString();
			sb = new StringBuilder();
			for(int i=0;i<n;i++)
				sb.append(sc.next());
			String s2 = sb.toString();

			UnionFind uf = new UnionFind(10);

			for(int i=0;i<s1.length();i++)
				if(Character.isDigit(s1.charAt(i)) && Character.isDigit(s2.charAt(i)))
					uf.unionSet(s1.charAt(i)-'0',(s2.charAt(i)-'0'));

			boolean can = true;
			char[] map = new char[10];
			Arrays.fill(map, '*');
			for(int i=0;i<s1.length();i++)
			{
				if(Character.isDigit(s1.charAt(i)) && !Character.isDigit(s2.charAt(i)))
				{
					if(map[uf.findSet(s1.charAt(i)-'0')] == '*' || map[uf.findSet(s1.charAt(i) - '0')] == s2.charAt(i))
						map[uf.findSet(s1.charAt(i)-'0')] = s2.charAt(i);
					else
						can = false;
				}
				if(!Character.isDigit(s1.charAt(i)) && Character.isDigit(s2.charAt(i)))
				{
					if(map[uf.findSet(s2.charAt(i)-'0')] == '*' || map[uf.findSet(s2.charAt(i) - '0')] == s1.charAt(i))
						map[uf.findSet(s2.charAt(i)-'0')] = s1.charAt(i);
					else
						can = false;
				}
				if(!Character.isDigit(s1.charAt(i)) && !Character.isDigit(s2.charAt(i)))
				{
					if(s1.charAt(i) != s2.charAt(i))
						can = false;
				}
			}
			
			if(!can)
				pw.println("NO");
			else
			{
				pw.println("YES");
				for(int i=1;i<10;i++)
				{
					if(map[uf.findSet(i)] != '*')
						pw.println(i + " " + map[uf.findSet(i)]);
				}
			}
		}
		pw.flush();
	}
	static class UnionFind {                                              
		int[] p, rank, setSize;
		int numSets;
		public UnionFind(int N) 
		{
			p = new int[N];
			rank = new int[N];
			setSize = new int[N];
			numSets = N;
			for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
		}
		public int findSet(int i) 
		{ 
			if (p[i] == i) return i;
			else return p[i] = findSet(p[i]);
		}
		public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
		public void unionSet(int i, int j) 
		{ 
			if (isSameSet(i, j)) 
				return;
			numSets--; 
			int x = findSet(i), y = findSet(j);
			if (rank[x] > rank[y]) 
			{ 
				p[y] = x;
				setSize[x] += setSize[y]; 
			}
			else
			{	
				p[x] = y;
				setSize[y] += setSize[x];
				if(rank[x] == rank[y])
					rank[y]++; 
			} 
		}
		public int numDisjointSets() { return numSets; }
		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
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
