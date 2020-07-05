package com.mauricio.moviles_bg2m

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list.view.*

class ProductAdapter(
    private val products: MutableList<Products>,
    private val fReference: String) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var args: Bundle


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
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


        holder.itemView.setOnClickListener { view: View ->
            when {
                fReference == "laptops" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController().navigate(
                        R.id.action_categories_laptops_to_descriptionProductFragment,
                        args
                    )
                }

                fReference == "processor" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController()
                        .navigate(
                            R.id.action_categories_Procesadores_to_descriptionProductFragment,
                            args
                        )
                }
                fReference == "storage" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController()
                        .navigate(
                            R.id.action_categories_almacenamiento_to_descriptionProductFragment,
                            args
                        )
                }
                fReference == "memories" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController()
                        .navigate(
                            R.id.action_categories_Memorias_to_descriptionProductFragment,
                            args
                        )
                }
                fReference == "powersource" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController()
                        .navigate(
                            R.id.action_categories_Fuentes_Poder_to_descriptionProductFragment,
                            args
                        )
                }
                fReference == "case" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController()
                        .navigate(
                            R.id.action_categories_Gabinetes_to_descriptionProductFragment,
                            args
                        )
                }
                fReference == "motherboard" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController()
                        .navigate(
                            R.id.action_categories_Tarj_Madre_to_descriptionProductFragment,
                            args
                        )
                }
                fReference == "videocard" -> {
                    args = bundleOf("product" to products.get(position).code)
                    view.findNavController()
                        .navigate(
                            R.id.action_categories_Tarj_video_to_descriptionProductFragment,
                            args
                        )
                }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pImage: ImageView = itemView.image_view_product
        val pName: TextView = itemView.name_product
        val pRank: TextView = itemView.text_view_product_nota
        val pPrice: TextView = itemView.text_view_product_precio
    }

}