package com.mda.grouplistview.demo.model;

import com.mda.grouplistview.demo.model.dto.Cards;
import com.mda.grouplistview.demo.model.dto.TypeTemplate;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Dmitriy Manzhosov on 7/6/13.
 */
public class DataManager {
    public static TreeMap<TypeTemplate, ArrayList<Cards>> prepareData(){
        TreeMap<TypeTemplate, ArrayList<Cards>> data = new TreeMap<TypeTemplate, ArrayList<Cards>>();

        Random randomGenerator = new Random();
        int types = randomGenerator.nextInt(30);
        for (int i = 0; i < types; i++){
            TypeTemplate typeTemplate = new TypeTemplate("TypeTemplate" + i);

            ArrayList<Cards> cardses = new ArrayList<Cards>();
            int cards = randomGenerator.nextInt(30);
            for(int j = 0; j <= cards ; j++){
                Cards card = new Cards("CardName" + j, "CardDescription" + j);
                cardses.add(card);
            }

            data.put(typeTemplate, cardses);
        }
        return data;
    }
}
