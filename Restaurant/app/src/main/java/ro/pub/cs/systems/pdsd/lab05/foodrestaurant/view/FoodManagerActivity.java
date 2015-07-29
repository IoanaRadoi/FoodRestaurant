package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.view;

import java.util.ArrayList;
import java.util.Collection;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.bd.ProduseDB;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Masa;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.ProdusComCurenta;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.network.Requests;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.R;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Comanda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FoodManagerActivity extends Activity {

    private ArrayAdapter<ProdusComCurenta> adaptor;
    private ArrayAdapter<String> adaptorSpinner;
    private ListView listaProduse;
    private Spinner listaMese;
    private ProduseDB produseDB;
    private TextView textView;
    private Button btn_trimite;


    private void afisareProduse() {
        ArrayList<ProdusComCurenta> produse = produseDB.getProduseComCur();
        for (ProdusComCurenta p : produse) {
            adaptor.add(p);
        }
    }

    private void afisareMeseSpinner() {
        ArrayList<Masa> mese = produseDB.getMese();
        for (Masa m : mese) {
            adaptorSpinner.add(m.getCod_masa());
        }
    }

    private double totalProduse() {
        double total = 0;
        ArrayList<ProdusComCurenta> produse = produseDB.getProduseComCur();
        for (ProdusComCurenta p : produse) {
            total += p.getPret();
        }
        return total;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.comanda);

        adaptor = new ArrayAdapter<ProdusComCurenta>(this, android.R.layout.simple_list_item_1);
        adaptorSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);

        listaProduse = (ListView) findViewById(R.id.comanda_curenta);
        listaMese = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.total);
        btn_trimite = (Button) findViewById(R.id.btn_trimite);


        listaProduse.setAdapter(adaptor);
        listaMese.setAdapter(adaptorSpinner);

        produseDB = new ProduseDB(this);

        Requests.getMese(new Callback<Collection<Masa>>() {
            @Override
            public void success(Collection<Masa> mese, Response response) {
                if (produseDB.getMese().size() > 0) {
                    produseDB.stergeMese();
                }

                for (Masa masa : mese) {
                    produseDB.adaugaMasa(masa);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        afisareMeseSpinner();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


        afisareProduse();

        textView.setText(totalProduse() + "");

        btn_trimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timp = System.currentTimeMillis();
                Comanda comCurenta = new Comanda();
                comCurenta.setId(1);
                comCurenta.setId_masa(Integer.parseInt(listaMese.getSelectedItem().toString()));
                comCurenta.setId_user(2);
                comCurenta.setTimestmp(timp);
                ArrayList<ProdusComCurenta> prodComCur = produseDB.getProduseComCur();
                ArrayList<Integer> prodId = new ArrayList<Integer>();

                for (ProdusComCurenta p : prodComCur) {
                    prodId.add(produseDB.getProdId(p.getName()));
                }
                comCurenta.setProduse(prodId);

                Requests.sendComanda(comCurenta, new Callback<Integer>() {


                    @Override
                    public void success(Integer integer, Response response) {

                        produseDB.stergeProduseComCur();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                adaptor.clear();
                                Toast.makeText(FoodManagerActivity.this,
                                        "Comanda a fost trimisa! Va rugam asteptati livrarea acesteia!",
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });

        listaProduse.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProdusComCurenta p = adaptor.getItem(i);
                produseDB.stergeProdusComCurenta(p.getId());
                adaptor.clear();
                afisareProduse();
                textView.setText(totalProduse() + "");
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainFoodActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_produse_db, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            Intent intent = new Intent(this, MainFoodActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

}
