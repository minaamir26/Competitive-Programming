import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.HashMap;

public class ORDERSET
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc =new Scanner(System.in);
		int n = sc.nextInt();
		char[] q = new char[n];
		int[] val = new int[n];
		Pair[] tmp = new Pair[n];
		for(int i=0;i<n;i++)
		{
			q[i] = sc.next().charAt(0);
			val[i] = sc.nextInt();
			tmp[i] = new Pair(val[i],i);
		}
		int k = 1;
		Arrays.sort(tmp);
		HashMap<Integer, Integer> map = new HashMap<>();
		HashMap<Integer, Integer> back = new HashMap<>();

		for(int i=0; i < n; i++)
			if(q[tmp[i].idx] != 'K')
			{
				Integer now = map.get(tmp[i]);
				if(now == null)
				{
					map.put(tmp[i].n,k);
					back.put(k, tmp[i].n);
					k++;
				}
			}
		for(int i=0;i<n;i++)
			if(q[i] != 'K')
				val[i] = map.get(val[i]);
		k++;
		FenwickTree ft = new FenwickTree(k);
		PrintWriter pw = new PrintWriter(System.out);
		for(int i=0;i<n;i++)
		{
			if(q[i] == 'I')
			{
				int now = ft.query(val[i]) - ft.query(val[i]-1);
				if(now == 0)
					ft.update(val[i], 1);
			}
			else
				if(q[i] == 'D')
				{
					int now = ft.query(val[i]) - ft.query(val[i]-1);
					if(now == 1)
						ft.update(val[i], -1);
				}
				else
					if(q[i] == 'K')
					{
						int ans = ft.findS(val[i]);
						if(ans == -1)
							pw.println("invalid");
						else
							pw.println(back.get(ans));
					}
					else
						pw.println(ft.query(val[i]-1));
		}
		pw.flush();
	}

	static class FenwickTree
	{
		public FenwickTree(int nn)
		{
			n = nn;
			tree = new int[n+1];
		}
		int[] tree;
		int n;
		int query(int idx)
		{
			int ret = 0;
			while(idx > 0)
			{
				ret+=tree[idx];
				idx-=(idx & -idx);
			}
			return ret;
		}
		void update(int idx , int val)
		{
			while(idx <= n)
			{
				tree[idx]+=val;
				idx+=(idx & -idx);
			}
		}

		int find(int cumFre){					//O(log(n)*log(n))

			int lo=1,hi=n-1,ans=-1;
			while(lo <= hi)
			{
				int mid= ((lo+hi)>>1);
				int q = query(mid);
				if(q >= cumFre)
					hi = mid - 1;
				else
					lo= mid + 1;
				if(q == cumFre)
					ans = mid;
			}
			return ans;
		}

		int findS(int cumFre){						//O(log(n))
			int idx = 0;
			int bitMask = Integer.highestOneBit(n);
			int ans = -1;
			while ((bitMask != 0) && (idx < n)){
				int tIdx = idx + bitMask;
				if(tIdx < n)
				{
					if (cumFre == tree[tIdx])
					{
						ans = tIdx;
					}
					else if (cumFre > tree[tIdx]){
						idx = tIdx;
						cumFre -= tree[tIdx];
					}
				}
				bitMask >>= 1;
			}
			if (ans == -1 && cumFre != 0)
				return -1;
			else
				return ans;
		}
	}

	static class Pair implements Comparable<Pair>{
		int n,idx;
		public Pair(int a,int b)
		{
			n = a;
			idx = b;
		}
		@Override
		public int compareTo(Pair o)
		{
			return n - o.n;
		}
	}

	static class Scanner
	{
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
