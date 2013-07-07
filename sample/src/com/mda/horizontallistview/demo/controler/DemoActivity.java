package com.mda.horizontallistview.demo.controler;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mda.horizontallistview.adapters.GroupViewHolder;
import com.mda.horizontallistview.demo.R;
import com.mda.horizontallistview.adapters.AbstractGroupAdapter;
import com.mda.horizontallistview.demo.model.dto.Cards;
import com.mda.horizontallistview.demo.model.dto.TypeTemplate;
import com.mda.horizontallistview.views.HorizontalVerticalListView;
import com.mda.horizontallistview.adapters.ViewHolder;
import com.mda.horizontallistview.demo.model.DataManager;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Dmitriy Manzhosov on 7/6/13.
 */
public class DemoActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        HorizontalVerticalListView listView = (HorizontalVerticalListView) findViewById(R.id.listView1);

        TreeMap<TypeTemplate, ArrayList<Cards>> data = DataManager.prepareData();

        AbstractGroupAdapter<TypeTemplate, Cards, TypeTemplateHolder, CardHolder>
                adapter = new AbstractGroupAdapter<TypeTemplate, Cards, TypeTemplateHolder, CardHolder>(this, data) {
            @Override
            public TypeTemplateHolder getItemViewHolder() {
                return new TypeTemplateHolder();
            }

            @Override
            public View getGroupView(int position, LayoutInflater inflater, ViewGroup parent, TypeTemplateHolder itemHolder) {
                View view = inflater.inflate(R.layout.group_item_layout, parent, false);
                itemHolder.mTitle = (TextView) view.findViewById(R.id.gallery_category_name);
                return view;
            }

            @Override
            public void updateGroupView(int position, TypeTemplateHolder itemHolder) {
                TextView title = itemHolder.mTitle;
                title.setText(getItem(position).mTitleTemplate);
            }

            @Override
            public CardHolder getSubgroupViewHolder() {
                return new CardHolder();
            }

            @Override
            public View getSubgroupView(int itemPosition, int subItemPosition, LayoutInflater inflater, ViewGroup parent, CardHolder subItemHolder) {
                View view = inflater.inflate(R.layout.subgroup_item_layout, parent, false);
                subItemHolder.mName = (TextView) view.findViewById(R.id.card_name);
                subItemHolder.mDescription = (TextView) view.findViewById(R.id.card_description);
                return view;
            }

            @Override
            public void updateSubgroupView(int itemPosition, int subItemPosition, CardHolder subItemHolder) {
                subItemHolder.mName.setText(getSubItem(itemPosition, subItemPosition).name);
                subItemHolder.mDescription.setText(getSubItem(itemPosition, subItemPosition).description);
            }
        };

        listView.setAdapter(adapter);
    }

    private static class TypeTemplateHolder extends GroupViewHolder {
        TextView mTitle;
    }

    private static class CardHolder extends ViewHolder {
        TextView mName;
        TextView mDescription;
    }
}
