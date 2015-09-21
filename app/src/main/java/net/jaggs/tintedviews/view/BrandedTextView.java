package net.jaggs.tintedviews.view;

import android.content.Context;
import android.util.AttributeSet;
import net.jaggs.tintedviews.R;

public class BrandedTextView extends TintedTextView {


	public BrandedTextView(Context context) {
		super(context);
		postConstruct();
	}

	public BrandedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		postConstruct();
	}

	public BrandedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		postConstruct();
	}

	public BrandedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		postConstruct();
	}

	private void postConstruct() {
		if (getDrawableTint() == TintedViewUtils.DEFAULT_TINT(getContext())) {
			setDrawableTint(getResources().getColor(R.color.brandTint));
		}
		if (getHighlightDrawableTint() == TintedViewUtils.DEFAULT_HIGHLIGHT_TINT(getContext())) {
			setHighlightDrawableTint(getResources().getColor(R.color.brandHighlightTint));
		}
	}
}
