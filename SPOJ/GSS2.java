import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GSS2
{
	/*
	 * Very nice problem about segment trees .. I liked it very much
	 * Here is where I got the idea of the solution
	 * https://www.quora.com/How-can-the-SPOJ-problem-GSS2-be-solved
	 */
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++)
			arr[i] = sc.nextInt();
		int q = sc.nextInt();
		Pair[] queries = new Pair[q];
		for(int i=0;i<q;i++)
			queries[i] = new Pair(sc.nextInt() , sc.nextInt(), i);
		Arrays.sort(queries);
		int N = 1;
		while(N < n+1)
			N<<=1;
		SegmentTree st = new SegmentTree(new int[N+1]);
		int k = 0;
		int[] ans = new int[q];
		int[] lastOcc = new int[200010];
		int offset = 100002;
		for(int i=0;i<n;i++)
		{
			int idx = lastOcc[arr[i] + offset];
			int start = idx+1;
			st.update_range(start, i+1, arr[i]);
			while(k < q && queries[k].r <= i+1)
			{
				ans[queries[k].idx] = st.query(queries[k].l, queries[k].r);
				k++;
			}
			lastOcc[arr[i] + offset] = i + 1;
		}
		PrintWriter pw = new PrintWriter(System.out);
		for(int x : ans)
			pw.println(x);
		pw.flush();
	}
	
	static class Pair implements Comparable<Pair>{
		int l,r , idx;
		public Pair(int a, int b, int c)
		{
			l = a;
			r = b;
			idx = c;
		}
		@Override
		public int compareTo(Pair o)
		{
			return r - o.r;
		}
	}
	
	
	static  class SegmentTree {
		
		int N;
		int[] array, sTree, lazy , best , bestLazy;
		
		SegmentTree(int[] in)		
		{
			array = in; N = in.length - 1;
			sTree = new int[N<<1];
			lazy = new int[N<<1];
			bestLazy = new int[N<<1];
			best = new int[N<<1];
			build(1,1,N);
		}
		
		void build(int node, int b, int e)
		{
			if(b == e)					
				sTree[node] = array[b];
			else						
			{
				build(node<<1,b,(b+e)>>1);
				build((node<<1)|1,((b+e)>>1)+1,e);
				//sTree[node] = Math.max(sTree[node<<1] , sTree[(node<<1)|1]);
			}
		}
		void update_range(int i, int j, int val)
		{
			update_range(1,1,N,i,j,val);
		}
		
		void update_range(int node, int b, int e, int i, int j, int val)
		{
			if(i > e || j < b)		
				return;
			if(b >= i && e <= j)		
			{
				sTree[node] += val;			
				lazy[node] += val;			
				bestLazy[node] = Math.max(bestLazy[node], lazy[node]);
				best[node] = Math.max(best[node], sTree[node]);
			}							
			else		
			{
				propagate(node, b, e);
				update_range(node<<1,b,(b+e)>>1,i,j,val);
				update_range((node<<1)|1,((b+e)>>1)+1,e,i,j,val);
				sTree[node] = Math.max(sTree[node<<1] , sTree[(node<<1)|1]);		
			}
			
		}
		void propagate(int node, int b, int e)		
		{
			int child = node<<1;
			bestLazy[child] = Math.max(bestLazy[child], lazy[child] + bestLazy[node]);
			best[child]= Math.max(best[child], sTree[child] + bestLazy[node]);
			lazy[child]+=lazy[node];
			sTree[child]+=lazy[node];
			
			child = (node<<1)|1;
			bestLazy[child] = Math.max(bestLazy[child], lazy[child] + bestLazy[node]);
			best[child]= Math.max(best[child], sTree[child] + bestLazy[node]);
			lazy[child]+=lazy[node];
			sTree[child]+=lazy[node];
			
			lazy[node] = bestLazy[node] = 0;
		}
		
		int query(int i, int j)
		{
			return query(1,1,N,i,j);
		}
		int query(int node, int b, int e, int i, int j)	// O(log n)
		{
			if(i>e || j <b)
				return 0;		
			if(b>= i && e <= j)
				return best[node];
			propagate(node, b, e);
			int q1 = query(node<<1,b,(b+e)>>1,i,j);
			int q2 = query((node<<1)|1,((b+e)>>1)+1,e,i,j);
			return Math.max(q1, q2);	
					
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
