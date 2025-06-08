package com.mobile.bce7.PersonAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.bce7.R
import com.mobile.bce7.model.products

class ProductAdapter(private val productList: List<products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.productImage)
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val rating: TextView = itemView.findViewById(R.id.productRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.title.text = product.title
        holder.price.text = "$${product.price}"
        holder.rating.text = "⭐ ${product.rating.rate} (${product.rating.count})"

        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.img)
    }
}