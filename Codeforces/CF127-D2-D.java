package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Password_127D
{
	static int n , pi[];
	static void prefixFunction(char[]s)
	{
		n = s.length;
		pi = new int[n];
		for(int i=1,j=0;i<s.length;i++)
		{
			while(j>0 && s[i]!=s[j])
				j = pi[j-1];
			if(s[i] == s[j])
				++j;
			pi[i] = j;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String ss = sc.next();
		char[] s = ss.toCharArray();
		PrintWriter pw = new PrintWriter(System.out);
		prefixFunction(s);
		boolean[] found = new boolean[n+1];
		for(int i=pi.length-2;i>=0;i--)
			found[pi[i]] = true;
		int cur = pi[s.length-1];
		while(cur != 0)
		{
			if(found[cur])
				break;
			cur = pi[cur-1];
		}
		if(cur > 0)
			pw.println(ss.substring(0, cur));
		else
			pw.println("Just a legend");
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
