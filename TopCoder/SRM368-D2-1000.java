import java.util.Arrays;

public class JumpingBoard {
	static int[] grid[];
	static int n,m;
	static int[][]vis;
	static boolean inf;
	static int[] dx = new int[]{1,-1,0,0};
	static int[] dy = new int[]{0,0,1,-1};
	static void dfs(int x,int y)
	{
		vis[x][y] = 1;
		for(int i=0;i<4;i++)
		{
			int x2 = x + grid[x][y] * dx[i];
			int y2 = y + grid[x][y] * dy[i];
			if(valid(x2,y2) && grid[x2][y2] != -1)
			{
				if(vis[x2][y2] == 1)
					inf = true;
				if(vis[x2][y2] == 0)
					dfs(x2,y2);
			}
		}
		vis[x][y] = 2;
	}

	static int memo[][];
	static int solve(int x , int y)
	{
		if(memo[x][y] != -1)
			return memo[x][y];
		int ans = 0;
		for(int i=0;i<4;i++)
		{
			int x2 = x + grid[x][y] * dx[i];
			int y2 = y + grid[x][y] * dy[i];
			if(valid(x2,y2) && grid[x2][y2]!=-1)
				ans= Math.max(ans, 1+solve(x2,y2));
		}
		return memo[x][y] = ans;
	}
	public static int maxJumps(String[] board)
	{
		grid = new int[board.length][board[0].length()];
		n = board.length;
		m = board[0].length();
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				if(board[i].charAt(j) == 'H')
					grid[i][j] = -1;
				else
					grid[i][j] = board[i].charAt(j)-'0';
			}
		inf = false;
		vis = new int[n][m];
		dfs(0,0);
		if(inf)
			return -1;
		memo = new int[n][m];
		for(int i=0;i<n;i++)
			Arrays.fill(memo[i], -1);
		return solve(0,0) + 1;
	}
	static boolean valid(int x , int y)
	{
		return x>=0 && y>=0 && x<n && y<m;
	}

}
