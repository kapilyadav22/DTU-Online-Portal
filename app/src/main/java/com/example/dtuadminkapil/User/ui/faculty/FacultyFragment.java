package com.example.dtuadminkapil.User.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dtuadminkapil.R;
import com.example.dtuadminkapil.faculty.Addteachers;
import com.example.dtuadminkapil.faculty.TeacherAdapter;
import com.example.dtuadminkapil.faculty.Updatefaculty;
import com.example.dtuadminkapil.faculty.teacherdata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView csdepartment,ECEdepartment,swedepartment,ITdepartment,chemistrydepartment,
            physicsdepartment,biotechdepartment,eeedepartment,civildepartment,environmentdepartment,
            mechanicaldepartment;

    private LinearLayout csnodata,ECEnodata,swenodata,ITnodata,chemistrydepartmentnodata,physicsdepartmentnodata,
            biotechdepartmentnodata, eeedepartmentnodata, civildepartmentnodata, environmentdepartmentnodata,
            mechanicaldepartmentnodata;
    private List<userteacherdata> list1,list2,list3,list4,list5,list6,list7,list8,list9,list10,list11;
    private userTeacherAdapter adapter;

    private DatabaseReference reference, dbref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_faculty, container, false);
        csdepartment = view.findViewById(R.id.csdepartment);
        ECEdepartment = view.findViewById(R.id.ECEdepartment);
        swedepartment = view.findViewById(R.id.swedepartment);
        ITdepartment = view.findViewById(R.id.ITdepartment);
        chemistrydepartment = view.findViewById(R.id.chemistrydepartment);
        physicsdepartment = view.findViewById(R.id.physicsdepartment);
        biotechdepartment = view.findViewById(R.id.biotechdepartment);
        eeedepartment = view.findViewById(R.id.eeedepartment);
        civildepartment = view.findViewById(R.id.civildepartment);
        environmentdepartment = view.findViewById(R.id.environmentdepartment);
        mechanicaldepartment = view.findViewById(R.id.mechanicaldepartment);

        csnodata = view.findViewById(R.id.csnodata);
        ECEnodata = view.findViewById(R.id.ECEnodata);
        swenodata = view.findViewById(R.id.swenodata);
        ITnodata = view.findViewById(R.id.ITnodata);
        chemistrydepartmentnodata = view.findViewById(R.id.chemistrydepartmentnodata);
        physicsdepartmentnodata = view.findViewById(R.id.physicsdepartmentnodata);
        biotechdepartmentnodata = view.findViewById(R.id.biotechdepartmentnodata);
        eeedepartmentnodata = view.findViewById(R.id.eeedepartmentnodata);
        civildepartmentnodata = view.findViewById(R.id.civildepartmentnodata);
        mechanicaldepartmentnodata = view.findViewById(R.id.mechanicaldepartmentnodata);
        environmentdepartmentnodata = view.findViewById(R.id.environmentdepartmentnodata);

        reference = FirebaseDatabase.getInstance().getReference().child("Teacher");

        csdepartment();
        ECEdepartment();
        swedepartment();
        ITdepartment();
        chemistrydepartment();
        physicsdepartment();
        biotechdepartment();
        eeedepartment();
        civildepartment();
        environmentdepartment();
        mechanicaldepartment();
        return view;
    }

    private void mechanicaldepartment() {
        dbref = reference.child("Mechanical Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list11 = new ArrayList<>();
                if(!snapshot.exists()){
                    mechanicaldepartmentnodata.setVisibility(View.VISIBLE);
                    mechanicaldepartment.setVisibility(View.GONE);
                } else
                {   mechanicaldepartmentnodata.setVisibility(View.GONE);
                    mechanicaldepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot11: snapshot.getChildren()){
                        userteacherdata data = snapshot11.getValue(userteacherdata.class);
                        list11.add(data);
                    }
                    mechanicaldepartment.setHasFixedSize(true);
                    mechanicaldepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list11,getContext(),"Mechanical Engineering");
                    mechanicaldepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void environmentdepartment() {
        dbref = reference.child("Environment Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list10 = new ArrayList<>();
                if(!snapshot.exists()){
                    environmentdepartmentnodata.setVisibility(View.VISIBLE);
                    environmentdepartment.setVisibility(View.GONE);
                } else
                {  environmentdepartmentnodata.setVisibility(View.GONE);
                    environmentdepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot10: snapshot.getChildren()){
                        userteacherdata data = snapshot10.getValue(userteacherdata.class);
                        list10.add(data);
                    }
                    environmentdepartment.setHasFixedSize(true);
                    environmentdepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list10,getContext(),"Environment Engineering");
                    environmentdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void civildepartment() {
        dbref = reference.child("Civil Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list9 = new ArrayList<>();
                if(!snapshot.exists()){
                    civildepartmentnodata.setVisibility(View.VISIBLE);
                    civildepartment.setVisibility(View.GONE);
                } else
                {   civildepartmentnodata.setVisibility(View.GONE);
                    civildepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot9: snapshot.getChildren()){
                        userteacherdata data = snapshot9.getValue(userteacherdata.class);
                        list9.add(data);
                    }
                    civildepartment.setHasFixedSize(true);
                    civildepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list9,getContext(),"Civil Engineering");
                    civildepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeedepartment() {
        dbref = reference.child("Electrical Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list8 = new ArrayList<>();
                if(!snapshot.exists()){
                    eeedepartmentnodata.setVisibility(View.VISIBLE);
                    eeedepartment.setVisibility(View.GONE);
                } else
                {   eeedepartmentnodata.setVisibility(View.GONE);
                    eeedepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot8: snapshot.getChildren()){
                        userteacherdata data = snapshot8.getValue(userteacherdata.class);
                        list8.add(data);
                    }
                    eeedepartment.setHasFixedSize(true);
                    eeedepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list8,getContext(),"Electrical Engineering");
                    eeedepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void biotechdepartment() {
        dbref = reference.child("Biotechnology");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list7 = new ArrayList<>();
                if(!snapshot.exists()){
                    biotechdepartmentnodata.setVisibility(View.VISIBLE);
                    biotechdepartment.setVisibility(View.GONE);
                } else
                {   biotechdepartmentnodata.setVisibility(View.GONE);
                    biotechdepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot7: snapshot.getChildren()){
                        userteacherdata data = snapshot7.getValue(userteacherdata.class);
                        list7.add(data);
                    }
                    biotechdepartment.setHasFixedSize(true);
                    biotechdepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list7,getContext(),"Biotechnology");
                    biotechdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void physicsdepartment() {
        dbref = reference.child("Applied Physics");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list6 = new ArrayList<>();
                if(!snapshot.exists()){
                    physicsdepartmentnodata.setVisibility(View.VISIBLE);
                    physicsdepartment.setVisibility(View.GONE);
                } else
                {   physicsdepartmentnodata.setVisibility(View.GONE);
                    physicsdepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot6: snapshot.getChildren()){
                       userteacherdata data = snapshot6.getValue(userteacherdata.class);
                        list6.add(data);
                    }
                    physicsdepartment.setHasFixedSize(true);
                    physicsdepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list6,getContext(),"Applied Physics");
                    physicsdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chemistrydepartment() {
        dbref = reference.child("Applied Chemistry");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5 = new ArrayList<>();
                if(!snapshot.exists()){
                    chemistrydepartmentnodata.setVisibility(View.VISIBLE);
                    chemistrydepartment.setVisibility(View.GONE);
                } else
                {   chemistrydepartmentnodata.setVisibility(View.GONE);
                    chemistrydepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot5: snapshot.getChildren()){
                        userteacherdata data = snapshot5.getValue(userteacherdata.class);
                        list5.add(data);
                    }
                    chemistrydepartment.setHasFixedSize(true);
                    chemistrydepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list5,getContext(),"Applied Chemistry");
                    chemistrydepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ITdepartment() {
        dbref = reference.child("Information Technology");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if(!snapshot.exists()){
                    ITnodata.setVisibility(View.VISIBLE);
                    ITdepartment.setVisibility(View.GONE);
                } else
                {   ITnodata.setVisibility(View.GONE);
                    ITdepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot4: snapshot.getChildren()){
                        userteacherdata data = snapshot4.getValue(userteacherdata.class);
                        list4.add(data);
                    }
                    ITdepartment.setHasFixedSize(true);
                    ITdepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list4,getContext(),"Information Technology");
                    ITdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void swedepartment() {
        dbref = reference.child("Software Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if(!snapshot.exists()){
                    swenodata.setVisibility(View.VISIBLE);
                    swedepartment.setVisibility(View.GONE);
                } else
                {   swenodata.setVisibility(View.GONE);
                    swedepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot3: snapshot.getChildren()){
                        userteacherdata data = snapshot3.getValue(userteacherdata.class);
                        list3.add(data);
                    }
                    swedepartment.setHasFixedSize(true);
                    swedepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list3,getContext(),"Software Engineering");
                    swedepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ECEdepartment() {
        dbref = reference.child("Electronics and Communication Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if(!snapshot.exists()){
                    ECEnodata.setVisibility(View.VISIBLE);
                    ECEdepartment.setVisibility(View.GONE);
                } else
                {   ECEnodata.setVisibility(View.GONE);
                    ECEdepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot2: snapshot.getChildren()){
                       userteacherdata data = snapshot2.getValue(userteacherdata.class);
                        list2.add(data);
                    }
                    ECEdepartment.setHasFixedSize(true);
                    ECEdepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list2,getContext(),"Electronics and Communication Engineering");
                    ECEdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void csdepartment() {
        dbref = reference.child("Computer Science");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                if(!snapshot.exists()){
                    csnodata.setVisibility(View.VISIBLE);
                    csdepartment.setVisibility(View.GONE);
                } else
                {   csnodata.setVisibility(View.GONE);
                    csdepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        userteacherdata data = snapshot1.getValue(userteacherdata.class);
                        list1.add(data);
                    }
                    csdepartment.setHasFixedSize(true);
                    csdepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new userTeacherAdapter(list1,getContext(),"Computer Science");
                    csdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    }
