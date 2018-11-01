package com.bjh.www.a1101_awesqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyDBOpenHelper dbHelper;
    SQLiteDatabase mdb;

    EditText editTextCountry, editTextCity;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDBOpenHelper(this, "awe.db", null, 1);
        mdb = dbHelper.getWritableDatabase();

        Button buttonInsert = (Button)findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(this);
        Button buttonSelect = (Button)findViewById(R.id.buttonSelect);
        buttonSelect.setOnClickListener(this);
        Button buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);
        Button buttonDelete = (Button)findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);
    }

    public void onClick(View v) {
        editTextCountry = (EditText)findViewById(R.id.editTextCountry);
        String country = editTextCountry.getText().toString();
        editTextCity = (EditText)findViewById(R.id.editTextCity);
        String city = editTextCity.getText().toString();

        textView = (TextView)findViewById(R.id.textViewResult);

        int id = v.getId();

        String selectSQL = "SELECT * FROM awe_country;";
        String str = "";
        Cursor cursor;

        switch (id) {

            case R.id.buttonInsert:
                String insertSQL = "INSERT INTO awe_country VALUES(null, '" + country + "', '" + city + "');";
                mdb.execSQL(insertSQL);
                break;

            case R.id.buttonSelect:
                cursor = mdb.rawQuery(selectSQL, null);

                while(cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    String readcountry = cursor.getString(cursor.getColumnIndex("country"));
                    String readcity = cursor.getString(2);
                    str += (id + ":" + readcountry + "-" + readcity + "\n");
                }
                textView.setText(str);
                break;

            case R.id.buttonUpdate: // ID로 할 사람들은 ID가져오기, delete, select    // Country로 하기
                String updateSQL = "UPDATE awe_country SET city='" + city + "' WHERE country='" + country + "';";
                mdb.execSQL(updateSQL);

                cursor = mdb.rawQuery(selectSQL, null);
                while(cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    String readcountry = cursor.getString(cursor.getColumnIndex("country"));
                    String readcity = cursor.getString(2);
                    str += (id + ":" + readcountry + "-" + readcity + "\n");
                }
                textView.setText(str);
                break;

            case R.id.buttonDelete:
                String deleteSQL = "DELETE FROM awe_country WHERE country='" + country + "';";
                mdb.execSQL(deleteSQL);

                cursor = mdb.rawQuery(selectSQL, null);
                while(cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    String readcountry = cursor.getString(cursor.getColumnIndex("country"));
                    String readcity = cursor.getString(2);
                    str += (id + ":" + readcountry + "-" + readcity + "\n");
                }
                textView.setText(str);
                break;

            default:
                break;
        }
    }
}
