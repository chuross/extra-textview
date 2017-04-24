package com.github.chuross.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class ToggleExtraTextView extends ExtraTextView {

    public enum State {
        ACTIVE, WAITING, IDLE
    }

    private State state;
    private int activeDrawableResourceId;
    private int activeTint;
    private int waitingTint;
    private int idleDrawableResourceId;
    private int idleTint;

    public ToggleExtraTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ToggleExtraTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToggleExtraTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToggleExtraTextView, defStyleAttr, 0);

        state = State.values()[typedArray.getInt(R.styleable.ToggleExtraTextView_ext_txt_state, State.IDLE.ordinal())];

        activeDrawableResourceId = typedArray.getResourceId(R.styleable.ToggleExtraTextView_ext_txt_activeDrawable, getDrawableResourceId());
        activeTint = typedArray.getColor(R.styleable.ToggleExtraTextView_ext_txt_activeTint, 0);

        waitingTint = typedArray.getColor(R.styleable.ToggleExtraTextView_ext_txt_waitingTint, Color.DKGRAY);

        idleDrawableResourceId = typedArray.getResourceId(R.styleable.ToggleExtraTextView_ext_txt_idleDrawable, getDrawableResourceId());
        idleTint = typedArray.getColor(R.styleable.ToggleExtraTextView_ext_txt_idleTint, 0);

        typedArray.recycle();

        updateState();
    }

    private void updateState() {
        switch (state) {
            case ACTIVE:
                setTextColor(activeTint);
                setRoundedCornerBorderColor(activeTint);
                setDrawableTint(activeTint);
                setDrawableResourceId(activeDrawableResourceId);
                setEnabled(true);
                requestLayout();
                break;
            case WAITING:
                setTextColor(waitingTint);
                setRoundedCornerBorderColor(waitingTint);
                setDrawableTint(waitingTint);
                setEnabled(false);
                requestLayout();
                break;
            case IDLE:
                setTextColor(idleTint);
                setRoundedCornerBorderColor(idleTint);
                setDrawableTint(idleTint);
                setDrawableResourceId(idleDrawableResourceId);
                setEnabled(true);
                requestLayout();
                break;
            default: throw new IllegalStateException("state is not found.");
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        updateState();
    }

    public void toggle() {
        if (state.equals(State.ACTIVE)) {
            setState(State.IDLE);
        } else if (state.equals(State.IDLE)) {
            setState(State.ACTIVE);
        }
    }
}
