package v003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
public class _315_Network
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			V = n;
			if(n == 0)
				break;
			adjList = new ArrayList[n];
			for(int i=0;i<n;i++)
				adjList[i] = new ArrayList<>();
			
			while(true)
			{
				String s = br.readLine();
				if(s.equals("0"))
					break;
				String[] sa = s.split(" ");
				int u = Integer.parseInt(sa[0])-1;
				
				for(int i=1;i<sa.length;i++)
				{
					adjList[u].add(Integer.parseInt(sa[i])-1);
					adjList[Integer.parseInt(sa[i])-1].add(u);
				}
			}
			findArtPointsAndBridges();
			int cnt = 0;
			for(int i=0;i<V;i++)
				if(artPoints[i])
					cnt++;
			pw.println(cnt);
		}
		pw.flush();

	}

	static ArrayList<Integer>[] adjList;
	static int[] dfs_low, dfs_num, parent;
	static int V, counter, root, rootChildren;
	static boolean artPoints[];


	static void findArtPointsAndBridges()
	{
		dfs_low = new int[V];
		dfs_num = new int[V];
		parent = new int[V];
		artPoints = new boolean[V];
		for(int i = 0; i < V; ++i)
			if(dfs_num[i] == 0)
			{
				root = i;
				rootChildren = 0;
				dfs(i);
				if(rootChildren <= 1)
					artPoints[i] = false;
			}
	}
	static void dfs(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		for(int v: adjList[u])
			if(dfs_num[v] == 0)
			{
				parent[v] = u;
				if(u == root)
					++rootChildren;
				dfs(v);
				if(dfs_low[v] >= dfs_num[u])
					artPoints[u] = true;
				dfs_low[u] = Math.min(dfs_low[v], dfs_low[u]);
			}
			else																
				if(parent[u] != v)
					dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
	}

}
