package com.bakmaps.cost_to_go.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by WORK with FUN on 20-01-2015.
 */
public class RailwayStationsDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "trainStationsDB.db";
    private static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_PID = "_pid";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_CATID = "category_id";
    public static final String COLUMN_SUBCATID = "subcategory_id";
    public static final String COLUMN_PDESCRIP = "prod_description";
    public static final String COLUMN_PRICE = "prod_price";     // individual prod
    public static final String COLUMN_PRODUCTAMT = "product_amt";    // all prods
    public static final String COLUMN_PRODIMGURL = "ProdImageUrl";
    Context context;


    public RailwayStationsDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("dbhandler***");
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("oncreate111***");
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_PRODUCTS + "(" + COLUMN_PID + " INTEGER PRIMARY KEY, "
                + COLUMN_PRODUCTNAME + " TEXT, "
                + COLUMN_QUANTITY + " INTEGER, "
                + COLUMN_CATID + " INTEGER, "
                + COLUMN_SUBCATID + " INTEGER, "
                + COLUMN_PDESCRIP + " TEXT, "
                + COLUMN_PRICE + " REAL, "
                + COLUMN_PRODUCTAMT + " REAL, "
                + COLUMN_PRODIMGURL + " TEXT" + ")";
        System.out.println(CREATE_PRODUCTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }


   /* public boolean addProduct(ArraysClass product,int quantity) {

        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("addprod***");

        if(findProduct(product.getProduct_name())== null) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PID, Integer.parseInt(product.getId()));
            values.put(COLUMN_PRODUCTNAME, product.getProduct_name());
            values.put(COLUMN_QUANTITY, quantity);
            values.put(COLUMN_CATID, Integer.parseInt(product.getCategory_id()));
            values.put(COLUMN_SUBCATID, Integer.parseInt(product.getSub_category_id()));
            values.put(COLUMN_PDESCRIP, product.getProdDesc());
            values.put(COLUMN_PRICE, Float.parseFloat(product.getProduct_price()));
            System.out.println("price from add : " + Float.parseFloat(product.getProduct_price()));
            values.put(COLUMN_PRODUCTAMT, (quantity * Float.parseFloat(product.getProduct_price())));
            values.put(COLUMN_PRODIMGURL, product.getProduct_imgurl());

            db.insert(TABLE_PRODUCTS, null, values);

            System.out.println("item added to cart 22222");
            return true;
        }
        else {
            System.out.println("Item exists");
            return false;
        }
    }
*/

  /*  public ArraysClass findProduct(String productName) {
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME+ " =  \"" + productName + "\"";
        System.out.println("find query : "+query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArraysClass product = new ArraysClass();

        System.out.println("findProduct");

        if(cursor.getCount()>0) {
            if (cursor.moveToFirst()) {
                System.out.println("find prod name  : " + cursor.getString(1));
                product.setId(cursor.getString(0));
                product.setProduct_name(cursor.getString(1));
                product.setCategory_id(cursor.getString(3));
                product.setSub_category_id(cursor.getString(4));
                product.setProdDesc(cursor.getString(5));
                product.setProduct_price(cursor.getString(6));
                System.out.println("price from find : " + Float.parseFloat(product.getProduct_price()));
                product.setProduct_imgurl(cursor.getString(8));

                //   product.setQuantity(Integer.parseInt(cursor.getString(2)));

            }
        } else {
            product = null;
        }
        cursor.close();

        return product;
    }
*/
  /*  public boolean deleteProduct(String productname) {

        boolean result = false;
        //   String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        //   Cursor cursor = db.rawQuery(query, null);
        ArraysClass product = new ArraysClass();
        product = findProduct(productname);

        System.out.println("del prod ******");

    *//*    if (cursor.moveToFirst()) {
            product.setProduct_name(cursor.getString(1));
            db.delete(TABLE_PRODUCTS, COLUMN_PID + " = ?", new String[] { String.valueOf(product.getId()) });
            cursor.close();
            result = true;
        }
    *//*  //  db.close();
        if(product!= null){
            db.delete(TABLE_PRODUCTS, COLUMN_PID + " = " + product.getId(), null );
            System.out.println("res : " + result);

            result = true;
        }

        return result;
    }
*/
  /*  public List<ArraysClass> getAllProducts() {
        List<ArraysClass> productList = new ArrayList<ArraysClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        System.out.println("getAllprod");
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ArraysClass prod = new ArraysClass();
                System.out.println("getAll prod name  : " + cursor.getString(1));
                prod.setId(cursor.getString(0));
                prod.setProduct_name(cursor.getString(1));
                prod.setCategory_id(cursor.getString(3));
                prod.setSub_category_id(cursor.getString(4));
                prod.setProdDesc(cursor.getString(5));
                prod.setProduct_price(cursor.getString(6));
                System.out.println("price from getA; : " + Float.parseFloat(prod.getProduct_price()));
                prod.setProduct_imgurl(cursor.getString(8));
                System.out.println("img db : " + prod.getProduct_imgurl());
                //   product.setQuantity(Integer.parseInt(cursor.getString(2)));

                // Adding contact to list
                productList.add(prod);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }*/
}
