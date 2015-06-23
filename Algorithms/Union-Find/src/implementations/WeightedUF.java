package implementations;

public class WeightedUF implements IUnionFind
{
	private int id[];
	private int sz[];
	
	public WeightedUF(int n)
	{
		id = new int[n];
		sz = new int[n];
		for (int i = 0; i < n; i++)
		{
			id[i] = i;
			sz[i] = 1;
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
		
		if (sz[pRoot] < sz[qRoot])
		{
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}
		else
		{
			id[qRoot] = id[pRoot];
			sz[pRoot] += sz[qRoot];
		}
	}
	
	public int getId(int i)
	{
		return id[i];
	}
	
}
