package algorithm;

import java.util.ArrayList;
import java.util.List;

public class DFA {
	private String[] arr = {"中国", "国人", "中国人" };
	private Node rootNode = new Node('R');

	private String content = "我是中国人民";

	private List<String> words = new ArrayList<String>();
	private List<String> word = new ArrayList<String>();
	int a = 0;

	private void searchWord() {
		char[] chars = content.toCharArray();
		Node node = rootNode;
		while (a < chars.length) {
			node = findNode(node, chars[a]);
			if (node == null) {
				node = rootNode;
				a = a - word.size();
				word.clear();
			} else if (node.flag == 1) {
				word.add(String.valueOf(chars[a]));
				StringBuffer sb = new StringBuffer();
				for (String str : word) {
					sb.append(str);
				}
				words.add(sb.toString());
				a = a - word.size() + 1;
				word.clear();
				node = rootNode;
			} else {
				word.add(String.valueOf(chars[a]));
			}
			a++;
		}
	}

	private void createTree() {
		for (String str : arr) {
			char[] chars = str.toCharArray();
			if (chars.length > 0)
				insertNode(rootNode, chars, 0);
		}
	}

	private void insertNode(Node node, char[] cs, int index) {
		Node n = findNode(node, cs[index]);
		if (n == null) {
			n = new Node(cs[index]);
			node.nodes.add(n);
		}

		if (index == (cs.length - 1))
			n.flag = 1;

		index++;
		if (index < cs.length)
			insertNode(n, cs, index);
	}

	private Node findNode(Node node, char c) {
		List<Node> nodes = node.nodes;
		Node rn = null;
		for (Node n : nodes) {
			if (n.c == c) {
				rn = n;
				break;
			}
		}
		return rn;
	}

	public static void main(String[] args) {
		DFA dfa = new DFA();
		dfa.createTree();
		dfa.searchWord();
		System.out.println(dfa.words);
	}

	private static class Node {
		public char c;
		public int flag; // 1：表示终结，0：延续
		public List<Node> nodes = new ArrayList<Node>();

		public Node(char c) {
			this.c = c;
			this.flag = 0;
		}

		public Node(char c, int flag) {
			this.c = c;
			this.flag = flag;
		}
	}
}