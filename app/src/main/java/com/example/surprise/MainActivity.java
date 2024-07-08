package com.example.surprise;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.surprise.API.ApiClient;
import com.example.surprise.API.Producto;
import com.example.surprise.Adapter.ProductoAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private List<Producto> productos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productoAdapter = new ProductoAdapter(productos);
        recyclerView.setAdapter(productoAdapter);

        cargarProductos();
    }

    private void cargarProductos() {
        String url = "https://surprisebackend.onrender.com/productos/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray productosArray = response.getJSONArray("producto");
                        Gson gson = new Gson();
                        productos.clear();
                        productos.addAll(gson.fromJson(productosArray.toString(),
                                new TypeToken<List<Producto>>(){}.getType()));
                        productoAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(MainActivity.this, "Error al cargar los productos", Toast.LENGTH_SHORT).show()
        );

        ApiClient.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}