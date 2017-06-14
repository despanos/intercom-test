package me.despanos.flattener;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ArrayFlattenerTest {

	@Test
	public void whenNullArrayIsUsedThenNullIsReturned(){
		assertNull("Null array", ArrayFlattener.flatten(null));
	}
	
	@Test
	public void whenEmptyArrayIsUsedThenEmptyArrayIsReturned(){
		assertArrayEquals("Empty array", new Integer[0], ArrayFlattener.flatten(new Object[0]));
	}
	
	@Test
	public void whenFlatArrayIsUsedThenTheSameArrayIsReturned(){
		assertArrayEquals("Flat array", new Integer[]{1,2,3,4,5}, ArrayFlattener.flatten(new Integer[]{1,2,3,4,5}));
	}
	
	@Test
	public void twoLevelNestedArrayTest(){
		assertArrayEquals("Two-level nested array", new Integer[]{1,2,3,4,5},
				ArrayFlattener.flatten(new Object[]{1, new Integer[]{2,3}, new Integer[]{4,5}}));
	}
	
	@Test
	public void twoLevelNestedArrayTestWithEmptyArray(){
		assertArrayEquals("Two-level nested array with empty array", new Integer[]{1,2,3,4,5},
				ArrayFlattener.flatten(new Object[]{1, new Integer[0], new Integer[]{2,3}, new Integer[]{4,5}}));
	}
	
	@Test
	public void threeLevelNestedArrayTest(){
		assertArrayEquals("Three-level nested array", new Integer[]{1,2,3,4,5,6,7},
				ArrayFlattener.flatten(new Object[]{1, new Object[]{new Integer[]{2,3}, new Integer[]{4,5}}, new Integer[]{6,7}}));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenAStringIsPassedInsideAFlatArrayThenAnExceptionIsThrown(){
		ArrayFlattener.flatten(new Object[]{1,2,"bla",4,5});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenAStringIsPassedInsideATwoLevelNestedArrayThenAnExceptionIsThrown(){
		ArrayFlattener.flatten(new Object[]{1, new Object[]{2,"bla"}, new Integer[]{4,5}});
	}
	
}
