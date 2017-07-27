
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Timus_1643 {
	static int n , m;
	static char  grid[][];
	static int[] dx = new int[]{0,0,1,-1,1,1,-1,-1};
	static int[] dy = new int[]{1,-1,0,0,-1,1,-1,1};
	static int[] disA;
	static int[] disB;
	static int[] disC;
	static int inf = (int)1e9;
	static boolean[] vis;
	static ArrayList<Integer> letters[];
	static void bfs(int node , int[] dis)
	{
		Arrays.fill(dis, (int)1e7);
		Queue<Integer> q = new LinkedList<>();
		q.add(node);
		dis[node] = 0;
		vis = new boolean[n*m];
		vis[node] = true;
		boolean[] visLetters = new boolean[26];
		while(!q.isEmpty())
		{
			int u = q.poll();
			int x = u/m;
			int y = u%m;
			for(int i=0;i<8;i++)
			{
				int x2 = x + dx[i];
				int y2 = y + dy[i];
				if(valid(x2 , y2))
				{
					if(grid[x2][y2] >= 'A' && grid[x2][y2] <= 'Z' && !visLetters[grid[x2][y2] - 'A'])
					{
						visLetters[grid[x2][y2] - 'A'] = true;
						for(int now : letters[grid[x2][y2]-'A'])
							if(!vis[now])
							{
								vis[now] = true;
								dis[now] = dis[u] + 1;
								q.add(now);
							}
					}
					else
					{
						int now = node(x2,y2);
						if(!vis[now])
						{
							vis[now] = true;
							dis[now] = dis[u] + 1;
							q.add(now);
						}
					}
				}
			}
		}
	}

	static boolean valid(int x, int y)
	{
		return x>=0 && y>=0 && x<n && y<m && grid[x][y] != '#' && grid[x][y] != '*';
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		grid = new char[n][m];
		for(int i=0;i<n;i++)
			grid[i] = sc.next().toCharArray();

		disA = new int[n*m];		//x --> !
		disB = new int[n*m];		//y --> $
		disC = new int[n*m];		//z --> *
		int nodex = 0;
		int nodey = 0;
		int nodez = 0;

		letters = new ArrayList[26];
		for(int i=0;i<26;i++)
			letters[i] = new ArrayList<>();
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				if(grid[i][j] == '*')
					nodez = node(i , j);
				if(grid[i][j] == '!')
					nodex = node(i , j);
				if(grid[i][j] == '$')
					nodey = node(i , j);
				if(grid[i][j] >='A' && grid[i][j] <= 'Z')
					letters[grid[i][j] - 'A'].add(node(i , j));
			}

		bfs(nodex , disA);
		bfs(nodey , disB);
		bfs(nodez , disC);
		int ans = 10000000;
		for(int i=0;i<n * m;i++)
		{
			if(disA[i] < (int)1e7 && disB[i] < (int)1e7 && disC[i] < (int)1e7)
				ans = Math.min(ans, Math.max(disA[i] , disB[i]) + disC[i]);
		}
		if(ans >= 10000000)
			System.out.println("Impossible");
		else
			System.out.println(ans);

	}

	static int node(int x, int y){
		return x * m + y;
	}

}
