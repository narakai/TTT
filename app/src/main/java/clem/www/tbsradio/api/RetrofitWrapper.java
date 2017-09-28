package clem.www.tbsradio.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：GXL on 2016/8/3 0003
 * 博客: http://blog.csdn.net/u014316462
 * 作用：FoodService的Retrofit包装类
 */
public class RetrofitWrapper {
    public static RetrofitWrapper instance;
    public static Retrofit retrofit;

    private RetrofitWrapper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitWrapper getInstance() {
        if (instance == null) {
            synchronized (RetrofitWrapper.class) {
                instance = new RetrofitWrapper();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    public class Constant {
        public static final String BASE_URL = "https://radiocloud.jp/";
    }
}

