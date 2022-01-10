package console_app;

import model.Projection;

public class ConsoleApp {

	public static void main(String[] args) {
		int[] a = new int[0];
		int[] b = new int[0];
		Projection p = new Projection(a, b);
		Object c = (Projection) p;
		
		System.out.println(2 % 4);
	}

}
