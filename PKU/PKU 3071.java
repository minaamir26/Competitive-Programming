import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3071_Football
{
	static int n;
	static double[][] prob, memo;

	static double solve(int level, int player)
	{
		if(level == 0)
			return 1;
		if(memo[level][player] != -1)
			return memo[level][player];
		double ret = 0;
		int resp = (1<<level);
		int start = (player/resp) * resp;
		int half = resp>>1;
		if(player < start + half)
			start+=half;
		for(int i=start;i<start + half;i++)
			ret+=prob[player][i] * solve(level-1, player) * solve(level-1, i);
		return memo[level][player] = ret;
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);	
		while(true)
		{
			n = sc.nextInt();
			if(n == -1)
				break;
			prob = new double[(1<<n)][(1<<n)];
			for(int i=0;i<(1<<n);i++)
				for(int j=0;j<(1<<n);j++)
					prob[i][j] = sc.nextDouble();
			memo = new double[n+1][(1<<n) + 1];
			for(int i=0;i<memo.length;i++)
				Arrays.fill(memo[i], -1);
			double max = 0;
			int winner = 0;
			for(int i=0;i<(1<<n);i++)
			{
				if(solve(n, i) > max)
				{
					winner = i + 1;
					max = solve(n, i);
				}
			}
			pw.println(winner);
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
