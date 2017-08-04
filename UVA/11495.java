package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _11495_BubblesAndBuckets
{
	static final int INF = Integer.MAX_VALUE;
	static long inv;
	static void mergeSort(int[] a, int b, int e)
	{ 
		if(b < e)
		{ 
			int q = (b + e) / 2;
			mergeSort(a, b, q);
			mergeSort(a, q + 1, e);
			merge(a, b, q, e);
		} 
	} 


	static void merge(int[] a, int b, int mid, int e)
	{ 
		int n1 = mid - b + 1;
		int n2 = e - mid;
		int[] L = new int[n1+1], R = new int[n2+1];

		for(int i = 0; i < n1; i++)  L[i] = a[b + i];
		for(int i = 0; i < n2; i++)  R[i] = a[mid + 1 + i];
		L[n1] = R[n2] = INF;

		for(int k = b, i = 0, j = 0; k <= e; k++)
			if(L[i] <= R[j])
			{
				a[k] = L[i++];
				inv+=j;
			}
			else 
			{
				a[k] = R[j++];
			}
	}


	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			int[] arr = new int[n];
			for (int i = 0; i < arr.length; i++)
				arr[i] = sc.nextInt();
			inv = 0;
			mergeSort(arr, 0, n-1);
			pw.println((inv%2==0)?"Carlos":"Marcelo");
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
