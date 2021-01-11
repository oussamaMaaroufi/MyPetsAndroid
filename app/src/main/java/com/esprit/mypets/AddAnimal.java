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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddAnimal extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99 ;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int SELECT_REQUEST_CODE = 1;
    private Spinner spinner;
    private EditText name,race;
    private Button save,btnuplode,captureBtn;
    private ImageView imgprofil;
    private ProgressDialog progressDialog;
    Bitmap bitmap;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);
    private ImageButton btnMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        btnMenu = findViewById(R.id.btnmenu9);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAnimal.this,SideMenu.class);
                startActivity(intent);
            }
        });

        name = findViewById(R.id.NameAddAnimal);
        race = findViewById(R.id.race);
        save = findViewById(R.id.SaveAddAnimal);
        Animal animal =new Animal();


        spinner = findViewById(R.id.spinner);
        String[] items = new String[]{"Cat", "Dog", "bird"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        imgprofil = findViewById(R.id.imgAddAnimal);

        progressDialog = new ProgressDialog(AddAnimal.this);
        progressDialog.setMessage("Image Upload....");


        btnuplode = findViewById(R.id.uplodeimageAnimal);

        btnuplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckPermission()) {
                    Intent select = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(select, SELECT_REQUEST_CODE);
                }
            }


        });

        captureBtn = findViewById(R.id.captureimageAnimal);
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
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(AddAnimal.this, "name required ", Toast.LENGTH_SHORT).show();
                }else if(race.getText().toString().isEmpty()){
                    Toast.makeText(AddAnimal.this, "Race required ", Toast.LENGTH_SHORT).show();
                }if(spinner.isSelected()){
                    Toast.makeText(AddAnimal.this, "Type required ", Toast.LENGTH_SHORT).show();
                }else{
                    String type = spinner.getSelectedItem().toString();
                    animal.setType(type);
                    animal.setRace(race.getText().toString());
                    animal.setName(name.getText().toString());
                    User u =(User) Vars.getUSER();
                    animal.setIdUser( u.getId());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                    animal.setImage(image);
                    Call<AnimalResponse> call  = iServiseAnimal.AddAnimal(animal);
                    call.enqueue(new Callback<AnimalResponse>() {
                        @Override
                        public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                            if (!response.isSuccessful()){
                                Toast.makeText(AddAnimal.this, "Error ", Toast.LENGTH_SHORT).show();
                            } else {
                                if(response.body().getSuccess().equals("true")){
                                    MyAnimals.getAllAnimal(iServiseAnimal);

                                  Toast.makeText(AddAnimal.this,  response.body().getAnimal().toString()  , Toast.LENGTH_SHORT).show();
                                  Intent intent =new Intent(AddAnimal.this,MyAnimals.class);
                                  startActivity(intent);
                                    progressDialog.dismiss();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<AnimalResponse> call, Throwable t) {

                        }
                    });



                }




            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case CAPTURE_REQUEST_CODE:
            {
                if(resultCode == RESULT_OK){

                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
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
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
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
        if (ContextCompat.checkSelfPermission(AddAnimal.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(AddAnimal.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(AddAnimal.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddAnimal.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(AddAnimal.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(AddAnimal.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(AddAnimal.this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(AddAnimal.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                                startActivity(new Intent(AddAnimal.this, MyAnimals.class));
                                AddAnimal.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(AddAnimal.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }
}