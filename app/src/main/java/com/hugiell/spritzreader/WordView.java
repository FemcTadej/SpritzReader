package com.hugiell.spritzreader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;

public class WordView extends View {
    public static final float TEXT_SIZE = 120;
    private TextPaint mTextPaint = new TextPaint();
    private StaticLayout mLayout;
    private int canvasCenterHoriz;
    private int canvasCenterVert;
    private String mWord = "";
    private int mCenterCharacterIndex = 0;

    public WordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTextPaint.setTextSize(TEXT_SIZE);
        mTextPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasCenterHoriz = canvas.getWidth()/2;
        canvasCenterVert = canvas.getHeight()/2;
        Spannable wordSpannable = new SpannableString(mWord);
        if(mWord.length() > 0) {
            wordSpannable.setSpan(new ForegroundColorSpan(Color.RED), mCenterCharacterIndex, mCenterCharacterIndex+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // calculate width of text before red character and after
            float leadingTextWidth = mTextPaint.measureText(mWord, 0, mCenterCharacterIndex);
            float trailingTextWidth = mTextPaint.measureText(mWord, mCenterCharacterIndex+1, mWord.length());
            // if bias is positive, leadingText is wider than trailingText and vice versa
            float bias = leadingTextWidth - trailingTextWidth;
            mLayout = new StaticLayout(wordSpannable, mTextPaint, getWidth(), Layout.Alignment.ALIGN_CENTER, 1, 0, false);
            // shift the canvas so that red character is always centered
            canvas.translate(-bias/2, canvasCenterVert -(mLayout.getHeight()/2));
            mLayout.draw(canvas);
        }
    }

    public void updateWord(final String word, final int centerCharacterIndex) {
        mWord = word;
        mCenterCharacterIndex = centerCharacterIndex;
        // refresh View
        this.invalidate();
    }

}
