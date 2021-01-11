package com.esprit.mypets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceAbri;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.IServiseVolontaires;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entity.Volontaires;
import com.esprit.mypets.entyityResponse.AbriResponse;
import com.esprit.mypets.entyityResponse.VeterinairesResponse;
import com.esprit.mypets.entyityResponse.VolontairesResponse;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99 ;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int SELECT_REQUEST_CODE = 1;
    private EditText phone,addres;
    private Button save,btnuplode,captureBtn;
    private TextView messageText,name;
    private ProgressDialog dialog;
    private IServiceAbri iServeceAbri;
    private IServiseVeterinaire iServiseVeterinaire;
    private IServiseVolontaires iServiseVolontaires;
    private Retrofit retrofit = RetrofitClient.getInstance();
    private User user;
    private ImageView imgprofil;
    private ProgressDialog progressDialog;
    Bitmap bitmap;
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
            progressDialog = new ProgressDialog(ProfileActivity.this);
            progressDialog.setMessage("Image Upload....");


        btnuplode = findViewById(R.id.uplodeimage);

        btnuplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckPermission()) {
                    Intent select = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(select, SELECT_REQUEST_CODE);
                }
            }


        });

        captureBtn = findViewById(R.id.captureimage);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()){
                    Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(capture, CAPTURE_REQUEST_CODE);
                }
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
                        volontaires.setName(user.getName());

                        CreateProfilVolo(volontaires,bitmap);
                    } else if (user.getType().equals("Abris")) {
                        iServeceAbri = retrofit.create(IServiceAbri.class);
                        Abris abris = new Abris();
                        abris.setIdUser(user.getId());
                        abris.setAdresse(addresS);
                        abris.setTelephon(phoneS);
                        abris.setName(user.getName());
                        CreateProfilAbri(abris,bitmap);
                    } else {
                        iServiseVeterinaire = retrofit.create(IServiseVeterinaire.class);
                        Veterinaires veterinaires = new Veterinaires();
                        veterinaires.setIdUser(user.getId());
                        veterinaires.setAdresse(addresS);
                        veterinaires.setTelephon(phoneS);
                        veterinaires.setName(user.getName());
                        CreateProfilVeto(veterinaires,bitmap);

                    }

                    Intent intent = new Intent(ProfileActivity.this, MyProfile.class);

                    startActivity(intent);

                }
            });



    }


    private  void CreateProfilVolo(Volontaires volontaires,Bitmap bitmap){

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
                        progressDialog.dismiss();
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
    private  void CreateProfilAbri(Abris abris ,Bitmap bitmap){


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
                        progressDialog.dismiss();

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
    private  void CreateProfilVeto(Veterinaires veterinaires,Bitmap bitmap){

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
                        progressDialog.dismiss();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case CAPTURE_REQUEST_CODE:
            {
                if(resultCode == RESULT_OK){

                     bitmap = (Bitmap) data.getExtras().get("data");
                    imgprofil.setImageBitmap(bitmap);
                    progressDialog.show();

                }

            }
            break;

            case SELECT_REQUEST_CODE:
            {
                if(resultCode == RESULT_OK){

                    try {
                        Uri ImageUri = data.getData();
                         bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
                        imgprofil.setImageBitmap(bitmap);
                        progressDialog.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            break;
        }
        progressDialog.dismiss();
    }

    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(ProfileActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(ProfileActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(ProfileActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(ProfileActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                                ProfileActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(ProfileActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }

}