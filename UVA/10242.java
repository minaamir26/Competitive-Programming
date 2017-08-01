package v102;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class _10242_FourthPoint
{

	public static void main(String[] args) throws Throwable
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		try
		{
			while (sc.ready())
			{
				Point a1 = new Point(sc.nextDouble(), sc.nextDouble());
				Point a2 = new Point(sc.nextDouble(), sc.nextDouble());
				Point a3 = new Point(sc.nextDouble(), sc.nextDouble());
				Point a4 = new Point(sc.nextDouble(), sc.nextDouble());

				if (same(a1, a3))
				{
					double dx = a4.x - a1.x;
					double dy = a4.y - a1.y;
					pw.printf("%.3f %.3f\n", dx + a2.x, dy + a2.y);
				} else if (same(a1, a4))
				{
					double dx = a3.x - a1.x;
					double dy = a3.y - a1.y;
					pw.printf("%.3f %.3f\n", dx + a2.x, dy + a2.y);
				} else if (same(a2, a3))
				{
					double dx = a4.x - a3.x;
					double dy = a4.y - a3.y;
					pw.printf("%.3f %.3f\n", dx + a1.x, dy + a1.y);
				} else
				{
					double dx = a3.x - a4.x;
					double dy = a3.y - a4.y;
					pw.printf("%.3f %.3f\n", dx + a1.x, dy + a1.y);
				}
			}
		} catch (Exception e)
		{

		}
		pw.flush();

	}

	static double EPS = 1e-7;

	static boolean same(Point a, Point b)
	{
		return Math.abs(a.x - b.x) < EPS && Math.abs(a.y - b.y) < EPS;
	}

	static class Point
	{
		double x, y;

		public Point(double a, double b)
		{
			x = a;
			y = b;
		}
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
