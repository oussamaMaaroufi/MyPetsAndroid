package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {



    private Button register;
    private EditText email,name,password,passwordconf;
    private RadioGroup radioGroup;
    private IServieceUser iServieceUser ;
    private Retrofit retrofit = RetrofitClient.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btnSignUp);
        name = findViewById(R.id.txtName);
        email = findViewById(R.id.TxtEmailRegister);
        password = findViewById(R.id.txtPwdRegister);
        passwordconf = findViewById(R.id.txtPwdRegisterConf);
        iServieceUser = retrofit.create(IServieceUser.class);
        radioGroup = findViewById(R.id.RadioGroupeReg);

        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                User user = new User();
                boolean b = false;

                if(email.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Email cannot be null or empyt",Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().isEmpty()){

                    Toast.makeText(RegisterActivity.this,"password cannot be null or empyt",Toast.LENGTH_SHORT).show();
                }else  if(name.getText().toString().isEmpty()) {

                    Toast.makeText(RegisterActivity.this, "name cannot be null or empyt", Toast.LENGTH_SHORT).show();
                }else if (radioGroup.getCheckedRadioButtonId() == R.id.RadioButton1) {
                    b= true;
                        user.setType("Volontaires");
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.RadioButton2) {
                    b= true;
                        user.setType("Abris");
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.RadioButton3) {
                    b= true;
                        user.setType("Veterinaires");
                }
                if(!b) {
                    Toast.makeText(RegisterActivity.this, "Role cannot be null or empyt", Toast.LENGTH_SHORT).show();
                }else
                {

                    user.setEmail(email.getText().toString());
                    user.setName(name.getText().toString());
                    user.setPassword(password.getText().toString());


                 //   Toast.makeText(RegisterActivity.this,user.toString(),Toast.LENGTH_SHORT).show();
                    RegisterUser(user);
                }

            }
        });


    }


    private  void RegisterUser(User user){


        try {
          //  Toast.makeText(RegisterActivity.this,user.toString(),Toast.LENGTH_SHORT).show();

            Call<UserResponse> call = iServieceUser.registerUser(user);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Error ", Toast.LENGTH_SHORT).show();
                       //       ErrorTxt.setText("Code : "+response.code());
                    }
                    UserResponse userResponse =response.body();
                    if (userResponse.getSuccess().equals("true")){

                        Vars.setUSER(userResponse.getUser());
                        Toast.makeText(RegisterActivity.this, "Vars.user"+Vars.getUSER(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);

                        startActivity(intent);

                    }else {
                        Toast.makeText(RegisterActivity.this, userResponse.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            });

        }catch (Exception e){
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }


}