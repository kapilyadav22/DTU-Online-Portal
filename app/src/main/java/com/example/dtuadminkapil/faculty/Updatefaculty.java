package com.example.dtuadminkapil.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dtuadminkapil.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Updatefaculty extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView csdepartment,ECEdepartment,swedepartment,ITdepartment,chemistrydepartment,
            physicsdepartment,biotechdepartment,eeedepartment,civildepartment,environmentdepartment,
            mechanicaldepartment;

    private LinearLayout csnodata,ECEnodata,swenodata,ITnodata,chemistrydepartmentnodata,physicsdepartmentnodata,
            biotechdepartmentnodata, eeedepartmentnodata, civildepartmentnodata, environmentdepartmentnodata,
            mechanicaldepartmentnodata;
     private List<teacherdata> list1,list2,list3,list4,list5,list6,list7,list8,list9,list10,list11;
     private TeacherAdapter adapter;

    private DatabaseReference reference, dbref;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatefaculty);

        csdepartment = findViewById(R.id.csdepartment);
        ECEdepartment = findViewById(R.id.ECEdepartment);
        swedepartment = findViewById(R.id.swedepartment);
        ITdepartment = findViewById(R.id.ITdepartment);
        chemistrydepartment = findViewById(R.id.chemistrydepartment);
        physicsdepartment = findViewById(R.id.physicsdepartment);
        biotechdepartment = findViewById(R.id.biotechdepartment);
        eeedepartment = findViewById(R.id.eeedepartment);
        civildepartment = findViewById(R.id.civildepartment);
        environmentdepartment = findViewById(R.id.environmentdepartment);
        mechanicaldepartment = findViewById(R.id.mechanicaldepartment);

        csnodata = findViewById(R.id.csnodata);
        ECEnodata = findViewById(R.id.ECEnodata);
        swenodata = findViewById(R.id.swenodata);
        ITnodata = findViewById(R.id.ITnodata);
        chemistrydepartmentnodata = findViewById(R.id.chemistrydepartmentnodata);
        physicsdepartmentnodata = findViewById(R.id.physicsdepartmentnodata);
        biotechdepartmentnodata = findViewById(R.id.biotechdepartmentnodata);
        eeedepartmentnodata = findViewById(R.id.eeedepartmentnodata);
        civildepartmentnodata = findViewById(R.id.civildepartmentnodata);
        mechanicaldepartmentnodata = findViewById(R.id.mechanicaldepartmentnodata);
        environmentdepartmentnodata = findViewById(R.id.environmentdepartmentnodata);

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

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Updatefaculty.this,Addteachers.class));
            }
        });
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
                        teacherdata data = snapshot11.getValue(teacherdata.class);
                        list11.add(data);
                    }
                    mechanicaldepartment.setHasFixedSize(true);
                    mechanicaldepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list11,Updatefaculty.this,"Mechanical Engineering");
                    mechanicaldepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot10.getValue(teacherdata.class);
                        list10.add(data);
                    }
                    environmentdepartment.setHasFixedSize(true);
                    environmentdepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list10,Updatefaculty.this,"Environment Engineering");
                    environmentdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot9.getValue(teacherdata.class);
                        list9.add(data);
                    }
                    civildepartment.setHasFixedSize(true);
                    civildepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list9,Updatefaculty.this,"Civil Engineering");
                    civildepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot8.getValue(teacherdata.class);
                        list8.add(data);
                    }
                    eeedepartment.setHasFixedSize(true);
                    eeedepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list8,Updatefaculty.this,"Electrical Engineering");
                    eeedepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot7.getValue(teacherdata.class);
                        list7.add(data);
                    }
                    biotechdepartment.setHasFixedSize(true);
                    biotechdepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list7,Updatefaculty.this,"Biotechnology");
                    biotechdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot6.getValue(teacherdata.class);
                        list6.add(data);
                    }
                    physicsdepartment.setHasFixedSize(true);
                    physicsdepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list6,Updatefaculty.this,"Applied Physics");
                    physicsdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot5.getValue(teacherdata.class);
                        list5.add(data);
                    }
                    chemistrydepartment.setHasFixedSize(true);
                    chemistrydepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list5,Updatefaculty.this,"Applied Chemistry");
                    chemistrydepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot4.getValue(teacherdata.class);
                        list4.add(data);
                    }
                    ITdepartment.setHasFixedSize(true);
                    ITdepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list4,Updatefaculty.this,"Information Technology");
                    ITdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot3.getValue(teacherdata.class);
                        list3.add(data);
                    }
                    swedepartment.setHasFixedSize(true);
                    swedepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list3,Updatefaculty.this,"Software Engineering");
                    swedepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        teacherdata data = snapshot2.getValue(teacherdata.class);
                        list2.add(data);
                    }
                    ECEdepartment.setHasFixedSize(true);
                    ECEdepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                    adapter = new TeacherAdapter(list2,Updatefaculty.this,"Electronics and Communication Engineering");
                    ECEdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
                    teacherdata data = snapshot1.getValue(teacherdata.class);
                    list1.add(data);
                }
                csdepartment.setHasFixedSize(true);
                csdepartment.setLayoutManager(new LinearLayoutManager(Updatefaculty.this));
                adapter = new TeacherAdapter(list1,Updatefaculty.this,"Computer Science");
                csdepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}