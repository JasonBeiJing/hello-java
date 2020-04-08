package com.hello.java.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.alibaba.fastjson.JSONObject;

public class Test31 {

	static class NonLeafNode {
		int nodeId;
		int[] subNodeIds;
		
		NonLeafNode(int nodeId, int subNodeNum){
			this.nodeId = nodeId;
			this.subNodeIds = new int[subNodeNum];
		}
	}
	
	static class Node{
		int nodeId;
		int capacity;
		int[] subNodeIds;
		List<Node> subNodes;
		public Node(int nodeId, int capacity) {
			super();
			this.nodeId = nodeId;
			this.capacity = capacity;
		}
	}
	
	/**
	 * 总结点：N < 100, 非叶子节点：M
	 * @param capacitys 20个节点容量，编码为00 01 02... 19 -> 10 2 4 ... 2
	 * @param nonLeafNodes 9个非叶子节点
	 * @param targatCapacity 目标数值
	 * @return
	 */
	static String[] getPathOfCapacity(int[] capacitys, NonLeafNode[] nonLeafNodes, int targatCapacity) {
		List<Node> nodes = buildNodes(capacitys, nonLeafNodes);
		return null;
//		Map<Integer, List<Node>> pathMap = new HashMap<>();
//		Stack<Node> stack = new Stack<>();
//		iterateNode(nodes.get(0), pathMap, stack);
//		Set<Entry<Integer, List<Node>>> map = pathMap.entrySet();
//		List<String> out = new ArrayList<>();
//		for (Entry<Integer, List<Node>> entry : map) {
//			List<Node> linkedNodes = entry.getValue();
//			int count = 0;
//			for (Node linkedNode : linkedNodes) {
//				count += linkedNode.capacity;
//			}
//			if (count == targatCapacity) {
//				StringBuilder sb = new StringBuilder();
//				for (Node linkedNode : linkedNodes) {
//					if (sb.length() > 0) {
//						sb.append(" ");
//					}
//					sb.append(linkedNode.capacity + "");
//				}
//				out.add(sb.toString());
//			}
//		}
//		out.sort(new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o1.compareTo(o2) * -1;
//			}
//		});
//		return out.toArray(new String[out.size()]);
	}
	
	static List<Node> buildNodes(int[] capacitys, NonLeafNode[] nonLeafNodes){
		int totalNodeSize = capacitys.length;
		List<Node> nodes = new ArrayList<>(totalNodeSize);
		for (int i = 0; i < totalNodeSize; i++) {
			Node node = new Node(i, capacitys[i]);
			int l = nonLeafNodes.length;
			for (int j = 0; j < l; j++) {
				NonLeafNode nonLeafNode = nonLeafNodes[j];
				if (nonLeafNode.nodeId == i) {
					node.subNodeIds = nonLeafNode.subNodeIds;
					break;
				}
			}
			nodes.add(node);
		}
		for (Node node : nodes) {
			node.subNodes = findNodesByIds(nodes, node.subNodeIds);
		}
		return nodes;
	}
	
	static void iterateNode(Node currenctNode, Map<Integer, List<Node>> pathMap, Stack<Node> stack) {
		stack.push(currenctNode);
		List<Node> subNodes = currenctNode.subNodes;
		if (subNodes == null) {
			List<Node> lst = new ArrayList<>();
			Iterator<Node> stackIt = stack.iterator();
			while (stackIt.hasNext()) {
				lst.add(stackIt.next());
			}
			pathMap.put(currenctNode.nodeId, lst);
			return;
		} else {
			Iterator<Node> it = subNodes.listIterator();
			while (it.hasNext()) {
				Node child = it.next();
				iterateNode(child, pathMap, stack);
				stack.pop();
			}
		}
	}
	
	static List<Node> findNodesByIds(List<Node> nodes, int[] ids) {
		List<Node> re = null;
		if (ids == null) {
			re = new ArrayList<>(0);
		} else {
			re = new ArrayList<>(ids.length);
			for (int id : ids) {			
				for (Node node : nodes) {
					if (node.nodeId == id) {
						Node newNode = new Node(node.nodeId, node.capacity);
						re.add(newNode);
						break;
					}
				}
			}
		}
		return re;
	}
	
	public static void main(String[] args) {
		int[] capacitys = {10, 2, 4, 3, 5, 10, 2, 18, 9, 7, 2, 2, 1, 3, 12, 1, 8, 6, 2, 2};
		NonLeafNode n1 = new NonLeafNode(0, 1);
		NonLeafNode n2 = new NonLeafNode(0, 2);
		NonLeafNode n3 = new NonLeafNode(0, 3);
		NonLeafNode n4 = new NonLeafNode(0, 4);
		NonLeafNode n5 = new NonLeafNode(2, 5);
		NonLeafNode n6 = new NonLeafNode(4, 6);
		NonLeafNode n7 = new NonLeafNode(4, 7);
		NonLeafNode n8 = new NonLeafNode(3, 11);
		NonLeafNode n9 = new NonLeafNode(3, 12);
		NonLeafNode n10 = new NonLeafNode(3, 13);
		NonLeafNode n11 = new NonLeafNode(3, 9);
		NonLeafNode n12 = new NonLeafNode(7, 8);
		NonLeafNode n13 = new NonLeafNode(7, 10);
		NonLeafNode n14 = new NonLeafNode(16, 15);
		NonLeafNode n15 = new NonLeafNode(13, 14);
		NonLeafNode n16 = new NonLeafNode(13, 16);
		NonLeafNode n17 = new NonLeafNode(13, 17);
		NonLeafNode n18 = new NonLeafNode(17, 18);
		NonLeafNode n19 = new NonLeafNode(17, 19);
		NonLeafNode[] nonLeafNodes = {n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19};
		String[] xx = getPathOfCapacity(capacitys, nonLeafNodes, 24);
	
	}
}

