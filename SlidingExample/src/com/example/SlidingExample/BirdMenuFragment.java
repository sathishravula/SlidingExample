package com.example.SlidingExample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class BirdMenuFragment extends Fragment implements AdapterView.OnItemClickListener {

  private ListView listView;
  private String[] menuItems;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.list_view, null);
    listView = (ListView)view.findViewById(R.id.list);
    menuItems = getResources().getStringArray(R.array.menu_items);
    MenuAdapter1 adapter=new MenuAdapter1(getActivity().getApplicationContext());
    listView.setAdapter(adapter);
    View header = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.header, null);
    listView.addHeaderView(header);
    listView.setOnItemClickListener(this);
    return view;

  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  private void switchFragment(Fragment fragment) {
    if (getActivity() == null)
      return;

    if (getActivity() instanceof ResponsiveUIActivity) {
      ResponsiveUIActivity ra = (ResponsiveUIActivity) getActivity();
      ra.switchContent(fragment);
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Fragment newContent=null;
    Log.d("test", "pos:" + position);
    switch (position){
      case 0:
        break;
      case 1:
        newContent = new BirdGridFragment(0);
        break;
      case 2:
        newContent=new EventFragment();
        break;
      default:
        newContent = new BirdGridFragment(position);
        break;
    }

    if (newContent != null)
      switchFragment(newContent);
  }

  private class MenuAdapter1 extends BaseAdapter {

    public MenuAdapter1(Context applicationContext) {

    }

    @Override
    public int getCount() {
      return menuItems.length;
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
        convertView = getActivity().getLayoutInflater().inflate(R.layout.menu_item, null);
      }
      TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
      textView.setText(menuItems[position]);
      ImageView img = (ImageView) convertView.findViewById(R.id.grid_item_img);
//      img.setImageResource(mImgRes);
      return convertView;
    }

  }
}
