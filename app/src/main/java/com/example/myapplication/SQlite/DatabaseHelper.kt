package com.example.myapplication.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.R
import com.example.myapplication.Class.Product

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1

        // Table names
        private const val TABLE_SANPHAM = "SANPHAM"
        private const val TABLE_HOADON = "HOADON"
        private const val TABLE_TAIKHOAN = "TAIKHOAN"
        private const val TABLE_HINHTHUCTHANHTOAN = "HINHTHUCTHANHTOAN"
        private const val TABLE_QUYEN = "QUYEN"
        private const val TABLE_CHITIETHOADON = "CHITIETHOADON"
        private const val TABLE_CHITIETTAIKHOAN = "CHITIETTAIKHOAN"

        // Create table statements
        private const val CREATE_TABLE_SANPHAM = """
            CREATE TABLE $TABLE_SANPHAM (
                MASANPHAM TEXT PRIMARY KEY, 
                TENSANPHAM TEXT, 
                HINHANH TEXT, 
                MOTA TEXT, 
                GIA REAL, 
                TRANGTHAI INTEGER, 
                SOLUONG INTEGER
            )
        """

        private const val CREATE_TABLE_HOADON = """
            CREATE TABLE $TABLE_HOADON (
                MAHOADON TEXT PRIMARY KEY, 
                NGAYLAP TEXT, 
                TONGTIEN REAL, 
                TRANGTHAI INTEGER, 
                DIACHI TEXT, 
                SDT TEXT, 
                MATAIKHOAN TEXT, 
                MAHINHTHUC TEXT, 
                FOREIGN KEY(MATAIKHOAN) REFERENCES TAIKHOAN(MATAIKHOAN), 
                FOREIGN KEY(MAHINHTHUC) REFERENCES HINHTHUCTHANHTOAN(MAHINHTHUC)
            )
        """

        private const val CREATE_TABLE_TAIKHOAN = """
            CREATE TABLE $TABLE_TAIKHOAN (
                MATAIKHOAN TEXT PRIMARY KEY, 
                TENNGUOIDUNG TEXT, 
                EMAIL TEXT, 
                MATKHAU TEXT
            )
        """

        private const val CREATE_TABLE_HINHTHUCTHANHTOAN = """
            CREATE TABLE $TABLE_HINHTHUCTHANHTOAN (
                MAHINHTHUC TEXT PRIMARY KEY, 
                TENHINHTHUC TEXT
            )
        """

        private const val CREATE_TABLE_QUYEN = """
            CREATE TABLE $TABLE_QUYEN (
                MAQUYEN TEXT PRIMARY KEY, 
                TENQUYEN TEXT
            )
        """

        private const val CREATE_TABLE_CHITIETHOADON = """
            CREATE TABLE $TABLE_CHITIETHOADON (
                MASANPHAM TEXT, 
                MAHOADON TEXT, 
                SOLUONG INTEGER, 
                TONGTIENSP REAL, 
                PRIMARY KEY (MASANPHAM, MAHOADON), 
                FOREIGN KEY(MASANPHAM) REFERENCES SANPHAM(MASANPHAM), 
                FOREIGN KEY(MAHOADON) REFERENCES HOADON(MAHOADON)
            )
        """

        private const val CREATE_TABLE_CHITIETTAIKHOAN = """
            CREATE TABLE $TABLE_CHITIETTAIKHOAN (
                MATAIKHOAN TEXT, 
                MAQUYEN TEXT, 
                PRIMARY KEY (MATAIKHOAN, MAQUYEN), 
                FOREIGN KEY(MATAIKHOAN) REFERENCES TAIKHOAN(MATAIKHOAN), 
                FOREIGN KEY(MAQUYEN) REFERENCES QUYEN(MAQUYEN)
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_SANPHAM)
        db.execSQL(CREATE_TABLE_HOADON)
        db.execSQL(CREATE_TABLE_TAIKHOAN)
        db.execSQL(CREATE_TABLE_HINHTHUCTHANHTOAN)
        db.execSQL(CREATE_TABLE_QUYEN)
        db.execSQL(CREATE_TABLE_CHITIETHOADON)
        db.execSQL(CREATE_TABLE_CHITIETTAIKHOAN)

        // Optionally insert sample data
        // insertSampleData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SANPHAM")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HOADON")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TAIKHOAN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HINHTHUCTHANHTOAN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUYEN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CHITIETHOADON")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CHITIETTAIKHOAN")
        onCreate(db)
    }

    // Method to fetch all products
    // Method to fetch all products
    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_SANPHAM", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndexOrThrow("MASANPHAM"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("TENSANPHAM"))
                val description = cursor.getString(cursor.getColumnIndexOrThrow("MOTA"))
                val imageResource = R.drawable.product // Replace with actual image resource if needed
                val price = cursor.getDouble(cursor.getColumnIndexOrThrow("GIA"))

                productList.add(Product(id, name, description, imageResource, price))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productList
    }

    // Method to count records in a table
    fun getCount(tableName: String): Int {
        val db = readableDatabase
        val countQuery = "SELECT COUNT(*) FROM $tableName"
        val cursor = db.rawQuery(countQuery, null)
        var count = 0
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }
        cursor.close()
        return count
    }

    // Method to add a product
    fun addProduct(product: Product) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("MASANPHAM", product.id)
            put("TENSANPHAM", product.name)
            put("MOTA", product.description)
            put("GIA", product.price)
        }
        db.insert(TABLE_SANPHAM, null, values)
    }
    fun deleteProduct(productId: String) {
        val db = writableDatabase
        db.delete(TABLE_SANPHAM, "MASANPHAM = ?", arrayOf(productId))
        db.close()
    }

}
