package me.despanos.flattener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayFlattener {

	public static Integer[] flatten(Object[] nested){
		if (nested==null)
			return null;
		List<Integer> flat = new ArrayList<>();
		for (Object element : nested){
			if (element instanceof Integer)
				flat.add((Integer)element);
			else if (element instanceof Object[])
				flat.addAll(Arrays.asList(flatten((Object[])element)));
			else throw new IllegalArgumentException("The input array must contain only integers");
		}
		return flat.toArray(new Integer[0]);
	}
	
}
