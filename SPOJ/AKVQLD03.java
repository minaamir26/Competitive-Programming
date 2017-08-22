import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AKVQLD03
{
	/*
	 * Just a simple binary indexed tree problem
	 */
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		n = sc.nextInt();
		int q = sc.nextInt();
		tree = new int[n+1];
		while(q-- > 0)
		{
			String s = sc.next();
			if(s.charAt(0) == 'f')
				pw.println(-query(sc.nextInt()-1) + query(sc.nextInt()));
			else
				update(sc.nextInt(), sc.nextInt());
		}
		pw.flush();
	}	
	

	static int[] tree;
	static int n;
	static int query(int idx)
	{
		int ret = 0;
		while(idx > 0)
		{
			ret+=tree[idx];
			idx-=(idx & -idx);
		}
		return ret;
	}
	static void update(int idx , int val)
	{
		while(idx <= n)
		{
			tree[idx]+=val;
			idx+=(idx & -idx);
		}
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
