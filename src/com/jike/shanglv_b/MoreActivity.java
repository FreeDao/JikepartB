package com.jike.shanglv_b;

import com.jike.shanglv_b.R;
import com.jike.shanglv_b.Enums.PackageKeys;
import com.jike.shanglv_b.Enums.SPkeys;
import com.jike.shanglv_b.Update.UpdateManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MoreActivity extends Activity {
	private ImageButton back_iv;
	private SharedPreferences sp;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		sp=getSharedPreferences(SPkeys.SPNAME.getString(), 0);
		mContext=this;
		((MyApplication)getApplication()).addActivity(this);
		
		 back_iv = (ImageButton) findViewById(R.id.back_imgbtn);
		 back_iv.setOnClickListener(btnClickListner);
		 ((RelativeLayout)findViewById(R.id.yjfh_rl)).setOnClickListener(btnClickListner);
		 ((RelativeLayout)findViewById(R.id.gwpf_rl)).setOnClickListener(btnClickListner);
		 ((RelativeLayout)findViewById(R.id.jcgx_rl)).setOnClickListener(btnClickListner);
		 ((RelativeLayout)findViewById(R.id.gyslgj_rl)).setOnClickListener(btnClickListner);
		 ((RelativeLayout)findViewById(R.id.xbjs_rl)).setOnClickListener(btnClickListner);
		 ((RelativeLayout)findViewById(R.id.yjfh_rl)).setVisibility(View.GONE);
		 ((RelativeLayout)findViewById(R.id.gwpf_rl)).setVisibility(View.GONE);
	}
	
	View.OnClickListener btnClickListner = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(MoreActivity.this,Activity_Web_Frame.class);
			MyApp ma=new MyApp(getApplicationContext());
			switch (v.getId()) {
			case R.id.back_imgbtn:
				startActivity(new Intent(MoreActivity.this, MainActivity.class));
				break;
			case R.id.yjfh_rl:
				intent.putExtra(Activity_Web_Frame.TITLE, "�������");
				intent.putExtra(Activity_Web_Frame.URL, "http://www.baidu.com");
				startActivity(intent);
				break;
			case R.id.gwpf_rl:
				intent.putExtra(Activity_Web_Frame.TITLE, "��������");
				intent.putExtra(Activity_Web_Frame.URL, "http://www.163.com");
				startActivity(intent);
				break;
			case R.id.xbjs_rl:
				startActivity(new Intent(MoreActivity.this,GuideActivity.class));
				break;
			case R.id.jcgx_rl:
				UpdateManager manager=new UpdateManager(MoreActivity.this,ma.getHm().get(PackageKeys.UPDATE_NOTE.getString()).toString());
				manager.checkForUpdates(true);
				break;
			case R.id.gyslgj_rl:
				String version="2.1";
				try {
					version = "V"+mContext.getPackageManager().getPackageInfo(
							mContext.getPackageName(), 0).versionName;
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				intent.putExtra(Activity_Web_Frame.TITLE, "��  ��");
//				http://m.51jp.cn/Aboutv2?productType=3&userKey=
				intent.putExtra(Activity_Web_Frame.URL, 
						String.format(ma.getAbout(),"��׿�ֻ��ͻ���",ma.getHm().get(PackageKeys.USERKEY.getString()).toString(),version));
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	};
}