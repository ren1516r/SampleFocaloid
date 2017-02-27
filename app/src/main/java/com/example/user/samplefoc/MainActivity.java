package com.example.user.samplefoc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.samplefoc.Adapter.productAdapter;
import com.example.user.samplefoc.Interface.productAPI;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
Button btn_login,btn_signup,btn_create;
    TextView user,pass;
    String username, password,query;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);















       // setContentView(R.layout.activity_user_navigation);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_create =(Button)findViewById(R.id.btn_create);
        btn_signup = (Button)findViewById(R.id.btn_signup);

        user = (TextView)findViewById(R.id.useq);
        pass=(TextView)findViewById(R.id.password);

        /*db = SQLiteDatabase.openDatabase("data/data/com.example.user.sqsample/databases/Focsample",
                null, SQLiteDatabase.OPEN_READWRITE);*/

        db=this.openOrCreateDatabase("Focsample", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS registration(id integer NOT NULL primary key AUTOINCREMENT,username text,password text)");

        //Button Create
      btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdetails();
                if(username.length()==0||password.length()==0)
                    Toast.makeText(getApplicationContext(), "Please fill username/Pass", Toast.LENGTH_LONG).show();
                else {

                    query = "SELECT * from registration where username='" + username + "'";

                    Cursor c =db.rawQuery(query,null);
                    if(c!=null) {
                        if (c.moveToFirst()) {

                                String UserName=c.getString(c.getColumnIndex("username"));
                            //    Log.e("username", UserName);
                                String Password=c.getString(c.getColumnIndex("password"));
                              //  Log.e("password", Password);

if(Password.equals(password)) {
    //Intent i = new Intent(MainActivity.this, userpanel.class);
    Intent i = new Intent(MainActivity.this,userNavigation.class);
    Bundle b=new Bundle();
    b.putString("UserName",UserName);
    i.putExtras(b);
    startActivity(i);

}
else {
    Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_LONG).show();

}

                        }
                    }

                }

            }
        });

        //Button SignUP
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });

    }


    private void getdetails() {
        username=user.getText().toString();
        password =pass.getText().toString();

    }
}
