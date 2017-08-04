import java.util.ArrayList;
import java.util.Arrays;

public class OrderDoesMatter
{
	static boolean[] vis;
	static void dfs(int u)
	{
		vis[u] = true;
		for(int v: adj[u])
			if(!vis[v])
				dfs(v);
	}

	static ArrayList<Integer> adj[];
	public static int getOrder(int[] N, int[] M)
	{
		int[] in = new int[1001];
		int[] out = new int[1001];
		adj = new ArrayList[1001];
		for (int i = 0; i < adj.length; i++)
			adj[i] = new ArrayList<>();
		for(int i=0;i<N.length;i++)
		{
			adj[N[i]].add(M[i]);
			in[M[i]]++;
			out[N[i]]++;
		}
		int start = N[0];
		vis = new boolean[1001];
		int one = 0;
		int negOne = 0;
		ArrayList<Integer> odds = new ArrayList<>();
		for(int i=0;i<1001;i++)
		{
			if(out[i]-in[i] == 1)
			{
				start = i;
				one++;
				odds.add(i);
			}
			else
				if(out[i]-in[i] == -1)
				{
					negOne++;
					odds.add(i);
				}
				else
					if(out[i]-in[i] != 0)
						return -1;
		}
		if(!(one == 1 && negOne == 1 || one == 0 && negOne == 0))
			return -1;
		//check for connectivity
		dfs(start);
		for(int i=0;i<N.length;i++)
			if(!vis[N[i]])
				return -1;
		for(int i=0;i<N.length;i++)
			if(!vis[M[i]])
				return -1;
		if(odds.size() == 2)
			return odds.get(0) * odds.get(1);
		Arrays.sort(N);
		Arrays.sort(M);
		return (Math.max(N[N.length-1], M[M.length-1]) * Math.max(N[N.length-1], M[M.length-1]));
	}

	public static void main(String[] args)
	{
		int x = getOrder(new int[]{2,3,5,3}, new int[]{3,5,7,8});
		System.out.println(x);
	}

}
