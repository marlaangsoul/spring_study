package com.springstudy.book.vo;

import java.util.Date;

public class cartVo {

//	create table cart (
//			cartNum INT NOT NULL auto_increment,
//			memId varchar(50) not null,
//			proNum int not null,
//			cartStock int not null,
//			cartDate datetime DEFAULT CURRENT_TIMESTAMP,
//			primary key(cartNum, memId));
//	
	
	private int cartNum;
	private String memId;
	private int proNum;
	private int cartStock;
	private Date cartDate;
	private int state;
	
	public cartVo() {
		
	}
		
	public cartVo(int cartNum, String memId, int proNum, int cartStock, Date cartDate, int state) {
		super();
		this.cartNum = cartNum;
		this.memId = memId;
		this.proNum = proNum;
		this.cartStock = cartStock;
		this.cartDate = cartDate;
		this.state = state;
	}


	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public int cartStock() {
		return cartStock;
	}
	public void setcartStock(int cartStock) {
		this.cartStock = cartStock;
	}
	public Date getCartDate() {
		return cartDate;
	}
	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	// ----
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		cartVo other = (cartVo) obj;
		if (cartNum != other.cartNum)
			return false;
		return true;
	}
// ----
	
	@Override
	public String toString() {
		return "cartVo [cartNum=" + cartNum + ", memId=" + memId + ", proNum=" + proNum + ", cartStock=" + cartStock
				+ ", cartDate=" + cartDate + ", state=" + state + "]";
	}
	
	
	
	
}
