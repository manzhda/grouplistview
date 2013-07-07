package com.mda.grouplistview.demo.model.dto;

/**
 * Created by Dmitriy Manzhosov on 7/6/13.
 */
public class TypeTemplate implements Comparable<TypeTemplate> {
    public String mTitleTemplate;

    public TypeTemplate(String titleTemplate) {
        mTitleTemplate = titleTemplate;
    }

    @Override
    public int compareTo(TypeTemplate another) {
        return mTitleTemplate.compareTo(another.mTitleTemplate);
    }
}
