package be.buyle.hendrik.scratch;

public class HuffmanTree implements Comparable<HuffmanTree>{

	private Node root ;

	public HuffmanTree(Node root) {
		super();
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return "HuffmanTree [root=" + root + "]";
	}

	@Override
	public int compareTo(HuffmanTree o) {
		return root.compareTo(o.root);
	}

	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
