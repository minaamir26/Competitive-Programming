import java.util.ArrayList;
import java.util.HashMap;
public class MaxTrip
{
	
	/*
	 * For each connected component I count the number of nodes with odd degree
	 * and then I can join them with edges so if odd-degree-nodes are more than 2 I add ceil(odd degree nodes / 2)
	 * then I connect each connected component with another one likethey are in a chain
	 */
	static ArrayList<Integer>[] adj;
	static boolean vis[];
	static void dfs(int u , ArrayList<Integer> now)
	{
		vis[u] = true;
		now.add(u);
		for(int v : adj[u])
			if(!vis[v])
				dfs(v,now);

	}
	@SuppressWarnings("unchecked")
	public static int minBuy(String portA, String portB)
	{
		int k = 0;
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < portA.length(); i++)
		{
			if(!map.containsKey(portA.charAt(i)))
				map.put(portA.charAt(i), k++);
		}
		for (int i = 0; i < portB.length(); i++)
		{
			if(!map.containsKey(portB.charAt(i)))
				map.put(portB.charAt(i), k++);
		}
		adj = new ArrayList[k];
		for(int i=0;i<k;i++)
			adj[i] = new ArrayList<>();

		for(int i=0;i<portA.length();i++)
		{
			adj[map.get(portA.charAt(i))].add(map.get(portB.charAt(i)));
			adj[map.get(portB.charAt(i))].add(map.get(portA.charAt(i)));
		}
		int ans = 0;
		int cc = 0;
		ArrayList<Integer> now = new ArrayList<>();
		vis = new boolean[k];
		for(int i=0;i<k;i++)
			if(!vis[i])
			{
				cc++;
				now.clear();
				dfs(i,now);
				int odd = 0;
				for(int u : now)
					if(adj[u].size() %2 != 0)
						odd++;
				if(odd > 2)
					ans+=(odd-1)/2;
			}
		return ans + cc - 1;
	}
}
