package com.tutoriales.simplepicasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.tutoriales.simplepicasso.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_INTERNET = "https://img.youtube.com/vi/1FPAtnTOHUA/maxresdefault.jpg";
    private static final String URL_INTERNET_PICASO= "https://images.samsung.com/is/image/samsung/co-uhd-tu8000-un43tu8000kxzl-frontblack-228765419?$684_547_PNG$";
    private ActivityMainBinding binding;
    private ProductRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try{
            loadImageWithPlaceHolderWithPicasso();
            configureRecycler();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void loadImageByInternetUrlWithPicasso(){
        Picasso.get()
                .load(URL_INTERNET)
                .into(binding.imageViewMain);
    }

    private void loadImageWithResizeWithPicasso(){
        Picasso.get()
                .load(URL_INTERNET)
                .resize(200, 200)
                .into(binding.imageViewMain);
    }

    private void loadImageWithResizeCenterCrop(){
        Picasso.get()
                .load(URL_INTERNET)
                .resize(200, 200)
                .centerCrop()
                .into(binding.imageViewMain);
    }

    private void loadImageWithResizeCenterInside(){
        Picasso.get()
                .load(URL_INTERNET)
                .resize(200, 200)
                .centerInside()
                .into(binding.imageViewMain);
    }

    private void loadImageWithScaleDownWithPicasso(){
        Picasso.get()
                .load(URL_INTERNET)
                .resize(2000, 2000)
                .onlyScaleDown()
                .into(binding.imageViewMain);
    }

    //Trabaja con el cache en el celular
    private void loadImageWithOnlyCacheWithPicasso(){
        Picasso.get()
                .load(URL_INTERNET_PICASO)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(binding.imageViewMain);
    }

    //No utiliza el cache, siempre consulta directo de internet
    private void loadImageWithOutCacheWithPicasso(){
        Picasso.get()
                .load(URL_INTERNET)
                .fetch(new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.get()
                                .load(URL_INTERNET)
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE)
                                .into(binding.imageViewMain);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    //Permite mostrar una imagen temporal mientras se carga la imagen de internet
    private void loadImageWithPlaceHolderWithPicasso(){
        Picasso.get()
                .load(URL_INTERNET_PICASO)
                .placeholder(R.mipmap.ic_launcher)
                .into(binding.imageViewMain);
    }

    private void configureRecycler(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Televisor HD LG","https://home.ripley.com.pe/Attachment/WOP_5/2018232894777/2018232894777-1.jpg"));
        productList.add(new Product("Refrigeradora LG","https://www.lg.com/pe/images/refrigeradoras/md07521758/md07521758-350x350.jpg"));
        productList.add(new Product("Microondas Samsung","https://home.ripley.com.pe/Attachment/WOP_5/2019140538104/2019140538104-1.jpg"));

        recyclerAdapter = new ProductRecyclerAdapter();
        recyclerAdapter.setProductList(productList);

        binding.recyclerViewImages.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewImages.setHasFixedSize(true);
        binding.recyclerViewImages.setAdapter(recyclerAdapter);
    }
}