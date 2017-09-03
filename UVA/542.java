package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _542_France98
{
	static double[] anss;
	
	static void bt(int[] players, double prop)
	{
		if(players.length == 1)
		{
			winn[players[0]] = true;
			anss[players[0]]+=prop;
			return;
		}
		int size = players.length/2;
		
		for(int msk=0;msk<(1<<size);msk++)
		{
			double prop2 = prop;
			int[] wins = new int[size];
			for(int j=0;j<size;j++)
			{
				if((msk & (1<<j)) != 0)
				{
					wins[j] = players[j * 2 + 1];
					prop2*=prob[players[j*2+1]][players[j*2]];
				}
				else
				{
					wins[j] = players[j * 2];
					prop2*=prob[players[j*2]][players[j*2+1]];
				}
			}
			bt(wins,prop2);
		}
	}
	
	static double[][] prob;
	static boolean[] winn;
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String[] blad = new String[16];
		winn = new boolean[16];
		for(int i=0;i<16;i++)
			blad[i] = sc.next();
		prob = new double[16][16];
		for(int i=0;i<16;i++)
			for(int j=0;j<16;j++)
				prob[i][j] = sc.nextDouble() /100.0;
		anss = new double[16];
		int[] arrr = new int[16];
		for(int i=0;i<arrr.length;i++)
			arrr[i] = i;
		bt(arrr,1);
		PrintWriter pw = new PrintWriter(System.out);
		for(int i=0;i<16;i++)
		{
			pw.print(fix(blad[i]) +"p=");
			pw.printf("%.2f",anss[i]*100);
			pw.println("%");
			
		}
		pw.flush();
		
	}
	
	static String fix(String s)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		for(int i=s.length();i<=10;i++)
			sb.append(" ");
		return sb.toString();
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
