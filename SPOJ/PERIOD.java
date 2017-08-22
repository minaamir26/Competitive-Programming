import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PERIOD
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
		PrintWriter pw = new PrintWriter(System.out);
		int t = sc.nextInt();
		int k = 1;
		while(t-- > 0)
		{
			pw.printf("Test case #%d\n" , k++);
			n = sc.nextInt();
			String s = sc.next();
			prefixFunction(s.toCharArray());
			for(int i=1;i<n;i++)
				if(pi[i] != 0)
			{
				int rem = i + 1 - pi[i];
				if((i + 1) % rem == 0)
					pw.printf("%d %d\n",i+1,(i + 1)/rem);
			}
			pw.println();
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
