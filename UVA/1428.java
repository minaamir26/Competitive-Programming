package v014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _1428_PingPong
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-- > 0)
		{
			n = 100000+10;
			tree = new int[n+10];
			int m = sc.nextInt();
			int[] arr = new int[m];
			for(int i=0;i<m;i++)
				arr[i] = sc.nextInt();
			int[] larger = new int[m];
			int[] smaller = new int[m];
			for(int i= m-1;i>=0;i--)
			{
				larger[i] = query(n) - query(arr[i]);
				update(arr[i], 1);
			}
			tree = new int[n+10];
			for (int i = 0; i < arr.length; i++)
			{
				smaller[i] = query(arr[i]-1);
				update(arr[i], 1);
			}
			int[] larger2 = new int[m];
			int[] smaller2 = new int[m];
			tree = new int[n+10];
			for(int i= m-1;i>=0;i--)
			{
				smaller2[i] = query(arr[i]-1);
				update(arr[i], 1);
				
			}
			tree = new int[n+10];
			for (int i = 0; i < arr.length; i++)
			{
				larger2[i] = query(n) - query(arr[i]);
				update(arr[i], 1);
			}
			
			
			long ans = 0;
			for (int i = 0; i < m; i++)
				ans += 1l * larger[i] * smaller[i] + 1l*smaller2[i]*larger2[i];
			pw.println(ans);
		}
		pw.flush();
	}

	static int[] tree;
	static int n;
	static int query(int idx)
	{
		int ret = 0;
		while(idx > 0)
		{
			ret+=tree[idx];
			idx-=(idx & -idx);
		}
		return ret;
	}
	static void update(int idx , int val)
	{
		while(idx <= n)
		{
			tree[idx]+=val;
			idx+=(idx & -idx);
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
