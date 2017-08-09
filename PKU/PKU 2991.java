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

public class _2991_Crane
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		cc = new double[360];
		ss = new double[360];
		for(int i=0;i<360;i++)
		{
			cc[i] = Math.cos(i*Math.PI /180.0);
			ss[i] = Math.sin(i*Math.PI /180.0);
		}
		
		PrintWriter pw = new PrintWriter(System.out);
		boolean first = false;
		while(sc.ready())
		{
			if(!sc.ready())
				break;
			if(!first)
				pw.println();
			else
				first = false;
			int n = sc.nextInt();
			if(!sc.ready())
				break;
			int N = 1;
			while(N<n)
				N<<=1;
			int c = sc.nextInt();
			int[] angles = new int[n+1];
			Arrays.fill(angles, 180);
			Point[] arr = new Point[N+1];
			for(int i=0;i<n;i++)
			{
				arr[i+1] = new Point(0,sc.nextInt());
			}
			SegmentTree st = new SegmentTree(arr);
			while(c-->0)
			{
				int idx = sc.nextInt();
				int angle = sc.nextInt();
				int delta = angle - angles[idx];
				st.update_range(idx+1, n, delta);
				angles[idx] = angle;
				Point ans= st.query(1, n);
				pw.printf("%.2f %.2f\n", ans.x , ans.y);
			}
			pw.flush();
		}
	}
	
	static class Point {
		static final double EPS = 1e-9;
		double x, y;                  
		Point(double a, double b){x = a; y = b;}  
		public String toString(){return x + " " + y;}
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
	
	static double[] cc,ss;
	
	static class SegmentTree{
		int N; 			//the number of elements in the array as a power of 2 (i.e. after padding)
		Point[] array, sTree;
		int lazy[];
		
		SegmentTree(Point[] in)		
		{
			array = in; N = in.length - 1;
			sTree = new Point[N<<1];		//no. of nodes = 2*N - 1, we add one to cross out index zero
			lazy = new int[N<<1];
			build(1,1,N);
		}
		
		void build(int node, int b, int e)	// O(n)
		{
			if(b == e)					
				sTree[node] = array[b];
			else						
			{
				build(node<<1,b,(b+e)>>1);
				build((node<<1)+1,((b+e)>>1)+1,e);
				sTree[node] = combine(sTree[node<<1],sTree[(node<<1)|1]);
			}
		}
		
		void update_range(int i, int j, int val)		// O(log n) 
		{
			update_range(1,1,N,i,j,val);
		}
		
		Point change(Point x, int val)
		{
			if(x == null)
				return null;
			val%=360;
			if(val < 0)
				val+=360;
			double c = cc[val];
			double s = ss[val];
			double xx = x.x * c - s * x.y;
			double yy = x.x * s + c * x.y;
			return new Point(xx,yy);
		}
		
		void update_range(int node, int b, int e, int i, int j, int val)
		{
			if(i > e || j < b)		
				return;
			if(b >= i && e <= j)		
			{
				sTree[node] = change(sTree[node],val);			
				lazy[node] += val;				
			}							
			else		
			{
				propagate(node, b, e);
				update_range(node<<1,b,(b+e)>>1,i,j,val);
				update_range((node<<1)+1,((b+e)>>1)+1,e,i,j,val);
				sTree[node] = combine(sTree[node<<1] , sTree[(node<<1)|1]);		
			}
			
		}
		void propagate(int node, int b, int e)		
		{
			lazy[node<<1] += lazy[node];
			lazy[(node<<1)|1] += lazy[node];
			sTree[node<<1] = change(sTree[node<<1],lazy[node]);		
			sTree[(node<<1)|1] = change(sTree[(node<<1)|1],lazy[node]);		
			lazy[node] = 0;
		}
		
		Point query(int i, int j)
		{
			return query(1,1,N,i,j);
		}
		
		Point query(int node, int b, int e, int i, int j)	// O(log n)
		{
			if(i>e || j <b)
				return null;		
			if(b>= i && e <= j)
				return sTree[node];
			propagate(node, b, e);
			Point q1 = query(node<<1,b,(b+e)>>1,i,j);
			Point q2 = query((node<<1)+1,((b+e)>>1)+1,e,i,j);
			return combine(q1 , q2);	
		}
		
		Point combine(Point a , Point b)
		{
			if(a == null)
				return b;
			if(b == null)
				return a;
			return new Point(b.x + a.x , a.y + b.y);
		}
	}
}
