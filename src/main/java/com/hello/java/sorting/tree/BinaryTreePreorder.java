package com.hello.java.sorting.tree;

import com.hello.java.sorting.util.RandomArrayUtil;

public class BinaryTreePreorder {
	
	public static void preOrder(BinaryTree root) { // 前序遍历
		if (root != null) {
			System.out.print(root.data + "==>");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public static void inOrder(BinaryTree root) { // 中序遍历
		if (root != null) {
			inOrder(root.left);
			System.err.print(root.data + "**>");
			inOrder(root.right);
		}
	}

	public static void postOrder(BinaryTree root) { // 后序遍历
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + "-->");
		}
	}

	public static void main(String[] str) throws InterruptedException {
		int[] array = RandomArrayUtil.getRandomInt(100);
		
		BinaryTree root = new BinaryTree(array[0]); // 创建二叉树
		for (int i = 1; i < array.length; i++) {
			root.add(root, array[i]); // 向二叉树中插入数据
		}
		
		///////////////////////////////////
		//不管什么遍历，左右这个顺序是不变的，变得只中间~~
//		preOrder(root);
		inOrder(root);
//		postOrder(root);
	}
}

class BinaryTree {
	int data; // 根节点数据
	BinaryTree left; // 左子树
	BinaryTree right; // 右子树

	public BinaryTree(int data) { // 实例化二叉树类
		this.data = data;
		left = null;
		right = null;
	}

	public void add(BinaryTree root, int data) { // 向二叉树中插入子节点
		if (data > root.data) { // 二叉树的左节点都比根节点小
			if (root.right == null) {
				root.right = new BinaryTree(data);
			} else {
				this.add(root.right, data);
			}
		} else { // 二叉树的右节点都比根节点大
			if (root.left == null) {
				root.left = new BinaryTree(data);
			} else {
				this.add(root.left, data);
			}
		}
	}
}
