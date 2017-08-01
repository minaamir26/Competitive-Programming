package v113;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _11345_Rectangles
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		PrintWriter pw = new PrintWriter(System.out);
		int k = 1;
		while(tc-->0)
		{
			int n = sc.nextInt();
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			n--;
			boolean ok = true;
			while(n-->0)
			{
				int x3 = sc.nextInt();
				int y3 = sc.nextInt();
				int x4 = sc.nextInt();
				int y4 = sc.nextInt();
				x1 = Math.max(x1, x3);
				y1 = Math.max(y1, y3);
				x2 = Math.min(x2, x4);
				y2 = Math.min(y2, y4);
				if(x2 < x1 || y2 < y1)
					ok = false;
			}
			if(!ok)
				pw.printf("Case %d: %d\n",k++,0);
			else
				pw.printf("Case %d: %d\n",k++,Math.max(0,(y2-y1)*(x2-x1)));
		}
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
