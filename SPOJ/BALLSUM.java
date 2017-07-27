

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SPOJ_BALLSUM {

	static long gcd(long a , long b)
	{
		if(b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			int k = sc.nextInt();
			if(n == -1 && k == -1)
				break;

			long num = 1l * k * (k-1) / 2 - ((k) / 2);
			long dem = 1l * n * (n-1);
			if(num == 0)
				pw.println(0);
			else
				if(num == dem)
					pw.println(1);
				else
				{
					long gcd  = (gcd(num, dem));
					pw.println((num/gcd) + "/" + (dem/gcd));
				}
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
