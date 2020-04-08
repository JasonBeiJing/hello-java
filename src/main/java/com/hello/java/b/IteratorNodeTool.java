package com.hello.java.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class IteratorNodeTool {
	static Map<String, List> pathMap = new HashMap();// 记录全部从根节点到叶子结点的路径

	public void iteratorNode(Node n, Stack<Node> pathstack) {
		pathstack.push(n);// 入栈
		List childlist = n.getChildList();
		if (childlist == null)// 没有孩子 说明是叶子结点
		{
			List lst = new ArrayList();
			Iterator stackIt = pathstack.iterator();
			while (stackIt.hasNext()) {
				lst.add(stackIt.next());
			}
			pathMap.put(n.getText(), lst);// 保存路径信息
			return;
		} else {
			Iterator it = childlist.iterator();
			while (it.hasNext()) {
				Node child = (Node) it.next();
				iteratorNode(child, pathstack);// 深度优先 进入递归
				pathstack.pop();// 回溯时候出栈
			}

		}

	}

	public static void main(String[] args) {
		Stack<Node> pathstack = new Stack<>();
		Node n = Node.getInitNode();
		IteratorNodeTool tool = new IteratorNodeTool();
		tool.iteratorNode(n, pathstack);
		pathMap.forEach((key, value) -> {
			System.out.println(key + " === " + value);
		});
	}

	static class Node {
		@Override
		public String toString() {
			return "node [text=" + text + "]";
		}

		private String text;
		private List<Node> childList;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public List<Node> getChildList() {
			return childList;
		}

		public void setChildList(List<Node> childList) {
			this.childList = childList;
		}

		public static Node getInitNode() {
			Node nodeA = new Node();
			nodeA.setText("A");
			Node nodeB = new Node();
			nodeB.setText("B");
			Node nodeC = new Node();
			nodeC.setText("C");
			Node nodeD = new Node();
			nodeD.setText("D");
			Node nodeE = new Node();
			nodeE.setText("E");

			List<Node> lstB = new ArrayList();
			lstB.add(nodeC);
			lstB.add(nodeD);
			nodeB.setChildList(lstB);

			List<Node> lstA = new ArrayList();
			lstA.add(nodeB);
			lstA.add(nodeE);
			nodeA.setChildList(lstA);
			return nodeA;

		}
	}
}
