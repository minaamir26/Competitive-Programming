package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _GargariAndPermutations_463D
{
	static int n,k;
	static int[][] arr;
	static int memo[][];
	
	static int solve(int idx, int lst)
	{
		if(idx == n)
			return 0;
		if(memo[idx][lst] != -1)
			return memo[idx][lst];
		int max = solve(idx+1, lst);
		boolean ok = true;
		for(int i=0;i<k;i++)
			if(arr[i][idx] < arr[i][lst])
				ok = false;
		if(lst == -1)
			ok = true;
		if(ok)
			max = Math.max(max, 1 + solve(idx+1, idx));
		return memo[idx][lst] = max;
	}
	
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		arr = new int[k][n];
		for(int i=0;i<k;i++)
			for(int j=0;j<n;j++)
				arr[i][sc.nextInt()-1] = j+1;
		Pair[] a = new Pair[n];
		for(int i=0;i<n;i++)
			a[i] = new Pair(arr[0][i],i);
		Arrays.sort(a);
		
		int tmp[][] = new int[k][n];
		for(int i=1;i<=n;i++)
			tmp[0][i-1] = i;
		for(int i=1;i<k;i++)
			for(int j=0;j<n;j++)
				tmp[i][j] = arr[i][a[j].idx];
		arr = new int[k][n+1];
		for(int i=0;i<k;i++)
			arr[i] = Arrays.copyOf(tmp[i], n+1);
		memo = new int[n+2][n+2];
		for(int i=0;i<memo.length;i++)
			Arrays.fill(memo[i], -1);
		System.out.println(solve(0,n));
	}
	
	static class Pair implements Comparable<Pair>{
		int n,idx;
		public Pair(int a, int b)
		{
			n = a;
			idx = b;
		}
		@Override
		public int compareTo(Pair o)
		{
			return n-o.n;
		}
		public String toString(){
			return n + " " + idx;
		}
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
