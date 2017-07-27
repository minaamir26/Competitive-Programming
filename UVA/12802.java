package v128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _12802 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		
		boolean[] isComposite = new boolean[1000001];
		isComposite[0] = isComposite[1] = true;
		for(int i=2;i<=1000;i++)
		{
			if(!isComposite[i])
			{
				for(long j=1l*i*i;j<isComposite.length;j+=i)
					isComposite[(int)j]= true;
			}
		}
		
		boolean ok = true;
		while(sc.ready())
		{
			int n = sc.nextInt();
			String s= ""+n;
			boolean habreak = true;
			for(int j=0;j<s.length()/2;j++)
				if(s.charAt(j) != s.charAt(s.length()-j-1))
					habreak = false;
			if(isComposite[n])
				habreak = false;
			if(ok)
				pw.println(n* 2);
			if(habreak)
				ok = false;
		}
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
