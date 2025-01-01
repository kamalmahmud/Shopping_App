package com.example.shopping_app.Repository;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.shopping_app.Model.ItemListModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ItemLsitRepository {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firebaseFirestore.collection("Products");
    private onFireStoreTaskComplete onFireStoreTaskComplete;

    public ItemLsitRepository(onFireStoreTaskComplete onFireStoreTaskComplete) {
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;
    }

    public void getItemsData() {
        reference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<ItemListModel> itemListModelList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    try {
                        ItemListModel item = document.toObject(ItemListModel.class);
                        item.setDocumentId(document.getId());
                        // Store the image URL instead of downloading immediately
                        item.setImage(document.getString("image"));
                        itemListModelList.add(item);
                    } catch (Exception e) {
                        Log.e("Repository", "Error processing document", e);
                    }
                }
                onFireStoreTaskComplete.itemdataloaded(itemListModelList);
            } else {
                onFireStoreTaskComplete.onError(task.getException());
            }
        });
    }

    public interface onFireStoreTaskComplete {
        void itemdataloaded(List<ItemListModel> itemListModelList);
        void onError(Exception e);
    }
}