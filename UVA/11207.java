package v112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _11207_TheEasiestWay {
	
	/*
	 * It is divided as cases
	 * if the height fits for 4 consecutive parts of the whole width , it is ok
	 * else
	 * we can divide the height into 4 pieces and the width will fit because w <= 4 * h
	 * or
	 * we can divide the take 2 of (2 adjacent pieces taking the whole width)
	 */
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw  = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			double max = -1;
			int maxi = -1;
			for(int i=0;i<n;i++)
			{
				int w = sc.nextInt() , h = sc.nextInt();
				if(w > h)
				{
					int tmp = w;
					w = h;
					h = tmp;
				}
				if(h >= 4 * w)
				{
					if(w > max)
					{
						max = w;
						maxi = i;
					}
				}
				else
				{
					if(h/4.0 > max)
					{
						max = h/4.0;
						maxi = i;
					}
					if(w / 2.0 > max)
					{
						max = w/2.0;
						maxi = i;
					}
				}
				
			}
			pw.println(maxi + 1);
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
