package com.example.myapplication.Adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Class.Product
import com.example.myapplication.R
import com.example.myapplication.sqlite.DatabaseHelper

class ProductAdapter(
    private val productList: MutableList<Product>,
    private val dbHelper: DatabaseHelper
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productDescription.text = product.description
        holder.productPrice.text = String.format("%.2f", product.price)
        holder.productImage.setImageResource(product.imageResource)

        holder.imageDelete.setOnClickListener {
            showConfirmationDialog(holder.itemView, product, position)
        }
    }

    private fun showConfirmationDialog(view: View, product: Product, position: Int) {
        val context = view.context
        AlertDialog.Builder(context)
            .setTitle("Xóa sản phẩm")
            .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?")
            .setPositiveButton("Có") { _, _ ->
                // Delete product from the database
                dbHelper.deleteProduct(product.id)
                // Remove product from the list
                productList.removeAt(position)
                // Notify adapter about the removal
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, productList.size)
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val imageDelete: ImageView = itemView.findViewById(R.id.imagedelete)
        val imageDetails: ImageView = itemView.findViewById(R.id.imgdetails)
    }
}
