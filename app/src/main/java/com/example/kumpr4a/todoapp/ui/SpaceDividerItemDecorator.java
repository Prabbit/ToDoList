package com.example.kumpr4a.todoapp.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by kumpr4a on 6/2/2017.
 */
public class SpaceDividerItemDecorator extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top =  Utility.dpToPx(12,parent.getContext());
        }
        outRect.bottom = Utility.dpToPx(12,parent.getContext());
    }
}