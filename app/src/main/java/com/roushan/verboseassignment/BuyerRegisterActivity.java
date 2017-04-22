package com.roushan.verboseassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyerRegisterActivity extends AppCompatActivity {

    private EditText fname, lname, add, mob;
    private Button register, btn_dob;
    private TextView go_back, dob_tv;
    String f, l;
    Session session;
    private DatePicker dp;

    public static String buyer_name, buyer_address, buyer_phone,
            buyer_day = "", buyer_month = "", buyer_year = "";
    public static int buyer_register_val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);

        session = new Session(this);

        fname = (EditText) findViewById(R.id.et_buyer_first_name);
        lname = (EditText) findViewById(R.id.et_buyer_last_name);
        add = (EditText) findViewById(R.id.et_buyer_add);
        mob = (EditText) findViewById(R.id.et_buyer_mob);

        dob_tv = (TextView) findViewById(R.id.tv_dob_buyer);
        go_back = (TextView) findViewById(R.id.txt_go_back_b);
        go_back.setPaintFlags(go_back.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerRegisterActivity.this, SelectBuyerSeller.class));
                finish();
            }
        });

        register = (Button) findViewById(R.id.btn_reg_buyer);
        btn_dob = (Button) findViewById(R.id.btn_dob_buyer);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f = fname.getText().toString();
                l = lname.getText().toString();
                buyer_name = f + " " + l;
                buyer_address = add.getText().toString();
                buyer_phone = mob.getText().toString();

                if(!f.equals("") && !l.equals("") && !buyer_address.equals("") && !buyer_phone.equals("")) {
                    if(dob_tv.getVisibility() == View.GONE) {
                        Toast.makeText(BuyerRegisterActivity.this, "Select Date of Birth",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        buyer_register_val = 1;

                        session.setLoggedIn(true);
                        session.setUserDetails(buyer_name, buyer_phone, buyer_address,
                                buyer_day, buyer_month, buyer_year);

                        Intent i = new Intent(BuyerRegisterActivity.this, BuyerSellerNavActivity.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Toast.makeText(BuyerRegisterActivity.this, "Enter empty field first",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder datePickerDialog = new AlertDialog.Builder(BuyerRegisterActivity.this);
                dp = new DatePicker(BuyerRegisterActivity.this);
                datePickerDialog.setView(dp);
                datePickerDialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        buyer_day = String.valueOf(dp.getDayOfMonth());
                        buyer_month = String.valueOf(dp.getMonth() + 1);
                        buyer_year = String.valueOf(dp.getYear());
                        btn_dob.setVisibility(View.GONE);
                        dob_tv.setVisibility(View.VISIBLE);
                        dob_tv.setText(buyer_day + "/" + buyer_month + "/" + buyer_year);
                    }
                });
                datePickerDialog.setCancelable(false);
                datePickerDialog.create();
                datePickerDialog.show();
            }
        });
    }
}
