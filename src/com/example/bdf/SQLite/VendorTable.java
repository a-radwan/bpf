package com.example.bdf.SQLite;

import java.util.Date;
import java.util.LinkedList;




import com.example.bdf.data.Vendor;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


class VendorTable {
	private static final String TABLE_VENDORS = "vendors";
	private static final String KEY_VENDORID = "vid";
	private static final String KEY_VENDOR_NAME = "name";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_LONGITUDE = "longitude";
	private static final String KEY_LATITUDE = "latitude";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONE = "Phone";
	static final String[] COLUMNS = { KEY_VENDORID, KEY_VENDOR_NAME,
		KEY_PASSWORD,KEY_LONGITUDE,KEY_LATITUDE , KEY_EMAIL , KEY_PHONE };
	static final int COL_VENDORID = 0;
	static final int COL_NAME= 1;
	static final int COL_PASSWORD = 2;
	static final int COL_LONGITUDE = 3;
	static final int COL_LATITUDE = 4;
	static final int COL_EMAIL = 5;
	static final int COL_PHONE = 6;

	public static Vendor add(SQLiteDatabase db, Vendor vendor) {

		ContentValues values = new ContentValues();
		values.put(KEY_VENDOR_NAME, vendor.getName()); // get title
		values.put(KEY_PASSWORD , vendor.getPassword());
		values.put(KEY_LATITUDE, vendor.getLatitude());
		values.put(KEY_LONGITUDE, vendor.getLongitude());
		values.put(KEY_EMAIL, vendor.getEmail());
		values.put(KEY_PHONE, vendor.getPhone());
		vendor.setId((int)db.insert(TABLE_VENDORS, // table
				null, // nullColumnHack
				values)); // key/value -> keys = column names/ values = column
							// values
		// 4. close
		// db.close();
		return vendor;
	}

	public static LinkedList<Vendor> getAll(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		LinkedList<Vendor> vendors = new LinkedList<Vendor>();
		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_VENDORS;

		// 2. get reference to writable DB
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		Vendor vendor = new Vendor();
		if (cursor.moveToFirst()) {
			do{
				vendor = new Vendor();
					vendor.setId(cursor.getInt(COL_VENDORID));
					vendor.setName(cursor.getString(COL_NAME));
					vendor.setPassword(cursor.getString(COL_PASSWORD));
					vendor.setLatitude(cursor.getDouble(COL_LATITUDE));
					vendor.setLongitude(cursor.getDouble(COL_LONGITUDE));
					vendor.setEmail(cursor.getString(COL_EMAIL));
					vendor.setPhone(cursor.getString(COL_PHONE));
				// Add book to books
				vendors.add(vendor);
			}while(cursor.moveToNext());
		}
		
//		db.close();
		return vendors;
	}

	public static Vendor get(SQLiteDatabase db, int id) {
		// 1. get reference to readable DB

		// 2. build query
		Cursor cursor = db.query(TABLE_VENDORS, // a. table
				COLUMNS, // b. column names
				KEY_VENDORID + "= ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		Vendor vendor=new Vendor();
		vendor.setId(cursor.getInt(COL_VENDORID));
		vendor.setName(cursor.getString(COL_NAME));
		vendor.setPassword(cursor.getString(COL_PASSWORD));
		vendor.setLatitude(cursor.getDouble(COL_LATITUDE));
		vendor.setLongitude(cursor.getDouble(COL_LONGITUDE));
		vendor.setEmail(cursor.getString(COL_EMAIL));
		vendor.setPhone(cursor.getString(COL_PHONE));
		// db.close();
		return vendor;

	}
	public static Vendor get(SQLiteDatabase db, String userName) {
		// 1. get reference to readable DB

		// 2. build query
		Cursor cursor = db.query(TABLE_VENDORS, // a. table
				COLUMNS, // b. column names
				KEY_VENDOR_NAME + "= ?", // c. selections
				new String[] { userName }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		Vendor vendor=new Vendor();


		if (cursor!= null)
		
			if(cursor.moveToFirst())
		{
		vendor.setId(cursor.getInt(COL_VENDORID));
		vendor.setName(cursor.getString(COL_NAME));
		vendor.setPassword(cursor.getString(COL_PASSWORD));
		vendor.setLatitude(cursor.getDouble(COL_LATITUDE));
		vendor.setLongitude(cursor.getDouble(COL_LONGITUDE));
		vendor.setEmail(cursor.getString(COL_EMAIL));
		vendor.setPhone(cursor.getString(COL_PHONE));
		// db.close();
		}
			
		return vendor;

	}



	public static void delete(SQLiteDatabase db, Vendor vendor) {
		db.delete(TABLE_VENDORS, KEY_VENDORID + " = ?",
				new String[] { String.valueOf(vendor.getId()) });

		// 3. close
//		db.close();

	}

}
