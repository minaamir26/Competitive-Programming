import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _2300
{
	static int mod = 10000;
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			int[][] a = new int[1][n];
			int[] b = new int[n];
			for(int i=0;i<n;i++)
				a[0][i] = sc.nextInt();
			for(int i=0;i<n;i++)
				b[i] = sc.nextInt();
			int k = sc.nextInt();
			if(k < n)
			{
				sb.append(a[0][k]).append("\n");
			}
			else
			{
				int[][] matrix = new int[n][n];
				for(int i=0;i<n;i++)
					matrix[i][n-1] = b[n-i-1];
				for(int i=0;i<n;i++)
					for(int j=0;j<n-1;j++)
					{
						if(j == i - 1)
							matrix[i][j] = 1;
					}
				int[][] pow = matPow(matrix, k-n+1);
				int[][] fin = matMul(a, pow, 1, n, n);
				sb.append(fin[0][n-1]).append("\n");
			}
		}
		pw.print(sb);
		pw.flush();
	}
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
		public Scanner(String s) throws FileNotFoundException{	br = new BufferedReader(new FileReader(new File(s)));}
		public String next() throws IOException 
		{while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());return st.nextToken();}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		public long nextLong() throws IOException {return Long.parseLong(next());}
		public String nextLine() throws IOException {return br.readLine();}
		public double nextDouble() throws IOException {return Double.parseDouble(next());}
		public boolean ready() throws IOException {return br.ready();}
	}
}
