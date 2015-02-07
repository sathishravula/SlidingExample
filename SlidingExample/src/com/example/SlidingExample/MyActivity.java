package com.example.SlidingExample;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivity extends Activity implements View.OnClickListener {
    private TextView singin;
    private LinearLayout singUp;
    private LinearLayout singIn;
    private TextView email;
    private TextView password;
    private TextView singInCountinue;
    private TextView singUpCountinue;
    private TextView lname, fname;
    private SharedPreferences session;
    private SharedPreferences.Editor editor;
    private String emailId;
    private String PassWord;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        singUp = (LinearLayout) findViewById(R.id.singup);
        singIn = (LinearLayout) findViewById(R.id.singin);
        singIn.setOnClickListener(this);
        singUp.setOnClickListener(this);
        session = getSharedPreferences("session", Context.MODE_MULTI_PROCESS);
        editor = session.edit();
        if(session.contains("ok")){
            startActivity();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singin:
                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.sing_in);
//                dialog.setTitle("Sing In");
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#99000000")));
                email = (TextView) dialog.findViewById(R.id.email);
                password = (TextView) dialog.findViewById(R.id.password);
                singInCountinue = (TextView) dialog.findViewById(R.id.singin_continue);
                singInCountinue.setOnClickListener(this);
                dialog.show();
                break;
            case R.id.singup:
                final Dialog dialog1 = new Dialog(this);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.sing_up);
//                dialog1.setTitle("Sing Up");
                email = (TextView) dialog1.findViewById(R.id.email);
                lname = (TextView) dialog1.findViewById(R.id.lname);
                fname = (TextView) dialog1.findViewById(R.id.fname);
                password = (TextView) dialog1.findViewById(R.id.password);
                singUpCountinue = (TextView) dialog1.findViewById(R.id.singup_continue);
                singUpCountinue.setOnClickListener(this);
                dialog1.show();
                break;
            case R.id.singup_continue:
                if (doValidation1()) {
                    editor.putString("ok", "ok").commit();
                    editor.putString("email", emailId).commit();
                    editor.putString("password", PassWord).commit();
                    startActivity();
                }
                break;

            case R.id.singin_continue:
                if (doValidation()) {
                    if (emailId.equalsIgnoreCase(session.getString("email", "")) && PassWord.equalsIgnoreCase(session.getString("password", ""))) {
                        startActivity();
                    } else {
                        email.setError("Invalid email");
                        password.setError("Invalid password");
                    }
                }
                break;
        }
    }

    private void startActivity() {
        Intent intent = new Intent(this, ResponsiveUIActivity.class);
        startActivity(intent);
    }

    private boolean doValidation() {
        String password = this.password.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        emailId = email;
        PassWord = password;
        if (email.equalsIgnoreCase("")) {
            this.email.setError("Invalid email id");
            this.email.requestFocus();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email)) {
            this.email.setError("Invalid email id");
            this.email.requestFocus();
            return false;
        }
        if (password.equalsIgnoreCase("")) {
            this.password.setError("Invalid Password");
            return false;
        }
        if (password.length() < 4) {
            this.password.setError("Length should be greater than 4");
            return false;
        }
        return true;
    }

    private boolean doValidation1() {
        String password = this.password.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String lname = this.lname.getText().toString().trim();
        String fname = this.fname.getText().toString().trim();
        emailId = email;
        PassWord = password;
        if (email.equalsIgnoreCase("")) {
            this.email.setError("Invalid email id");
            this.email.requestFocus();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email)) {
            this.email.setError("Invalid email id");
            this.email.requestFocus();
            return false;
        }
        if (password.equalsIgnoreCase("")) {
            this.password.setError("Invalid Password");
            return false;
        }
        if (password.length() < 4) {
            this.password.setError("Length should be greater than 4");
            return false;
        }

        if (lname.equalsIgnoreCase("")) {
            this.lname.setError("Invalid Password");
            return false;
        }
        if (fname.equalsIgnoreCase("")) {
            this.fname.setError("Invalid Password");
            return false;
        }
        return true;
    }
}
