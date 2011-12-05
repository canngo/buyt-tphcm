package vn.spidersoft.buyt;

import java.util.ArrayList;

import vn.spidersoft.buyt.db.DatabaseAccesser;
import vn.spidersoft.buyt.model.BusLine;
import vn.spidersoft.buyt.model.BusLinePoint;
import vn.spidersoft.buyt.model.BusStop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class SearchActivity extends Activity implements OnFocusChangeListener, OnClickListener, OnItemClickListener{
	private DatabaseAccesser mDatabaseAccesser = null;
	private AutoCompleteTextView autoCompleteTextViewStartStreet = null;
	private AutoCompleteTextView autoCompleteTextViewEndStreet = null;
	private Spinner spinnerStart = null;
	private Spinner spinnerEnd = null;
	private View buttonSearch = null;
	private ArrayAdapter<String> adapter;
	private int mCurrentFocusId = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
        mDatabaseAccesser = DatabaseAccesser.getInstance(getApplication());
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mDatabaseAccesser.getStreets());
        autoCompleteTextViewStartStreet = (AutoCompleteTextView)findViewById(R.id.edittext_start);
		autoCompleteTextViewEndStreet = (AutoCompleteTextView)findViewById(R.id.edittext_end);
		spinnerStart = (Spinner)findViewById(R.id.spinner_start);
		spinnerEnd = (Spinner)findViewById(R.id.spinner_end);
		buttonSearch = findViewById(R.id.btn_search);

		autoCompleteTextViewStartStreet.setAdapter(adapter);
		autoCompleteTextViewEndStreet.setAdapter(adapter);
		
		autoCompleteTextViewStartStreet.setOnItemClickListener(this);
		autoCompleteTextViewEndStreet.setOnItemClickListener(this);
		buttonSearch.setOnClickListener(this);
		autoCompleteTextViewStartStreet.setOnFocusChangeListener(this);
		autoCompleteTextViewEndStreet.setOnFocusChangeListener(this);

	}
	
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		switch (mCurrentFocusId) {
		case R.id.edittext_start:
			String streetName1 = adapter.getItem(pos).toString();
			ArrayList<String> list1 = mDatabaseAccesser.selectHouseNumByStreetName(streetName1);
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(view.getContext(),
					android.R.layout.simple_spinner_item, list1);
			spinnerStart.setAdapter(adapter1);
			break;
		case R.id.edittext_end:
			String streetName2 = adapter.getItem(pos).toString();
			ArrayList<String> list2 = mDatabaseAccesser.selectHouseNumByStreetName(streetName2);
			ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(view.getContext(),
					android.R.layout.simple_spinner_item, list2);
			spinnerEnd.setAdapter(adapter2);
			break;
		default:
			break;
		}
	}
	public void onClick(View v) {
		mCurrentFocusId = v.getId();
		switch (mCurrentFocusId) {
		case R.id.btn_search:
			ArrayList<BusLinePoint> list = new ArrayList<BusLinePoint>();
			String streetNameSrc = autoCompleteTextViewStartStreet.getText().toString();
			String houseNumSrc = (String) spinnerStart.getSelectedItem();
			ArrayList<BusStop> from = mDatabaseAccesser.selectBusStopByStreetName2(streetNameSrc, houseNumSrc);
			String streetNameDes = autoCompleteTextViewEndStreet.getText().toString();
			String houseNumDes = (String) spinnerEnd.getSelectedItem();
			ArrayList<BusStop> to = mDatabaseAccesser.selectBusStopByStreetName2(streetNameDes, houseNumDes);
			for (BusStop src : from) {
				for (BusStop des : to) {
					if (src.getBuytCode().equals(des.getBuytCode())) {
						System.out.println(">>>RESULT BUS CODE="+src);
						String time = "";
						BusLine busLine = new BusLine(src.getBuytCode(), "buyt name", "exchange");
						BusLinePoint busLinePoint = new BusLinePoint(time, src, busLine);
						list.add(busLinePoint);
					}
				}
			}
			Intent intent = new Intent(this, RouteListActivity.class);
			intent.putParcelableArrayListExtra(CommonDefine.EXTRA_SEARCH_RESULT, list);
			startActivity(intent);
			break;
			default:
				break;
		}
	}

	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus)
			mCurrentFocusId = v.getId();
	}
}
