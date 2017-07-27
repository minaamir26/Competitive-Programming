package v106;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;
public class _10686 {
	public static void main(String[] args) throws IOException {
				Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner("in.txt");

		PrintWriter pw = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			String[] categories = new String[n];
			TreeSet<String>[] keywords = new TreeSet[n];
			int[] max = new int[n];
			for(int i=0;i<n;i++)
			{
				categories[i] = sc.next();
				int m = sc.nextInt();
				keywords[i] = new TreeSet<>();
				max[i] = sc.nextInt();
				for(int j=0;j<m;j++)
					keywords[i].add(sc.next());
			}
			ArrayList<String> ans = new ArrayList<>();
			TreeSet<String> cats[] = new TreeSet[n];
			for(int i=0;i<cats.length;i++)
				cats[i] = new TreeSet<>();
			while(sc.ready())
			{
				String s = sc.nextLine();
				if(s.length() == 0)
					break;
				String[] sa = s.split("[^a-zA-Z]+");
				for(int i=0;i<n;i++)
				{
					for(String x : keywords[i])
						for(int k=0;k<sa.length;k++)
							if(sa[k].equals(x))
								cats[i].add(x);
				}
			}
			for(int i=0;i<cats.length;i++)
				if(cats[i].size() >= max[i])
					ans.add(categories[i]);
			if(ans.size() == 0)
				pw.println("SQF Problem.");
			else
			{
				for(int i=0;i<ans.size();i++)
				{
					pw.print(ans.get(i));
					if(i!=ans.size()-1)
						pw.print(",");
				}
				pw.println();
			}
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
