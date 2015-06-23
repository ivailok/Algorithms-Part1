package implementations;

public class QuickUnionUF implements IUnionFind 
{
	private int id[];
	
	public QuickUnionUF(int n)
	{
		id = new int[n];
		for (int i = 0; i < n; i++)
		{
			id[i] = i;
		}
	}
	
	private int root(int i)
	{
		while (id[i] != i)
		{
			i = id[i];
		}
		return i;
	}
	
	@Override
	public boolean connected(int p, int q) 
	{
		return root(p) == root(q);
	}

	@Override
	public void union(int p, int q) 
	{
		int pRoot = root(p),
			qRoot = root(q);
		
		if (pRoot == qRoot)
		{
			return;
		}
		
		id[pRoot] = qRoot;
	}
}
