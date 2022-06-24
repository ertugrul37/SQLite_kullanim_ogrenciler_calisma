package com.pre.sqlite_ogrenciler_calisma;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //veri tabanı tanımlama
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //VERİ TABANI OLUŞTURMA
            database = this.openOrCreateDatabase("Okul", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS Ogrenciler (ad VARCHAR,soyad VARCHAR,ogrenci_no INT)");

        } catch (Exception exception) {

            exception.printStackTrace();

        }

    }

    public void sqliteBtnClick(View v) {
        switch (v.getId()) {
            case R.id.btnEkle:
                try {
                    database.execSQL("INSERT INTO ogrenciler(ad,soyad,ogrenci_no)VALUES('Elif','Keten',110)");
                    getData();

                    Toast.makeText(getApplicationContext(), "Kayıt başarıyla eklendi", Toast.LENGTH_SHORT).show();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;

            case R.id.btnGuncelle:
                try {
                    database.execSQL("UPDATE Ogrenciler SET ogrenci_no = 999 Where ad = 'Elif' AND soyad = 'Keten'");
                    getData();

                    Toast.makeText(getApplicationContext(), "Kayıt başarıyla güncellendi ", Toast.LENGTH_SHORT).show();

                } catch (Exception exception) {
                    exception.printStackTrace();

                }
                break;

            case R.id.btnSil:
                try {
                    database.execSQL("DELETE FROM Ogrenciler WHERE soyad = 'Gündüz'");
                    getData();

                    Toast.makeText(getApplicationContext(), "Kayıt başarıyla silindi ", Toast.LENGTH_SHORT).show();

                } catch (Exception exception) {
                    exception.printStackTrace();

                }
                break;

            case R.id.btnTabloyuSil:
                try {
                    database.execSQL("DROP TABLE Ogrenciler");
                    getData();

                    Toast.makeText(getApplicationContext(), "Tablo başarıyla silindi ", Toast.LENGTH_SHORT).show();


                }catch (Exception exception){
                    exception.printStackTrace();
                }
                break;
        }
    }

    private void getData() {
        Cursor cursor = database.rawQuery("SELECT * FROM Ogrenciler", null);

        int adIndex = cursor.getColumnIndex("ad");
        int soyadIndex = cursor.getColumnIndex("soyad");
        int ogrencinoIndex = cursor.getColumnIndex("ogrenci_no");

        while (cursor.moveToNext())

            System.out.println("Ad = " + cursor.getString(adIndex) + " Soyad = " + cursor.getString(soyadIndex) + " No =" + cursor.getInt(ogrencinoIndex));
            System.out.println("--------------------------------------");
        cursor.close();
    }
}