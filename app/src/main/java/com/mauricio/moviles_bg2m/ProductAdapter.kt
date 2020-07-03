package com.mauricio.moviles_bg2m

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yinglan.shadowimageview.RoundImageView
import kotlinx.android.synthetic.main.product_list.view.*

class ProductAdapter(private val products: MutableList<Products>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list,parent,false)
        context = view.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        Picasso.with(context).load(product.imageUrl).into(holder.pImage)
        holder.pName.text = product.nameProduct
        holder.pRank.text = product.productRank
        holder.pPrice.text = product.productPrice
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val pImage : ImageView = itemView.image_view_product
        val pName :TextView = itemView.name_product
        val pRank :TextView = itemView.text_view_product_nota
        val pPrice :TextView = itemView.text_view_product_precio
    }

}