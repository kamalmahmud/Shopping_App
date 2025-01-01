package com.example.shopping_app.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.example.shopping_app.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ItemListModel {
    private String name,description,image,price;
    private String DocumentId;
    Bitmap img;

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(String documentId) {
        DocumentId = documentId;
    }

    public ItemListModel() {


    }



    public void downloadimage(){
//        Bitmap img = null;
//        try {
//
//            Log.println(Log.ASSERT,"DownloadStrated","ex");
//            URL imageurl= new URL(image);
//            URLConnection urlconnection = imageurl.openConnection();
//            urlconnection.connect();
//            InputStream inputStream= new BufferedInputStream(imageurl.openStream(),8192);
//            img= BitmapFactory.decodeStream(inputStream);
//            inputStream.close();
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
        try {
            Log.println(Log.WARN,"Hiiii","ggfg");
            URL strUrl = new URL(image);
            Log.println(Log.WARN,"Hiiillllli5","ggfg");
            URLConnection connection = strUrl.openConnection();
            Log.println(Log.WARN,"Hiiillllli4","ggfg");
            connection.connect();
            Log.println(Log.WARN,"Hiiillllli3","ggfg");
            InputStream inputstream = new BufferedInputStream(strUrl.openStream(), 8192);
            Log.println(Log.WARN,"Hiiillllli2","ggfg");
            img=BitmapFactory.decodeStream(inputstream);
            Log.println(Log.WARN,"Hiiillllli1","ggfg");
            inputstream.close();
            Log.println(Log.WARN,"Hiiillllli","ggfg");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("ggggggggg",e.getMessage());
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemListModel(String name, String description, String image, String price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;




    }
}
