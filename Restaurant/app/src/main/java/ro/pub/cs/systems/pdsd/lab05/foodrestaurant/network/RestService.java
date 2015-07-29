package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.network;

import java.util.Collection;

import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Comanda;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Masa;
import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model.Produs;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Ioana.Radoi on 2/20/2015.
 */
public interface RestService {

    @POST("/ComenziRestaurant/task3")
    public void sendComanda(@Body Comanda comanda, Callback<Integer> callback);

    @GET("/ComenziRestaurant/task1")
    public void getProduse(Callback<Collection<Produs>> callback);

    @GET("/ComenziRestaurant/task2")
    public void getMese(Callback<Collection<Masa>> callback);




}
