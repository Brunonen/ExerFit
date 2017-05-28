package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;

import com.example.bruno.exerfit.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 28/05/2017.
 */

public class DrawableResourceScanner {

    List<Drawable> drawables = new ArrayList<>();
    Context currentContext;

    private Map<Integer, String> imageMap = new ArrayMap<>();

    public DrawableResourceScanner(Context current){
        currentContext = current;
        loadAllResources();
    }

    private void loadAllResources(){

        for(int identifier = (R.drawable.aaaa + 1);
            identifier <= (R.drawable.zzzz - 1);
            identifier++) {
            String name = currentContext.getResources().getResourceEntryName(identifier);

            imageMap.put(identifier, name);
            //name is the file name without the extension, indentifier is the resource ID
        }

    }

    public int getImageResourceByName(String imageName){

        for(Integer key : imageMap.keySet()){
            if(imageMap.get(key).equals(imageName)){
                return key;
            }
        }

        //IF no key was available
        for(Integer key : imageMap.keySet()){
            if(imageMap.get(key).equals("nopicture")){
                return key;
            }
        }

        return 0;
    }

    public void printAllDrawables(){
        /*for(Drawable draw : drawables){
            System.out.println(draw.get)
        }*/
    }
}
