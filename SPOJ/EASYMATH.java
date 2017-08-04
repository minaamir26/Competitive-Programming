import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EASYMATH
{
	
	//overflow !!
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		PrintWriter pw = new PrintWriter(System.out);
		while (tc-- > 0)
		{
			n = sc.nextLong();
			m = sc.nextLong();
			long a = sc.nextLong();
			long d = sc.nextLong();
			long div = 0;
			long[] arr = new long[] { a, a + d, a + 2 * d, a + 3 * d, a + 4 * d };
			int nn = 5;
			for(int i=0;i<arr.length;i++)
				div+=cnt(arr[i]);
			for(int i=0;i<arr.length;i++)
				for(int j=i+1;j<arr.length;j++)
					div-=cnt(lcm(arr[i],arr[j]));
			for(int i=0;i<nn;i++)
				for(int j=i+1;j<nn;j++)
					for(int k=j+1;k<nn;k++)
						div+=cnt(lcm(lcm(arr[i],arr[j]),arr[k]));
			for(int i=0;i<nn;i++)
				for(int j=i+1;j<nn;j++)
					for(int k=j+1;k<nn;k++)
						for(int l=k+1;l<nn;l++)
							div-=cnt(lcm(lcm(lcm(arr[i],arr[j]),arr[k]),arr[l]));
			div+=cnt(lcm(arr[0],lcm(lcm(lcm(arr[1],arr[2]),arr[3]),arr[4])));
			pw.println(m-n+1-div);
		}
		pw.flush();
	}

	static long lcm(long a, long b)
	{
		return a*b/gcd(a,b);
	}

	static long gcd(long a , long b)
	{
		if(b==0) return a;
		return gcd(b,a%b);
	}

	static long n,m;
	static long cnt(long div)
	{
		if(div <= 0)
			return 0;
		long l = n;
		long r = m;
		l = ((long)Math.ceil(1.0*l/div)) * div;
		r = (r/div)*div;
		if(l > r)
			return 0;
		long ans = (r - l) / div;
		ans++;
		return ans;
	}

	static class Scanner
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s)
		{
			br = new BufferedReader(new InputStreamReader(s));
		}

		public Scanner(String s) throws FileNotFoundException
		{
			br = new BufferedReader(new FileReader(new File(s)));
		}

		public String next() throws IOException
		{
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException
		{
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException
		{
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException
		{
			return br.readLine();
		}

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException
		{
			return br.ready();
		}
	}
}
