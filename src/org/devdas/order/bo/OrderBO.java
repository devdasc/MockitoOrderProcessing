package org.devdas.order.bo;

import org.devdas.order.bo.exception.BOException;
import org.devdas.order.dto.Order;

public interface OrderBO {
	boolean placeOrder(Order order) throws BOException;
	boolean cancelOrder(int id) throws BOException;
	boolean deleteOrder(int id) throws BOException;

}
