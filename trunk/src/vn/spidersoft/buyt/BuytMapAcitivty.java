package vn.spidersoft.buyt;

import jp.co.mapion.android.maps.MapView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class BuytMapAcitivty extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		MapView mapView = new MapView(this, 
				new BuytTPHCMMap("c3931a481f3837ca86062cc426a69e01", R.drawable.tttttt));
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.getController().setZoom(4);
        setContentView(mapView);
	}
}
