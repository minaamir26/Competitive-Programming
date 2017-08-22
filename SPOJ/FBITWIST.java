import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FBITWIST
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int[] arr[] = new int[][]{{0,1,0,0} , {1,1,0,0} , {0,1,1,0}, {0,0,1,1}};
		int[] base[] = new int[][]{{0,1,1,1}};
		PrintWriter pw = new PrintWriter(System.out);
		while(tc-- > 0)
		{
			int n = sc.nextInt();
			mod = sc.nextInt();
			int[][] pow = matPow(arr, n);
			pw.println(matMul(base, pow, 1, 4, 4)[0][0]);
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
