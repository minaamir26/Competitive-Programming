import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SUMSUMS
{

	static int mod = 98765431;

	static int[][] matMul(int[][] A, int[][] B, int p, int q, int r)	
	{ 
		int[][] C = new int[p][r];
		for(int i = 0; i < p; ++i)
			for(int j = 0; j < r; ++j)
				for(int k = 0; k < q; ++k)
					C[i][j] =(int)((C[i][j] %mod + (1l*A[i][k] * B[k][j])%mod)%mod);
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

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int t = sc.nextInt();

		int[] arr = new int[n];
		int sum = 0;
		for(int i=0;i<n;i++)
		{
			arr[i] = sc.nextInt()%mod;
			sum = (sum + arr[i])%mod;
		}

		int[][] mat = new int[2][2];
		mat[0][0] = 0;
		mat[0][1] = 1;
		mat[1][0] = n-1;
		mat[1][1] = n-2;

		int[][] base = new int[1][2];
		base[0][0] = 0;
		base[0][1] = 1;
		
		mat = matPow(mat, t-1);
		base = matMul(base, mat, 1, 2, 2);
		int a = base[0][0];
		int b = base[0][1];
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append(((1l * arr[i] * a )%mod +(((      0L + sum - arr[i] + mod+mod   )  %mod) * b) %mod)%mod).append("\n");
		pw.println(sb);
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
