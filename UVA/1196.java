package v011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1196 {
	static int[] memo[];
	static Pair[] arr;
	static int n;


	static int solve(int idx , int m)
	{
		if(idx == n)
			return 0;
		if(memo[idx][m] != -1)
			return memo[idx][m];
		int ans = solve(idx+1,m);
		if(arr[idx].b >= m)
			ans = Math.max(ans, 1 + solve(idx+1,arr[idx].b));
		return memo[idx][m] = ans;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc =  new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			n = sc.nextInt();
			if(n == 0)
				break;
			arr = new Pair[n];
			for(int i=0;i<n;i++)
				arr[i] = new Pair(sc.nextInt(),sc.nextInt());
			Arrays.sort(arr);
			memo = new int[n+1][101];
			for(int i=0;i<memo.length;i++)
				Arrays.fill(memo[i], -1);
			pw.println(solve(0,0));
		}
		pw.println("*");
		pw.flush();
	}


	static class Pair implements Comparable<Pair>{
		int a , b;

		Pair(int x, int y)
		{
			a=x;
			b=y;
		}
		@Override
		public int compareTo(Pair o) {
			if(a!=o.a)
				return a-o.a;
			return b-o.b;
		}
		public String toString(){
			return a + " " + b;
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
