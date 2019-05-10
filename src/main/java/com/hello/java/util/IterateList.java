package com.hello.java.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

public class IterateList {
	
	public static void main(String[] args) {
		List<Integer> list = getUnknownList();
		
		if(list instanceof RandomAccess) { //for(...) > Iterator > ForEach
			int size = list.size();
			for(int i=0; i<size; i++) {
				System.err.println(i);
			}
		}else { // Iterator > ForEach			
			Iterator<Integer> is = list.iterator();
			for(; is.hasNext() ;) {
				System.out.println(is.next());
			}
			
			while(is.hasNext()) {
				System.out.println(is.next());
			}
		}
		
		//Actually ForEach's implementation is from Iterator, but with one more conversion, so a little bit slow
	}
	
	private static List<Integer> getUnknownList(){
		List<Integer> list;
		int size = new Random().nextInt(1000);
		if(size < 100) {
			list = new ArrayList<>(size);
		}else {
			list = new LinkedList<Integer>();
		}
		for(int i=0; i<size; i++) {
			list.add(i);
		}
		return list;
	}
}
