package com.tutoriales.simplepicasso;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.tutoriales.simplepicasso.databinding.RowRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductVH> {
    private List<Product> productList;

    public ProductRecyclerAdapter() {
        productList=new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRecyclerBinding binding = RowRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ProductVH holder, int position) {
        holder.onBindViewHolder(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public class ProductVH extends RecyclerView.ViewHolder{
        private RowRecyclerBinding binding;

        public ProductVH(@NonNull RowRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void onBindViewHolder(Product product){
            binding.textViewRow.setText(product.getName());
            loadImageWithPiccaso(product.getUrl());
        }

        private void loadImageWithPiccaso(String urlImage){
            Picasso.get()
                    .load(urlImage)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.imageViewRow);
        }
    }
}
