package com.example.shopping_app.Repository;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ItemLsitRepository {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference reference= firebaseFirestore.collection("Products");
    private onFireStoreTaskComplete onFireStoreTaskComplete;
    public ItemLsitRepository(onFireStoreTaskComplete onFireStoreTaskComplete) {
        this.onFireStoreTaskComplete=onFireStoreTaskComplete;
    }

    public void getItemsData(){
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    List<ItemListModel> itemListModelList=new ArrayList<>();
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        try {
                            ItemListModel item = queryDocumentSnapshot.toObject(ItemListModel.class);
                            item.setDocumentId(queryDocumentSnapshot.getId());
                            DownloadImageTask dimg=new DownloadImageTask();
                            Bitmap img= dimg.execute(queryDocumentSnapshot.getString("image")).get();
                            item.setImg(img);
                            itemListModelList.add(item);
                        }catch (Exception e){
                            Log.e("Error12356", "An error occurred", e);  // Correct way to log an error with an exception
                            e.printStackTrace();
                        }


                    }
                    onFireStoreTaskComplete.itemdataloaded(itemListModelList);
                }else{
                    onFireStoreTaskComplete.onError(task.getException());
                }
//                if(task.isSuccessful()){
//                    onFireStoreTaskComplete.itemdataloaded(task.getResult().toObjects(ItemListModel.class));
//                }else{
//                    onFireStoreTaskComplete.onError(task.getException());
//                }
            }
        }
        );
    }
    public interface onFireStoreTaskComplete {
        void itemdataloaded(List<ItemListModel> itemListModelList);
        void onError(Exception e);

    }
    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            String Url=urls[0];
            Bitmap bitImage = null;
            try {
                InputStream in = new java.net.URL(Url).openStream();
                Log.d("imager12","fff");
                bitImage=BitmapFactory.decodeStream(in);
                Log.d("imager34","fff");;
                in.close();
                return bitImage;


            }catch (UnknownHostException e) {
                Log.e("DownloadImageTask", "Unable to resolve host: " + Url, e);
                Bitmap imageerroe= BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.ioadingimg);
                return imageerroe;
            }catch (Exception e ){
                e.printStackTrace();
            }

            return bitImage;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
