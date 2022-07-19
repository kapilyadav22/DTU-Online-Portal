package com.example.dtuadminkapil.notice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadActivity extends AppCompatActivity {
    private final int REQ = 1;
   private CardView Image;
   private Bitmap bitmap;
   private ImageView noticeimageview;
   private EditText noticetitle;
  private Button uploadbutton;
  private DatabaseReference reference,dbref;
  private StorageReference storageReference;
  String downloadurl= "";
 private ProgressDialog pd;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadactivity);

        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        pd = new ProgressDialog(this);

        Image = findViewById(R.id.addimage);
        noticeimageview = findViewById(R.id.imageView);
        noticetitle = findViewById(R.id.noticetitle);
        uploadbutton = findViewById(R.id.uploadbutton);

        Image.setOnClickListener(view -> openGallery());
         uploadbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(noticetitle.getText().toString().isEmpty())
                 {   noticetitle.setError("Please fill the title");
                     noticetitle.requestFocus();
                 } else if(bitmap==null) {
                     Toast.makeText(UploadActivity.this,"Please Upload Notice",Toast.LENGTH_SHORT).show();
                     //uploadData();
                 } else {
                     uploadImage();
                 }
             }
         });

    }

    private void uploadImage() {
      pd.setMessage("Uploading...");
      pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filepath;
        filepath = storageReference.child("Notice").child(finalimg+"jpg");
        final UploadTask uploadTask = filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UploadActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                        uploadData();
                                        noticetitle.getText().clear();
                                        noticeimageview.setImageBitmap(null);
                                        bitmap=null;
                                    }
                                });
                            }
                        });
                } else
                {   pd.dismiss();
                    Toast.makeText(UploadActivity.this,"Something went wrong", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    private void uploadData() {
      dbref =reference.child("Notice");
      final String uniquekey = dbref.push().getKey();
        Calendar calfordate = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("dd--MM--yy");
        String date = currentdate.format(calfordate.getTime());
        String title = noticetitle.getText().toString();

        Calendar calfortime = Calendar.getInstance();
        SimpleDateFormat currenttime = new SimpleDateFormat("hh:mm:a");
        String time = currenttime.format(calfortime.getTime());

        Noticedata noticedata = new Noticedata(title,downloadurl,date,time,uniquekey);
       dbref.child(uniquekey).setValue(noticedata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(UploadActivity.this,"Notice Uploaded",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            Toast.makeText(UploadActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
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
            noticeimageview.setImageBitmap(bitmap);

        }
}
}