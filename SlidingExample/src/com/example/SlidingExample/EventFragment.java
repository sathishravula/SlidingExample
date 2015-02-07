package com.example.SlidingExample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by ehc on 7/2/15.
 */
public class EventFragment  extends Fragment implements AdapterView.OnItemClickListener {
  private int mPos = -1;
  private int mImgRes;
  private ListView listView;

  public EventFragment() { }
  public EventFragment(int pos) {
    mPos = pos;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.event_view, null);
    listView = (ListView)view.findViewById(R.id.list);
    MenuAdapter1 adapter=new MenuAdapter1(getActivity().getApplicationContext());
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(this);
    return view;
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
//    outState.putInt("mPos", mPos);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

  }

  private class MenuAdapter1 extends BaseAdapter {

    public MenuAdapter1(Context applicationContext) {

    }

    @Override
    public int getCount() {
      return 30;
    }

    @Override
    public Object getItem(int position) {
      return null;
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
        convertView = getActivity().getLayoutInflater().inflate(R.layout.event_item, null);
      }
//      TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
//      textView.setText(menuItems[position]);
//      ImageView img = (ImageView) convertView.findViewById(R.id.grid_item_img);
//      img.setImageResource(mImgRes);
      return convertView;
    }

  }
}
