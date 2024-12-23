package com.example.shopping_app.Repository;

import androidx.annotation.NonNull;

import com.example.shopping_app.Model.ItemListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
                    onFireStoreTaskComplete.itemdataloaded(task.getResult().toObjects(ItemListModel.class));
                }else{
                    onFireStoreTaskComplete.onError(task.getException());
                }
            }
        }
        );
    }
    public interface onFireStoreTaskComplete {
        void itemdataloaded(List<ItemListModel> itemListModelList);
        void onError(Exception e);

    }
}
