package net.jaggs.tintedviews.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import static net.jaggs.tintedviews.view.TintedViewUtils.*;

public class BrandedImageView extends TintedImageView {

	public BrandedImageView(Context context) {
		super(context);
		postConstruct();
	}

	public BrandedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		postConstruct();
	}

	public BrandedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		postConstruct();
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public BrandedImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		postConstruct();
	}

	private void postConstruct() {
		if (getTintColor() == DEFAULT_TINT(getContext())) {
			setTintColor(BRAND_TINT(getContext()));
		}
		if (getHighlightTintColor() == DEFAULT_HIGHLIGHT_TINT(getContext())) {
			setHighlightTintColor(BRAND_HIGHLIGHT_TINT(getContext()));
		}
	}
}
