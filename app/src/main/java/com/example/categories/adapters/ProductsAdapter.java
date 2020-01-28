package com.example.categories.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.categories.R;
import com.example.categories.api.models.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private Context mContext;

    private List<Product> mProductsList;

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mProductImage;
        TextView mProductName;
        TextView mProductPrice;
        TextView mProductType;

        private MyViewHolder(View view) {
            super(view);

            mProductImage = view.findViewById(R.id.productImage);
            mProductName = view.findViewById(R.id.productName);
            mProductPrice = view.findViewById(R.id.price);
            mProductType = view.findViewById(R.id.productType);
        }

        public void bind(Product product) {

            // Setting product image with Glide library
            Glide.with(mContext).load(product.getImageURL())
                    .into(mProductImage);

            mProductName.setText(product.getName());
            mProductPrice.setText(product.getPrice() + "");
            mProductType.setText(product.getType());
        }
    }

    public ProductsAdapter(Context context, List<Product> productsList) {
        mContext = context;
        mProductsList = productsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.product_list_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Product product = mProductsList.get(i);
        myViewHolder.bind(product);
    }

    @Override
    public int getItemCount() {
        return mProductsList == null ? 0 : mProductsList.size();
    }
}
