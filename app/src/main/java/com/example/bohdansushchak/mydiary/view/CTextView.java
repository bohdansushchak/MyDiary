package com.example.bohdansushchak.mydiary.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.example.bohdansushchak.mydiary.R;

public class CTextView extends android.support.v7.widget.AppCompatTextView {

    private String fontPath;

    public CTextView(Context context) {
        super(context);
        init();
    }

    public CTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        fontPath = getContext().getString(R.string.font_path);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), fontPath);
        this.setTypeface(font);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        fontPath = getContext().getString(R.string.font_path);
        tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        fontPath = getContext().getString(R.string.font_path);
        tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);
        super.setTypeface(tf);
    }
}
