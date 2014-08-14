package cy.demo.imagescaledemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.PopupWindow;

public class ZoomInPopupWindow {
	private int backgroundColor = -1;
	private int alpha = -1;
	private static int DEF_BG_COLOR = Color.BLACK;
	private static int DEF_BG_ALPHA = 150;

	private Point fromPostion, toPosition;

	private View frontView, behindView;

	private int width;
	private int height;
	private PopupWindow popupWindow;

	public ZoomInPopupWindow(Context context) {
		fromPostion = new Point();
		toPosition = new Point();
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		width = dm.widthPixels;
		height = dm.heightPixels;
	}

	/**
	 * 设置背景颜色
	 * 
	 * @param backgroundColor
	 */
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * 设置透明度
	 * 
	 * @param alpha
	 */
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	/**
	 * 设置前景图
	 * 
	 * @param frontView
	 */
	public void setFrontView(View frontView) {
		this.frontView = frontView;
	}

	public View getFrontView() {
		return frontView;
	}

	/**
	 * 设置背景图
	 * 
	 * @param behindView
	 */
	public void setBehindView(View behindView) {
		this.behindView = behindView;
	}

	public View getBehindView() {
		return behindView;
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
