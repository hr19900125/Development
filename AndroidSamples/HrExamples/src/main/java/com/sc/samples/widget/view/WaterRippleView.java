package com.sc.samples.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.sc.samples.R;

/**
 *
 */

public class WaterRippleView extends View {

    private boolean mRunning = false;
    private int[] mStrokeWidthArr;
    private int mMaxStrokeWidth;
    private int mRippleCount;
    private int mRippleSpacing;
    private Paint mPaint;
    //    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;

    public WaterRippleView(Context context) {
        this(context, null);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaterRippleView);
        int waveColor = typedArray.getColor(R.styleable.WaterRippleView_myRippleColor,
                ContextCompat.getColor(context, R.color.colorPrimary));
        Drawable drawable = typedArray.getDrawable(R.styleable.WaterRippleView_myRippleCenterIcon);
        mRippleCount = typedArray.getInt(R.styleable.WaterRippleView_myRippleCount, 2);
        mRippleSpacing = typedArray.getDimensionPixelSize(R.styleable.WaterRippleView_myRippleSpacing,
                16);
        mRunning = typedArray.getBoolean(R.styleable.WaterRippleView_myRippleAutoRunning, false);
        typedArray.recycle();
//        mBitmap = ((BitmapDrawable) drawable).getBitmap();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(waveColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (mRippleCount * mRippleSpacing) * 2;
        mWidth = resolveSize(size, widthMeasureSpec);
        mHeight = resolveSize(size, heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        mMaxStrokeWidth = (mWidth) / 2;
        initArray();
    }

    private void initArray() {
        mStrokeWidthArr = new int[mRippleCount];
        for (int i = 0; i < mStrokeWidthArr.length; i++) {
            mStrokeWidthArr[i] = -mMaxStrokeWidth / mRippleCount * i;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        drawBitmap(canvas);
        if (mRunning) {
            drawRipple(canvas);
            postInvalidateDelayed(50);
        }
    }

    private void drawBitmap(Canvas canvas) {
//        int left = (mWidth - mBitmap.getWidth()) / 2;
//        int top = (mHeight - mBitmap.getHeight()) / 2;
//        canvas.drawBitmap(mBitmap, left, top, null);
    }

    private void drawRipple(Canvas canvas) {
        for (int strokeWidth : mStrokeWidthArr) {
            if (strokeWidth < 0) {
                continue;
            }
            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setAlpha(255 - 255 * strokeWidth / mMaxStrokeWidth);
            RectF rectF = new RectF((mWidth - strokeWidth) / 2, (mHeight - strokeWidth) / 2, (mWidth + strokeWidth) / 2,  (mHeight + strokeWidth) / 2);
//            canvas.drawCircle(mWidth / 2, mHeight / 2, strokeWidth / 2,
//                    mPaint);
            canvas.drawArc(rectF, 180, 180, false, mPaint);
        }
        for (int i = 0; i < mStrokeWidthArr.length; i++) {
            if ((mStrokeWidthArr[i] += 4) > mMaxStrokeWidth) {
                mStrokeWidthArr[i] = 0;
            }
        }
    }

    public void start() {
        mRunning = true;
        postInvalidate();
    }

    public void stop() {
        mRunning = false;
        initArray();
        postInvalidate();
    }

}
