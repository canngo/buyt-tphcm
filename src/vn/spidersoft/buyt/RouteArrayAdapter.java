package vn.spidersoft.buyt;

import java.util.List;

import vn.spidersoft.buyt.model.BusLinePoint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RouteArrayAdapter extends ArrayAdapter<BusLinePoint> {
	LayoutInflater mInflater = null;
	List<BusLinePoint> mItems = null;
	public RouteArrayAdapter(Context context,
			int textViewResourceId, List<BusLinePoint> objects) {
		super(context,textViewResourceId, objects);
		this.mItems = objects;  
		this.mInflater = (LayoutInflater) context  
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ビューを受け取る  
		View view = convertView;  
		if (view == null) {  
			// 受け取ったビューがnullなら新しくビューを生成  
			view = mInflater.inflate(R.layout.routeitem, null);  
		}
		// 表示すべきデータの取得  
		BusLinePoint item = (BusLinePoint)mItems.get(position);
		if (item != null) {  
			TextView time = (TextView)view.findViewById(R.id.textViewTime);  
			time.setTypeface(Typeface.DEFAULT_BOLD);  
			
			TextView busstopName = (TextView)view.findViewById(R.id.textViewBusstopName);  
			TextView lineName = (TextView)view.findViewById(R.id.textViewTenTuyen);  
			TextView lineGoing = (TextView)view.findViewById(R.id.textViewHuongDi);
			
			time.setText(item.getTime());  
			busstopName.setText(item.getBusStop().getStreetName()+item.getBusStop().getHouseNum());  
			lineName.setText(item.getBusLine().getBuytCode());
			lineGoing.setText(item.getBusLine().getBuytGoing());
		}
		return view;
	}
}
