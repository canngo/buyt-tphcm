package vn.spidersoft.buyt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class BuytTPHCMActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        findViewById(R.id.btn_search).setOnClickListener(this);
        findViewById(R.id.btn_map).setOnClickListener(this);
        findViewById(R.id.btn_bookmark).setOnClickListener(this);
        findViewById(R.id.btn_statusInfo).setOnClickListener(this);
        findViewById(R.id.btn_timeInfo).setOnClickListener(this);
        findViewById(R.id.btn_news).setOnClickListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.menu_setting:
			Intent intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_help:
			
			break;
		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_search:
				Intent intent = new Intent(this, SearchActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_map:
				intent = new Intent(this, BuytMapAcitivty.class);
				startActivity(intent);		
				break;
			case R.id.btn_bookmark:
				
				break;
			case R.id.btn_statusInfo:
				
				break;
			case R.id.btn_timeInfo:
						
				break;
			case R.id.btn_news:
				
				break;
		}
	}
}