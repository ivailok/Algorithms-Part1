package implementations;

public class QuickFindUF implements IUnionFind
{	
	private int[] id;
	
	public QuickFindUF(int N)
	{
		id = new int[N];
		for (int i = 0; i < N; i++)
		{
			id[i] = i;
		}
	}
	
	@Override
	public boolean connected(int p, int q)
	{
		return id[p] == id[q];
	}
	
	@Override
	public void union(int p, int q)
	{
		int pid = id[p],
			qid = id[q];
		
		for (int i = 0; i < id.length; i++)
		{
			if (id[i] == pid)
			{
				id[i] = qid;
			}
		}
	}
}
