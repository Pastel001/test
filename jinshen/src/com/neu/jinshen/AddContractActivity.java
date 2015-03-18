package com.neu.jinshen;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.neu.jinshen.dto.Buildcontent;
import com.neu.jinshen.dto.Contract;
import com.neu.jinshen.dto.Project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddContractActivity extends Activity {
	Project pro = null;
	Buildcontent buildcon = null;
	Contract contract = null;
	
	private DatePickerDialog dialog;// 日历框
	private Calendar calendar;
	private EditText conName;
	private EditText conOne;
	private EditText conTwo;
	private EditText conThree;
	private EditText conDate;
	private EditText conFund;
	private EditText project;
	private EditText buildcontent;
	private EditText conRemark;
	private Button yesButton;
	private Button backButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtcontract);

		// 获得当前日期
		calendar = Calendar.getInstance();

		conName = (EditText) this.findViewById(R.id.conName);
		conOne = (EditText) this.findViewById(R.id.conOne);
		conTwo = (EditText) this.findViewById(R.id.conTwo);
		conThree = (EditText) this.findViewById(R.id.conThree);
		conDate = (EditText) this.findViewById(R.id.conDate);
		conFund = (EditText) this.findViewById(R.id.conFund);
		project = (EditText) this.findViewById(R.id.project);
		buildcontent = (EditText) this.findViewById(R.id.buildcontent);
		conRemark = (EditText) this.findViewById(R.id.conRemark);
		yesButton = (Button) this.findViewById(R.id.yesButton);
		backButton = (Button) this.findViewById(R.id.backButton);

		conDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new DatePickerDialog(AddContractActivity.this,
						dateListener, calendar.get(Calendar.YEAR), calendar
								.get(Calendar.MONTH), calendar
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});

		project.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AddContractActivity.this,
						ListProjectActivity.class);
				AddContractActivity.this.startActivityForResult(intent, 1);
			}
		});

		buildcontent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AddContractActivity.this,
						ListBuildcontentActivity.class);
				AddContractActivity.this.startActivityForResult(intent, 2);
			}
		});

		yesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 核心对象
				HttpClient client = new DefaultHttpClient();
				// POST请求对象
				HttpPost httpPost = new HttpPost(
						"http://10.0.2.2:2012/jinshen/contract/addContract.action");

				// 创建一个集合，存储发送的键值对
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// 增加键值对
				params.add(new BasicNameValuePair("conName", conName.getText()
						.toString()));
				params.add(new BasicNameValuePair("conOne", conOne.getText()
						.toString()));
				params.add(new BasicNameValuePair("conTwo", conTwo.getText()
						.toString()));
				params.add(new BasicNameValuePair("conThree", conThree
						.getText().toString()));
				params.add(new BasicNameValuePair("conDate", conDate.getText()
						.toString()));
				params.add(new BasicNameValuePair("conFund", conFund.getText()
						.toString()));
				params.add(new BasicNameValuePair("conRemark", conRemark
						.getText().toString()));
				params.add(new BasicNameValuePair("proId", pro.getProId() + "" ));
				params.add(new BasicNameValuePair("buildconId", buildcon.getBuildconId() + ""));

				// 创建一个请求实体，传入键值对集合，并且设置编码
				HttpEntity httpEntity;
				try {
					httpEntity = new UrlEncodedFormEntity(params, "utf-8");
					// 设置请求实体
					httpPost.setEntity(httpEntity);

					// 发出POST请求
					client.execute(httpPost);
				
					AddContractActivity.this.finish();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddContractActivity.this.finish();
			}
		});

	}

	// 选择日期之后的回调函数
	DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int year, int month,
				int dayOfMonth) {
			// 注意！！月份是用0~11表示
			// System.out.println("date=" + year + "-" + (month + 1) + "-"
			// + dayOfMonth);

			conDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);

		}
	};

	// 当被打开的Activity返回值时的事件处理，如果没有返回值resultCode=0,data=null
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1 && resultCode == -1 && data != null) {
			Bundle bundle = data.getExtras();
			pro = (Project) bundle.get("project");
			project.setText(pro.getProName());
		} else if (requestCode == 2 && resultCode == -2 && data != null) {
			Bundle bundle = data.getExtras();
			buildcon = (Buildcontent) bundle.get("buildcontent");
			buildcontent.setText(buildcon.getBuildconName());
		}
	};

}