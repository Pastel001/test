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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.neu.jinshen.dto.Project;

public class ListProjectActivity extends Activity {
	private ListView proListView;
	ArrayList<Project> list = new ArrayList<Project>();
	List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project);

		proListView = (ListView) this.findViewById(R.id.projectListView);

		HttpClient client = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(
				"http://10.0.2.2:2012/jinshen/project/listProjectForAndroid.action");
		try {
			HttpResponse response = client.execute(httpGet);

			String jsonData = EntityUtils.toString(response.getEntity());
			
			JSONArray jsonArray = new JSONArray(jsonData);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);

				Project project = new Project();
				project.setProId(json.getInt("proId"));
				project.setProName(json.getString("proName"));
				list.add(project);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("proId", project.getProId());
				map.put("proName", project.getProName());
				list1.add(map);

			}

			// 创建适配器对象
			SimpleAdapter adapter = new SimpleAdapter(this,
					(List<? extends Map<String, ?>>) list1,
					R.layout.project_item, new String[] { "proId", "proName" },
					new int[] { R.id.proId, R.id.proName });

			// 设置适配器

			proListView.setAdapter(adapter);

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
		proListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Project project = null;
				Intent intent = new Intent(ListProjectActivity.this,
						AddContractActivity.class);
				Bundle bundle = new Bundle();
				int proId = list.get(position).getProId();
				for (Project pro : list) {
					if (pro.getProId() == proId) {
						project = pro;
					}
				}
				bundle.putSerializable("project", project);
				intent.putExtras(bundle);
				ListProjectActivity.this.setResult(-1, intent);
				ListProjectActivity.this.finish();
			}

		});

	}

}
