package com.example.usermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.usermanagement.Adapter.RecyclerViewAdapter;
import com.example.usermanagement.Model.Users;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recUser;
    private EditText edtName, edtPhone;
    private Spinner spnAddress;
    private RecyclerViewAdapter recyclerViewAdapter;
    public List<Users> usersList;
    private Button btnAdd;
    private String[] addresses = {"Ha Noi", "Bac Ninh", "Ha Nam", "Nghe An", "Thanh Hoa", "Hung Yen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recUser = findViewById(R.id.rec1);
        btnAdd = findViewById(R.id.btn_add);
        edtName = findViewById(R.id.edt_name);
        spnAddress = findViewById(R.id.spn_address);
        edtPhone = findViewById(R.id.edt_phone);

        usersList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, addresses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAddress.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recUser.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(this, usersList);
        recUser.setAdapter(recyclerViewAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = spnAddress.getSelectedItem().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)){
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.dialog);

                    Button btnOk = dialog.findViewById(R.id.btn_ok);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    Users users = new Users(name, phone, address);
                    recyclerViewAdapter.addUser(users);
                }

            }
        });



    }
}