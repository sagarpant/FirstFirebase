package com.example.abc.firstfirebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText Email_txt;
    private EditText Password_txt;
    private TextView Login;
    private Button Button_register;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email_txt = (EditText) findViewById(R.id.Email);
        Password_txt = (EditText) findViewById(R.id.Password);
        Login = (TextView) findViewById(R.id.Login_txt);
        Button_register = (Button) findViewById(R.id.RegisterBtn);
        progressDialog = new ProgressDialog(this);

        Button_register.setOnClickListener(this);
        Login.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();


    }

    private void registeruser(){
        String ema = Email_txt.getText().toString().trim();
        String pass = Password_txt.getText().toString().trim();

        if(TextUtils.isEmpty(ema)){
            Toast.makeText(this,"Please Enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Enter password", Toast.LENGTH_SHORT).show();
            return ;
        }

        progressDialog.setMessage("Registering Please Wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(ema,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"User register successfully" , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"COuldn't register try again" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        progressDialog.dismiss();

    }

    @Override
    public void onClick(View view) {

        if(view == Button_register){
                registeruser();
        }
        if(view == Login){
            // Open Login Activity
        }



    }
}
