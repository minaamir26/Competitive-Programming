package v002;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class _270_Lineup
{

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		br.readLine();
		PrintWriter pw = new PrintWriter(System.out);
		while(tc-- > 0)
		{
			ArrayList<Integer> x = new ArrayList<>();
			ArrayList<Integer> y = new ArrayList<>();
			while(br.ready())
			{
				String s = br.readLine();
				if(s.length() == 0)
					break;
				String[] sa = s.split(" ");
				x.add(Integer.parseInt(sa[0]));
				y.add(Integer.parseInt(sa[1]));
			}
			int n = x.size();
			int max = 1;
			for(int i=0;i<n;i++)
			{
				HashMap<String, Integer> map = new HashMap<String,Integer>();
				for(int j=0;j<n;j++)
					if(i!=j)
					{
						int dx = x.get(j)-x.get(i);
						int dy = y.get(j)-y.get(i);
						int gcd = gcd(dx,dy);
						dx/=gcd;
						dy/=gcd;
						String cf = dx + "/" + dy;
						Integer before = map.get(cf);
						if(before == null)
						{
							map.put(cf, 0);
							before = 0;
						}
						map.put(cf,before+1);
					}
				for(Entry<String, Integer> entry : map.entrySet())
				{
					max = Math.max(max, entry.getValue());
				}
			}
			pw.println(max+1);
			if(tc!=0)
				pw.println();
		}
		pw.flush();

	}

	static int gcd(int a , int b)
	{
		if(b==0) return a;
		return gcd(b,a%b);
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
