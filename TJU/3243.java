import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class _3243_BlockedRoad
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			n = sc.nextInt();
			int m = sc.nextInt();
			tree = new int[n+1];
			while(m-- > 0){
				int type = sc.nextInt();	//0 --> unblocked   		||			 1--> blocked
				if(type == 0)
				{
					int road = sc.nextInt();
					if(query(road) - query(road - 1) == 0)
						update(road, 1);
					else
						update(road, -1);
				}
				else
				{
					int a = sc.nextInt();
					int b = sc.nextInt();
					int u = Math.min(a, b);
					int v = Math.max(a, b);
					int sum = query(v-1) - query(u-1);
					int sum2 = query(u-1) + query(n) - query(v-1);
					if(sum == 0 || sum2 == 0)
						pw.println(1);
					else
						pw.println(0);
				}
			}
		}
		pw.flush();
		pw.close();
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
