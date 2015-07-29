package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.view;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Produs;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.R;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.bd.ProduseDB;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.controller.FoodAdapter;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.network.Requests;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Collection;

public class FoodBasicDetailsFragment extends Fragment {

    private ListView addressBookListView;
    private FoodAdapter addressBookFoodAdapter;

    private EditText searchFieldEditText;
    private String searchCriteria;
    private ProduseDB produseDB;

    private EditTextListener searchFieldEditTextListener = new EditTextListener();

    private class EditTextListener implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence text, int start, int before, int count) {
            searchCriteria = text.toString();
            addressBookFoodAdapter.setCheckedItemPosition(-1);

            addressBookFoodAdapter.getFilter().filter(searchCriteria);

        }

        @Override
        public void beforeTextChanged(CharSequence text, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable view) {
        }
    }

    private ListViewItemClickListener addressBookListViewItemClickListener = new ListViewItemClickListener();

    private class ListViewItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            addressBookListView.setItemChecked(position, true);
            addressBookFoodAdapter.setCheckedItemPosition(position);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        return inflater.inflate(R.layout.fragment_food_basic_details, parent, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        produseDB = new ProduseDB(getActivity().getApplicationContext());
        addressBookListView = (ListView) getActivity().findViewById(R.id.address_book_list_view);
        addressBookListView.setOnItemClickListener(addressBookListViewItemClickListener);

        addressBookFoodAdapter = new FoodAdapter(getActivity(), produseDB.getProduse());
        addressBookListView.setAdapter(addressBookFoodAdapter);


        searchFieldEditText = (EditText) getActivity().findViewById(R.id.search_field_edit_text);
        searchFieldEditText.addTextChangedListener(searchFieldEditTextListener);


        Requests.getProduse(new Callback<Collection<Produs>>() {
            @Override
            public void success(Collection<Produs> produses, Response response) {

                //produseDB.stergeProduse();
                if (produseDB.getProduse().size() > 0) {

                    produseDB.stergeProduse();
                }


                for (Produs produs : produses) {
                    produseDB.adaugaProdus(produs);
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        addressBookFoodAdapter.setData(produseDB.getProduse());

                    }
                });


            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Failure");

            }
        });
    }

}
