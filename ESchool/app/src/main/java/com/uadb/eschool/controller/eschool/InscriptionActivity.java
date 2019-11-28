package com.uadb.eschool.controller.eschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {
    private EditText txtFirstName, txtLastName;
    private CheckBox cbOLevel,cbBachelor,cbMaster;
    private Button btnSave;
    private String firstName,lastName,degrees,formation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        formation = getIntent().getStringExtra("FORMATION");
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);

        cbBachelor = findViewById(R.id.cbBachelor);
        cbMaster = findViewById(R.id.cbMaster);
        cbOLevel = findViewById(R.id.cbOLevel);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = txtFirstName.getText().toString();
                lastName = txtLastName.getText().toString();
                degrees = "";
                if (cbOLevel.isChecked())
                    degrees += cbOLevel.getText().toString()+" ";
                if (cbBachelor.isChecked())
                    degrees += cbBachelor.getText().toString()+" ";
                if (cbMaster.isChecked())
                    degrees += cbMaster.getText().toString();

                String info = firstName+"\n\n"+lastName+"\n\n"+degrees+"\n\n"+formation;

                Toast.makeText(InscriptionActivity.this, info, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
