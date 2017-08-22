import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * I solved this using square root decomposition
 * 
 * for each block .. I keep it sorted
 * for each query C
 * 		I solve for the leftmost and rightmost block naively while for the inner ones, I go through them and find the answer through binary search
 * for each query M
 * 		I know what this index contains through the initial array then go and substitute with the new value 
 * 		and keep it sorted by swapping left and right in two passes
 */

public class RACETIME
{

	public static void main(String[] args) throws IOException
	{
		long ll = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("out.txt");
		int n = sc.nextInt();
		int q = sc.nextInt();
		PrintWriter pw = new PrintWriter("mina.txt");
		int[] arr = new int[n];
		for(int i=0;i<n;i++)
			arr[i] = sc.nextInt();

		int size = (int)Math.sqrt(n) + 1;

		int nn = (int)(Math.ceil(1.0*n/size));
		int[][] a = new int[nn][];
		for(int i=0,j=0;i<nn;i++,j+=size)
		{
			if(j + size < n)
				a[i] = new int[size];
			else
				a[i] = new int[n-j];
		}
		for(int i=0,j=0,k=0;k<n;k++,j++)
		{
			a[i][j] = arr[k];
			if(j == size-1)
			{
				j = -1;
				i++;
			}
		}
		for(int i=0;i<a.length;i++)
			Arrays.sort(a[i]);
		while(q-- > 0)
		{
			char c = sc.next().charAt(0);
			if(c == 'C')
			{
				int l = sc.nextInt()-1;
				int r = sc.nextInt()-1;
				int x = sc.nextInt();
				int left = l/size;
				int right = r/size;
				int ans = 0;
				for(int i=l;i<Math.min(Math.min(n, r+1),left*size + size);i++)
					if(arr[i] <= x)
						ans++;
				if(left != right)
					for(int i=right*size;i<=r;i++)
						if(arr[i] <= x)
							ans++;
				for(int i=left+1;i<right;i++)
				{
					int lo = 0,hi = a[i].length-1 , loc = -1;
					while(lo <= hi)
					{
						int mid = ((lo + hi)>>1);
						if(a[i][mid] <= x)
						{
							loc = mid;
							lo = mid + 1;
						}
						else
							hi = mid-1;
					}
					if(loc != -1)
						ans+=(loc+1);
				}
				pw.println(ans);
			}
			else
			{
				int idx = sc.nextInt()-1;
				int val = sc.nextInt();
				int old = arr[idx];
				int sq = idx / size;
				int id = 0;
				for(int i=0;i<a[sq].length;i++)
					if(a[sq][i] == old)
					{
						a[sq][i] = val;
						id = i;
						break;
					}
				int i = id;
				while(i + 1 < a[sq].length && a[sq][i+1] < a[sq][i])
				{
					int tmp = a[sq][i+1];
					a[sq][i+1] = a[sq][i];
					a[sq][i] = tmp;
					i++;
				}
				i = id;
				while(i > 0 && a[sq][i-1] > a[sq][i])
				{
					int tmp = a[sq][i-1];
					a[sq][i-1] = a[sq][i];
					a[sq][i] = tmp;
					i--;
				}
				arr[idx] = val;
			}
		}
		pw.flush();
		System.out.println(System.currentTimeMillis()-ll);
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
