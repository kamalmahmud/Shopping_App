package com.example.shopping_app.ViewModel;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.Repository.ItemLsitRepository;

import java.util.List;
import java.util.Objects;

public class  ItemListViewModel extends ViewModel implements ItemLsitRepository.onFireStoreTaskComplete {
    private static MutableLiveData<List<ItemListModel>> ItemListLiveData=new MutableLiveData<>();
    private ItemLsitRepository repository = new ItemLsitRepository(this);
    private static  List<ItemListModel> listModels;

    public static MutableLiveData<List<ItemListModel>> getItemListLiveData(){

        return ItemListLiveData;
    }
    public ItemListViewModel(){
        try {
            if (ItemListLiveData.getValue() == null) {
                repository.getItemsData();
            }
        }catch (Exception e){
            Log.e("Data Fetching in View Model",e.getMessage());
        }
    }
    public ItemListModel getItemById(String id){
        for (ItemListModel item : listModels){
            if(Objects.equals(item.getDocumentId(), id)){
                return item;
            }
        }
        return null;
    }

    @Override
    public void itemdataloaded(List<ItemListModel> itemListModelList) {

    ItemListLiveData.setValue(itemListModelList);
    listModels=itemListModelList;


    }

    @Override
    public void onError(Exception e) {
        Log.d(TAG ,"Error : Data Not Loaded"+ e.getMessage());
    }
}
