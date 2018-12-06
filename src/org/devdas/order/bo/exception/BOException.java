package org.devdas.order.bo.exception;

import java.sql.SQLException;

public class BOException extends Exception {

	 static final long serialVersionUID = 1L;

	public BOException(SQLException e) {
		super(e);
	}

}
