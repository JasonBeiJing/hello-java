package com.hello.java.a;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main10 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			int size = scanner.nextInt();
			int prefixNumber = 0;
			for (int i = size; i > 0; i--) {
				List<String> line = getNumbers(i);
				insertPrefixTab(line, prefixNumber++);
				System.out.println(join(line, ""));
			}
		}
	}

	private static List<String> getNumbers(int size) {
		List<String> list1 = new ArrayList<>(size);
		for (int i = 1; i <= size; i++) {
			list1.add("\t" + i);
		}

		List<String> list2 = new ArrayList<>(size - 1);
		int rs = list1.size() - 2;
		for (int i = rs; i > -1; i--) {
			list2.add(list1.get(i));
		}
		List<String> out = new ArrayList<>(size * 2 - 1);
		out.addAll(list1);
		out.addAll(list2);
		return out;
	}

	private static void insertPrefixTab(List<String> list, int lineNumber) {
		String firstElement = list.get(0);
		firstElement = getTabs(lineNumber) + firstElement;
		list.set(0, firstElement);
	}

	private static String getTabs(int size) {
		StringBuilder tabs = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			tabs.append("\t");
		}
		return tabs.toString();
	}
	
	private static String join(final List<String> in, final String separator) {
		Iterator<String> line = in.iterator();
		if (!line.hasNext()) {
            return "";
        }
        final Object first = line.next();
        if (!line.hasNext()) {
            return Objects.toString(first, "");
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (line.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = line.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }
}
