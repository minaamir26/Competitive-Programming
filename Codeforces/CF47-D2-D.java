package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Safe_47D
{
	static String[] arr;
	static int[] good;
	static int n,m;			//13
	static int ans ;
	static void solve(int pos)		// pos --> current position to be checked
	{
		if(pos == n)
		{
			for(int i=0;i<m;i++)
				if(good[i] != 0)
					return;
			ans++;
			return;
		}
		for(int i=0;i<m;i++)
			if(good[i] > n - pos)
				return;
		boolean ok = true;
		for(int i=0;i<m;i++)
			if(arr[i].charAt(pos) == '0')
			{
				if(good[i] == 0)
					ok =false;
			}
		if(ok)
		{
			for(int i = 0; i < m; i++)
				if(arr[i].charAt(pos) == '0')
					good[i]--;
			solve(pos+1);
			for(int i = 0; i < m; i++)
				if(arr[i].charAt(pos) == '0')
					good[i]++;
		}
		ok = true;
		for(int i=0;i<m;i++)
			if(arr[i].charAt(pos) == '1')
			{
				if(good[i] <= 0)
				{
					ok =false;
					break;
				}
			}
		if(!ok)
			return;
		for(int i = 0; i < m; i++)
			if(arr[i].charAt(pos) == '1')
				good[i]--;
		solve(pos+1);
		for(int i = 0; i < m; i++)
			if(arr[i].charAt(pos) == '1')
				good[i]++;

	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		arr = new String[m];
		good = new int[m];
		for (int i = 0; i < m; i++)
		{
			arr[i] = sc.next();
			good[i] = sc.nextInt();
		}
		solve(0);
		System.out.println(ans);
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
