import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class POSTERS
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		PrintWriter pw = new PrintWriter(System.out);
		while(tc-->0)
		{
			int n = sc.nextInt();
			int[] x = new int[n];
			int[] y = new int[n];
			HashMap<Integer, Integer> map = new HashMap<>();
			int k = 0;
			int[] arr = new int[2*n];
			for (int i = 0; i < n; i++)
			{
				x[i] = sc.nextInt();
				arr[k++] = x[i];
				y[i] = sc.nextInt();
				arr[k++] = y[i];
			}
			
			Arrays.sort(arr);
			k = 1;
			for (int i = 0; i < arr.length; i++)
			{
				Integer now = map.get(arr[i]);
				if(now == null)
					map.put(arr[i], k++);
			}
			
			
			int MAX = k;
			int N =1;
			while(N<MAX)
				N<<=1;
			MAX = N;
			
//			st.update_range(s, e, i);
			
			SegmentTree st = new SegmentTree(new int[MAX]);
			for (int i = 1; i <= n; i++)
			{
				int s = map.get(x[i-1]);
				int e = map.get(y[i-1]);
				st.update_range(s, e, i);
			}
			boolean vis[]= new boolean[MAX+1];
			for (int i = 1; i < MAX; i++)
				vis[st.query(i, i)] = true;
			int cnt = 0;
			for (int i = 1; i < vis.length; i++)
				if(vis[i])
					cnt++;
			pw.println(cnt);
		}
		pw.flush();
	}

	static class SegmentTree {

		int N;
		int[] array, sTree, lazy;

		SegmentTree(int[] in)		
		{
			array = in; N = in.length - 1;
			sTree = new int[N<<1];
			lazy = new int[N<<1];
			build(1,1,N);
		}

		void build(int node, int b, int e)	// O(n)
		{

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
				sTree[node] = val;			
				lazy[node] = val;				
			}							
			else		
			{
				propagate(node, b, e);
				update_range(node<<1,b,(b+e)/2,i,j,val);
				update_range((node<<1)+1,(b+e)/2+1,e,i,j,val);
				sTree[node] = sTree[node<<1] + sTree[(node<<1)+1];		
			}

		}
		void propagate(int node, int b, int e)		
		{
			//			int mid = (b+e)/2;
			if(lazy[node] != 0)
			{
				lazy[node<<1] = lazy[node];
				lazy[(node<<1)+1] = lazy[node];
				sTree[node<<1] = lazy[node];		
				sTree[(node<<1)+1] = lazy[node];		
				lazy[node] = 0;
			}
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
			int q1 = query(node<<1,b,(b+e)/2,i,j);
			int q2 = query((node<<1)+1,(b+e)/2+1,e,i,j);
			return q1 + q2;	

		}

	}
}