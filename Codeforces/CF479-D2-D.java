package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _LongJumps_479D
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int l = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();

		long dif = Math.abs(x-y);
		TreeSet<Long> set = new TreeSet<>();

		boolean can_x = false, can_y = false;
		long one = -1;
		long doble = -1;
		for (int i = 0; i < n; i++)
		{
			long now = sc.nextInt();
			if(set.contains(now - x))
				can_x = true;
			if(set.contains(now - y))
				can_y = true;
			if(set.contains(now - (x+y)))
				doble = (now) - (x+y);
			if(set.contains(now - dif) && now + Math.min(x, y) <=l)
				one = now + Math.min(x, y);
			else
				if(set.contains(now - dif) && now - dif - Math.min(x, y) >= 0)
					one = now - dif - Math.min(x, y);

			set.add(now);
		}
		if(can_x && can_y)
			System.out.println(0);
		else
			if(doble != -1)
				System.out.println("1\n"+ (doble + x));
			else
				if(one != -1)
					System.out.println("1\n"+one);
				else
					if(can_y)
						System.out.println("1\n"+x);
					else
						if(can_x)
							System.out.println("1\n"+y);
						else
							System.out.println("2\n"+x+" "+y);
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