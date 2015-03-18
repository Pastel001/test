package com.neu.jinshen;

import com.neu.jinshen.dto.Contract;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ListContractActivity extends Activity {
	private EditText conId;
	private EditText conName;
	private EditText conOne;
	private EditText conTwo;
	private EditText conThree;
	private EditText conDate;
	private EditText conFund;
	private EditText project;
	private EditText buildcontent;
	private EditText conRemark;
	private Button backButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listcontract);

		conId = (EditText) this.findViewById(R.id.conId);
		conName = (EditText) this.findViewById(R.id.conName);
		conOne = (EditText) this.findViewById(R.id.conOne);
		conTwo = (EditText) this.findViewById(R.id.conTwo);
		conThree = (EditText) this.findViewById(R.id.conThree);
		conDate = (EditText) this.findViewById(R.id.conDate);
		conFund = (EditText) this.findViewById(R.id.conFund);
		project = (EditText) this.findViewById(R.id.project);
		buildcontent = (EditText) this.findViewById(R.id.buildcontent);
		conRemark = (EditText) this.findViewById(R.id.conRemark);
		backButton = (Button) this.findViewById(R.id.backButton);
		

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		Contract contract = (Contract) bundle.get("contract");

		
		conId.setText(contract.getConId()+"");
		conName.setText(contract.getConName());
		conOne.setText(contract.getConOne());
		conTwo.setText(contract.getConTwo());
		conThree.setText(contract.getConThree());
		conDate.setText(contract.getConDate().toString());
		conFund.setText(contract.getConFund().toString());
		project.setText(contract.getProject().getProName());
		buildcontent.setText(contract.getBuildcontent().getBuildconName());
		conRemark.setText(contract.getConRemark());

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ListContractActivity.this.finish();
			}
		});

	}

}