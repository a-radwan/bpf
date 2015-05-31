package com.example.bdf.SQLite;

import java.util.Date;
import java.util.LinkedList;

import com.example.bdf.data.Product;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


class ProductTable {
	private static final String TABLE_PRODUCTS = "products";
	private static final String KEY_PRODUCT_BARCODE = "barcode";
	private static final String KEY_PRODUCT_NAME = "name";
	private static final String KEY_CATEGORYID = "categoryid";

	static final String[] COLUMNS = { KEY_PRODUCT_BARCODE, KEY_PRODUCT_NAME,
		KEY_CATEGORYID };
	static final int COL_PRODUCT_BARCODE = 0;
	static final int COL_NAME= 1;
	static final int COL_CATEGORYID = 2;

        
	public static void add(SQLiteDatabase db, Product product) {

		ContentValues values = new ContentValues();
		values.put(KEY_PRODUCT_BARCODE, product.getBarcode()); // get title
		values.put(KEY_PRODUCT_NAME , product.getName());
		values.put(KEY_CATEGORYID, product.getCategoryId());
		db.insert(TABLE_PRODUCTS, // table
				null, // nullColumnHack
				values); // key/value -> keys = column names/ values = column
							// values
		// 4. close
		// db.close();
		
	}

	public static LinkedList<Product> getAll(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		LinkedList<Product> products = new LinkedList<Product>();
		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_PRODUCTS;

		// 2. get reference to writable DB
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		Product product = new Product();
		if (cursor.moveToFirst()) {
					product.setBarcode(cursor.getString(COL_PRODUCT_BARCODE));
					product.setName(cursor.getString(COL_NAME));
					product.setCategoryId(cursor.getString(COL_CATEGORYID));
				
				// Add book to books
				products.add(product);
			} 
		
//		db.close();
		return products;
	}

	public static Product get(SQLiteDatabase db, int id) {
		// 1. get reference to readable DB

		// 2. build query
		Cursor cursor = db.query(TABLE_PRODUCTS, // a. table
				COLUMNS, // b. column names
				KEY_PRODUCT_BARCODE + "= ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		Product product=new Product();
		product.setBarcode(cursor.getString(COL_PRODUCT_BARCODE));
		product.setName(cursor.getString(COL_NAME));
		product.setCategoryId(cursor.getString(COL_CATEGORYID));
		// db.close();
		return product;

	}
	public static boolean has(SQLiteDatabase db, String barcode) {
		// 1. get reference to readable DB

		// 2. build query
		Cursor cursor = db.query(TABLE_PRODUCTS, // a. table
				COLUMNS, // b. column names
				KEY_PRODUCT_BARCODE + "= ?", // c. selections
				new String[] { String.valueOf(barcode) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one

			if(cursor.moveToFirst())
			return true;
		return false;
	}


	public static void delete(SQLiteDatabase db, Product product) {
		db.delete(TABLE_PRODUCTS, KEY_PRODUCT_BARCODE + " = ?",
				new String[] { String.valueOf(product.getCategoryId()) });

		// 3. close
//		db.close();

	}

}
