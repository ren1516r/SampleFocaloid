package com.example.user.samplefoc;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {
Button btn_create;
    SQLiteDatabase db;
    TextView txt_username,txt_password,txt_repassword;
    String username,password,repassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        btn_create =(Button)findViewById(R.id.btn_create);
        txt_username =(TextView)findViewById(R.id.sg_username);
        txt_password =(TextView)findViewById(R.id.sg_password);
        txt_repassword =(TextView)findViewById(R.id.sg_repassword);

        db = SQLiteDatabase.openDatabase("data/data/com.example.user.samplefoc/databases/Focsample",
                null, SQLiteDatabase.OPEN_READWRITE);

      /*  db=this.openOrCreateDatabase("Focsample", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS registration(id integer NOT NULL primary key AUTOINCREMENT,username text,password text)");
*/

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdetails();


             /*   Log.e("USERNAME",username);
                Log.e("PASSWORD",password);
                Log.e("REPASSSSS",repassword);*/

                if(username.length()==0||password.length()==0) {

                    Toast.makeText(getApplicationContext(), "Please fill username/Pass", Toast.LENGTH_LONG).show();
                }
  else
                {
                   if( checkpasswword(password,repassword)){


                       Toast.makeText(getApplicationContext(),"Updating",Toast.LENGTH_LONG).show();
                       db.execSQL("INSERT INTO registration(username,password) values('"+username+"','"+password+"');");
                       Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_LONG).show();

                   }

                    else
                       Toast.makeText(getApplicationContext(),"Re-type password Carefully",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void getdetails() {
        username=txt_username.getText().toString();
        password =txt_password.getText().toString();
        repassword=txt_repassword.getText().toString();
    }

    private boolean checkpasswword(String password, String repassword) {
boolean status=false;

        if(repassword!=null && password!=null)
        {
            if(repassword.equals(password))
                status=true;
            }

        return status;
    }
}