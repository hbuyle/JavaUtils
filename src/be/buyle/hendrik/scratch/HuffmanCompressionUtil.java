package be.buyle.hendrik.scratch;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanCompressionUtil {

	/**
	 * Compresses a string using a previously generated huffman tree.
	 */
	public String compress(HuffmanTree tree, String uncompressed) {
		final Map<Character, String> mappingTable = createMappingTable(tree);
		final String compressed = compressWithTable(uncompressed.toCharArray(),
				mappingTable);
		return compressed;
	}
	
	/**
	 * Decompresses the bit sequence (represented as String) back into original message using the original huffman tree.
	 */
	public String decompress(HuffmanTree tree, String compressed) {
		StringBuilder message = new StringBuilder();
		int pointer = 0;
		Node curNode = tree.getRoot();
		while (pointer < compressed.length()) {
			if (curNode.getValue() == null) {
				if (compressed.charAt(pointer) == '0') {
					curNode = curNode.getLeftChild();
				} else {
					curNode = curNode.getRightChild();
				}
				pointer++;
			} else {
				message.append(curNode.getValue());
				curNode = tree.getRoot();
			}
		}
		message.append(curNode.getValue());
		return message.toString();
	}
	
	public static void main(String[] args) {
		HuffmanCompressionUtil compressionUtil = new HuffmanCompressionUtil();
		String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
		System.out.println("input: " + input);
		final HuffmanTree tree = compressionUtil.buildTree(input.toCharArray());
		String compressed = compressionUtil.compress(tree, input);
		System.out.println("compressed: " + compressed);
		String output = compressionUtil.decompress(tree, compressed);
		System.out.println("output: " + output);
	}

	private Map<Character, String> createMappingTable(HuffmanTree tree) {
		final Map<Character, String> map = new HashMap<>();
		visitTree(map, "", tree.getRoot());
		return map;
	}

	/**
	 * Recursive method used to visit all leaves of the huffman tree in order to
	 * populate the mapping table.
	 */
	private void visitTree(Map<Character, String> map, String value, Node root) {
		if (root.getValue() != null) {
			// this is a leaf ;
			map.put(root.getValue(), value);
		} else {
			// non-leaf
			if (root.getLeftChild() != null) {
				visitTree(map, value + "0", root.getLeftChild());
			}
			if (root.getRightChild() != null) {
				visitTree(map, value + "1", root.getRightChild());
			}
		}

	}

	private HuffmanTree buildTree(char[] chars) {

		final Map<Character, Integer> counts = countOccurences(chars);
		
		// in order to construct the tree we add all individual nodes into a priority queue...
		final PriorityQueue<HuffmanTree> queue = new PriorityQueue<>(100,
				new Comparator<HuffmanTree>() {

					@Override
					public int compare(HuffmanTree o1, HuffmanTree o2) {
						return o1.compareTo(o2);
					}
				});
		for (Entry<Character, Integer> e : counts.entrySet()) {
			queue.add(new HuffmanTree(new Node(e.getKey(), e.getValue(), null,
					null)));
		}

		// ... and then merge the two smallest sub-trees together and put it back into the queue...
		while (queue.size() > 1) {
			final HuffmanTree first = queue.poll();
			final HuffmanTree second = queue.poll();
			final Node newRoot = new Node(null, first.getRoot().getOccurence()
					+ second.getRoot().getOccurence(), first.getRoot(),
					second.getRoot());
			queue.add(new HuffmanTree(newRoot));
		}
		
		// ... until only one tree is left
		return queue.poll();
	}

	private Map<Character, Integer> countOccurences(char[] chars) {
		final Map<Character, Integer> counts = new HashMap<>();
		for (char c : chars) {
			if (counts.containsKey(c)) {
				counts.put(c, counts.get(c).intValue() + 1);
			} else {
				counts.put(c, 1);
			}
		}
		return counts;
	}

	private String compressWithTable(char[] uncompressed,
			Map<Character, String> mappingTable) {

		final StringBuilder sb = new StringBuilder();
		for (char c : uncompressed) {
			sb.append(mappingTable.get(c));
		}
		return sb.toString();
	}

}
