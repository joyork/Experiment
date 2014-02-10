package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Tree {
	private T head;
	private ArrayList<Tree> leafs = new ArrayList<Tree>();
	private Tree parent = null;
	private HashMap<T, Tree> locate = new HashMap<T, Tree>();

	public Tree(T head) {
		this.head = head;
		locate.put(head, this);
	}

	public void addLeaf(T root, T leaf) {
		if (locate.containsKey(root)) {
			locate.get(root).addLeaf(leaf);
		} else {
			addLeaf(root).addLeaf(leaf);
		}
	}

	public Tree addLeaf(T leaf) {
		Tree t = new Tree(leaf);
		leafs.add(t);
		t.parent = this;
		t.locate = this.locate;
		locate.put(leaf, t);
		return t;
	}

	public Tree setAsParent(T parentRoot) {
		Tree t = new Tree(parentRoot);
		t.leafs.add(this);
		this.parent = t;
		t.locate = this.locate;
		t.locate.put(head, this);
		t.locate.put(parentRoot, t);
		return t;
	}

	public T getHead() {
		return head;
	}

	public Tree getTree(T element) {
		return locate.get(element);
	}

	public Tree getParent() {
		return parent;
	}

	public Collection getSuccessors(T root) {
		Collection successors = new ArrayList();
		Tree tree = getTree(root);
		if (null != tree) {
			for (Tree leaf : tree.leafs) {
				successors.add(leaf.head);
			}
		}
		return successors;
	}

	public Collection<Tree> getSubTrees() {
		return leafs;
	}

	public static Collection getSuccessors(T of, Collection<Tree> in) {
		for (Tree tree : in) {
			if (tree.locate.containsKey(of)) {
				return tree.getSuccessors(of);
			}
		}
		return new ArrayList();
	}

	public String toString() {
		return printTree(0);
	}

	private static final int indent = 2;

	private String printTree(int increment) {
		String s = "";
		String inc = "";
		for (int i = 0; i < increment; ++i) {
			inc = inc + "   ";
		}
		s = inc + head;
		for (Tree child : leafs) {
			s += "\n" + child.printTree(increment + indent);
		}
		return s;
	}
}