package com.hello.java.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test3 {

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
		List<Node> nodes = listAllNodes(capacitys, nonLeafNodes);
		String[] out = new String[0];
		return out;
	}
	
	static List<Node> listAllNodes(int[] capacitys, NonLeafNode[] nonLeafNodes){
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
		return nodes;
	}
	
	static List<List<Node>> listLinkedNodes(List<Node> nodes, Node currentNode) {
		List<List<Node>> out = new ArrayList<>();
		if (currentNode == null) {			
			currentNode = nodes.get(0);
		}
		return out;
	}
	
	static List<Node> listSubNodes(List<Node> nodes, Node currentNode){
		int[] subNodeIds;
		int subNodeLength;
		if (currentNode == null || (subNodeIds = currentNode.subNodeIds) == null
				|| (subNodeLength = subNodeIds.length) == 0) {
			return Collections.emptyList();
		}
		List<Node> result = new ArrayList<>(subNodeLength);
		for (int i = 0; i < subNodeLength; i++) {
			int subNodeId = subNodeIds[i];
			result.add(findNodeById(nodes, subNodeId));
		}
		return result;
	}
	
	static Node findNodeById(List<Node> nodes, int id) {
		for (Node node : nodes) {
			if (node.nodeId == id) {
				return node;
			}
		}
		return null;
	}
}

