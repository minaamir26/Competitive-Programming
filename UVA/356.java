package v003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _356_SquarePegsAndRoundHoles {
	/*
	 * I check for every square's 4 corners how many for them are inside the circle
	 * if the 4 corners are inside, then this square is completely inside
	 * else if 1, 2 or 3, then it is partially inside the circle
	 */

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		boolean first = true;
		while(sc.ready())
		{
			int n = sc.nextInt();
			rad = (2 * n - 1)/2.0;
			cx = n;
			cy = n;
			int[] dx = new int[]{0,0,1,1};
			int[] dy = new int[]{0,1,0,1};
			int part = 0 , total = 0;
			for(int i = 0; i < 2 * n; i++)
			{
				for(int j = 0; j < 2 * n; j++)
				{
					int cnt = 0;
					for(int k=0;k<4;k++)
						if(inside(i + dx[k],j+dy[k]))
							cnt++;
					if(cnt == 4)
						total++;
					else
						if(cnt != 0)
							part++;
				}
			}
			if(first)
				first = false;
			else
				pw.println("");
			pw.printf("In the case n = %d, %d cells contain segments of the circle.\nThere are %d cells completely contained in the circle.\n"
					,n,part,total);
		}
		pw.flush();
	}
	static double rad;
	static int cx , cy;
	static boolean inside(int x , int y)
	{
		int dis2 = (cx-x) * (cx-x) + (cy-y) * (cy-y);
		if(dis2 <= rad * rad)
			return true;
		return false;
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
