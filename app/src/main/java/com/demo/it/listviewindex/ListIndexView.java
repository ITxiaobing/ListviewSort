package com.demo.it.listviewindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class ListIndexView extends View {
    /*绘制的列表导航字母*/
    private String words[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private Paint mBgPaint;
    private Context mContext;
    private Paint mTvPaint;
    private int mTouchIndex;
    private float mItemHeight;
    private int mItemWidth = 50;
    private SelectIndexListener mListener;

    public ListIndexView(Context context) {
        this(context, null);
    }

    public ListIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mItemHeight = MeasureSpec.getSize(heightMeasureSpec) - 20;
        mItemHeight = mItemHeight / 27;
        mItemWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    private void initPaint(Context context) {
        mContext = context;

        mContext = context;
        mBgPaint = new Paint();
        mBgPaint.setColor(Color.GREEN);
        mTvPaint = new Paint();
        mTvPaint.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {
            //画选中的索引和背景
            if (i == mTouchIndex) {
                mTvPaint.setColor(Color.YELLOW);
                canvas.drawCircle(mItemWidth / 2, mItemHeight / 2 + i * mItemHeight, mItemWidth / 2 - 10, mBgPaint);
            } else {
                mTvPaint.setColor(Color.GRAY);
            }
            //画出所有索引
            Rect rect = new Rect();
            mTvPaint.getTextBounds(words[i], 0, 1, rect);
            int wordWidth = rect.width();
            int wordHeight = rect.height();
            float wordX = mItemWidth / 2 - wordWidth / 2;
            float wordY = mItemHeight - wordHeight + i * mItemHeight;
            canvas.drawText(words[i], wordX, wordY, mTvPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                float x = event.getX();
                int index = (int) (y / mItemHeight);
                mTouchIndex = index;
                if (mListener != null && mTouchIndex < words.length && mTouchIndex >= 0 && x >= 0) {
                    mListener.selectIndex(words[mTouchIndex]);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null) {
                    mListener.hideSelect();
                }
                break;
        }
        return true;
    }

    public void setOnSelectIndex(SelectIndexListener listener) {
        mListener = listener;
    }

    public interface SelectIndexListener {
        void selectIndex(String index);

        void hideSelect();
    }

    public void setTouchIndex(String word) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                mTouchIndex = i;
                invalidate();
                return;
            }
        }
    }
}
