package net.jaggs.tintedviews.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import net.jaggs.tintedviews.R;

public class TintedViewUtils {

	public static Drawable generateColoredDrawable(Drawable drawable, @ColorInt int color) {
		drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
		return drawable;
	}

	public static ColorStateList makeTintedButtonColorStateViaLayout(Context context) {
		return context.getResources().getColorStateList(R.color.default_color_state_list);
	}

	public static int makeTintedColorViaLayout(@ColorInt int color, @ColorInt int highlightColor, @ColorInt int stateColor, Context context) {
		return makeTintedColorViaLayout(DEFAULT_TINT(context), DEFAULT_HIGHLIGHT_TINT(context), color, highlightColor, stateColor, context);
	}

	public static int makeTintedColorViaLayout(@ColorInt int overrideColor, @ColorInt int highlightOverrideColor, @ColorInt int color, @ColorInt int highlightColor, @ColorInt int stateColor, Context context) {
		if (stateColor == DEFAULT_TINT(context)) {
			return overrideColor != DEFAULT_TINT(context) ? overrideColor : color != DEFAULT_TINT(context) ? color : DEFAULT_TINT(context);
		} else {
			return highlightOverrideColor != DEFAULT_HIGHLIGHT_TINT(context) ? highlightOverrideColor : highlightColor != DEFAULT_HIGHLIGHT_TINT(context) ? highlightColor : DEFAULT_HIGHLIGHT_TINT(context);
		}
	}

	public static int DEFAULT_TINT(Context context) {
		return context.getResources().getColor(R.color.defaultTint);
	}

	public static int DEFAULT_HIGHLIGHT_TINT(Context context) {
		return context.getResources().getColor(R.color.defaultHighlightTint);
	}

	public static int BRAND_TINT(Context context) {
		return context.getResources().getColor(R.color.brandTint);
	}

	public static int BRAND_HIGHLIGHT_TINT(Context context) {
		return context.getResources().getColor(R.color.brandHighlightTint);
	}
}
