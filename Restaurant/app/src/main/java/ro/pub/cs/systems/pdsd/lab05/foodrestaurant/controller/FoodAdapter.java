package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.controller;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Produs;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.R;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.utils.PictureFinder;

import android.app.Activity;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAdapter extends BaseAdapter implements Filterable {

    Activity context;
    LayoutInflater inflater;

    ArrayList<Produs> dataOriginal;
    ArrayList<Produs> dataFiltered;
    String searchCriteria;

    Cursor cursor;
    int checkedItemPosition = -1;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    results.values = dataOriginal;
                    results.count = dataOriginal.size();
                } else {

                    ArrayList<Produs> filterResultsData = new ArrayList<Produs>();

                    for (Produs data : dataOriginal) {
                        if (data.getNume().contains((String) charSequence) && data.getNume().startsWith((String) charSequence)) {
                            filterResultsData.add(data);
                        }
                    }

                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataFiltered = (ArrayList<Produs>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public static class ViewHolder {
        ImageView foodPictures;
        TextView name, price, continut;
    }

    ;

    public final static int CONTACT_VIEW_TYPES = 2;
    public final static int CONTACT_VIEW_TYPE_ODD = 0;
    public final static int CONTACT_VIEW_TYPE_EVEN = 1;


    public FoodAdapter(Activity context, ArrayList<Produs> content) {
        this.context = context;
        inflater = (LayoutInflater) context.getLayoutInflater();

        this.dataOriginal = content;
        this.dataFiltered = content;

    }

    public FoodAdapter(Activity context) {
        this.context = context;
        inflater = (LayoutInflater) context.getLayoutInflater();

    }


    @Override
    public int getCount() {
        return dataFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return dataFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View contactView;
        ViewHolder viewHolder;
        Produs foodModel = dataFiltered.get(position);
        if (convertView == null) {
            contactView = inflater.inflate(R.layout.food_view_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.foodPictures = (ImageView) contactView.findViewById(R.id.food_image_view);
            viewHolder.name = (TextView) contactView.findViewById(R.id.name_food_view);
            viewHolder.price = (TextView) contactView.findViewById(R.id.price_food_view);
            viewHolder.continut = (TextView) contactView.findViewById(R.id.details);
            contactView.setTag(viewHolder);
        } else {
            contactView = convertView;
        }

        if (getCheckedItemPosition() == position) {
            contactView.setBackgroundColor(context.getResources().getColor(R.color.light_blue));
        } else {
            contactView.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
        }

        viewHolder = (ViewHolder) contactView.getTag();
        viewHolder.foodPictures.setImageResource(PictureFinder.findPictureByName(foodModel.getPicture()));
        viewHolder.name.setText(foodModel.getNume());
        viewHolder.price.setText(foodModel.getPret() + "");
        viewHolder.continut.setText(foodModel.getContinut());
        return contactView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }


    public ArrayList<Produs> getData() {
        return dataFiltered;
    }

    public void setData(ArrayList<Produs> data) {
        this.dataOriginal = data;
        this.dataFiltered = data;
    }

    public Produs getSelectedContact() {
        return dataFiltered.get(checkedItemPosition);
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCheckedItemPosition(int checkedItemPosition) {
        this.checkedItemPosition = checkedItemPosition;
    }

    public int getCheckedItemPosition() {
        return checkedItemPosition;
    }


}
