package com.example.food;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity {
ImageView bgimage;
String s1,s2;
FirebaseAuth Fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        final Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);

        bgimage = findViewById(R.id.back2);
        bgimage.setAnimation(zoomin);
        bgimage.setAnimation(zoomout);

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage.startAnimation(zoomin);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        TextInputLayout et1 = (TextInputLayout) findViewById(R.id.Demail);
        TextInputLayout et2 = (TextInputLayout) findViewById(R.id.Dpassword);
        Button Login = (Button) findViewById(R.id.bt1);
        Button SNGUP = (Button) findViewById(R.id.bt2);
        TextView tv=(TextView)findViewById(R.id.Dforgotpass);
        Fauth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = et1.getEditText().getText().toString().trim();
                s2 = et2.getEditText().getText().toString().trim();
                if (isValid()) {

                    final ProgressDialog mDialog = new ProgressDialog(MainMenu.this);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setCancelable(false);
                    mDialog.setMessage("Sign In Please Wait.......");
                    mDialog.show();
                    Fauth.signInWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid()+"/Role");
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String role = snapshot.getValue(String.class);
                                        if(role.equals("Customer")){
                                            if (Fauth.getCurrentUser().isEmailVerified()) {
                                                mDialog.dismiss();
                                                Toast.makeText(MainMenu.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                                Intent Z = new Intent(MainMenu.this,customer_panel.class);
                                                startActivity(Z);
                                                //finish();

                                            } else {
                                                ReusableCodeForAll.ShowAlert(MainMenu.this, "Verification Failed", "Verifcation fail");

                                            }

                                        }
                                        else if(role.equals("restaurant")){
                                            if (Fauth.getCurrentUser().isEmailVerified()) {
                                                mDialog.dismiss();
                                                Toast.makeText(MainMenu.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                                Intent Z = new Intent(MainMenu.this,Admin.class);
                                                startActivity(Z);
                                                //finish();

                                            } else {
                                                ReusableCodeForAll.ShowAlert(MainMenu.this, "Verification Failed", "Verifcation fail");

                                            }

                                        }



                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(MainMenu.this,error.getMessage(),Toast.LENGTH_LONG).show();

                                    }
                                });
                                mDialog.dismiss();


                            } else {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(MainMenu.this, "Error", task.getException().getMessage());
                            }
                        }
                    });
                }
            }


            String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            public boolean isValid() {

                et1.setErrorEnabled(false);
                et1.setError("");
                et2.setErrorEnabled(false);
                et2.setError("");

                boolean isvalid = false, isvalidemail = false, isvalidpassword = false;
                if (TextUtils.isEmpty(s1)) {
                    et1.setErrorEnabled(true);
                    et1.setError("Email is required");
                } else {
                    if (s1.matches(emailpattern)) {
                        isvalidemail = true;
                    } else {
                        et1.setErrorEnabled(true);
                        et1.setError("Invalid Email Address");
                    }
                }
                if (TextUtils.isEmpty(s2)) {

                    et2.setErrorEnabled(true);
                    et2.setError("Password is Required");
                } else {
                    isvalidpassword = true;
                }
                isvalid = (isvalidemail && isvalidpassword) ? true : false;
                return isvalid;
            }
        });

        SNGUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, singup.class);
                startActivity(intent);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainMenu.this,Forgot.class);
                startActivity(i);
            }
        });


    }}
