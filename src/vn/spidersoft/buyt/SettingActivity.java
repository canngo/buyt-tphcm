package vn.spidersoft.buyt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import vn.spidersoft.buyt.db.DatabaseAccesser;
import vn.spidersoft.buyt.model.BusLine;
import vn.spidersoft.buyt.model.BusStop;
import vn.spidersoft.buyt.model.Buyt;
import vn.spidersoft.buyt.model.Street;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class SettingActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting);
		findViewById(R.id.btn_import_data).setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_import_data:
			ImportDataAsyncTask task = new ImportDataAsyncTask();
			task.execute();
			break;

		default:
			break;
		}
	}
	
	private class ImportDataAsyncTask extends AsyncTask<Integer, Integer, Boolean> {

		ProgressDialog dialog = null;
		DatabaseAccesser mDatabaseAccesser = null;
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(SettingActivity.this);
			dialog.setMessage("Loading...");
			dialog.show();
			mDatabaseAccesser = DatabaseAccesser.getInstance(SettingActivity.this);
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(Integer... arg0) {
			boolean result = false;
			mDatabaseAccesser.clearAllData();
			result = importStreet(result);
			result = importBuyt(result);
			result = importBusStop(result);
			result = importBusLine(result);
			return result;
		}

		private boolean importStreet(boolean result) {
			File file = new File("sdcard/tenduong_utf8.txt");
			try {
				FileReader fileReader = new FileReader(file);
				String thisLine = "";
				Street street = new Street("", "");
				//Open the file for reading
			     try {
			       BufferedReader br = new BufferedReader(fileReader);
			       while ((thisLine = br.readLine()) != null) { // while loop begins here
			         System.out.println(thisLine);
			         String[] split = thisLine.split(";");
			         street.setName(split[1]);
			         mDatabaseAccesser.insertStreet(street);
			       } // end while 
			       result = true;
			     } // end try
			     catch (IOException e) {
			       System.err.println("Error: " + e);
			     }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		private boolean importBuyt(boolean result) {
			File file = new File("sdcard/tentuyen_utf8.txt");
			try {
				FileReader fileReader = new FileReader(file);
				String thisLine = "";
				Buyt buyt = new Buyt("", "");
				//Open the file for reading
			     try {
			       BufferedReader br = new BufferedReader(fileReader);
			       while ((thisLine = br.readLine()) != null) { // while loop begins here
			         System.out.println(thisLine);
			         String[] split = thisLine.split(";");
			         buyt.setCode(split[0]);
			         buyt.setName(split[1]);
			         mDatabaseAccesser.insertBuyt(buyt);
			       } // end while 
			       result = true;
			     } // end try
			     catch (IOException e) {
			       System.err.println("Error: " + e);
			     }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		private boolean importBusStop(boolean result) {
			File file = new File("sdcard/busstop_utf8.txt");
			try {
				FileReader fileReader = new FileReader(file);
				String thisLine = "";
				BusStop busStop = new BusStop("", "", "", "", "");
				//Open the file for reading
			     try {
			       BufferedReader br = new BufferedReader(fileReader);
			       while ((thisLine = br.readLine()) != null) { // while loop begins here
			         System.out.println(thisLine);
			         String[] split = thisLine.split(";");
			         busStop.setHouseNum(split[0]);
			         busStop.setStreetName(split[1]);
			         busStop.setArea(split[2]);
			         String[] buytCodes = split[3].split(",");
			         for (String buytCode : buytCodes) {
			        	 busStop.setBuytCode(buytCode);
			        	 mDatabaseAccesser.insertBusStop(busStop);
			         }
			       } // end while 
			       result = true;
			     } // end try
			     catch (IOException e) {
			       System.err.println("Error: " + e);
			     }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		private boolean importBusLine(boolean result) {
			File file = new File("sdcard/busline_utf8.txt");
			try {
				FileReader fileReader = new FileReader(file);
				String thisLine = "";
				BusLine busLine = new BusLine("", "", "");
				//Open the file for reading
			     try {
			       BufferedReader br = new BufferedReader(fileReader);
			       while ((thisLine = br.readLine()) != null) { // while loop begins here
			         System.out.println(thisLine);
			         String[] split = thisLine.split(";");
			         busLine.setBuytCode(split[0]);
			         busLine.setBuytGoing(split[1]);
			         busLine.setBuytCodeEx(split[2]);
			         mDatabaseAccesser.insertBusline(busLine);
			       } // end while 
			       result = true;
			     } // end try
			     catch (IOException e) {
			       System.err.println("Error: " + e);
			     }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			super.onPostExecute(result);
		}
	}
}
