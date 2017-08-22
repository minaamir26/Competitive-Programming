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

public class Pawn_41D
{
	static int n,m,k;
	static int[] arr[];
	static int memo[][][];

	static int solve(int r, int c, int mod)
	{
		if(r == n)
			if(mod == 0)
				return 0;
			else
				return -(int)1e8;

		if(memo[r][c][mod] != -1)
			return memo[r][c][mod];

		int ret = arr[r][c];
		int add = -(int)1e8;
		if(c > 0)
			add = Math.max(add, solve(r+1,c-1,(mod+ret)%k));
		if(c < m-1)
			add = Math.max(add, solve(r+1,c+1,(mod+ret)%k));
		return memo[r][c][mod] = ret + add;
	}

	static StringBuilder sb;
	static void print(int r, int c, int mod)
	{
		if(r == n)
			return;
		int ret = arr[r][c];
		if(c > 0)
		{
			if(solve(r,c,mod) == ret + solve(r+1,c-1,(mod+ret)%k))
			{
				sb.append('L');
				print(r+1,c-1,(mod+ret)%k);
				return;
			}
		}
		if(c < m-1)
		{
			if(solve(r,c,mod) == ret + solve(r+1,c+1,(mod+ret)%k))
			{
				sb.append('R');
				print(r+1,c+1,(mod+ret)%k);
				return;
			}
		}
	}




	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		k++;
		arr = new int[n][m];
		for(int i=0;i<n;i++)
		{
			String s = sc.next();
			for(int j=0;j<m;j++)
				arr[n-1-i][j] = s.charAt(j)-'0';
		}
		memo = new int[n+1][m+1][11];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				Arrays.fill(memo[i][j], -1);
		int max = -(int)1e8;
		int maxI = -1;
		for(int i=0;i<m;i++)
		{
			int cur = solve(0,i,0);
			if(cur < 0)
				continue;
			if(cur > max)
			{
				max = cur;
				maxI = i;
			}
		}
		if(max < 0)
		{
			System.out.println(-1);
			return;
		}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(max);
		sb = new StringBuilder();
		print(0, maxI, 0);
		pw.println(maxI+1);
		pw.println(sb.substring(0,sb.length()-1));

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
