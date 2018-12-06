package org.devdas.mockito.spy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class ListTest {
	@Spy
	List<String> myList=new ArrayList<>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() {
		myList.add("devdas");
		myList.add("devdas2");
		
		
		Mockito.doReturn(3).when(myList).size();
		assertSame(3,myList.size());
	}

}
