package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.view;

import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.bd.ProduseDB;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.controller.FoodAdapter;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Produs;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.ProdusComCurenta;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.R;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainFoodActivity extends Activity {
    private ProduseDB produseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_food_activity);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contact_basic_details, new FoodBasicDetailsFragment());
        fragmentTransaction.commit();

        produseDB = new ProduseDB(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.address_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        ListView addressBookListView = (ListView) findViewById(R.id.address_book_list_view);
        FoodAdapter addressBookFoodAdapter = (FoodAdapter) addressBookListView.getAdapter();

        int id = item.getItemId();
        if (id == R.id.action_insert) {
            if (addressBookFoodAdapter.getCheckedItemPosition() != -1) {
                Produs foodModel = addressBookFoodAdapter.getData().get(addressBookFoodAdapter.getCheckedItemPosition());
                ProdusComCurenta p = new ProdusComCurenta();
                p.setName(foodModel.getNume());
                p.setPret(foodModel.getPret());
                produseDB.adaugaProdusComCur(p);
                Toast.makeText(MainFoodActivity.this,
                        "Produsul a fost adaugat!",
                        Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        if (id == R.id.action_update) {
            Intent intent = new Intent(this, FoodManagerActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
