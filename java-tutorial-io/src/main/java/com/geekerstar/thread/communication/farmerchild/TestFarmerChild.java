package com.geekerstar.thread.communication.farmerchild;

public class TestFarmerChild {
	public static void main(String[] args) {
		new Farmer().start();
		new Child().start();
	}
}
