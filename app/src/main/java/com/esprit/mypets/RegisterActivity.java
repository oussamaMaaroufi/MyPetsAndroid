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
import com.esprit.mypets.entity.TypeUser;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private IServieceUser iServieceUser;
    private Button register;
    private EditText email,name,password,passwordconf;
    private RadioGroup radioGroup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btnSignUp);
        name = findViewById(R.id.txtName);
        email = findViewById(R.id.TxtEmailRegister);
        password = findViewById(R.id.txtPwdRegister);
        passwordconf = findViewById(R.id.txtPwdRegisterConf);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();

                if(email.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Email cannot be null or empyt",Toast.LENGTH_SHORT).show();
                }
                user.setEmail(email.getText().toString());
                if(password.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"password cannot be null or empyt",Toast.LENGTH_SHORT).show();
                }
                user.setPassword(password.getText().toString());
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"name cannot be null or empyt",Toast.LENGTH_SHORT).show();
                }
                user.setName(name.getText().toString());

             /*
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.RadioButton1 : user.setType(TypeUser.Volontaires);break;
                    case R.id.RadioButton2 : user.setType(TypeUser.Abris);break;
                    case R.id.RadioButton3 : user.setType(TypeUser.Veterinaires);break;
                    default:  Toast.makeText(RegisterActivity.this,"Role cannot be null or empyt",Toast.LENGTH_SHORT).show();
                }

              */
                user.setType(TypeUser.Veterinaires);



                    RegisterUser(user);

            }
        });


    }


    private  void RegisterUser(User user){
        Intent intent = new Intent(RegisterActivity.this,ProfileActivity.class);

        startActivity(intent);

/*
        try {
            Toast.makeText(RegisterActivity.this,user.toString(),Toast.LENGTH_SHORT).show();

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
                        Intent intent = new Intent(RegisterActivity.this,ProfileActivity.class);

                        startActivity(intent);

                    }
                    //  Toast.makeText(LoginActivity.this,userResponse.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


*/



    }
}