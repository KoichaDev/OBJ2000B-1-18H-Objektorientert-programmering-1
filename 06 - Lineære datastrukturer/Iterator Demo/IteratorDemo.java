import java.util.*;

public class IteratorDemo {

	public static void main(String[] args){

		ArrayList <String> list = new ArrayList<String>();

		list.add("One");
		list.add("Two");
		list.add("Three");

		System.out.println(list);


		Iterator <String> it = list.iterator();
		String l = "";		
		while(it.hasNext()) {

			l = it.next();
			System.out.println(l);
		}

		//System.out.println(list);
	}	
}