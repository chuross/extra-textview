package com.github.chuross.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class ExtraTextView extends AppCompatTextView {

    private int drawableResourceId = 0;
    private int drawableWidth = 0;
    private int drawableHeight = 0;
    private DrawablePosition drawablePosition;
    private int drawableTint = 0;
    private boolean isDrawableFit = false;
    private final Paint roundedCornerBackgroundPaint = new Paint();
    private final Paint roundedCornerBorderPaint = new Paint();
    private final RectF roundedCornerRect = new RectF();
    private int roundedCornerRadius;
    private int roundedCornerBorderSize;
    private int roundedCornerBorderColor;
    private int roundedCornerBackgroundColor;

    private enum DrawablePosition {
        LEFT, TOP, RIGHT, BOTTOM
    }

    public ExtraTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ExtraTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ExtraTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExtraTextView, defStyleAttr, 0);


        drawableResourceId = typedArray.getResourceId(R.styleable.ExtraTextView_ext_txt_drawable, 0);
        drawableWidth = typedArray.getDimensionPixelSize(R.styleable.ExtraTextView_ext_txt_drawableWidth, 0);
        drawableHeight = typedArray.getDimensionPixelSize(R.styleable.ExtraTextView_ext_txt_drawableHeight, 0);
        drawablePosition = DrawablePosition.values()[typedArray.getInt(R.styleable.ExtraTextView_ext_txt_drawablePosition, DrawablePosition.LEFT.ordinal())];
        drawableTint = typedArray.getColor(R.styleable.ExtraTextView_ext_txt_drawableTint, Color.TRANSPARENT);
        isDrawableFit = typedArray.getBoolean(R.styleable.ExtraTextView_ext_txt_drawableFit, false);

        roundedCornerRadius = typedArray.getDimensionPixelSize(R.styleable.ExtraTextView_ext_txt_cornerRadius, 0);
        roundedCornerBorderSize = typedArray.getDimensionPixelSize(R.styleable.ExtraTextView_ext_txt_cornerBorderSize, 0);
        roundedCornerBorderColor = typedArray.getColor(R.styleable.ExtraTextView_ext_txt_cornerBorderColor, Color.TRANSPARENT);
        roundedCornerBackgroundColor = typedArray.getColor(R.styleable.ExtraTextView_ext_txt_cornerBackgroundColor, Color.TRANSPARENT);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        if (drawableResourceId == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        updateDrawable();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void updateDrawable() {
        final Drawable drawable = ResourcesCompat.getDrawable(getResources(), drawableResourceId, null);
        updateDrawable(drawable);
    }

    private void updateDrawable(final Drawable drawable) {
        if (drawable == null) {
            return;
        }

        Drawable target = DrawableCompat.wrap(drawable);
        if (drawableTint != Color.TRANSPARENT) {
            DrawableCompat.setTint(target.mutate(), drawableTint);
        }

        target.setBounds(0, 0, getDrawableWidth(), getDrawableHeight());
        Integer fitPadding = null;
        if (isDrawableFit) {
            float textWidth = getPaint().measureText(getText().toString());
            fitPadding = Math.round((getMeasuredWidth() - textWidth) / 2);
            setCompoundDrawablePadding(-fitPadding + getDrawableWidth());
        }

        switch(drawablePosition) {
            case TOP:
                setCompoundDrawables(null, target, null, null);
                break;
            case RIGHT:
                setCompoundDrawables(null, null, target, null);
                if (fitPadding != null) setPadding(0, 0, fitPadding, 0);
                break;
            case BOTTOM:
                setCompoundDrawables(null, null, null, target);
                break;
            default:
                setCompoundDrawables(target, null, null, null);
                if (fitPadding != null) setPadding(fitPadding, 0, 0, 0);
                break;
        }
    }

    private int getDrawableWidth() {
        return drawableWidth > 0 ? drawableWidth : getMeasuredWidth();
    }

    private int getDrawableHeight() {
        return drawableHeight > 0 ? drawableHeight : getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (needDrawRoundCorner()) drawRoundCorner(canvas);
        super.onDraw(canvas);
    }

    protected boolean needDrawRoundCorner() {
        return roundedCornerRadius > 0 && (roundedCornerBackgroundColor != Color.TRANSPARENT || roundedCornerBorderColor != Color.TRANSPARENT);
    }

    private void drawRoundCorner(Canvas canvas) {
        roundedCornerBackgroundPaint.reset();
        roundedCornerBorderPaint.reset();
        roundedCornerRect.setEmpty();

        roundedCornerRect.set(roundedCornerBorderSize, roundedCornerBorderSize, getMeasuredWidth() - roundedCornerBorderSize, getMeasuredHeight() - roundedCornerBorderSize);

        if (roundedCornerBackgroundColor != Color.TRANSPARENT) {
            roundedCornerBackgroundPaint.setAntiAlias(true);
            roundedCornerBackgroundPaint.setColor(roundedCornerBackgroundColor);
            roundedCornerBackgroundPaint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(roundedCornerRect, roundedCornerRadius, roundedCornerRadius, roundedCornerBackgroundPaint);
        }

        if (roundedCornerBorderColor != Color.TRANSPARENT) {
            roundedCornerBorderPaint.setAntiAlias(true);
            roundedCornerBorderPaint.setColor(roundedCornerBorderColor);
            roundedCornerBorderPaint.setStrokeWidth(roundedCornerBorderSize);
            roundedCornerBorderPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(roundedCornerRect, roundedCornerRadius, roundedCornerRadius, roundedCornerBorderPaint);
        }
    }

    public int getDrawableResourceId() {
        return drawableResourceId;
    }

    public void setDrawableResourceId(int drawableResourceId) {
        this.drawableResourceId =  drawableResourceId;
        requestLayout();
    }

    public void setDrawableTint(int drawableTint) {
        this.drawableTint = drawableTint;
        requestLayout();
    }

    public void setRoundedCornerBorderColor(int borderColor) {
        roundedCornerBorderColor = borderColor;
        invalidate();
    }

    public void setRoundedCornerBackgroundColor(int backgroundColor) {
        roundedCornerBackgroundColor = backgroundColor;
        invalidate();
    }
}
