package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _10453_MakePalindrome
{
	static char[] s;
	static int[] memo[];
	static int solve(int i, int j)
	{
		if(j - i <= 0)
			return 0;
		if(memo[i][j] != -1)
			return memo[i][j];
		int ans = 0;
		if(s[i] == s[j])
			ans = solve(i+1, j-1);
		else
			ans = 1 + Math.min(solve(i+1,j), solve(i,j-1));
		return memo[i][j] = ans;
	}
	
	static void print(int i, int j)
	{
		if(j < i)
			return;
		if(j - i == 0)
		{
			a.add(s[i]);
			return ;
		}
		if(s[i] == s[j])
		{
			a.add(s[i]);
			b.add(s[j]);
			print(i+1, j-1);
		}
		else
		{
			if(memo[i][j] == 1 + solve(i+1,j))
			{
				a.add(s[i]);
				b.add(s[i]);
				print(i+1 , j);
			}
			else
			{
				a.add(s[j]);
				b.add(s[j]);
				print(i, j-1);
			}
		}
	}
	static ArrayList<Character> a;
	static ArrayList<Character> b;
	
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(sc.ready())
		{
			s = sc.next().toCharArray();
			memo = new int[s.length][s.length];
			for(int i=0;i<memo.length;i++)
				Arrays.fill(memo[i], -1);
			int k = solve(0,s.length-1);
			a =new ArrayList<>();
			b = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			print(0,s.length-1);
			for(char c : a)
				sb.append(c);
			for(int i=b.size()-1;i>=0;i--)
				sb.append(b.get(i));
			pw.print(k +" ");
			pw.println(sb);
		}
		pw.flush();
	}
	static class Scanner
	{
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
