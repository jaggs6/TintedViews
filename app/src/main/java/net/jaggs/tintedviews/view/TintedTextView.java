package net.jaggs.tintedviews.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.TextView;
import net.jaggs.tintedviews.R;

import static net.jaggs.tintedviews.view.TintedViewUtils.*;

public class TintedTextView extends TextView {

	private int drawableTint;

	private int leftDrawableTint;
	private int topDrawableTint;
	private int rightDrawableTint;
	private int bottomDrawableTint;

	private int highlightDrawableTint;
	private int leftHighlightDrawableTint;
	private int topHighlightDrawableTint;
	private int rightHighlightDrawableTint;
	private int bottomHighlightDrawableTint;

	private ColorStateList colorState;

	public TintedTextView(Context context) {
		super(new ContextThemeWrapper(context, R.style.TintedTextViewStyle));
	}

	public TintedTextView(Context context, AttributeSet attrs) {
		super(new ContextThemeWrapper(context, R.style.TintedTextViewStyle), attrs);
		initContent(context, attrs);
	}

	public TintedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(new ContextThemeWrapper(context, R.style.TintedTextViewStyle), attrs, defStyleAttr);
		initContent(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public TintedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(new ContextThemeWrapper(context, R.style.TintedTextViewStyle), attrs, defStyleAttr, defStyleRes);
		initContent(context, attrs);
	}

	private void initContent(Context context, AttributeSet attrs) {
		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TintedTextView);
		try {
			drawableTint = a.getColor(R.styleable.TintedTextView_allDrawableColor, DEFAULT_TINT(context));
			leftDrawableTint = a.getColor(R.styleable.TintedTextView_leftDrawableColor, DEFAULT_TINT(context));
			topDrawableTint = a.getColor(R.styleable.TintedTextView_topDrawableColor, DEFAULT_TINT(context));
			rightDrawableTint = a.getColor(R.styleable.TintedTextView_rightDrawableColor, DEFAULT_TINT(context));
			bottomDrawableTint = a.getColor(R.styleable.TintedTextView_bottomDrawableColor, DEFAULT_TINT(context));

			highlightDrawableTint = a.getColor(R.styleable.TintedTextView_allDrawableHighlightColor, DEFAULT_HIGHLIGHT_TINT(context));
			leftHighlightDrawableTint = a.getColor(R.styleable.TintedTextView_leftDrawableHighlightColor, DEFAULT_HIGHLIGHT_TINT(context));
			topHighlightDrawableTint = a.getColor(R.styleable.TintedTextView_topDrawableHighlightColor, DEFAULT_HIGHLIGHT_TINT(context));
			rightHighlightDrawableTint = a.getColor(R.styleable.TintedTextView_rightDrawableHighlightColor, DEFAULT_HIGHLIGHT_TINT(context));
			bottomHighlightDrawableTint = a.getColor(R.styleable.TintedTextView_bottomDrawableHighlightColor, DEFAULT_HIGHLIGHT_TINT(context));
		} finally {
			a.recycle();
		}
		paintDrawables();
	}

	private void paintDrawables() {
		colorState = makeTintedButtonColorStateViaLayout(getContext());
		updateTintColor();
	}

	public int getDrawableTint() {
		return drawableTint;
	}

	public void setDrawableTint(@ColorInt int drawableTint) {
		this.drawableTint = drawableTint;
		paintDrawables();
	}

	public int getLeftDrawableTint() {
		return leftDrawableTint;
	}

	public void setLeftDrawableTint(@ColorInt int leftDrawableTint) {
		this.leftDrawableTint = leftDrawableTint;
		paintDrawables();
	}

	public int getTopDrawableTint() {
		return topDrawableTint;
	}

	public void setTopDrawableTint(@ColorInt int topDrawableTint) {
		this.topDrawableTint = topDrawableTint;
		paintDrawables();
	}

	public int getRightDrawableTint() {
		return rightDrawableTint;
	}

	public void setRightDrawableTint(@ColorInt int rightDrawableTint) {
		this.rightDrawableTint = rightDrawableTint;
		paintDrawables();
	}

	public int getBottomDrawableTint() {
		return bottomDrawableTint;
	}

	public void setBottomDrawableTint(@ColorInt int bottomDrawableTint) {
		this.bottomDrawableTint = bottomDrawableTint;
		paintDrawables();
	}

	public int getHighlightDrawableTint() {
		return highlightDrawableTint;
	}

	public void setHighlightDrawableTint(@ColorInt int highlightDrawableTint) {
		this.highlightDrawableTint = highlightDrawableTint;
		paintDrawables();
	}

	public int getLeftHighlightDrawableTint() {
		return leftHighlightDrawableTint;
	}

	public void setLeftHighlightDrawableTint(int leftHighlightDrawableTint) {
		this.leftHighlightDrawableTint = leftHighlightDrawableTint;
	}

	public int getTopHighlightDrawableTint() {
		return topHighlightDrawableTint;
	}

	public void setTopHighlightDrawableTint(int topHighlightDrawableTint) {
		this.topHighlightDrawableTint = topHighlightDrawableTint;
	}

	public int getRightHighlightDrawableTint() {
		return rightHighlightDrawableTint;
	}

	public void setRightHighlightDrawableTint(int rightHighlightDrawableTint) {
		this.rightHighlightDrawableTint = rightHighlightDrawableTint;
	}

	public int getBottomHighlightDrawableTint() {
		return bottomHighlightDrawableTint;
	}

	public void setBottomHighlightDrawableTint(int bottomHighlightDrawableTint) {
		this.bottomHighlightDrawableTint = bottomHighlightDrawableTint;
	}

	public ColorStateList getColorState() {
		return colorState;
	}

	public void setColorState(ColorStateList colorState) {
		this.colorState = colorState;
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		if (colorState != null && colorState.isStateful()) {
			updateTintColor();
		}
	}

	private void updateTintColor() {
		int stateColor = colorState.getColorForState(getDrawableState(), DEFAULT_TINT(getContext()));
		Drawable[] compoundDrawables = getCompoundDrawables();
		compoundDrawables[0].mutate().setColorFilter(makeTintedColorViaLayout(leftDrawableTint, leftHighlightDrawableTint, drawableTint, highlightDrawableTint, stateColor, getContext()), PorterDuff.Mode.SRC_IN);
		compoundDrawables[1].mutate().setColorFilter(makeTintedColorViaLayout(topDrawableTint, topHighlightDrawableTint, drawableTint, highlightDrawableTint, stateColor, getContext()), PorterDuff.Mode.SRC_IN);
		compoundDrawables[2].mutate().setColorFilter(makeTintedColorViaLayout(rightDrawableTint, rightHighlightDrawableTint, drawableTint, highlightDrawableTint, stateColor, getContext()), PorterDuff.Mode.SRC_IN);
		compoundDrawables[3].mutate().setColorFilter(makeTintedColorViaLayout(bottomDrawableTint, bottomHighlightDrawableTint, drawableTint, highlightDrawableTint, stateColor, getContext()), PorterDuff.Mode.SRC_IN);
	}
}
