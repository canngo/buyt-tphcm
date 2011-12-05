package vn.spidersoft.buyt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BusLinePoint implements Parcelable {
	private String mTime = "";
	private BusStop mBusStop = null;
	private BusLine mBusLine = null;
	
	public BusLinePoint(String time, BusStop busStop, BusLine busLine) {
		this.mTime = time;
		this.mBusStop = busStop;
		this.mBusLine = busLine;
	}

	public String getTime() {
		return mTime;
	}

	public void setTime(String time) {
		this.mTime = time;
	}

	public BusStop getBusStop() {
		return mBusStop;
	}

	public void setBusStop(BusStop busStop) {
		this.mBusStop = busStop;
	}

	public BusLine getBusLine() {
		return mBusLine;
	}

	public void setBusLine(BusLine busLine) {
		this.mBusLine = busLine;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mTime);
		dest.writeParcelable(mBusStop, flags);
		dest.writeParcelable(mBusLine, flags);
	}
	
	public static final Parcelable.Creator<BusLinePoint> CREATOR
		= new Parcelable.Creator<BusLinePoint>() {
		public BusLinePoint createFromParcel(Parcel in) {
			return new BusLinePoint(in);
	}
	
	public BusLinePoint[] newArray(int size) {
		return new BusLinePoint[size];
	}
	};
	
	private BusLinePoint(Parcel in) {
		mTime = in.readString();
		mBusStop = in.readParcelable(BusStop.class.getClassLoader());
		mBusLine = in.readParcelable(BusLine.class.getClassLoader());
	}
	
}
