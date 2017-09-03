package Codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BagOfMice_148D
{
	static double[][][] memo;

	static double solve(int w, int b, int turn)
	{
		if(w == 0 && b == 0)
			return 0;
		if(w < 0 || b < 0)
			return 0;


		if(memo[turn][w][b] != -1)
			return memo[turn][w][b];

		double ret = 0;
		if(turn == 0)
		{
			ret = 1.0 * w / (w+b) + 1.0 * b / (w+b) * solve(w,b-1,1);
		}
		else
		{
			if(w + b - 1 > 0)
			{
				ret+= 1.0 * b / (w+b) * 1.0 * (b-1) / (w+b-1) * solve(w, b-2, 0);
				ret+= 1.0 * b / (w+b) * 1.0 * (w) / (w+b-1) * solve(w-1, b-1, 0);
			}
		}
		return memo[turn][w][b] = ret;
	}


	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int b = sc.nextInt();

		memo = new double[3][w+1][b+1];
		for(double [][] c : memo)
			for(double[] cc : c)
				Arrays.fill(cc, -1);
		System.out.println(solve(w, b, 0));
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
