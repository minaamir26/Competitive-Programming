import java.util.TreeSet;

public class WeirdRooks
{
	/*
	 * Backtracking problem
	 * process the rows from down to top and choose to add one rook each time in different cell in that row
	 * Each time count the cells to its right with (no cell have rook) down it .. I made this using vis array
	 */
	static int n;
	static int arr[];
	static boolean vis[];
	static int[] grid[];
	static TreeSet<Pair> ans;
	static void bt(int r, int special, int rooks)
	{
		if(r == -1)
		{
			ans.add(new Pair(rooks,special));
			return;
		}
		int cc = 0;
		for(int j=0;j<arr[r];j++)
			if(!vis[j])
				cc++;
		bt(r-1,cc + special,rooks);
		for(int i=0;i<arr[r];i++)
			if(!vis[i])
			{
				int cnt = 0;
				vis[i] = true;
				grid[r][i] = 1;
				for(int j=i+1;j<arr[r];j++)
					if(!vis[j]) 
						cnt++;
				bt(r-1 , cnt + special , rooks + 1);
				grid[r][i] = 0;
				vis[i] = false;
			}
	}
	static class Pair implements Comparable<Pair>{
		int a,b;
		public Pair(int x, int y)
		{
			a = x;
			b = y;
		}
		@Override
		public int compareTo(Pair o)
		{
			if(a != o.a)
				return a - o.a;
			return b - o.b;
		}
		public String toString(){
			return a+","+b;
		}
	}
	public static String describe(int[] cols)
	{
		n = cols.length;
		arr = cols;
		ans = new TreeSet<>();
		vis = new boolean[11];
		grid = new int[11][11];
		bt(n-1, 0 , 0);
		StringBuilder sb = new StringBuilder();
		while(!ans.isEmpty())
		{
			sb.append(ans.pollFirst().toString());
			if(!ans.isEmpty())
				sb.append(" ");
		}
		return sb.toString();
	}
}
