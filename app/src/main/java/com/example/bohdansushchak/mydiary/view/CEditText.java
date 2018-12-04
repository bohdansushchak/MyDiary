package com.example.bohdansushchak.mydiary.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import com.example.bohdansushchak.mydiary.R;

public class CEditText extends AppCompatEditText {

    private String fontPath;

    public CEditText(Context context) {
        super(context);
        init();
    }

    public CEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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