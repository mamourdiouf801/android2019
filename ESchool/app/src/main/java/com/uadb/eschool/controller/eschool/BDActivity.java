package com.uadb.eschool.controller.eschool;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class BDActivity extends AppCompatActivity {

    private EditText txtLoginBD, txtPasswordBD;
    private Button btnAdd,btnDelete,btnList,btnUpdate;
    private String login,password;
    private BDSchool bd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);

        //les liens entre les composants graphiques et les variables déclarées
        txtLoginBD = findViewById(R.id.txtloginBD);
        txtPasswordBD = findViewById(R.id.txtpasswordBD);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnList = findViewById(R.id.btnList);

        bd = new BDSchool(this);

        //implémentation des événements click sur les buttons

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLoginBD.getText().toString();
                password = txtPasswordBD.getText().toString();

                boolean b = bd.addUser(login,password);
                if(b)
                    Toast.makeText(BDActivity.this, "Ajout réussi", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BDActivity.this, "Ajout échoué", Toast.LENGTH_SHORT).show();

                //Pour passer un appel
                /*Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:781506624"));
                startActivity(intent);*/
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLoginBD.getText().toString();
                password = txtPasswordBD.getText().toString();

                boolean b = bd.updateUser(login, password);
                if(b)
                    Toast.makeText(BDActivity.this, "Modif réussi", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BDActivity.this, "Modif échoué", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLoginBD.getText().toString();
                password = txtPasswordBD.getText().toString();

                boolean b = bd.deleteUser(login);
                if(b)
                    Toast.makeText(BDActivity.this, "Supress réussi", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BDActivity.this, "Supress échoué", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = bd.getUsers();
                String chaine ="";
                for (int i = 0; i <list.size() ; i++) {
                    chaine+=list.get(i)+"\n\n";
                }
                Toast.makeText(BDActivity.this, chaine, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
