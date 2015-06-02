package com.example.bdf.SQLite;

import java.util.Date;
import java.util.LinkedList;
import com.example.bdf.data.Product;
import com.example.bdf.data.Vendor;
import com.example.bdf.data.VendorHasProduct;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


class VendorHasProductTable {

	private static final String TABLE_VENDORS_HAS_PRODUCT = "vendorhasproduct";
	private static final String KEY_ID = "hasid";
	private static final String KEY_BARCODE = "barcode";
	private static final String KEY_VENDOR_ID = "vid";
	private static final String KEY_PRICE = "price";

	static final String[] COLUMNS = { KEY_ID, KEY_VENDOR_ID,
		KEY_BARCODE,KEY_PRICE };
	static final int COL_ID = 0;
	static final int COL_BARCODE = 1;
	static final int COL_VENDOR_ID= 2;
	static final int COL_PRICE = 3;


	public static void add(SQLiteDatabase db, Vendor vendor,Product product,double price) {
		if (!ProductTable.has(db, product.getBarcode())) {
			ProductTable.add(db, product);
		}
		ContentValues values = new ContentValues();
		values.put(KEY_VENDOR_ID, vendor.getId()); // get title
		values.put(KEY_BARCODE , product.getBarcode());
		values.put(KEY_PRICE, price);
		vendor.setId((int)db.insert(TABLE_VENDORS_HAS_PRODUCT, // table
				null, // nullColumnHack
				values)); // key/value -> keys = column names/ values = column
							// values
		// 4. close
		// db.close();
		
	}
	public static VendorHasProduct add(SQLiteDatabase db, VendorHasProduct vendorHasProduct) {

		ContentValues values = new ContentValues();
		values.put(KEY_VENDOR_ID, vendorHasProduct.getVendorId()); // get title
		values.put(KEY_BARCODE ,vendorHasProduct.getProductBarcode());
		values.put(KEY_PRICE, vendorHasProduct.getPrice());
		vendorHasProduct.setId((int)db.insert(TABLE_VENDORS_HAS_PRODUCT, // table
				null, // nullColumnHack
				values)); // key/value -> keys = column names/ values = column
							// values
		// 4. close
		// db.close();
		return vendorHasProduct;
	}


	public static LinkedList<VendorHasProduct> getAll(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		LinkedList<VendorHasProduct>vendorHasProductList = new LinkedList<VendorHasProduct>();
		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_VENDORS_HAS_PRODUCT;

		// 2. get reference to writable DB
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		VendorHasProduct vendorHasProduct = new VendorHasProduct();
		if (cursor.moveToFirst()) {
					vendorHasProduct.setId(cursor.getInt(COL_ID));
					vendorHasProduct.setProductBarcode(cursor.getString(COL_BARCODE));
					vendorHasProduct.setVendorId(cursor.getInt(COL_VENDOR_ID));
					vendorHasProduct.setPrice(cursor.getDouble(COL_PRICE));
				// Add book to books
					vendorHasProductList.add(vendorHasProduct);
			} 
		
//		db.close();
		return vendorHasProductList;
	}
	public static LinkedList<VendorHasProduct> getAll(SQLiteDatabase db,Product product) {
		// TODO Auto-generated method stub
		LinkedList<VendorHasProduct>vendorHasProductList = new LinkedList<VendorHasProduct>();
		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_VENDORS_HAS_PRODUCT +" where "+KEY_BARCODE+"="+product.getBarcode();

		// 2. get reference to writable DB
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		VendorHasProduct vendorHasProduct = new VendorHasProduct();
		if (cursor.moveToFirst()) {
			do{
					vendorHasProduct.setId(cursor.getInt(COL_ID));
					vendorHasProduct.setProductBarcode(cursor.getString(COL_BARCODE));
					vendorHasProduct.setVendorId(cursor.getInt(COL_VENDOR_ID));
					vendorHasProduct.setPrice(cursor.getDouble(COL_PRICE));
				// Add book to books
					vendorHasProductList.add(vendorHasProduct);
			}while(cursor.moveToNext());
			} 
		
//		db.close();
		return vendorHasProductList;
	}
	public static LinkedList<VendorHasProduct> getAll(SQLiteDatabase db,Vendor vendor) {
		// TODO Auto-generated method stub
		LinkedList<VendorHasProduct>vendorHasProductList = new LinkedList<VendorHasProduct>();
		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_VENDORS_HAS_PRODUCT +" where "+KEY_VENDOR_ID+"="+vendor.getId();

		// 2. get reference to writable DB
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		VendorHasProduct vendorHasProduct;
		if (cursor.moveToFirst()) {
			do{
				vendorHasProduct = new VendorHasProduct();
					vendorHasProduct.setId(cursor.getInt(COL_ID));
					vendorHasProduct.setProductBarcode(cursor.getString(COL_BARCODE));
					vendorHasProduct.setVendorId(cursor.getInt(COL_VENDOR_ID));
					vendorHasProduct.setPrice(cursor.getDouble(COL_PRICE));
				// Add book to books
					vendorHasProductList.add(vendorHasProduct);
		}while(cursor.moveToNext());
		} 
		
//		db.close();
		return vendorHasProductList;
	}


//	public static Vendor get(SQLiteDatabase db, int id) {
//		// 1. get reference to readable DB
//
//		// 2. build query
//		Cursor cursor = db.query(TABLE_VENDORS_HAS_PRODUCT, // a. table
//				COLUMNS, // b. column names
//				KEY_VENDOR_ID + "= ?", // c. selections
//				new String[] { String.valueOf(id) }, // d. selections args
//				null, // e. group by
//				null, // f. having
//				null, // g. order by
//				null); // h. limit
//
//		// 3. if we got results get the first one
//		if (cursor != null)
//			cursor.moveToFirst();
//		Vendor vendor=new Vendor();
//		vendor.setId(cursor.getInt(COL_VENDORID));
//		vendor.setName(cursor.getString(COL_NAME));
//		vendor.setPassword(cursor.getString(COL_PASSWORD));
//		vendor.setLatitude(cursor.getDouble(COL_LATITUDE));
//		vendor.setLongitude(cursor.getDouble(COL_LONGITUDE));
//		
//		// db.close();
//		return vendor;
//
//	}

	public static int update(SQLiteDatabase db, VendorHasProduct vendorHasProduct) {
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_VENDOR_ID, vendorHasProduct.getVendorId()); // get title
		values.put(KEY_BARCODE ,vendorHasProduct.getProductBarcode());
		values.put(KEY_PRICE, vendorHasProduct.getPrice());

		// 3. updating row
		int i = db.update(TABLE_VENDORS_HAS_PRODUCT, // table
				values, // column/value
				KEY_ID + " = ?", // selections
				new String[] { String.valueOf(vendorHasProduct.getId()) }); // selection
																	// args

		// 4. close
		// db.close();
		return i;
	}
	public static void delete(SQLiteDatabase db, VendorHasProduct vendorHasProduct) {
		db.delete(TABLE_VENDORS_HAS_PRODUCT, KEY_ID + " = ?",
				new String[] { String.valueOf(vendorHasProduct.getId()) });

		// 3. close
//		db.close();

	}

}
