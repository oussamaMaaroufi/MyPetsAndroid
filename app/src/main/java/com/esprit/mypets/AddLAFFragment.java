package com.esprit.mypets;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceLAF;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.LostAndFound;
import com.esprit.mypets.entyityResponse.LostFoundResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddLAFFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddLAFFragment extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99 ;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int SELECT_REQUEST_CODE = 1;
    private TextView Desc;
    private Button save,Uplode,capture;
    private RadioButton lost,found;
    private RadioGroup radioGroup;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    private ProgressDialog progressDialog;
    Bitmap bitmap;
    ImageView postImage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddLAFFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddLAFFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddLAFFragment newInstance(String param1, String param2) {
        AddLAFFragment fragment = new AddLAFFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    boolean b = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        IServiceLAF iServiceLAF =retrofitClient.create(IServiceLAF.class);
        View v = inflater.inflate(R.layout.fragment_add_l_a_f, container, false);

        Desc = v.findViewById(R.id.TxtDesc);
        save = v.findViewById(R.id.SaveLAF);
        Uplode = v.findViewById(R.id.uplodeimageLAF);
        lost = v.findViewById(R.id.AddLAFLost);
        found =v.findViewById(R.id.AddLAFFound);
        radioGroup = v.findViewById(R.id.RadioGroupeLAF);
        postImage = v.findViewById(R.id.imgAddLAF);
        capture = v.findViewById(R.id.captureimageLAF);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Image Upload....");
        Uplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckPermission()) {
                    Intent select = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(select, SELECT_REQUEST_CODE);
                }
            }
        });

        capture.setOnClickListener(new View.OnClickListener() {
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
             //   Toast.makeText(getActivity(),"test",Toast.LENGTH_LONG);

                LostAndFound lostAndFound = new LostAndFound();
                lostAndFound.setIdUser(Vars.getUSER().getId());
                lostAndFound.setUserName(Vars.getUSER().getName());
             //   Toast.makeText(getActivity(),lostAndFound.toString(),Toast.LENGTH_LONG);
                if(radioGroup.getCheckedRadioButtonId()== R.id.AddLAFLost){
                    lostAndFound.setType("Lost");
                }else if(radioGroup.getCheckedRadioButtonId()== R.id.AddLAFFound){
                    lostAndFound.setType("Found");
                }else{
                    b = false;
                    Toast.makeText(v.getContext(),"type is required",Toast.LENGTH_LONG);
                }

                if(!Desc.getText().toString().isEmpty()){
                    lostAndFound.setDesc(Desc.getText().toString());
                }else {
                    lostAndFound.setDesc("");
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                lostAndFound.setImage(image);
                if(b){

                    Call<LostFoundResponse> call  = iServiceLAF.AddlostAndfound(lostAndFound);
                    call.enqueue(new Callback<LostFoundResponse>() {
                        @Override
                        public void onResponse(Call<LostFoundResponse> call, Response<LostFoundResponse> response) {
                            System.out.println(response.body());
                            if (!response.isSuccessful()){
                            //    Toast.makeText(getContext(), "Error ", Toast.LENGTH_SHORT).show();
                            } else {
                          //      Toast.makeText(getActivity(),  response.body().getLostAndFound().toString() , Toast.LENGTH_SHORT).show();
                             //   if(response.body().getSuccess().equals("true")) {
                                    if (radioGroup.getCheckedRadioButtonId()== R.id.AddLAFLost) {
                                        LostFragment.getAllLost(iServiceLAF);
                                        LostFragment f = new LostFragment();
                                        getActivity().getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.fragmentsContainer, f)
                                                .commit();
                                        progressDialog.dismiss();
                                    } else if (radioGroup.getCheckedRadioButtonId()== R.id.AddLAFFound) {
                                        FoundFragment.getAllFound(iServiceLAF);
                                        FoundFragment f = new FoundFragment();
                                        getActivity().getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.fragmentsContainer, f)
                                                .commit();
                                        progressDialog.dismiss();
                                   // }

                                }


                            }

                        }

                        @Override
                        public void onFailure(Call<LostFoundResponse> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });


                }

            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case CAPTURE_REQUEST_CODE:
            {
                if(resultCode == RESULT_OK){

                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    postImage.setImageBitmap(bitmap);
                    progressDialog.show();

                }

            }
            break;

            case SELECT_REQUEST_CODE:
            {
                if(resultCode == RESULT_OK){

                    try {
                        Uri ImageUri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), ImageUri);
                        postImage.setImageBitmap(bitmap);
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
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                                startActivity(new Intent(getActivity(), LostAndFound.class));
                                getActivity().overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }
}