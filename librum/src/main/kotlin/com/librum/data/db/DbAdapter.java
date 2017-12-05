package com.librum.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {

  private static final String TAG = "DBAdapter";
  public static SQLiteDatabase mDatabase;
  private Context mContext;

  public DbAdapter(Context ctx) {
    this.mContext = ctx;
    mDatabase = FolioDatabaseHelper.getInstance(mContext).getMyWritableDatabase();
  }

  public static boolean insert(String table, ContentValues contentValues) {

    return mDatabase.insert(table, null, contentValues) > 0;
  }

  public static boolean update(String table, String key, String value,
      ContentValues contentValues) {

    return mDatabase.update(table, contentValues, key + "=?", new String[]{value}) > 0;
  }

  public static Cursor getAllByKey(String table, String[] projection, String key, String value)
      throws SQLException {
    return mDatabase.query(table, projection,
        key + "=?", new String[]{value}, null, null, null, null);
  }

  public static boolean deleteById(String table, String key, String value) {
    return mDatabase.delete(table, key + "=?", new String[]{value}) > 0;
  }

  public boolean deleteAll(String table) {
    return mDatabase.delete(table, null, null) > 0;
  }

  public boolean deleteAll(String table, String whereClause, String[] whereArgs) {
    return mDatabase.delete(table, whereClause + "=?", whereArgs) > 0;
  }

  public Cursor getAll(String table, String[] projection, String selection,
      String[] selectionArgs, String orderBy) {
    return mDatabase.query(table, projection, selection, selectionArgs, null, null, orderBy);
  }

  public Cursor getAll(String table) {
    return getAll(table, null, null, null, null);
  }

  public Cursor get(String table, long id, String[] projection, String key) throws SQLException {
    return mDatabase.query(table, projection,
        key + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
  }

  public Cursor get(String table, long id) throws SQLException {
    return get(table, id, null, FolioDatabaseHelper.KEY_ID);
  }

  public Cursor getMaxId(String tableName, String key) {
    return mDatabase.rawQuery("SELECT MAX(" + key + ") FROM " + tableName, null);
  }

  public void deleteDatabase() {
    /*	mDatabase.execSQL(LeadTypesTable.SQL_DROP);
        mDatabase.execSQL(LeadStagesTable.SQL_DROP);
		mDatabase.execSQL(LeadSourcesTable.SQL_DROP);
		mDatabase.execSQL(ExpensesTable.SQL_DROP);*/
    mDatabase.execSQL(HighLightTable.SQL_DROP);
    /*mDatabase.execSQL(EmailAddressesTable.SQL_DROP);
		mDatabase.execSQL(PhoneNumbersTable.SQL_DROP);
		//mDatabase.execSQL(SMSLeadTable.SQL_DROP);
		mDatabase.execSQL(SMSPatternsTable.SQL_DROP);*/
  }

  public void createDtabase() {
		/*mDatabase.execSQL(LeadTypesTable.SQL_CREATE);
		mDatabase.execSQL(LeadStagesTable.SQL_CREATE);
		mDatabase.execSQL(LeadSourcesTable.SQL_CREATE);
		mDatabase.execSQL(ExpensesTable.SQL_CREATE);*/
    mDatabase.execSQL(HighLightTable.SQL_CREATE);
		/*mDatabase.execSQL(EmailAddressesTable.SQL_CREATE);
		mDatabase.execSQL(PhoneNumbersTable.SQL_CREATE);
		mDatabase.execSQL(SMSLeadTable.SQL_CREATE);
		mDatabase.execSQL(SMSPatternsTable.SQL_CREATE);*/
  }
}
