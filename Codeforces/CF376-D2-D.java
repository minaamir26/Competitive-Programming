package Codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaximumSubMatrix2_376D
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] arr = new int[n][m];
		for(int i=0;i<n;i++)
		{
			String s = sc.next();
			for(int j=0;j<m;j++)
				arr[i][j] = s.charAt(j)-'0';
		}
		int[][] right = new int[n][m];
		for(int i=0;i<n;i++)
		{
			right[i][m-1] = arr[i][m-1];
			for(int j=m-2;j>=0;j--)
				if(arr[i][j] == 1)
					right[i][j] = 1 + right[i][j+1];
		}
		int max = 0;
		for(int j=0;j<m;j++)
		{
			int[] in = new int[n];
			for(int i=0;i<n;i++)
				in[i] = right[i][j];
			Arrays.sort(in);
			for(int i=n-1;i>=0;i--)
				max = Math.max(max, (n-i)*in[i]);
		}
		pw.println(max);
		pw.flush();

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
