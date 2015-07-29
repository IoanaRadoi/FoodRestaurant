package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.network;


import java.util.Collection;

import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Comanda;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Masa;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Produs;
import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Ioana.Radoi on 2/20/2015.
 */
public class Requests {
    private static RestAdapter restAdapter = new RestAdapter.Builder()
            //.setEndpoint("http://10.1.1.2:8080")
            .setEndpoint("http://192.168.100.64:8080")

            .build();

    private static RestService service = restAdapter.create(RestService.class);

    public static void sendComanda(Comanda comanda, Callback<Integer> callback) {
        service.sendComanda(comanda, callback);
    }

    public static void getProduse(Callback<Collection<Produs>> callback) {
        service.getProduse(callback);
    }

    public static void getMese(Callback<Collection<Masa>> callback) {
        service.getMese(callback);
    }


}
