package com.hello.java.b;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main7 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			calculate(scanner.nextInt());
		}
	}

	public static void calculate(int n) {
		int[] level1 = new int[n];
		int[] level2 = new int[n];
		calculate(0, level1, level2);
	}

	private static void calculate(int step, int[] level1, int[] level2) {
		int length = level1.length;
		if (step == length) {
			List<Integer> nums = new ArrayList<>(length);
			for (int i = 0; i < length; i++) {
				//System.out.print(level1[i]);
				nums.add(level1[i]);
			}
			System.out.print(join(nums, " "));
			System.out.println();
			return;
		}
		for (int i = 0; i < length; i++) {
			if (level2[i] == 0) {
				level1[step] = i + 1;
				level2[i] = 1;
				calculate(step + 1, level1, level2);
				level2[i] = 0;
			}
		}
	}
	
	private static String join(final List<Integer> in, final String separator) {
		Iterator<Integer> line = in.iterator();
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
