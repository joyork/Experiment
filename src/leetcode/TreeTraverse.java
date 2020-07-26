package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTraverse {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node != null) {
                if(node.right != null) stack.push(node.right);
                stack.push(node);
                stack.push(null);
                if(node.left != null) stack.push(node.left);
            } else {
                res.add(stack.pop().val);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        TreeNode right = new TreeNode(3);
        right.left = new TreeNode(2);
        right.right = new TreeNode(5);
        root.right = right;
        List<Integer> list = inorderTraversal(root);
        System.out.println(list);
    }

}
