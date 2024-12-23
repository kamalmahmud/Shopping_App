package com.example.shopping_app.ViewModel;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.Repository.ItemLsitRepository;

import java.util.List;

public class ItemListViewModel extends ViewModel implements ItemLsitRepository.onFireStoreTaskComplete {
    private MutableLiveData<List<ItemListModel>> ItemListLiveData=new MutableLiveData<>();
    private ItemLsitRepository repository = new ItemLsitRepository(this);

    public MutableLiveData<List<ItemListModel>> getItemListLiveData(){
        return ItemListLiveData;
    }
    public ItemListViewModel(){
        repository.getItemsData();
    }

    @Override
    public void itemdataloaded(List<ItemListModel> itemListModelList) {
    ItemListLiveData.setValue(itemListModelList);
    }

    @Override
    public void onError(Exception e) {
        Log.d(TAG ,"Error : Data Not Loaded"+ e.getMessage());
    }
}
