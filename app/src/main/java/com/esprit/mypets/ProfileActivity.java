package com.esprit.mypets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServeceAbri;
import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.IServiseVolontaires;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entity.Volontaires;
import com.esprit.mypets.entyityResponse.AbriResponse;
import com.esprit.mypets.entyityResponse.UserResponse;
import com.esprit.mypets.entyityResponse.VeterinairesResponse;
import com.esprit.mypets.entyityResponse.VolontairesResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {

    private EditText phone,addres;
    private Button save,btnuplode;
    private TextView messageText,name;
    private ProgressDialog dialog;
    private IServeceAbri iServeceAbri;
    private IServiseVeterinaire iServiseVeterinaire;
    private IServiseVolontaires iServiseVolontaires;
    private Retrofit retrofit = RetrofitClient.getInstance();
    private User user;
    private ImageView imgprofil;

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.nameCreateProfil);
        phone = findViewById(R.id.phoneCreateprofil);
        addres =findViewById(R.id.addressCreateProfil);
        save = findViewById(R.id.ProfilSave);
        user =(User) Vars.getUSER();
        name.setText(user.getName());
        imgprofil = findViewById(R.id.IdProf);
        Toast.makeText(ProfileActivity.this, "Welcome " + Vars.getUSER().toString(), Toast.LENGTH_SHORT).show();


        btnuplode = findViewById(R.id.uplodeimage);

        btnuplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnuplode = (Button) findViewById(R.id.uplodeimage);
                messageText = (TextView) findViewById(R.id.messageText);

                dialog = ProgressDialog.show(ProfileActivity.this, "", "Uploading file...", true);

              /*  new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                messageText.setText("uploading started.....");
                            }
                        });

                        //     uploadFile(uploadFilePath + "" + uploadFileName);

                    }
                }).start();
*/


            }


        });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneS = "", addresS = "";
                    if (!phone.getText().toString().isEmpty()) {
                        phoneS = phone.getText().toString();
                    }
                    if (!addres.getText().toString().isEmpty()) {
                        addresS = addres.getText().toString();
                    }
                    Toast.makeText(ProfileActivity.this, phoneS+addresS+user.getType(), Toast.LENGTH_SHORT).show();
                    if (user.getType().equals("Volontaires")) {
                        iServiseVolontaires = retrofit.create(IServiseVolontaires.class);
                        Volontaires volontaires = new Volontaires();
                        volontaires.setIdUser(user.getId());
                        volontaires.setAdresse(addresS);
                        volontaires.setTelephon(phoneS);
                        CreateProfilVolo(volontaires);
                    } else if (user.getType().equals("Abris")) {
                        iServeceAbri = retrofit.create(IServeceAbri.class);
                        Abris abris = new Abris();
                        abris.setIdUser(user.getId());
                        abris.setAdresse(addresS);
                        abris.setTelephon(phoneS);
                        CreateProfilAbri(abris);
                    } else {
                        iServiseVeterinaire = retrofit.create(IServiseVeterinaire.class);
                        Veterinaires veterinaires = new Veterinaires();
                        veterinaires.setIdUser(user.getId());
                        veterinaires.setAdresse(addresS);
                        veterinaires.setTelephon(phoneS);
                        CreateProfilVeto(veterinaires);

                    }

                    Intent intent = new Intent(ProfileActivity.this, MyProfile.class);

                    startActivity(intent);

                }
            });



    }


    private  void CreateProfilVolo(Volontaires volontaires){

        try {
            Call<VolontairesResponse> call = iServiseVolontaires.registerVeterinaires(volontaires);
            call.enqueue(new Callback<VolontairesResponse>() {
                @Override
                public void onResponse(Call<VolontairesResponse> call, Response<VolontairesResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(ProfileActivity.this,"Error ", Toast.LENGTH_SHORT).show();
                        //       ErrorTxt.setText("Code : "+response.code());
                    }
                    VolontairesResponse volontairesResponse =response.body();
                    if (volontairesResponse.getSuccess().equals("true")){
                        Vars.setAddress(addres.getText().toString());
                        Vars.setPhone(phone.getText().toString());
                    }else {
                        Toast.makeText(ProfileActivity.this, volontairesResponse.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<VolontairesResponse> call, Throwable t) {
                    Toast.makeText(ProfileActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private  void CreateProfilAbri(Abris abris){

        try {
            Call<AbriResponse> call = iServeceAbri.registerAbri(abris);
            call.enqueue(new Callback<AbriResponse>() {
                @Override
                public void onResponse(Call<AbriResponse> call, Response<AbriResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(ProfileActivity.this,"Error ", Toast.LENGTH_SHORT).show();
                        //       ErrorTxt.setText("Code : "+response.code());
                    }
                    AbriResponse abriResponse =response.body();
                    if (abriResponse.getSuccess().equals("true")){
                        Vars.setAddress(addres.getText().toString());
                        Vars.setPhone(phone.getText().toString());

                    }else {
                        Toast.makeText(ProfileActivity.this, abriResponse.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<AbriResponse> call, Throwable t) {
                    Toast.makeText(ProfileActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private  void CreateProfilVeto(Veterinaires veterinaires){

        try {
            Call<VeterinairesResponse> call = iServiseVeterinaire.registerVeterinaires(veterinaires);
            call.enqueue(new Callback<VeterinairesResponse>() {
                @Override
                public void onResponse(Call<VeterinairesResponse> call, Response<VeterinairesResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(ProfileActivity.this,"Error ", Toast.LENGTH_SHORT).show();
                        //       ErrorTxt.setText("Code : "+response.code());
                    }
                    VeterinairesResponse veterinairesResponse =response.body();
                    if (veterinairesResponse.getSuccess().equals("true")){
                        Vars.setAddress(addres.getText().toString());
                        Vars.setPhone(phone.getText().toString());
                    }else {
                        Toast.makeText(ProfileActivity.this, veterinairesResponse.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<VeterinairesResponse> call, Throwable t) {
                    Toast.makeText(ProfileActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(takePicture, 0);

                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    //Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imgprofil.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                            imgprofil.setImageBitmap(bitmap);

                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                    break;
            }
        }
    }

}