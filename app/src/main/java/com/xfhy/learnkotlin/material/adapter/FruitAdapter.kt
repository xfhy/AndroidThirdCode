package com.xfhy.learnkotlin.material.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xfhy.learnkotlin.R
import com.xfhy.learnkotlin.material.CollapsingToolbarLayoutActivity

/**
 * @author : xfhy
 * Create time : 2020-04-18 16:38
 * Description :
 */
class FruitAdapter(val context: Context, val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.ivFruitImage)
        val tvFruitName: TextView = view.findViewById(R.id.tvFruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_fruit, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context, CollapsingToolbarLayoutActivity::class.java).apply {
                putExtra(CollapsingToolbarLayoutActivity.FRUIT_NAME, fruit.name)
                putExtra(CollapsingToolbarLayoutActivity.FRUIT_IMAGE_ID, fruit.res)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount(): Int = fruitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        Glide.with(context).load(fruit.res).into(holder.fruitImage)
        holder.tvFruitName.text = fruit.name
    }
}

data class Fruit(val name: String, val res: Int)