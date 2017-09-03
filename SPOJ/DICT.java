import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class DICT
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sba = new StringBuilder();
		int n = sc.nextInt();
		Trie trie = new Trie();
		while(n-- > 0)
			trie.addWord(sc.next());
		
		int k = sc.nextInt();
		PrintWriter pw = new PrintWriter(System.out);
		for(int i=1;i<=k;i++)
		{
			sba.append("Case #").append(i).append(":\n");
			String s = sc.next();
			Trie anss = trie.containsPrefix(s);
			if(anss == null)
				sba.append("No match.\n");
			else
			{
				stack = new Stack<>();
				for(char c : s.toCharArray())
					stack.push(c);
				ans = new ArrayList<String>();
				for(int j=0;j<26;j++)
				{
					Trie child = anss.children[j];
					if(child != null)
					{
						stack.push((char)('a' + j));
						dfs(child);
						stack.pop();
					}
				}
				if(ans.size() == 0)
					sba.append("No match.\n");
				else
				{
					for(String sss : ans)
						sba.append(sss).append("\n");
				}
			}
		}
		pw.print(sba);
		pw.flush();
	}
	static ArrayList<String> ans;
	static Stack<Character> stack;
	static void dfs(Trie t)
	{
		if(t.isWord)
		{
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty())
				sb.append(stack.pop());
			for(int i=0;i<sb.length();i++)
				stack.push(sb.charAt(sb.length()-1-i));
			ans.add(sb.reverse().toString());
		}
		for(int i=0;i<26;i++)
		{
			Trie child = t.children[i];
			if(child != null)
			{
				stack.push((char)('a' + i));
				dfs(child);
				stack.pop();
			}
		}
	}
	
	static class Trie
	{
		Trie[] children;
		boolean isWord;
		public Trie()
		{
			children = new Trie[26];
			isWord = false;
		}
		
		public void addWord(String s)
		{
			addWord(s,0);
		}
		
		public void addWord(String s, int idx)
		{
			if(idx == s.length())
				isWord = true;
			else
			{
				int c = s.charAt(idx)-'a';
				if(children[c] == null)
					children[c] = new Trie();
				children[c].addWord(s,idx+1);
			}
		}
		
		public boolean contains(String s)
		{
			return contains(s,0);
		}
		
		public boolean contains(String s , int idx)
		{
			if(idx == s.length())
				return isWord ;
			else
			{
				int c = s.charAt(idx)-'a';
				if(children[c] == null)
					return false;
				return children[c].contains(s,idx+1);
			}
		}
		
		public Trie containsPrefix(String s)
		{
			return containsPrefix(s,0);
		}
		
		public Trie containsPrefix(String s , int idx)
		{
			if(idx == s.length())
				return this ;
			else
			{
				int c = s.charAt(idx)-'a';
				if(children[c] == null)
					return null;
				return children[c].containsPrefix(s,idx+1);
			}
		}
	}
	
	
	static class Scanner
	{
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
