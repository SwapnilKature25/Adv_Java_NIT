package com.nit.jdbc.assignments.day5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class OnlineBankingSystem {
	// 1
	static String url = "jdbc:oracle:thin:@localhost:1522:orcl";
	static String userName = "swapnil";
	static String pass = "swap25";

	// 3
	private long senderAccNo = 123456;
	private long receiverAccNo = 654321;
	private float transferAmount = 500f;

	public long getSenderAccNo() {
		return senderAccNo;
	}

	public long getReceiverAccNo() {
		return receiverAccNo;
	}

	public float getTransferAmount() {
		return transferAmount;
	}

	public static void main(String[] args) {
		OnlineBankingSystem bs = new OnlineBankingSystem();
		try {
			// 2
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, userName, pass);

			// 4
			con.setAutoCommit(false);

			// 5
			PreparedStatement ps1 = con.prepareStatement("select * from accounts where accNo = ?");
			PreparedStatement ps2 = con.prepareStatement("Update accounts set balance=balance+? where accNo = ? ");

			long sendacc = bs.getSenderAccNo();
			long receacc = bs.getReceiverAccNo();
//			System.out.println("Enter the Sender's Account no : ");
//			long sAccNo=Long.parseLong(sc.nextLine());
			ps1.setLong(1, sendacc);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				float senderBal = rs1.getFloat(2);
				ps1.setLong(1, receacc);
				ResultSet rs2 = ps1.executeQuery();

				if (rs2.next()) {
					float amt = bs.getTransferAmount();
					if (amt <= senderBal) {
						ps2.setFloat(1, -amt);
						ps2.setLong(2, sendacc);
						int p = ps2.executeUpdate();

						ps2.setFloat(1, amt);
						ps2.setLong(2, receacc);
						int q = ps2.executeUpdate();

						if (p == 1 && q == 1) {
							con.commit();
							System.out.println("Transaction Successfull...");
						} else {

							System.out.println("Transaction Failed..");
						}

					} else {
						System.err.println("Insufficient funds in Senders account");
						System.exit(0);
					}
				} else {
					System.out.println("Invalid BankAccNo..");
				}
			} else {
				System.out.println("Invalid Sender Account No..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
