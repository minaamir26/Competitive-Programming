package v105;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _10518_HowManyCalls
{
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw= new PrintWriter(System.out);
		int[][] mat = new int[][]{{0,1,0},{1,1,0},{0,1,1}};
		int[][] base = new int[][]{{1,1,1}};
		int k = 1;
		while(true)
		{
			long n = sc.nextLong();
			mod = sc.nextInt();
			if(n == 0 && mod == 0) break;
			int[][] pow = matPow(mat, n);
			int[][] ret = matMul(base, pow, base.length, base[0].length, pow.length);
			pw.printf("Case %d: %d %d %d\n", k++,n,mod,ret[0][0]);
		}
		pw.flush();
	}
	static int mod;

	static int[][] matMul(int[][] A, int[][] B, int p, int q, int r)	
	{ 
		int[][] C = new int[p][r];
		for(int i = 0; i < p; ++i)
			for(int j = 0; j < r; ++j)
				for(int k = 0; k < q; ++k)
					C[i][j] =(int)( (C[i][j] %mod + 1l*A[i][k] * B[k][j]%mod)%mod);
		return C;
	}
	static int[][] matPow(int[][] base, long p)
	{ 
		int n = base.length;
		int[][] ans = new int[n][n];
		for(int i = 0; i < n; i++)
			ans[i][i] = 1;
		while(p != 0)
		{ 
			if((p & 1) == 1)
				ans = matMul(ans, base, n, n, n);
			base = matMul(base, base, n, n, n);
			p >>= 1;
		} 
		return ans;
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
