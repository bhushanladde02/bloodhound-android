package com.smallemperor.bloodhound.db;
 
import java.util.ArrayList;
import java.util.List;

import com.smallemperor.bloodhound.pojo.Contact;
import com.smallemperor.bloodhound.pojo.Register;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "lostDatabase";
 
    private static final String TABLE_REGISTER = "LOST";   
    private static final String beaconeId ="BEACONID";
    private static final String fName  ="FNAME";
    private static final String lName ="LNAME";
    private static final String imgurl ="IMGURL";
    private static final String street ="STREET";
    private static final String city ="CITY";
    private static final String state ="STATE";
    private static final String zip ="ZIP";
    private static final String age ="AGE";
    private static final String height ="HEIGHT";
    private static final String weight ="WEIGHT";
    private static final String hcolor ="HCOLOR";
    private static final String ecolor ="ECOLOR";
    private static final String feature ="FEATURE";
    private static final String special ="SPECIAL";
    private static final String action ="ACTION";
    private static final String report ="REPORT";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_REGISTER + "("+beaconeId+" TEXT PRIMARY KEY,"+
    			fName+" TEXT,"+ lName +" TEXT,"+imgurl+" TEXT," +street +" TEXT,"+city+" TEXT,"+
    			state+" TEXT,"+zip+" TEXT,"+
                age+" TEXT,"+height+" TEXT,"+weight+" TEXT,"+hcolor+" TEXT,"+ ecolor+" TEXT,"+ 
    			feature+" TEXT,"+special+" TEXT,"+action+" TEXT,"+report+" TEXT)";
    	System.out.println("Final Table Creation Query ::::::::::::::::::::::"+CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    
    public long add(Register register) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("I am inside insertion of data");
        ContentValues values = new ContentValues();
        values.put(beaconeId ,register.getBeaconeId() );
        values.put(fName  ,register.getfName() );
        values.put(lName ,register.getlName() );
        values.put(imgurl ,register.getImgurl() );
        values.put(street ,register.getStreet() );
        values.put(city ,register.getCity());
        values.put(state ,register.getState() );
        values.put(zip ,register.getZip() );
        values.put(age ,register.getAge() );
        values.put(height,register.getHeight() );
        values.put(weight,register.getWeight() );
        values.put(hcolor ,register.getHcolor() );
        values.put(ecolor ,register.getEcolor() );
        values.put(feature ,register.getFeature() );
        values.put(special ,register.getSpecial() );
        values.put(action ,register.getAction() );
        values.put(report ,register.getReport() );
        // Inserting Row
       long l= db.insert(TABLE_REGISTER, null, values);
        db.close();
        return l;// Closing database connection
    }

   public Register get(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_REGISTER, new String[] {beaconeId,fName ,lName,imgurl,street,city,state,zip,age,height,weight,hcolor,ecolor,feature,special,action,report  }, beaconeId + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Register register = new Register(cursor.getString(0),
                cursor.getString(1), cursor.getString(2)
                , cursor.getString(3), cursor.getString(4)
                , cursor.getString(5), cursor.getString(6)
                , cursor.getString(7), cursor.getString(8)
                , cursor.getString(9), cursor.getString(10)
                , cursor.getString(11), cursor.getString(12)
                , cursor.getString(13), cursor.getString(14)
                , cursor.getString(15), cursor.getString(16));
        // return contact
        return register;
    }
   
 
    // Getting All Contacts
    public List<Register> getAll() {
        List<Register> registerList = new ArrayList<Register>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REGISTER;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Register register = new Register();
                register.setBeaconeId(cursor.getString(0));
                register.setfName(cursor.getString(1));
                register.setlName(cursor.getString(2));
                register.setImgurl(cursor.getString(3));
                register.setStreet(cursor.getString(4));
                register.setCity(cursor.getString(5));
                register.setState(cursor.getString(6));
                register.setZip(cursor.getString(7));
                register.setAge(cursor.getString(8));
                register.setHeight(cursor.getString(9));
                register.setWeight(cursor.getString(10));
                register.setHcolor(cursor.getString(11));
                register.setEcolor(cursor.getString(12));
                register.setFeature(cursor.getString(13));
                register.setSpecial(cursor.getString(14));
                register.setAction(cursor.getString(15));
                register.setReport(cursor.getString(16));
                // Adding contact to list
                registerList.add(register);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return registerList;
    }
 

    public int update(Register register) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(beaconeId ,register.getBeaconeId() );
        values.put(fName  ,register.getfName() );
        values.put(lName ,register.getlName() );
        values.put(imgurl ,register.getImgurl() );
        values.put(street ,register.getStreet() );
        values.put(city ,register.getCity());
        values.put(state ,register.getState() );
        values.put(zip ,register.getZip() );
        values.put(age ,register.getAge() );
        values.put(height,register.getHeight() );
        values.put(weight,register.getWeight() );
        values.put(hcolor ,register.getHcolor() );
        values.put(ecolor ,register.getEcolor() );
        values.put(feature ,register.getFeature() );
        values.put(special ,register.getSpecial() );
        values.put(action ,register.getAction() );
        values.put(report ,register.getReport() );
 
        // updating row
        return db.update(TABLE_REGISTER, values, beaconeId + " = ?",
                new String[] { String.valueOf(register.getBeaconeId()) });
    }
 
    public void delete(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REGISTER, beaconeId + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
 
    
    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_REGISTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     // return count
        int p= cursor.getCount();
        cursor.close();
        return p;
        
    }
}