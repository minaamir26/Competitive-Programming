import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class _PrefixesAndSuffixes_432D
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
		String s = sc.next();

		n = s.length();
		int[] z = zAlgo(s.toCharArray());

		int[] cum = new int[n+1];
		for (int i = 1; i < z.length; i++)
			if(z[i] > 0)
			{
				cum[1]++;
				cum[z[i]+1]--;
			}
		for (int i = 1; i < cum.length; i++)
			cum[i]+=cum[i-1];
		ArrayList<Pair> arr = new ArrayList<>();
		for (int i = 1; i < z.length; i++)
		{
			if(z[i] + i == n)
				arr.add(new Pair(z[i] , cum[z[i]]+1));
		}
		arr.add(new Pair(s.length(),1));
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(arr.size());
		Collections.sort(arr);
		for(Pair x : arr)
			pw.println(x.a + " " + x.b);
		pw.flush();
	}

	static int[] zAlgo(char[] s)
	{ 
		int n = s.length;
		int[] z = new int[n];
		for(int i = 1, l = 0, r = 0; i < n; ++i)
		{ 
			if(i <= r)
				z[i] = Math.min(r - i + 1, z[i - l]);
			while(i + z[i] < n && s[z[i]] == s[i + z[i]])
				++z[i];
			if(i + z[i] - 1 > r)
				r = i + z[l = i] - 1;
		} 
		return z;
	} 

	static class Pair implements Comparable<Pair>{
		int a,b;
		Pair(int x, int y)
		{
			a=x;
			b=y;
		}
		@Override
		public int compareTo(Pair o)
		{
			return a-o.a;
		}
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