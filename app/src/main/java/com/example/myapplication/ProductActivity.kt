package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.ProductAdapter
import com.example.myapplication.Class.Product
import com.example.myapplication.sqlite.DatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: MutableList<Product>
    private lateinit var dbHelper: DatabaseHelper

    private val ADD_PRODUCT_REQUEST_CODE = 1 // Request code for adding product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dbHelper = DatabaseHelper(this)
        productList = dbHelper.getAllProducts().toMutableList()

        productAdapter = ProductAdapter(productList, dbHelper)
        recyclerView.adapter = productAdapter

        val addProductButton: FloatingActionButton = findViewById(R.id.floatingActionButton)
        addProductButton.setOnClickListener {
            // Navigate to AddsanphamActivity
            val intent = Intent(this, AddsanphamActivity::class.java)
            startActivityForResult(intent, ADD_PRODUCT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PRODUCT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            refreshProductList()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshProductList()
    }

    private fun refreshProductList() {
        productList.clear()
        productList.addAll(dbHelper.getAllProducts())
        productAdapter.notifyDataSetChanged()
    }
}
