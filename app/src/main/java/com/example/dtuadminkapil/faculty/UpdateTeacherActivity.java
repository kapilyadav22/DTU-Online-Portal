package com.example.dtuadminkapil.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateTeacherActivity extends AppCompatActivity {
    private static final int REQ = 1;
    private ImageView updateTeacherImage;
    private EditText updateTeacherName,updateTeacherEmail, updateTeacherPost;
    private String name,email,image,post;
    private  Bitmap bitmap ;
    private StorageReference storageReference;
    private DatabaseReference reference;

    private String downloadurl="", category, uniquekey="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        image= getIntent().getStringExtra("image");
        post= getIntent().getStringExtra("post");


        updateTeacherImage = findViewById(R.id.updateTeacherImage);
        updateTeacherName = findViewById(R.id.updateTeacherName);
        updateTeacherEmail = findViewById(R.id.updateTeacherEmail);
        updateTeacherPost = findViewById(R.id.updateTeacherPost);
        Button updateTeacherBtn = findViewById(R.id.updateTeacherBtn);
        Button deleteTeacherBtn = findViewById((R.id.deleteTeacherBtn));

        reference = FirebaseDatabase.getInstance().getReference().child("Teacher");;
        storageReference = FirebaseStorage.getInstance().getReference();


        try {
            Picasso.get().load(image).into(updateTeacherImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateTeacherEmail.setText(email);
        updateTeacherName.setText(name);
        updateTeacherPost.setText(post);

        updateTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        updateTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = updateTeacherName.getText().toString();
                email = updateTeacherEmail.getText().toString();
                post = updateTeacherPost.getText().toString();

                checkvalidation();
            }
        });

        deleteTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTeacherActivity.this);
                builder.setMessage("Do you want to delete this notice?");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteData();
                            }

                        });

                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog=null;
                try {
                    dialog = builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(dialog!=null)
                    dialog.show();

            }
        });


    }
    private void deleteData() {
        category = getIntent().getStringExtra("category");
        uniquekey = getIntent().getStringExtra("key");
        reference.child(category).child(uniquekey).removeValue().
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UpdateTeacherActivity.this,"Teacher Deleted Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateTeacherActivity.this,Updatefaculty.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateTeacherActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkvalidation() {
        if(name.isEmpty()){
            updateTeacherName.setError("Empty");
            updateTeacherName.requestFocus();
        } else if(post.isEmpty()){
            updateTeacherPost.setError("Empty");
            updateTeacherPost.requestFocus();
        } else if(email.isEmpty()){
            updateTeacherEmail.setError("Empty");
            updateTeacherEmail .requestFocus();
        } else if(bitmap==null){
            updateData(image);
        } else {
            uploadImage();
        }
    }

    private void updateData(String s) {
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("name", name);
        hp.put("email", email);
        hp.put("post",post);
        hp.put("image", s);

        category = getIntent().getStringExtra("category");
        uniquekey = getIntent().getStringExtra("key");
        reference.child(category).child(uniquekey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateTeacherActivity.this,"Teacher Updated Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateTeacherActivity.this,Updatefaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            Toast.makeText(UpdateTeacherActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void uploadImage() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filepath;
        filepath = storageReference.child(finalimg+"jpg");
        final UploadTask uploadTask = filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UpdateTeacherActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    updateData(downloadurl);
                                }
                            });
                        }
                    });
                } else
                { Toast.makeText(UpdateTeacherActivity.this,"Something went wrong", Toast.LENGTH_SHORT);
                }
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
        {
            assert data != null;
            Uri uri = data.getData();


            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateTeacherImage.setImageBitmap(bitmap);

        }
    }

}