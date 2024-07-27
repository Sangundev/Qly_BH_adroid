package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Class.Product
import com.example.myapplication.sqlite.DatabaseHelper

class AddsanphamActivity : AppCompatActivity() {
    private lateinit var edtProductId: EditText
    private lateinit var edtProductName: EditText
    private lateinit var edtProductDescription: EditText
    private lateinit var edtProductPrice: EditText
    private lateinit var btnAddProduct: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addsanpham)

        dbHelper = DatabaseHelper(this)

        edtProductId = findViewById(R.id.edtProductId)
        edtProductName = findViewById(R.id.edtProductName)
        edtProductDescription = findViewById(R.id.edtProductDescription)
        edtProductPrice = findViewById(R.id.edtProductPrice)
        btnAddProduct = findViewById(R.id.btnAddProduct)

        btnAddProduct.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {
        val id = edtProductId.text.toString().trim()
        val name = edtProductName.text.toString().trim()
        val description = edtProductDescription.text.toString().trim()
        val priceStr = edtProductPrice.text.toString().trim()

        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceStr.toDoubleOrNull() ?: run {
            Toast.makeText(this, "Giá sản phẩm không hợp lệ", Toast.LENGTH_SHORT).show()
            return
        }

        val newProduct = Product(id, name, description, R.drawable.product, price)
        dbHelper.addProduct(newProduct)

        Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show()

        // Set result and finish
        setResult(Activity.RESULT_OK)
        finish() // Quay trở lại trang trước đó sau khi thêm sản phẩm
    }
}
