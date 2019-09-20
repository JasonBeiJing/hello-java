package com.hello.java.b;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
    	Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        while (scanner.hasNext()) {
        	BigDecimal price1 = new BigDecimal(scanner.nextLine());
        	BigDecimal price2 = new BigDecimal(scanner.nextLine());
        	if (price1.compareTo(BigDecimal.ZERO) < 0 || 
        			price2.compareTo(BigDecimal.ZERO) < 0) {
				System.err.println("Invlid Price Input: " + price1 + ", " + price2);
			} else {				
				BigDecimal finalPrice = price1.subtract(price2);
				finalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
				if (finalPrice.compareTo(BigDecimal.ZERO) < 0) {
					System.err.println("Invlid Price Input: " + price1 + ", " + price2);
				} else {					
					System.out.println(finalPrice);
				}
			}
        }
	}

}
