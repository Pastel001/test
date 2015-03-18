package com.neu.jinshen;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.neu.jinshen.dto.Buildcontent;
import com.neu.jinshen.dto.Contract;
import com.neu.jinshen.dto.Project;

public class MainActivity extends Activity {

	private ListView conListView;

	ArrayList<Contract> list = new ArrayList<Contract>();

	List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		conListView = (ListView) this.findViewById(R.id.contractListView);

		HttpClient client = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(
				"http://10.0.2.2:2012/jinshen/contract/listContractForAndroid.action");

		try {
			HttpResponse response = client.execute(httpGet);

			String jsonData = EntityUtils.toString(response.getEntity());

			JSONArray jsonArray = new JSONArray(jsonData);

			for (int i = 0; i < jsonArray.length(); i++) {
				Buildcontent buildcontent = new Buildcontent();
				Project project = new Project();

				JSONObject json = jsonArray.getJSONObject(i);
				JSONObject jsonPro = json.getJSONObject("project");
				JSONObject jsonBul = json.getJSONObject("buildcontent");

				project.setProId(jsonPro.getInt("proId"));
				project.setProName(jsonPro.getString("proName"));

				buildcontent.setBuildconId(jsonBul.getInt("buildconId"));
				buildcontent.setBuildconName(jsonBul.getString("buildconName"));

				Contract contract = new Contract(json.getInt("conId"),
						json.getString("conName"), json.getString("conOne"),
						json.getString("conTwo"), json.getString("conThree"),
						Date.valueOf(json.getString("conDate")),
						json.getDouble("conFund"), json.getString("conRemark"),
						project, buildcontent);
				list.add(contract);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("conId", contract.getConId());
				map.put("conName", contract.getConName());
				map.put("project", project.getProName());
				list1.add(map);
			}

			// 创建适配器对象
			SimpleAdapter adapter = new SimpleAdapter(this,
					(List<? extends Map<String, ?>>) list1,
					R.layout.contract_item, new String[] { "conId", "conName",
							"project" }, new int[] { R.id.conId, R.id.conName,
							R.id.project });

			// 设置适配器
			conListView.setAdapter(adapter);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 增加条目单击事件处理
		conListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Contract contract = null;
				Intent intent = new Intent(MainActivity.this,
						ListContractActivity.class);
				Bundle bundle = new Bundle();
				int conId = (Integer) list1.get(position).get("conId");
				for (Contract con : list) {
					if (con.getConId() == conId) {
						contract = new Contract();
						contract = con;
					}
				}
				bundle.putSerializable("contract", contract);
				intent.putExtras(bundle);
				MainActivity.this.startActivity(intent);

			}
		});

		// 增加条目长按事件处理
		conListView
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						// 设置弹出菜单标题
						menu.setHeaderTitle("合同管理");
						// 设置弹出菜单图标
						menu.setHeaderIcon(R.drawable.icon);
						// 增加菜单项
						menu.add(0, 0, 0, "合同详细");
						menu.add(0, 1, 1, "增加合同");
						menu.add(0, 2, 2, "删除合同");
						menu.add(0, 3, 3, "修改合同");
						menu.add(0, 4, 4, "返回");
					}
				});
	}

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 获得被选择的item信息，否则无法知道那个选项被长按
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Contract contract = null;
		if (item.getItemId() == 0) {
			Intent intent = new Intent(this, ListContractActivity.class);
			Bundle bundle = new Bundle();
			int conId = (Integer) list1.get(menuInfo.position).get("conId");
			for (Contract con : list) {
				if (con.getConId() == conId) {
					contract = new Contract();
					contract = con;
				}
			}
			bundle.putSerializable("contract", contract);
			intent.putExtras(bundle);
			MainActivity.this.startActivity(intent);
		} else if (item.getItemId() == 1) {
			Intent intent = new Intent(this, AddContractActivity.class);
			MainActivity.this.startActivity(intent);
		} else if (item.getItemId() == 2) {
			Intent intent = new Intent(this, MainActivity.class);
			int conId = (Integer) list1.get(menuInfo.position).get("conId");
			HttpClient client = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet(
					"http://10.0.2.2:2012/jinshen/contract/delContract.action?conId="
							+ conId);
			try {
				client.execute(httpGet);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainActivity.this.startActivity(intent);

		} else if (item.getItemId() == 3) {
			Intent intent = new Intent(this, UpdateContractActivity.class);
			Bundle bundle = new Bundle();
			int conId = (Integer) list1.get(menuInfo.position).get("conId");
			for (Contract con : list) {
				if (con.getConId() == conId) {
					contract = new Contract();
					contract = con;
				}
			}
			bundle.putSerializable("contract", contract);
			intent.putExtras(bundle);
			MainActivity.this.startActivity(intent);
			

		} else if (item.getItemId() == 4) {
			Intent intent = new Intent(this,MainActivity.class);
			MainActivity.this.startActivity(intent);
		}

		return super.onContextItemSelected(item);
	}

}