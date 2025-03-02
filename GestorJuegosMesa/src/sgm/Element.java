package sgm;

public abstract class Element {
	String name;
	boolean delete;

	public Element(String name) {
		this.name = name;
	}
	
	abstract void delElement ();
	
	
}
