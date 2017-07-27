
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SPOJ_NOCHANGE {

	static int n,k;
	static Boolean[][] memo;
	static int[] arr;

	static boolean solve(int money , int idx)
	{
		if(idx == k)
			if(money == 0)
				return true;
			else
				return false;

		if(memo[money][idx] != null)
			return memo[money][idx];
		boolean ret = false;
		if(money-arr[idx] >=0)
		{
			ret|=solve(money-arr[idx], idx);
			ret|=solve(money-arr[idx], idx+1);
		}
		ret|=solve(money, idx+1);

		return memo[money][idx] = ret;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		arr = new int[k];
		for (int i = 0; i < k; i++) {
			arr[k-i-1] = sc.nextInt();
		}

		for(int i=k-2;i>=0;i--)
			arr[i] = arr[i+1]+arr[i];
		memo = new Boolean[100001][k+1];
		int st = 1;
		while(st < n)
		{
			for(int i=0;i<k;i++)
				solve(st, i);
			st++;
		}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(solve(n, 0)?"YES" : "NO");
		pw.flush();
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
