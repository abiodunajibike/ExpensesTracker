package com.abiodunajibike.app.expensestracker.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.abiodunajibike.app.expensestracker.Expense;

/**
 * Created by AJ on 13/05/2016.
 */
public class DatabaseController extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "expensesTrackerDB";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    //DB Table Names
    public static final String TABLE_EXPENSES = "expenses";

    public static final String KEY_ID = "_id";

    public static final String KEY_DESCRIPTION = "description";

    public static final String KEY_AMOUNT = "amount";

    public static final String KEY_DATE = "date";

    private static final String CREATE_TABLE_EXPENSES = "CREATE_TABLE "
            + TABLE_EXPENSES + "(" + KEY_ID + " INTEGER PRIMARY AUTOINCREMENT,"
            + KEY_DESCRIPTION + " TEXT," + KEY_AMOUNT + " DOUBLE," + KEY_DATE + " TEXT" + ")";


    public DatabaseController(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_EXPENSES);
        Log.d("Calling", "onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("Calling", "onUpgrade()");
    }

    public long addExpense(Expense expense){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION,expense.getDescription());
        values.put(KEY_AMOUNT,expense.getAmount());
        values.put(KEY_DATE,expense.getDate());

        long expense_id  = db.insert(TABLE_EXPENSES, null, values);
        db.close();
        return expense_id;
    }
}
