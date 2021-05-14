package com.example.iwimhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwimhub.Models.Professeur;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProfProfile extends AppCompatActivity {
    private final int PICK_IMAGE_REQUEST = 22;
    private static int i=0;
    private FirebaseUser fUser;
    private EditText nom;
    private EditText prenom;
    private EditText departement;
    private ImageView photoProfile;
    private Button save;
    private Uri imageUri;
    private Object url;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_profile);

        nom = findViewById(R.id.nomProfileProf);
        prenom = findViewById(R.id.prenomProfileProf);
        departement = findViewById(R.id.departementProfileProf);
        photoProfile = findViewById(R.id.imageProfileProf);
        save = findViewById(R.id.profileProfBtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                LinkedList<Professeur> professeurs = new LinkedList<Professeur>();

                CollectionReference profs = db.collection("professeurs");
                List<Task<Void>> futures = new ArrayList<>();
                futures.add(profs.document().set(new Professeur(nom.getText().toString(),prenom.getText().toString(),departement.getText().toString())));
                uploadImage();
            }
        });


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        photoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,
                resultCode,
                data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            imageUri = data.getData();
            try {
                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                imageUri);
                photoProfile.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

   private void uploadImage()
   {
       if (imageUri != null) {

           // Code for showing progressDialog while uploading
           ProgressDialog progressDialog
                   = new ProgressDialog(this);
           progressDialog.setTitle("Uploading...");
           progressDialog.show();

           // Defining the child of storageReference
           StorageReference ref
                   = storageReference
                   .child(
                           "images/"
                                   + UUID.randomUUID().toString());

           ref.putFile(imageUri)
                   .addOnSuccessListener(
                           new OnSuccessListener<UploadTask.TaskSnapshot>() {

                               @Override
                               public void onSuccess(
                                       UploadTask.TaskSnapshot taskSnapshot)
                               {

                                   // Image uploaded successfully
                                   // Dismiss dialog
                                   progressDialog.dismiss();
                                   Toast
                                           .makeText(ProfProfile.this,
                                                   "Image Uploaded!!",
                                                   Toast.LENGTH_SHORT)
                                           .show();
                                   url= storageReference.getDownloadUrl();
                               }
                           })

                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e)
                       {

                           // Error, Image not uploaded
                           progressDialog.dismiss();
                           Toast
                                   .makeText(ProfProfile.this,
                                           "Failed " + e.getMessage(),
                                           Toast.LENGTH_SHORT)
                                   .show();
                       }
                   })
                   .addOnProgressListener(
                           new OnProgressListener<UploadTask.TaskSnapshot>() {

                               // Progress Listener for loading
                               // percentage on the dialog box
                               @Override
                               public void onProgress(
                                       UploadTask.TaskSnapshot taskSnapshot)
                               {
                                   double progress
                                           = (100.0
                                           * taskSnapshot.getBytesTransferred()
                                           / taskSnapshot.getTotalByteCount());
                                   progressDialog.setMessage(
                                           "Uploaded "
                                                   + (int)progress + "%");
                               }
                           });
       }

   }
}