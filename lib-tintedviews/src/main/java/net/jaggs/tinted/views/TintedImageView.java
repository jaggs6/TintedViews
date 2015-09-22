package net.jaggs.tinted.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;

import static net.jaggs.tinted.views.TintedViewUtils.*;

public class TintedImageView extends ImageView {

	private int tintColor;
	private int highlightTintColor;
	private ColorStateList colorState;

	public TintedImageView(Context context) {
		super(new ContextThemeWrapper(context, R.style.TintedDrawableImageViewStyle));
	}

	public TintedImageView(Context context, AttributeSet attrs) {
		super(new ContextThemeWrapper(context, R.style.TintedDrawableImageViewStyle), attrs);
		initContent(context, attrs);
	}

	public TintedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(new ContextThemeWrapper(context, R.style.TintedDrawableImageViewStyle), attrs, defStyleAttr);
		initContent(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public TintedImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(new ContextThemeWrapper(context, R.style.TintedDrawableImageViewStyle), attrs, defStyleAttr, defStyleRes);
		initContent(context, attrs);
	}

	private void initContent(Context context, AttributeSet attrs) {
		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TintedImageView);
		try {
			tintColor = a.getColor(R.styleable.TintedImageView_drawableColor, DEFAULT_TINT(context));
			highlightTintColor = a.getColor(R.styleable.TintedImageView_drawableHighlightColor, DEFAULT_HIGHLIGHT_TINT(context));
		} finally {
			a.recycle();
		}
		paintDrawableStates();
	}

	private void paintDrawableStates() {
		colorState = makeTintedButtonColorStateViaLayout(getContext());
		updateDrawableTintColor();
	}

	public int getTintColor() {
		return tintColor;
	}

	public void setTintColor(@ColorInt int tintColor) {
		this.tintColor = tintColor;
		paintDrawableStates();
	}

	public int getHighlightTintColor() {
		return highlightTintColor;
	}

	public void setHighlightTintColor(@ColorInt int highlightTintColor) {
		this.highlightTintColor = highlightTintColor;
		paintDrawableStates();
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		if (colorState != null && colorState.isStateful()) {
			updateDrawableTintColor();
		}
	}

	private void updateDrawableTintColor() {
		int stateColor = colorState.getColorForState(getDrawableState(), DEFAULT_TINT(getContext()));
		stateColor = makeTintedColorViaLayout(tintColor, highlightTintColor, stateColor, getContext());
		setColorFilter(stateColor, PorterDuff.Mode.SRC_IN);
	}
}
