package org.devdas.order.bo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.devdas.order.bo.exception.BOException;
import org.devdas.order.dao.OrderDAO;
import org.devdas.order.dto.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrderBOImplTest {
	private static final int ORDER_ID = 123;
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}

	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException{	
		
		Order order=new Order();
		//when(dao.create(order)).thenReturn(new Integer(1));
		when(dao.create(any(Order.class))).thenReturn(new Integer(1));
		
		boolean result=bo.placeOrder(order);
		
		assertTrue(result);
		//verify(dao).create(order);
		//verify(dao,times(1)).create(order);
		verify(dao,atLeast(1)).create(order);
	}
	
	@Test
	public void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException{
		
		Order order=new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		
		boolean result=bo.placeOrder(order);
		
		assertFalse(result);
		verify(dao).create(order);
	}
	
	@Test
	public void placeOrder_Should_Throw_An_BOException() throws SQLException, BOException{
		assertThrows(BOException.class, ()->{
			
			Order order=new Order();
			when(dao.create(order)).thenThrow(SQLException.class);
			
			boolean result=bo.placeOrder(order);	
		});
	}
	// to test cancel order
	
	@Test
	public void cancelOrder_Should_Cancel_The_order() throws SQLException, BOException{
		
		Order order=new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertTrue(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);		
	}
	//testing cancel for negative secnario
	@Test
	public void cancelOrder_Should_Not_Cancel_The_order() throws SQLException, BOException{
		
		Order order=new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertFalse(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);		
	}
	@Test
	public void cancelOrder_Should_Throw_A_BOException_On_Read() throws SQLException, BOException{
		
		assertThrows(BOException.class,()->{
			when(dao.read(anyInt())).thenThrow(SQLException.class);
			bo.cancelOrder(ORDER_ID);
		});		
	}
	@Test
	public void cancelOrder_Should_Throw_BOException_On_Update() throws SQLException, BOException{
		
		assertThrows(BOException.class,()->{
		Order order=new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
		});
		
	}
	
	//to test delete order 
	@Test
	public void deleteOrder_Deletes_The_order() throws SQLException, BOException{
		when(dao.delete(ORDER_ID)).thenReturn(1);
		boolean result = bo.deleteOrder(ORDER_ID);
		assertTrue(result);
		
		verify(dao).delete(ORDER_ID);
		
	}

}
