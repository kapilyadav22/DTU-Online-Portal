package com.example.dtuadminkapil.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dtuadminkapil.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Addteachers extends AppCompatActivity {
   private ImageView addteacherimage;
   private EditText addteachername,addteachemail,addteacherpost;
   private Spinner addteachercategory;
   private Button addteacherbutton;
   private final int REQ=1;
   private Bitmap bitmap = null;
   private String category;
    private ProgressDialog pd;
    private DatabaseReference reference,dbref;
    private StorageReference storageReference;
   private String name, email, post, downloadurl="";
    String[] items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteachers);
        addteacherimage = findViewById(R.id.teacherimage);
        addteachername = findViewById(R.id.addteachername);
        addteachemail = findViewById(R.id.addteacheremail);
        addteacherpost = findViewById((R.id.addteacherpost));
        addteachercategory = findViewById(R.id.addteachercategory);
        addteacherbutton = findViewById(R.id.addteaherbutton);
        pd = new ProgressDialog(this);
        reference = FirebaseDatabase.getInstance().getReference().child("Teacher");;
        storageReference = FirebaseStorage.getInstance().getReference();


        /*
        "Select Category","Computer Science","Artificial Intelligence","Software Engineering","Information System",
                " Microwave and Optical Communication", "Signal Processing & Digital Design","VLSI Design and Embedded Systems",
                "Control & Instrumentation", "Power System","Environmental Engineering", "Production Engineering","Thermal Engineering",
                "Industrial Engineering", "Data Science","Polymer Technology", "Nano Science & Technology", " Bioinformatics",
                "Geotechnical Engineering","Hydraulics & Water Resources Engineering", "Structural Engineering","Geoinformatics Engineering"
        * */
        items = new String[]{"Select Category","Computer Science","Software Engineering","Information Technology",
                "Electronics and Communication Engineering","Applied Chemistry","Applied Physics","Biotechnology",
                "Civil Engineering", "Electrical Engineering","Environment Engineering"," Mechanical Engineering"
        };
        addteachercategory.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));

        addteachercategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category =  addteachercategory.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addteacherbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkvalidation();
            }
        });
        addteacherimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    }
    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK);
        pickImage.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK)
        { Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addteacherimage.setImageBitmap(bitmap);

        }
    }

    private void checkvalidation() {
        name = addteachername.getText().toString();
        email = addteachemail.getText().toString();
        post=addteacherpost.getText().toString();

        if(name.isEmpty())
        {   addteachername.setError("Empty");
            addteachername.requestFocus();
        } else if(email.isEmpty())
        {   addteachemail.setError("Empty");
            addteachemail.requestFocus();
        } else if(post.isEmpty())
        {   addteacherpost.setError("Empty");
            addteacherpost.requestFocus();
        } else if(category.equals("Select Category"))
        { Toast.makeText(Addteachers.this,"Please Select image category",Toast.LENGTH_SHORT).show();
        }  else if(bitmap==null) {
            Toast.makeText(Addteachers.this, "Please Upload an image", Toast.LENGTH_SHORT).show();}
            else{
                pd.setMessage("Uploading...");
            pd.show();
                insertImage();
            }

    }

    private void insertImage() {
        pd.setMessage("Uploading...");
        pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filepath;
        filepath = storageReference.child(finalimg+"jpg");
        final UploadTask uploadTask = filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(Addteachers.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadurl = String.valueOf(uri);
                                    insertData();
                                    addteachername.getText().clear();
                                    addteachemail.getText().clear();
                                    addteacherpost.getText().clear();
                                    addteacherimage.setImageBitmap(null);
                                    bitmap=null;

                                }
                            });
                        }
                    });
                } else
                {   pd.dismiss();
                    Toast.makeText(Addteachers.this,"Something went wrong", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    private void insertData() {
        dbref=reference.child(category);
        final String uniquekey = dbref.push().getKey();


        teacherdata teacherdata = new teacherdata(name,email,post,downloadurl,uniquekey);
        dbref.child(uniquekey).setValue(teacherdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(Addteachers.this,"Teacher Added",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Addteachers.this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}