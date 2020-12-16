package com.esprit.mypets;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceLAF;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.LostAndFound;
import com.esprit.mypets.entyityResponse.LostFoundResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddLAFFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddLAFFragment extends Fragment {

    private TextView Desc;
    private Button save,Uplode;
    private RadioButton lost,found;
    private RadioGroup radioGroup;
    Retrofit retrofitClient = RetrofitClient.getInstance();





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

        Uplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"test",Toast.LENGTH_LONG);
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
                                    } else if (radioGroup.getCheckedRadioButtonId()== R.id.AddLAFFound) {
                                        FoundFragment.getAllFound(iServiceLAF);
                                        FoundFragment f = new FoundFragment();
                                        getActivity().getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.fragmentsContainer, f)
                                                .commit();
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


}