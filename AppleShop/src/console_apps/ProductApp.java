package console_apps;

import java.util.Scanner;

import model.Product;

public class ProductApp {

	public static void main(String[] args) {
		String result = null;
		String s = "hello";
		if (s.length() <= 1) {
			result = s;
		}
		else {
			String part = s.substring(0, s.length() - 1);
			result = s.substring(s.length() - 1);
			System.out.println(result);
			}
		}
	}

