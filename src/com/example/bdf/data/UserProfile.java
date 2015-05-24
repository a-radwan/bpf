package com.example.bdf.data;

import android.content.Context;

import com.example.bdf.SQLite.SQLiteHelper;

public class UserProfile {
	public static Vendor currentUser;
	private UserProfile() {
		// TODO Auto-generated constructor stub
	}
	public Vendor login(int userId,Context context)
	{
		SQLiteHelper db=SQLiteHelper.getInstance(context);
		currentUser=db.getVendor(userId);
		return currentUser;
	}
	public static Vendor getCurrntUser()
	{
		return currentUser;
	}
	

}
