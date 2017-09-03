package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _547_DDF
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int max = 4005;
		int divs[] = new int[max];
		for(int i=1;i<max; i++)
		{
			int sum = 0;
			for(int j=1;j * j <= i; j++)
				if(i % j == 0)
				{
					sum+=(cntDigs(j));
					if(j * j != i)
						sum+=cntDigs(i / j);
				}
			divs[i] = sum;
		}
		int[] maxx = new int[3004];
		for(int i=1;i<maxx.length;i++)
		{
			int cur = i;
			int len = 0;
			while(divs[cur] != cur)
			{
				len++;
				cur = divs[cur];
			}
			len++;
			maxx[i] = len;
		}
		int k = 1;
		PrintWriter pw = new PrintWriter(System.out);
		while(sc.ready())
		{
			int l = sc.nextInt();
			int r = sc.nextInt();
			int aa = l;
			int bb = r;
			int a = Math.min(l, r);
			r= Math.max(l, r);
			l = a;
			int idx = 0;
			int maxSoFar = 0;
			for(int i=l;i<=r;i++)
				if(maxx[i] > maxSoFar)
				{
					idx = i;
					maxSoFar = maxx[i];
				}
			ArrayList<Integer> pr = new ArrayList<>();
			int cur = idx;
			while(divs[cur] != cur)
			{
				pr.add(cur);
				cur = divs[cur];
			}
			pr.add(cur);
			pw.printf("Input%d: %d %d\n", k, aa, bb);
			pw.printf("Output%d: ", k++);
			for(int i=0;i<pr.size();i++)
			{
				pw.print(pr.get(i));
				if(i != pr.size()-1)
					pw.print(" ");
			}
			pw.println();
		}
		pw.flush();
	}

	static int cntDigs(int n)
	{
		String s = ""+n;
		int ret = 0;
		for(int i=0;i<s.length();i++)
			ret+=(s.charAt(i)-'0');
		return ret;
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
