package com.Zeft.zeftproject.ListView;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.Zeft.zeftproject.R;
import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.Product;
import com.example.bdf.data.Vendor;
import com.example.bdf.data.VendorHasProduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class SearchCategoryProductAdapter extends BaseAdapter implements ListAdapter{
		private final Context context;
		private List<VendorHasProduct> vendorPeoducts;
		private SQLiteHelper db;

//		public public ProductAdapter(Context context) {
//			this.context = context;
//			this.db = SQLiteHelper.getInstance(context);
//		}

		public SearchCategoryProductAdapter(Context context, List<VendorHasProduct> vendorProducts) {
			this.context = context;
			this.vendorPeoducts = vendorProducts;
			this.db = SQLiteHelper.getInstance(context);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View rowView;
//			if (convertView == null) {

				rowView = LayoutInflater.from(context).inflate(
						R.layout.product_component, null);
					VendorHasProduct vendorHasProduct=	vendorPeoducts.get(position);
					Product product =db.getProduct(vendorHasProduct.getProductBarcode());
					Vendor vendor =db.getVendor(vendorHasProduct.getVendorId());	
					
					TextView productName = (TextView) rowView.findViewById(R.id.tvProductName);
					productName.setText(vendor.getName());
					TextView barcode = (TextView) rowView
							.findViewById(R.id.tvBarcode);
					TextView etendor = (TextView) rowView
							.findViewById(R.id.tvFollowersNO);
					etendor.setText("Vendor");
					barcode.setText(product.getBarcode()+"");
					TextView category = (TextView) rowView
							.findViewById(R.id.tvCategory);
					category.setText(vendor.getName());
					TextView price = (TextView) rowView
							.findViewById(R.id.tvPrice);
					price.setText(vendorHasProduct.getPrice() + "");
			return rowView;
		}

		@Override
		public int getCount() {
			return vendorPeoducts.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	}
