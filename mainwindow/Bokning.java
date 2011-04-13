package mainwindow;
public interface Bokning {
	public int pris();
	public String toString(); //TODO not toString - that is already implemented implicitly in every class
	public Bokningstyp getType();
	// true if the search for objects has been done so that the resultPanel can be drawn
	public boolean searchIsDone();
	public String getOrt();
}
