package com.neu.jinshen;

import java.io.IOException;

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

import com.neu.jinshen.dto.Buildcontent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ListBuildcontentActivity extends Activity {

	private ListView buildconListView;
	ArrayList<Buildcontent> list = new ArrayList<Buildcontent>();
	List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buildcontent);

		buildconListView = (ListView) this
				.findViewById(R.id.buildcontentListView);

		HttpClient client = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(
				"http://10.0.2.2:2012/jinshen/datadic/listBuildContentForAndroid.action");
		try {
			HttpResponse response = client.execute(httpGet);

			String jsonData = EntityUtils.toString(response.getEntity());

			JSONArray jsonArray = new JSONArray(jsonData);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);

				Buildcontent buildcontent = new Buildcontent(
						json.getInt("buildconId"),
						json.getString("buildconName"),
						json.getDouble("buildconFund"));
				list.add(buildcontent);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("buildconId", buildcontent.getBuildconId());
				map.put("buildconName", buildcontent.getBuildconName());
				map.put("buildconFund", buildcontent.getBuildconFund());
				list1.add(map);

			}

			// 创建适配器对象
			SimpleAdapter adapter = new SimpleAdapter(this,
					(List<? extends Map<String, ?>>) list1,
					R.layout.buildcontent_item, new String[] { "buildconId",
							"buildconName", "buildconFund" }, new int[] {
							R.id.buildconId, R.id.buildconName,
							R.id.buildconFund });

			// 设置适配器
			buildconListView.setAdapter(adapter);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 增加条目点击事件处理
		buildconListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Buildcontent buildcontent = null;
				Intent intent = new Intent(ListBuildcontentActivity.this,
						AddContractActivity.class);
				Bundle bundle = new Bundle();
				int buildconId = list.get(position).getBuildconId();
				for (Buildcontent buildcon : list) {
					if (buildcon.getBuildconId() == buildconId) {
						buildcontent = buildcon;
					}
				}
				bundle.putSerializable("buildcontent", buildcontent);
				intent.putExtras(bundle);
				ListBuildcontentActivity.this.setResult(-2, intent);
				ListBuildcontentActivity.this.finish();
			}

		});

	}

}
