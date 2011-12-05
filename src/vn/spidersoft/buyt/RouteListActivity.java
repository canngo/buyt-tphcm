package vn.spidersoft.buyt;

import java.util.ArrayList;

import vn.spidersoft.buyt.model.BusLinePoint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class RouteListActivity extends Activity {
	private ListView mListView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.routelist);
		mListView = (ListView) findViewById(R.id.routelist);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		ArrayList<BusLinePoint> busLinePoints = bundle.getParcelableArrayList(CommonDefine.EXTRA_SEARCH_RESULT);
		RouteArrayAdapter adapter = 
			new RouteArrayAdapter(this, R.layout.routeitem, busLinePoints);  
		mListView.setAdapter(adapter);  
	}
}
