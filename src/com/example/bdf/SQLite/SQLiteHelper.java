package com.example.bdf.SQLite;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.example.bdf.data.Category;
import com.example.bdf.data.Product;
import com.example.bdf.data.Vendor;
import com.example.bdf.data.VendorHasProduct;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
	private static SQLiteHelper sInstance;
	private static final String ENCODING_SETTING = "PRAGMA encoding ='windows-1256'";
	private static Context context;
	public static int OTHER_CATEGORY_ID;
	public static int notificationFlag=0;
	// Database Version
	private static final int DATABASE_VERSION = 10;
	// Database Name
	private static final String DATABASE_NAME = "bdfDB";

	public static SQLiteHelper getInstance(Context context) {

		if (sInstance == null) {
			sInstance = new SQLiteHelper(context.getApplicationContext());
			SQLiteHelper.context = context.getApplicationContext();
		}
		return sInstance;
	}

	/**
	 * Constructor should be private to prevent direct instantiation. make call
	 * to static factory method "getInstance()" instead.
	 */
	private SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create  tables
		String CREATE_VENDOR_TABLE = "CREATE TABLE vendors ( "
				+ "vid INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT, " 
				+ "password TEXT, " 
				+ "longitude DOUBLE, "
				+ "latitude DOUBLE, "
				+ "email  TEXT, "
				+ "Phone TEXT)";

		String CREATE_CATEGORY_TABLE = "CREATE TABLE categories ( "
				+ "categoryid INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT )";

		String CREATE_PRODUCT_TABLE = "CREATE TABLE products ( "
				+ "barcode INTEGER PRIMARY KEY , " + "name TEXT, "
				+ "categoryid INTEGER, FOREIGN KEY (categoryid) REFERENCES categories (categoryid) )";



		String CREATE_VENDOR_HAS_PRODUCT_TABLE = "CREATE TABLE  vendorhasproduct ( "
				+ "hasid INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "barcode integer, " 
				+ "vid integer, "
				+ "price DOUBLE, "
				+ "FOREIGN KEY (barcode) REFERENCES products (exid), "
				+ "FOREIGN KEY (vid) REFERENCES vendors (vid) )";


		// create  tables
		db.execSQL(CREATE_VENDOR_TABLE);
		db.execSQL(CREATE_CATEGORY_TABLE);
		db.execSQL(CREATE_PRODUCT_TABLE);
		db.execSQL(CREATE_VENDOR_HAS_PRODUCT_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
		db.execSQL("DROP TABLE IF EXISTS vendors");
		db.execSQL("DROP TABLE IF EXISTS categories");
		db.execSQL("DROP TABLE IF EXISTS products");
		db.execSQL("DROP TABLE IF EXISTS vendorhasproduct");

		Log.d("onUpgrde", "delete all records in tbles");

		// create fresh books table
		this.onCreate(db);
	}

	// ---------------------------------------------------------------------

	/**
	 * CRUD operations (create "add", read "get", update, delete) book + get all
	 * books + delete all books
	 */


	public void addProduct(Product product) {
		 ProductTable.add(this.getWritableDatabase(), product);
	}

	public Product getProduct(int id) {
		return ProductTable.get(this.getReadableDatabase(), id);
	}

	// Get All Books
	public List<Product> getAllProducts() {
		return ProductTable.getAll(this.getReadableDatabase());
	}

	// Deleting single book
	public void deleteProduct(Product product) {
		ProductTable.delete(this.getWritableDatabase(), product);

	}

	/******************************
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * 
	 ************************************/

	public Vendor addVendor(Vendor vendor) {
		return VendorTable.add(this.getWritableDatabase(), vendor);
	}

	public Vendor getVendor(int id) {
		return VendorTable.get(this.getReadableDatabase(), id);
	}	
	public Vendor getVendor(String userName) {
		return VendorTable.get(this.getReadableDatabase(), userName);
	}


	// Get All Books
	public List<Vendor> getAllIcomes() {
		return VendorTable.getAll(this.getReadableDatabase());
	}

	// Deleting single book
	public void deleteVendor(Vendor vendor) {
		VendorTable.delete(this.getWritableDatabase(), vendor);

	}

	/******************************
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * 
	 ************************************/
	public Category addCategory(Category category) {
		return CategoryTable.add(this.getWritableDatabase(), category);
	}

	public Category getCategory(int id) {
		return CategoryTable.get(this.getWritableDatabase(), id);
	}

	// Get All Books
	public LinkedList<Category> getAllCategories() {
		return CategoryTable.getAll(this.getReadableDatabase());
	}

	// Updating single book
//	public int updateCategory(Category category) {
//		return CategoryTable.update(this.getWritableDatabase(), category);
//	}
//
	// Deleting single book
	public void deleteCategory(Category category) {
		CategoryTable.delete(this.getWritableDatabase(), category);
	}

	/******************************
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * ---------------------------------------------------------------------
	 * 
	 ************************************/
	public void addVendorHasProduct(Vendor vendor,Product product ,double price) {
		 VendorHasProductTable.add(this.getWritableDatabase(), vendor,product,price);
	}
	public void addVendorHasProduct(VendorHasProduct vendorHasProduct) {
		 VendorHasProductTable.add(this.getWritableDatabase(), vendorHasProduct);
	}

	// Get All Books
	public LinkedList<VendorHasProduct> getAllVendorsAndProducts() {
		return VendorHasProductTable.getAll(this.getReadableDatabase());
	}
	public LinkedList<VendorHasProduct> getAllVendorsAndProducts(Product product) {
		return VendorHasProductTable.getAll(this.getReadableDatabase(),product);
	}


	// Updating single book
	public int updateVendorProduct(VendorHasProduct vendorHasProduct) {
		return VendorHasProductTable.update(this.getWritableDatabase(), vendorHasProduct);
	}

	// Deleting single book
	public void deleteVendorHasProduct(VendorHasProduct vendorHasProduct) {
		VendorHasProductTable.delete(this.getWritableDatabase(), vendorHasProduct);
	}


}

