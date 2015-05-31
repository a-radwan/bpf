package com.example.bdf.SQLite;

import java.util.Date;
import java.util.LinkedList;




import com.example.bdf.data.Category;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


class CategoryTable {
	private static final String TABLE_CATEGORIES = "categories";
	private static final String KEY_ID = "categoryid";
	private static final String KEY_NAME = "name";

	static final String[] COLUMNS = { KEY_ID, KEY_NAME };
	static final int COL_ID = 0;
	static final int COL_NAME= 1;
     
         
	
	
	//sdfsdf
	public static Category add(SQLiteDatabase db, Category category) {

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, category.getName()); // get title
		category.setId((int)db.insert(TABLE_CATEGORIES, // table
				null, // nullColumnHack
				values)); // key/value -> keys = column names/ values = column
							// values
		// 4. close
		// db.close();
		return category;
	}

	public static LinkedList<Category> getAll(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		LinkedList<Category> categorys = new LinkedList<Category>();
		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_CATEGORIES;

		// 2. get reference to writable DB
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		Category category = new Category();
		if (cursor.moveToFirst()) {
			do {
				
			category.setId(cursor.getInt(COL_ID));
					category.setName(cursor.getString(COL_NAME));
				// Add book to books
				categorys.add(category);
			} while (cursor.moveToNext());		

			} 
		
//		db.close();
		return categorys;
	}

	public static Category get(SQLiteDatabase db, int id) {
		// 1. get reference to readable DB

		// 2. build query
		Cursor cursor = db.query(TABLE_CATEGORIES, // a. table
				COLUMNS, // b. column names
				KEY_NAME + "= ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		Category category=new Category();
		category.setId(cursor.getInt(COL_ID));
		category.setName(cursor.getString(COL_NAME));
		
		// db.close();
		return category;

	}


	public static void delete(SQLiteDatabase db, Category category) {
		db.delete(TABLE_CATEGORIES, KEY_ID + " = ?",
				new String[] { String.valueOf(category.getId()) });

		// 3. close
//		db.close();

	}

}
