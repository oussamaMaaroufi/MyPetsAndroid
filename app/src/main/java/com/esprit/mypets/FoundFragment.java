package com.esprit.mypets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esprit.mypets.Retrofit.IServiceLAF;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.MyAdapterLAF;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.LostAndFound;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;
import com.esprit.mypets.entyityResponse.LostFoundResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoundFragment extends Fragment {

    private RecyclerView recyclerView ;
    private MyAdapterLAF myAdapter;
    private static ArrayList<LostAndFound> lostAndFounds ;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiceLAF iServiceLAF =retrofitClient.create(IServiceLAF.class);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoundFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoundFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoundFragment newInstance(String param1, String param2) {
        FoundFragment fragment = new FoundFragment();
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
        getAllFound(iServiceLAF);
        View v= inflater.inflate(R.layout.fragment_found, container, false);

        recyclerView = v.findViewById(R.id.FoundRecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
       // fillData();
        myAdapter = new MyAdapterLAF(getActivity(),lostAndFounds);

        recyclerView.setAdapter(myAdapter);

        return v;

    }

    public static void getAllFound(IServiceLAF iServiceLAF){
        //    ArrayList<Animal> list = new ArrayList<Animal>();
        AnimalResponse animalResponse =new AnimalResponse();
        User u = (User) Vars.getUSER();

        Call<LostFoundResponseList> call = iServiceLAF.GetAllFound();

        call.enqueue(new Callback<LostFoundResponseList>() {
            @Override
            public void onResponse(Call<LostFoundResponseList> call, Response<LostFoundResponseList> response) {
                if (!response.isSuccessful()){
                    //   Toast.makeText(MyAnimals.this, "Error "+response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    if(response.body().getSuccess().equals("true")) {
                        FoundFragment.lostAndFounds = response.body().getLostAndFound();
                    }
                }

            }

            @Override
            public void onFailure(Call<LostFoundResponseList> call, Throwable t) {

            }
        });
    }



}