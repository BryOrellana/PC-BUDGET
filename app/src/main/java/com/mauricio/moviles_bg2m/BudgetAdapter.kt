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
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.budget_list.view.*
import kotlinx.android.synthetic.main.product_list.view.*

class BudgetAdapter(private val budgets: MutableList<UploadBudget>) : RecyclerView.Adapter<BudgetAdapter.ViewHolder>() {

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.budget_list, parent, false)
        context = view.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = budgets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val budget = budgets[position]


        if(budget.bname == ""){
            Picasso.with(context).load(R.drawable.ic_processor).placeholder(R.drawable.ic_processor).into(holder.bImg1)
            Picasso.with(context).load(R.drawable.ic_cpu).placeholder(R.drawable.ic_cpu).into(holder.bImg2)
            Picasso.with(context).load(R.drawable.ic_ram).placeholder(R.drawable.ic_ram).into(holder.bImg3)
            Picasso.with(context).load(R.drawable.ic_motherboard).placeholder(R.drawable.ic_motherboard).into(holder.bImg4)
            holder.bName.text = "Nombre de presupuesto"
            holder.bPrice.text = "0.0"
            holder.bdesc.text = "Agrega una pequeña descripción para tu presupuesto!"

        }
        else{
            if(budget.pimage == ""){
                Picasso.with(context).load(R.drawable.ic_processor).placeholder(R.drawable.ic_processor).into(holder.bImg1)
            }else{
                Picasso.with(context).load(budget.pimage).into(holder.bImg1)
            }
            if(budget.tgImage == ""){
                Picasso.with(context).load(R.drawable.ic_cpu).placeholder(R.drawable.ic_cpu).into(holder.bImg2)
            }else{
                Picasso.with(context).load(budget.tgImage).into(holder.bImg2)
            }
            if(budget.rimage == ""){
                Picasso.with(context).load(R.drawable.ic_ram).placeholder(R.drawable.ic_ram).into(holder.bImg3)
            }else{
                Picasso.with(context).load(budget.rimage).into(holder.bImg3)
            }
            if(budget.tmImage == ""){
                Picasso.with(context).load(R.drawable.ic_motherboard).placeholder(R.drawable.ic_motherboard).into(holder.bImg4)
            }else{
                Picasso.with(context).load(budget.tmImage).into(holder.bImg4)
            }

            holder.bName.text = budget.bname

            if(budget.totalP == "null"){
                holder.bPrice.text = "0.0"
            }else{
                holder.bPrice.text = budget.totalP
            }

            holder.bdesc.text = budget.budgetDesc
        }
        /*holder.itemView.setOnClickListener { view: View ->
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
        }*/
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val pImage: ImageView = itemView.image_view_product
        val bName: TextView = itemView.tituloPresupuesto1
        val bPrice: TextView = itemView.totalPrice
        val bdesc: TextView = itemView.descPresupuesto1

        val bImg1: CircleImageView = itemView.img1
        val bImg2: CircleImageView = itemView.img2
        val bImg3: CircleImageView = itemView.img3
        val bImg4: CircleImageView = itemView.img4
    }


}