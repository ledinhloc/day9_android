package com.example.bt_viewpager_cricleindicator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    ImagesViewPager2Adapter adapter;
    private List<ImagesSlider> imagesList;

    private SliderView sliderView;
    private ArrayList<Integer> arrayList;
    private SliderAdapter sliderAdapter;

    //autorun

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == imagesList.size()-1){
                viewPager2.setCurrentItem(0);
            }
            else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //cách 1: viewPager circleIndicator
        viewPager2 = findViewById(R.id.viewpage2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);

        //imagesList = getListImages();
        imagesList = new ArrayList<>();
        getListImages();
        adapter = new ImagesViewPager2Adapter(imagesList);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);

//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                handler.removeCallbacks(runnable);
//                handler.postDelayed(runnable, 3000);
//            }
//        });
        viewPager2.registerOnPageChangeCallback(pageChangeCallback);
        viewPager2.setPageTransformer(new DepthPageTransformer());

        //cách 2 sliderView
        sliderView = findViewById(R.id.imageSlider);
        arrayList = new ArrayList<>();
        arrayList.add(R.drawable.shoppe1);
        arrayList.add(R.drawable.shoppe2);
        arrayList.add(R.drawable.shoppe3);
        arrayList.add(R.drawable.shoppe4);
        //getData();

        sliderAdapter = new SliderAdapter(getApplicationContext(), arrayList);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(getResources().getColor(R.color.red));
        sliderView.startAutoCycle();
        sliderView.setScrollTimeInSec(5);
    }

//    private void getData() {
//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//        Call<MessageModel> call = apiService.lo
//    }

//    private List<Images> getListImages() {
//        List<Images> list = new ArrayList<>();
//        list.add(new Images(R.drawable.quangcao));
//        list.add(new Images(R.drawable.coffee));
//        list.add(new Images(R.drawable.coffee2));
//        list.add(new Images(R.drawable.monan));
//        return list;
//    }
    private void getListImages(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MessageModel> call = apiService.LoadImageSlider(1);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if(response.isSuccessful() && response.body() != null){
                    MessageModel messageModel = response.body();

                    imagesList.clear();
                    imagesList.addAll(messageModel.getResult());
                    adapter.notifyDataSetChanged();
                    circleIndicator3.setViewPager(viewPager2);
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {

            }
        });

    }

    private ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 3000);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        viewPager2.unregisterOnPageChangeCallback(pageChangeCallback);
    }
}