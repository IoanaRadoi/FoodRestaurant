package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Masa;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Produs;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.ProdusComCurenta;

/**
 * Created by Ioana.Radoi on 5/4/2015.
 */

public class ProduseDB extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "comanda.db";

    private static final String TABLE_NAME = "produs";
    private static final String TABLE_NAME2 = "produs_comanda";
    private static final String TABLE_NAME3 = "masa";

    private static final String ID = "id";
    private static final String NUME = "nume";
    private static final String PRET = "pret";
    private static final String CONTINUT = "continut";
    private static final String PICTURE = "picture";

    private static final String COD_MASA = "cod_masa";

    public ProduseDB(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER primary key," +
                NUME + " TEXT," +
                PRET + " REAL," +
                CONTINUT + " CONTINUT," +
                PICTURE + " PICTURE)";

        String createSQL2 = "CREATE TABLE " + TABLE_NAME2 + " (" +
                ID + " INTEGER primary key autoincrement," +
                NUME + " TEXT," +
                PRET + " REAL)";

        String createSQL3 = "CREATE TABLE " + TABLE_NAME3 + " (" +
                ID + " INTEGER primary key," +
                COD_MASA + " TEXT)";

        db.execSQL(createSQL);
        db.execSQL(createSQL2);
        db.execSQL(createSQL3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropSQL = "DROP TABLE " + TABLE_NAME;
        db.execSQL(dropSQL);
        String dropSQL2 = "DROP TABLE " + TABLE_NAME2;
        db.execSQL(dropSQL2);
        String dropSQL3 = "DROP TABLE " + TABLE_NAME3;
        db.execSQL(dropSQL3);
        this.onCreate(db);
    }

    public void adaugaMasa(Masa m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID, m.getId());
        cv.put(COD_MASA, m.getCod_masa());

        db.insert(TABLE_NAME3, null, cv);
        db.close();
    }

    public void adaugaProdus(Produs p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID, p.getId());
        cv.put(NUME, p.getNume());
        cv.put(PRET, p.getPret());
        cv.put(CONTINUT, p.getContinut());
        cv.put(PICTURE, p.getPicture());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public void adaugaProdusComCur(ProdusComCurenta p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(NUME, p.getName());
        cv.put(PRET, p.getPret());


        db.insert(TABLE_NAME2, null, cv);
        db.close();
    }

    public ArrayList<Masa> getMese() {
        ArrayList<Masa> mese = new ArrayList<Masa>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME3, null);

        if (c != null) {
            if (c.moveToFirst()) {
                Masa m = null;
                do {
                    m = new Masa(c.getInt(0), c.getString(1));
                    mese.add(m);
                } while (c.moveToNext());
            }
        }

        return mese;
    }


    public ArrayList<Produs> getProduse() {
        ArrayList<Produs> produse = new ArrayList<Produs>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (c != null) {
            if (c.moveToFirst()) {
                Produs p = null;
                do {
                    p = new Produs(c.getInt(0), c.getString(1), c.getDouble(2), c.getString(4), c.getString(3));
                    produse.add(p);
                } while (c.moveToNext());
            }
        }

        return produse;
    }

    public ArrayList<ProdusComCurenta> getProduseComCur() {
        ArrayList<ProdusComCurenta> produse = new ArrayList<ProdusComCurenta>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);

        if (c != null) {
            if (c.moveToFirst()) {
                ProdusComCurenta p = null;
                do {
                    p = new ProdusComCurenta(c.getInt(0), c.getString(1), c.getDouble(2));
                    produse.add(p);
                } while (c.moveToNext());
            }
        }

        return produse;
    }

    public void stergeProdusComCurenta(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, "id = ?", new String[]{id + ""});
        db.close();
    }

    public void stergeProduse() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }

    public void stergeProduseComCur() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME2);
        db.close();
    }


    public void stergeMese() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME3);
        db.close();
    }

    public int getProdId(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM produs WHERE nume = ?", new String[]{name});
        c.moveToFirst();
        return c.getInt(0);
    }


}
