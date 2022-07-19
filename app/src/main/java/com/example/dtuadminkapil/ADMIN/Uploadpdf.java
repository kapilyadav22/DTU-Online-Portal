package com.example.dtuadminkapil.ADMIN;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.io.File;
import java.util.HashMap;

public class Uploadpdf extends AppCompatActivity {
    private final int REQ = 12;
    private CardView addpdf;
    private Uri pdfdata;
    private EditText pdftitle;
    private Button uploadpdfbutton;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    String downloadurl= "";
    private String pdfname,title;
    private ProgressDialog pd;
    private TextView pdftextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpdf);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        pd = new ProgressDialog(this);

        addpdf = findViewById(R.id.addpdf);
        pdftitle = findViewById(R.id.pdftitle);
        uploadpdfbutton = findViewById(R.id.uploadpdfbutton);
        pdftextview = findViewById((R.id.pdftext));

         addpdf.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openGallery(view);
             }
         });

        uploadpdfbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = pdftitle.getText().toString();
                if(title.isEmpty()){
                    pdftitle.setError("Empty");
                    pdftitle.requestFocus();
                } else if(pdfdata==null){
                    Toast.makeText(Uploadpdf.this," Please upload pdf",Toast.LENGTH_SHORT).show();
                } else {
                    uploadpdf();
                }
            }
        });
    }

    private void uploadpdf() {
        pd.setTitle("Please Wait...");
        pd.setMessage("Uploading Pdf");
        pd.show();
    StorageReference reference = storageReference.child(("pdf/"+ pdfname+"-"+System.currentTimeMillis())+".pdf");
    reference.putFile(pdfdata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            while(!uriTask.isComplete());
            Uri uri = uriTask.getResult();
            uploadData(String.valueOf(uri));
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            pd.dismiss();
            Toast.makeText(Uploadpdf.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
        }
    });
    }

    private void uploadData(String valueOf) {
        String uniqueKey = databaseReference.child("pdf").push().getKey();

        HashMap data = new HashMap();
        data.put("pdfTitle",title);
        data.put("pdfUrl",downloadurl);

        databaseReference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(Uploadpdf.this,"Pdf Uploaded Successfully",Toast.LENGTH_SHORT).show();
                pdftitle.getText().clear();
                pdfdata=null;
                pdftextview.setText("No file Uploaded");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Uploadpdf.this,"Failed To upload Pdf",Toast.LENGTH_SHORT).show();
            }
        });

    }
    ActivityResultLauncher<Intent> sactivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("Range")
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        pdfdata = data.getData();
                        Toast.makeText(Uploadpdf.this, "Pdf Selected", Toast.LENGTH_SHORT).show();
                        if (pdfdata.toString().startsWith("content://")) {
                            Cursor cursor = null;
                            try {
                                cursor = Uploadpdf.this.getContentResolver().query(pdfdata, null, null, null, null);
                                if (cursor != null && cursor.moveToFirst()) {
                                    pdfname = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (pdfdata.toString().startsWith("file://")) {
                            pdfname = new File(pdfdata.toString()).getName();
                        }
                        pdftextview.setText(pdfname);
                    }
                }
            });

    private void openGallery(View view) {
            Intent data = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            data.setType("application/pdf");
            data = Intent.createChooser(data,"Choose a file");
            sactivityResultLauncher.launch(data);
    }



}