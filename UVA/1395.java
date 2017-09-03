package v013;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1395_SlimSpan
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0) break;
			Edge[] edges = new Edge[m];
			int k = 0;
			while(m-- > 0)
				edges[k++] = new Edge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt());
			Arrays.sort(edges);
			int min = 1000000000;
			for(int i=0;i<edges.length;i++)
			{
				int lo=i, hi = edges.length-1, ans = 1000000000;
				while(lo <= hi){
					int mid = ((lo + hi)>>1);
					UnionFind uf = new UnionFind(n);
					for(int j=i;j<=mid;j++)
						uf.unionSet(edges[j].from, edges[j].to);
					if(uf.numSets == 1)
					{
						ans = Math.min(ans, edges[mid].cost - edges[i].cost);
						hi = mid - 1;
					}
					else
						lo = mid + 1;
				}
				min = Math.min(min, ans);
			}
			if(min >= 10000000)
				min = -1;
			pw.println(min);
		}
		pw.flush();
	}


	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		public Edge(int a, int b, int c)
		{
			from = a;
			to = b;
			cost = c;
		}
		@Override
		public int compareTo(Edge o)
		{
			return cost -o.cost;
		}
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
			// rank is used to keep the tree short
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
}
