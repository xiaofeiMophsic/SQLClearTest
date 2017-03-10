package com.example.paozi.sqlcleartest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mContainer;
    private Button mBtnRead, mBtnWrite;
    private SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mContainer = (TextView) findViewById(R.id.container);
        mBtnRead = (Button) findViewById(R.id.btn_read);
        mBtnWrite = (Button) findViewById(R.id.btn_write);

        mDatabase = DBHelper.getInstance(getApplicationContext()).getWritableDatabase();

        initEvent();
    }

    private void initEvent() {

        mBtnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues datas = new ContentValues();
                datas.put("desc", "生成新数据" + new Random().nextInt(100));
                mDatabase.insert("test", null, datas);
            }
        });

        mBtnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try(Cursor c = mDatabase.query("test", new String[]{"id", "desc"}, null, null, null, null, null)) {
                    StringBuilder sb = new StringBuilder();
                    while (c.moveToNext()) {
                        int id = c.getInt(c.getColumnIndex("id"));
                        String desc = c.getString(c.getColumnIndex("desc"));

                        sb.append(id)
                                .append(": ")
                                .append(desc)
                                .append("\n");
                    }
                    mContainer.setText(sb.toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
