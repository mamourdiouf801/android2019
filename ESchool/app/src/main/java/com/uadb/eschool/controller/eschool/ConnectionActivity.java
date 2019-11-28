package com.uadb.eschool.controller.eschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class ConnectionActivity extends AppCompatActivity {

    //Etape 1: declaration de variables
    private EditText txtLogin, txtPassword; //ces deux variables sont des composents graphiques mais pas des chaines de caracteres
    private Button btnConnection, btnInscription;// de meme ces deux sont des composents graphiques
    private String login,password;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.wait));
        //Etape 2: link entre les variables et les composent de l'interface graphique
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);

        btnConnection = findViewById(R.id.btnConnection);
        btnInscription = findViewById(R.id.btnInscription);

        //Etape 3: gerer les evenement click
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Etape 4: recupere login et mot de passe
                login = txtLogin.getText().toString();
                password = txtPassword.getText().toString();

                //Etape 5: Verification
                if (login.isEmpty() || password.isEmpty()){
                    Toast.makeText(ConnectionActivity.this, getString(R.string.errorFields), Toast.LENGTH_SHORT).show();
                }
                else{
//                    //Action redirection vers une autre page
//                    Intent intent = new Intent(ConnectionActivity.this,HomeActivity.class);
//                    intent.putExtra("LOGIN",login);
//                    startActivity(intent);

                    String url="http://169.254.153.63/uadb/connexion.php?login=" +login+"&password="+password;
                    ConnexionServer test= new ConnexionServer();
                    test.execute(url);
                }

            }
        });
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnectionActivity.this,BDActivity.class);
                startActivity(intent);
            }
        });
    }

    protected  class ConnexionServer extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(params[0]);
                ResponseHandler<String> buffer = new BasicResponseHandler();
                String result = client.execute(get,buffer);
                return result;
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            try {
                dialog.dismiss();
                if(result == null){
                    Toast.makeText(ConnectionActivity.this,getString(R.string.errorParameters),Toast.LENGTH_SHORT).show();

                }
                else{
                    JSONObject jo =  new JSONObject(result);
                    String status = jo.getString("status");
                    if (status.equalsIgnoreCase("ko")){
                        Toast.makeText(ConnectionActivity.this, getString(R.string.errorParameters), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent=new Intent(ConnectionActivity.this , HomeActivity.class);
                        intent.putExtra("LOGIN",login);
                        startActivity(intent);
                    }
                }
            }
            catch (Exception e){

            }
        }
    }
}
