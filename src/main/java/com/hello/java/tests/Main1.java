package com.hello.java.tests;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.Stack;

//括号
public class Main1 {

	private final static char[] left = {'{', '(', '[', '<'};
	private final static char[] right = {'}', ')', ']', '>'};
	
    public static void main(String[] args) {
    	System.out.println("输入：");
    	Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        while (scanner.hasNext()) {
        	String str = scanner.nextLine();
        	char[] cs =str.toCharArray();
        	Stack<Character> stack = new Stack<>();
        	boolean ok = true;
        	for (char c : cs) {
        		if (isLeft(c)) {
        			stack.push(c);
    			} else if (!stack.isEmpty() && isRight(c) && isOk(stack.peek(), c)) {
    				stack.pop();
				} else {
					ok = false;
					break;
				}
			}
        	System.out.println(" ---> " + (ok && stack.isEmpty() ? "YES" : "NO"));
        }
    }
    
    private static boolean isLeft(char in) {
    	for (char l : left) {
			if (l == in) {
				return true;
			}
		}
    	return false;
    }
    
    private static boolean isRight(char in) {
    	for (char r : right) {
			if (r == in) {
				return true;
			}
		}
    	return false;
    }
    
    private static boolean isOk(char l, char r) {
    	int length = left.length;
    	for (int i = 0; i < length; i++) {
			char c = left[i];
			if (c == l && right[i] == r) {
				return true;
			}
		}
    	return false;
    }
}

