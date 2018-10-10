package com.ms.square.android.expandabletextview.sample.phong;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Phong Huynh on 10/10/2018.
 * depend on short text and long text to get the height of textview to animated between them
 */
public class CustomExpandableTextView extends AppCompatTextView {
    private boolean mIsExpanded = false;
    @Nullable
    private String mShortText;
    @Nullable
    private String mLongText;
    private int mCollapseHeight;
    private int mExpandHeight;

    private Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private long mAnimationDuration = 300;
    private boolean mAnimating = false;
    private boolean mInitialized = false;
    private int mFullHeight;

    // TODO: 10/10/2018 set scrollable for text view when height is too big
    public CustomExpandableTextView(Context context) {
        super(context);
    }

    public CustomExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mFullHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    public void toggle() {
        if (!mInitialized) {
            StaticLayout layout = new StaticLayout(mShortText, getPaint(), getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f,
                    false);
            mCollapseHeight = layout.getHeight();
            Log.e("TAG", "collapse height bound " + mCollapseHeight);

            StaticLayout layout2 = new StaticLayout(mLongText, getPaint(), getWidth(), Layout.Alignment.ALIGN_NORMAL,
                    1.0f, 0.0f, false);
            mExpandHeight = layout2.getHeight();

            Log.e("TAG", "expand height bound " + mExpandHeight);
//        mExpandHeight = Math.min(mBound.height(), );
            mInitialized = true;
        }

        if (mIsExpanded) {
            collapse();
        } else {
            expand();
        }
    }

    /**
     * need to set text for calculate the height of collapsed textview
     */
    public void setCollapseText(String text) {
        mShortText = text;
    }

    /**
     * need to set text for calculate the height of expanded textview
     */
    public void setExpandText(String text) {
        mLongText = text;
    }

    private void expand() {
        if (mAnimating) return;
        mAnimating = true;
        setText(mLongText);

        final ValueAnimator valueAnimator = ValueAnimator.ofInt(getHeight(), mExpandHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                setHeight((int) animation.getAnimatedValue());
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                mIsExpanded = true;
                mAnimating = false;
            }
        });

        valueAnimator.setInterpolator(mInterpolator);
        valueAnimator
                .setDuration(mAnimationDuration)
                .start();
    }

    private void collapse() {
        if (mAnimating) return;
        mAnimating = true;

        // animate from collapsed height to expanded height
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(getHeight(), mCollapseHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                setHeight((int) animation.getAnimatedValue());
            }
        });

        // wait for the animation to end to prevent clicking fast
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                mIsExpanded = false;
                mAnimating = false;
                setText(mShortText);
            }
        });

        valueAnimator.setInterpolator(mInterpolator);
        valueAnimator
                .setDuration(mAnimationDuration)
                .start();
    }
}
