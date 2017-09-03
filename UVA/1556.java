package v015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _1556_DiskTree
{
	static TreeSet<Integer> adj[];
	static HashMap<String, Integer> map;
	static ArrayList<String> back;
	static boolean vis[];
	static StringBuilder sb;
	static int lvl;
	static void dfs(Node cur)
	{
		for(int i=0;i<lvl;i++)
			sb.append(' ');
		sb.append(cur.s);
		sb.append('\n');
		Collections.sort(cur.children);
		for(Node v : cur.children)
		{
			lvl++;
			dfs(v);
			lvl--;
		}
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		sb = new StringBuilder();
		try
		{
			while(true)
			{
				int n = sc.nextInt();
				int k = 0;
				map = new HashMap<>();
				adj = new TreeSet[500*80+5];
				back = new ArrayList<>();
				for(int i=0;i<adj.length;i++)
					adj[i] = new TreeSet<>();
				ArrayList<Node> fst = new ArrayList<>();
				for(int i=0;i<n;i++)
				{
					String s = sc.next();
					String[] sa = split(s);
					if(!map.containsKey(sa[0]))
					{
						map.put(sa[0], k++);
						fst.add(new Node(sa[0]));
					}
					Node cur = fst.get(map.get(sa[0]));
					for(int j = 1; j < sa.length; j++)
					{
						int idx = cur.findIdx(sa[j]);
						if(idx == -1)
						{
							cur.add(sa[j]);
							idx = cur.children.size()-1;
						}
						cur = cur.children.get(idx);
					}
				}
				Collections.sort(fst);
				for(Node v : fst)
				{
					dfs(v);
				}
				sb.append('\n');
			}
		}
		catch(Exception e)
		{

		}
		pw.print(sb);
		pw.flush();
	}

	static class Node implements Comparable<Node>{
		ArrayList<Node> children;
		String s;
		public Node(String ss)
		{
			children = new ArrayList<>();
			s = ss;
		}
		public void add(String s)
		{
			children.add(new Node(s));
		}
		public int findIdx(String s)
		{
			for(int i=0;i<children.size();i++)
				if(s.equals(children.get(i).s))
					return i;
			return -1;
		}
		@Override
		public int compareTo(Node o)
		{
			return s.compareTo(o.s);
		}
	}


	static String[] split(String s)
	{
		ArrayList<String> ret = new ArrayList<>();
		for(int i=0;i<s.length();i++)
		{
			StringBuilder sb = new StringBuilder();
			while(i < s.length() && s.charAt(i) != '\\')
				sb.append(s.charAt(i++));
			ret.add(sb.toString());
		}
		String[] rett = new String[ret.size()];
		for(int i=0;i<ret.size();i++)
			rett[i] = ret.get(i);
		return rett;
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
