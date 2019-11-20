package com.geekerstar.thread.safe;

public class TestSaleWindow {
	public static void main(String[] args) {
		SaleWindow sw=new SaleWindow();

		Thread t1=new Thread(sw);
		Thread t2=new Thread(sw);

		t1.setName("窗口A");
		t2.setName("窗口B");

		t1.start();
		t2.start();
	}
}
