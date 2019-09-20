package com.hello.java.tests;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.util.Scanner;

// 每个句子由多个单词组成，句子中的每个单词的长度都可能不一样，我们假设每个单词的长度Ni为该单词的重量，你需要做的就是给出整个句子的平均重量V。
public class Main3 {

    public static void main(String[] args) {
    	System.out.println("输入：");
    	Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        while (scanner.hasNext()) {
            String[] cs = scanner.nextLine().split(" ");
            int length = cs.length;
            double total = 0;
            for (int i = 0; i < length; i++) {
                total += cs[i].length();
            }
            double result = total / length;
            BigDecimal bd = BigDecimal.valueOf(result);
            result = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            System.out.println(result);
        }
    }
}
