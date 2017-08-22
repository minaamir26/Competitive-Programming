import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CDC12_H
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int tc= sc.nextInt();
		int k = 1;
		while(tc-- > 0)
		{
			pw.printf("Scenario #%d:\n", k++);
			int n = sc.nextInt();
			int q= sc.nextInt();
			int N = 1;
			while(N < n)
				N<<=1;
			int[] arr = new int[N+1];
			SegmentTree st = new SegmentTree(arr);
			while(q-- > 0)
			{
				String s = sc.next();
				if(s.charAt(0) == 'a')
				{
					int l = sc.nextInt();
					int r = sc.nextInt();
					pw.println(st.query(l, r));
				}
				else
				{
					int l = sc.nextInt();
					int r = sc.nextInt();
					st.update_range(l, r,1);
					pw.println("OK");
				}
			}
		}
		pw.flush();
		pw.close();
	}

	static  class SegmentTree {		// 1-based DS, OOP

		int N;
		int[] array, sTree, lazy;

		SegmentTree(int[] in)		
		{
			array = in; N = in.length - 1;
			sTree = new int[N<<1];
			lazy = new int[N<<1];
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
				sTree[node] = sTree[node<<1]+sTree[(node<<1)|1];
			}
		}

		void update_point(int index, int val)			// O(log n)
		{
			index += N - 1;				
			sTree[index] += val;			
			while(index>1)				
			{
				index >>= 1;
				sTree[index] = sTree[index<<1] + sTree[(index<<1) | 1];		
			}
		}


		void update_range(int i, int j, int val)		// O(log n) 
		{
			update_range(1,1,N,i,j,val);
		}

		void update_range(int node, int b, int e, int i, int j, int val)
		{
			if(i > e || j < b)		
				return;
			if(b >= i && e <= j)		
			{
				sTree[node] += (e-b+1)*val;			
				lazy[node] += val;				
			}							
			else		
			{
				propagate(node, b, e);
				update_range(node<<1,b,(b+e)>>1,i,j,val);
				update_range((node<<1)|1,((b+e)>>1)+1,e,i,j,val);
				sTree[node] = sTree[node<<1] + sTree[(node<<1)|1];		
			}

		}
		void propagate(int node, int b, int e)		
		{
			int mid = (b+e)>>1;
				lazy[node<<1] += lazy[node];
				lazy[(node<<1)+1] += lazy[node];
				sTree[node<<1] += (mid-b+1)*lazy[node];		
				sTree[(node<<1)+1] += (e-mid)*lazy[node];		
				lazy[node] = 0;
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
				return sTree[node];
			propagate(node, b, e);
			int q1 = query(node<<1,b,(b+e)>>1,i,j);
			int q2 = query((node<<1)|1,((b+e)>>1)+1,e,i,j);
			return q1 + q2;	

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
