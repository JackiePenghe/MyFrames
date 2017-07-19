package com.penghe.www.fabnavagationview;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

/**
 * Created by pengh on 2017/7/18.
 *
 */

public class FabNavigationView extends FloatingActionButton {
    public FabNavigationView(Context context) {
        this(context,null);
    }

    public FabNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FabNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initial();
    }

    private void initial() {
        
    }
}
