package dao;

import java.util.List;

public interface daoInterface<G> {
	public boolean insertX(G G);
	public boolean deletaX(G G);
	public boolean updateX(G G);
	
	public G selectG(G g);
	public List<G> selectAll();
}
