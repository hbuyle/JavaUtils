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
		StringBuilder builder = new StringBuilder();
		serializeNode(root, builder);
		return builder.toString().trim();
	}
	
	public static HuffmanTree deserialize(String data) {
		if (data == null || data.isBlank()) {
			return new HuffmanTree(null);
		}

		String[] tokens = data.trim().split("\\s+");
		Index index = new Index();
		Node root = deserializeNode(tokens, index);
		return new HuffmanTree(root);
	}

	private static void serializeNode(Node node, StringBuilder builder) {
		if (node == null) {
			builder.append("N ");
			return;
		}

		if (node.getLeftChild() == null && node.getRightChild() == null) {
			builder.append("L:")
				.append((int) node.getValue())
				.append(":")
				.append(node.getOccurence())
				.append(" ");
			return;
		}

		builder.append("I:")
			.append(node.getOccurence())
			.append(" ");
		serializeNode(node.getLeftChild(), builder);
		serializeNode(node.getRightChild(), builder);
	}

	private static Node deserializeNode(String[] tokens, Index index) {
		if (index.value >= tokens.length) {
			return null;
		}

		String token = tokens[index.value++];
		if ("N".equals(token)) {
			return null;
		}

		String[] parts = token.split(":");
		if ("L".equals(parts[0])) {
			char value = (char) Integer.parseInt(parts[1]);
			int occurence = Integer.parseInt(parts[2]);
			return new Node(value, occurence, null, null);
		}

		int occurence = Integer.parseInt(parts[1]);
		Node leftChild = deserializeNode(tokens, index);
		Node rightChild = deserializeNode(tokens, index);
		return new Node(null, occurence, leftChild, rightChild);
	}

	private static final class Index {
		private int value;
	}
	
	
	
}
