package cy.demo.imagescaledemo;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class ZoomPopupWindow extends PopupWindow {
	private int width,height;
	private LinearLayout mLayout;
	private View scaleRotateView;
	private View frontView,behindView;
	private int backgroundColor;
	private static final int DEF_BG_COLOR = 0x88000000;
	private float MULTIPLEX;
	private float MULTIPLEY;
	private Point fromPosition,toPosition;
	
	public ZoomPopupWindow(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		width = dm.widthPixels;
		height = dm.heightPixels;
		mLayout = initLayout(context);
		this.setFocusable(true);
	}
	
	/**
	 * @param scaleRotateView 需要包含id为front_view和behind_view
	 */
	public void setView(View scaleRotateView) {
		mLayout.addView(scaleRotateView);
		this.setContentView(mLayout);
		frontView = scaleRotateView.findViewById(R.id.front_view);
		behindView = scaleRotateView.findViewById(R.id.behind_view);
		this.scaleRotateView = scaleRotateView;
	}
	
	public View getFrontView(View parent) {
		return parent.findViewById(R.id.front_view);
	}
	
	public View getBehindView(View parent){
		return parent.findViewById(R.id.behind_view);
	}
	
	public void setBackgroundColor(int color) {
		mLayout.setBackgroundColor(color);
		this.backgroundColor = color;
	}
	
	public void zoomIn(View view,float multipleX, float mutipleY){
		MULTIPLEX = multipleX;
		MULTIPLEY = mutipleY;
		// 设置位置
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		params.width = view.getLayoutParams().width;
		params.height = view.getLayoutParams().height;
		int[] location = new int[2];
		//获取在整个屏幕内的绝对坐标，注意这个值是要从屏幕顶端算起，也就是包括了通知栏的高度。
		view.getLocationOnScreen(location);
	    int x = location[0];
	    int y = location[1];
		params.setMargins(x, y, 0, 0);
		this.frontView.setLayoutParams(params);
		this.showAtLocation(view, 0, 0, 0);
		int viewWidth = view.getWidth();
		int viewHeight = view.getHeight();
		fromPosition.x = 0;
		fromPosition.y = 0;
		//先平移后放大
		toPosition.x =  (int) ((width - viewWidth )/2) - x;
		//先平移后放大
		toPosition.y =  (int) ( (height - viewHeight)/2)  - y;
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.setInterpolator(new OvershootInterpolator(1F));
		
		ScaleAnimation scaleAnimation = new ScaleAnimation(1, multipleX, 1, mutipleY,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		animationSet.addAnimation(scaleAnimation);
		TranslateAnimation translateAnimation = new TranslateAnimation(fromPosition.x, toPosition.x, fromPosition.y , toPosition.y);
		animationSet.addAnimation(translateAnimation);
		
		animationSet.setDuration(500);
		animationSet.setFillAfter(true);
		frontView.setAnimation(animationSet);
	}
	
	private LinearLayout initLayout(Context context) {
		LinearLayout layout = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		layout.setFocusable(true);
		layout.setFocusableInTouchMode(true);
		layout.setBackgroundColor(DEF_BG_COLOR);
		layout.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_BACK ){
					ZoomPopupWindow.this.dismiss();
				}
				return false;
			}
		});
		return layout;
	}
}
