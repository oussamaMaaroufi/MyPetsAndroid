package com.esprit.mypets;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceLAF;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.LostAndFound;
import com.esprit.mypets.entyityResponse.AnimalResponse;
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
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiceLAF iServiceLAF =retrofitClient.create(IServiceLAF.class);




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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_l_a_f, container, false);
        boolean b =true;
        Desc = v.findViewById(R.id.TxtDesc);
        save = v.findViewById(R.id.SaveLAF);
        Uplode = v.findViewById(R.id.uplodeimageLAF);
        lost = v.findViewById(R.id.AddLAFLost);
        found =v.findViewById(R.id.AddLAFFound);

        LostAndFound lostAndFound = new LostAndFound();
        lostAndFound.setIdUser(Vars.getUSER().getId());
        lostAndFound.setUserName(Vars.getUSER().getName());
        if(lost.isSelected()){
            lostAndFound.setType("Lost");
        }else if(found.isSelected()){
            lostAndFound.setType("Found");
        }else{
            b=false;
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
                    if (!response.isSuccessful()){
                        Toast.makeText(getContext(), "Error ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),  response.body().getLostAndFound().toString() , Toast.LENGTH_SHORT).show();
                        if(response.body().getSuccess().equals("true")) {
                            if (lost.isSelected()) {
                                LostFragment.getAllLost(iServiceLAF);
                                LostFragment f = new LostFragment();
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragmentsContainer, f)
                                        .commit();
                            } else if (found.isSelected()) {
                                FoundFragment.getAllFound(iServiceLAF);
                                FoundFragment f = new FoundFragment();
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragmentsContainer, f)
                                        .commit();
                            }

                        }


                    }

                }

                @Override
                public void onFailure(Call<LostFoundResponse> call, Throwable t) {

                }
            });


        }





        return v;
    }


}