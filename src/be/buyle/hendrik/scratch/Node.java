package be.buyle.hendrik.scratch;

public class Node implements Comparable<Node>{

	private Character value;
	private int occurence;

	private Node leftChild;
	private Node rightChild;

	public Node(Character value, int occurence, Node leftChild, Node rightChild) {
		super();
		this.value = value;
		this.occurence = occurence;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public Character getValue() {
		return value;
	}

	public void setValue(Character value) {
		this.value = value;
	}

	public int getOccurence() {
		return occurence;
	}

	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", occurence=" + occurence
				+ ", leftChild=" + leftChild + ", rightChild=" + rightChild
				+ "]";
	}

	@Override
	public int compareTo(Node o) {
		return Integer.valueOf(this.getOccurence()).compareTo(Integer.valueOf(o.getOccurence()));
	}

}
