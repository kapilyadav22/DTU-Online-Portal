package com.example.dtuadminkapil.User.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dtuadminkapil.Model.SliderItem;
import com.example.dtuadminkapil.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private SliderView sliderView;
    private SliderAdapterExample adapter;
    private ImageView map;

    public HomeFragment() {
    }

    String downloadurl="";
    String downloadurl1="";
    String downloadurl2="";
    String downloadurl3="";
    String downloadurl4="";
    String downloadurl5="";
    String downloadurl6="";
    String downloadurl7="";
    String downloadurl8="";

    private final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://dtu-admin-981bd.appspot.com/");
    StorageReference dbref;

    private void openmaps() {
        Uri uri = Uri.parse("geo:0,0?q=Delhi Technological University, Bawana Road, Delhi Technological University, Shahbad Daulatpur Village, Rohini, New Delhi, Delhi");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = view.findViewById(R.id.slider);
        adapter = new SliderAdapterExample(getActivity());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.FILL);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setScrollTimeInMillis(3000);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
        @Override
        public void onIndicatorClicked(int position) {
            Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
        }
    });
        renewItems(view);
        adapter.notifyDataSetChanged();
        map = view.findViewById(R.id.dtumap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmaps();
            }
        });
       return view;

    }

    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();

        int i;

        for (i = 0; i < 9; i++) {

            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("GLIMPSE OF DTU ");
            switch (i){
                case 0: dbref =  storageReference.child("Imageslider/DTU1.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                        sliderItem.setImageUrl(downloadurl);
                        break;
                case 1:  dbref =  storageReference.child("Imageslider/DTU2.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl1 = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl);
                    break;
                case 2:  dbref =  storageReference.child("Imageslider/DTU3.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl2 = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl2);
                    break;
                case 3:  dbref =  storageReference.child("Imageslider/DTU4.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl3 = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl3);
                    break;
                case 4:   dbref =  storageReference.child("Imageslider/DTU5.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl4 = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl4);
                    break;
                case 5:   dbref =  storageReference.child("Imageslider/DTU6.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl5 = downloadurl.concat(String.valueOf(uri));
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl5);
                    break;
                case 6:  dbref =  storageReference.child("Imageslider/DTU7.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl6 = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl);
                    break;
                case 7:  dbref =  storageReference.child("Imageslider/DTU8.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl7 = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl7);
                    break;
                case 8: dbref =  storageReference.child("Imageslider/DTU9.jpg");
                    dbref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadurl8 = String.valueOf(uri);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"failed to load the image!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    sliderItem.setImageUrl(downloadurl8);
                    break;
            }


            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }



    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        adapter.addItem(sliderItem);
    }

        }


