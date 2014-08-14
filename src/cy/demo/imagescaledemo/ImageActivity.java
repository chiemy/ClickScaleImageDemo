package cy.demo.imagescaledemo;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		LinearLayout lay = (LinearLayout) findViewById(R.id.layImg);
		
		final CyZoomInImagePopupWindow popupWindow = new CyZoomInImagePopupWindow(this);
		for(int i = 0; i < lay.getChildCount(); i ++){
			lay.getChildAt(i).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					popupWindow.zoomIn(v,(float)1.5,2);
				}
			});
		}
		
		
	}


}
