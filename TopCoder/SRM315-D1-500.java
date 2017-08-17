import java.util.HashSet;

public class SillySudoku
{

	static int[] grid[];

	public static int countWays(String[] board)
	{
		grid = new int[4][4];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if(board[i].charAt(j) == '-')
					grid[i][j] = -1;
				else
				{
					grid[i][j] = board[i].charAt(j)-'0';
				}
		cnt = 0;
		bt(0,0);
		return cnt;

	}

	static int[] possibleValues = new int[]{1,2,3,4};
	static int cnt;
	static void bt(int r,int c)
	{
		if(r == 4 && c == 0)
		{
			if(checkAll())
				cnt++;
		}
		else
		{
			if(grid[r][c] == -1)
			{
				for(int i=0;i<4;i++)
				{
					int mul = r*4+c;
					mul++;
					int rr=mul/4;
					int cc=mul%4;
					if(check(possibleValues[i],r,c))
					{
						grid[r][c] = possibleValues[i];
						bt(rr, cc);
						grid[r][c] = -1;
					}
				}
			}
			else
			{
				int mul = r*4+c;
				mul++;
				r=mul/4;
				c=mul%4;
				bt(r,c);
			}

		}
	}

	static boolean checkAll()
	{
		for(int i=0;i<4;i++)
		{
			HashSet<Integer> set = new HashSet<>();
			for(int j=0;j<4;j++)
				set.add(grid[i][j]);
			if(set.size() != 4)
				return false;
		}
		for(int i=0;i<4;i++)
		{
			HashSet<Integer> set = new HashSet<>();
			for(int j=0;j<4;j++)
				set.add(grid[j][i]);
			if(set.size() != 4)
				return false;
		}
		HashSet<Integer> set = new HashSet<>();
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				set.add(grid[i][j]);
		if(set.size() != 4)
			return false;
		set = new HashSet<>();
		for(int i=2;i<4;i++)
			for(int j=0;j<2;j++)
				set.add(grid[i][j]);
		if(set.size() != 4)
			return false;
		set = new HashSet<>();
		for(int i=0;i<2;i++)
			for(int j=2;j<4;j++)
				set.add(grid[i][j]);
		if(set.size() != 4)
			return false;
		set = new HashSet<>();
		for(int i=2;i<4;i++)
			for(int j=2;j<4;j++)
				set.add(grid[i][j]);
		if(set.size() != 4)
			return false;

		return true;
	}


	static int[] dx = new int[]{0,0,-1,-1,-1,1,1,1};
	static int[] dy = new int[]{1,-1,0,-1,1,-1,0,1};

	static boolean check(int n , int r , int c)
	{
		for(int j=0;j<4;j++)
			if(n == grid[r][j])
				return false;
		for(int i=0;i<4;i++)
			if(n == grid[i][c])
				return false;

		int r1 = (r/2)*2;
		int c1 = (c/2)*2;

		for(int i=r1;i<r1+2;i++)
			for(int j=c1;j<c1+2;j++)
				if(n == grid[i][j])
					return false;
		return true;

	}

	static boolean valid(int x, int y)
	{
		return x>=0 && y>=0 && x<4 && y<4;
	}

	public static void main(String[] args)
	{
		int x = countWays(new String[]
				{"----", 
						"----", 
						"----", 
				"----"}
				);
		System.out.println(x);
	}

}
