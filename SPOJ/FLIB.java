import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FLIB
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int[] mat[] = new int[][]{{1,0,0},{1,2,3},{1,3,5}};
		int[][] base = new int[][]{{0,1,1}};
		int tc = sc.nextInt();
		while(tc-- > 0)
		{
			long n = sc.nextLong();
			
			int[][] res = matMul(base, matPow(mat, n), 1, 3, 3);
			pw.println(res[0][0]);
		}
		pw.flush();
	}
	
	static int mod = (int)1e9+7;
	static int[][] matMul(int[][] A, int[][] B, int p, int q, int r)	
	{ 
		int[][] C = new int[p][r];
		for(int i = 0; i < p; ++i)
			for(int j = 0; j < r; ++j)
				for(int k = 0; k < q; ++k)
				{
					long x = C[i][j] + 1l*A[i][k] * B[k][j];
					x%=mod;
					C[i][j] = (int)x;
				}
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
