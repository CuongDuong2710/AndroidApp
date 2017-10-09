package organization.cuong.orderfoods.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import organization.cuong.orderfoods.Model.Order;

/**
 * Created by QUOC CUONG on 09/10/2017.
 * Accessing order detail database
 */
public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "orderfoods.db";
    private static final int DB_VER = 1;

    /**
     * Building constructor of Database
     * @param context
     */
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    /**
     * Querying to get all order details
     * @return
     */
    public List<Order> getCarts() {
        // Init some variables
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // select column and table
        String[] sqlSelect = {"ProductName", "ProductId", "Quantity", "Price", "Discount"};
        String table = "OrderDetail";

        // set table for QueryBuilder
        qb.setTables(table);

        // set cursor
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        // Init new array list
        final List<Order> result = new ArrayList<>();

        // moving cursor each row
        if (c.moveToFirst()) {
            do {
                // adding new Order to array list
                result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))
                ));
            } while(c.moveToNext()); // continue looping
        }
        // return array list with order details
        return result;
    }

    /**
     * Addding new order details
     * @param order
     */
    public void addToCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId, ProductName, Quantity, Price, Discount) VALUES ('%s', '%s', '%s', '%s', '%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }

    /**
     * Clearing all order details
     */
    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }
}
