package clem.www.tbsradio.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by laileon on 2017/9/28.
 */

public class RetrofitRadioWrapper {
    public static RetrofitRadioWrapper instance;
    public static Retrofit retrofit;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private RetrofitRadioWrapper() {
        retrofit = builder.build();
    }

    public static RetrofitRadioWrapper getInstance() {
        if (instance == null) {
            synchronized (RetrofitWebWrapper.class) {
                instance = new RetrofitRadioWrapper();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service, final String code) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//        //add header
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                Request requst = builder.addHeader("Referer", "https://radiocloud.jp/archive/" + code)
                        .addHeader("Host", "www.spiral-pf.com")
                        .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
                        .build();
                return chain.proceed(requst);
            }
        });
        okHttpClientBuilder.retryOnConnectionFailure(true);
        okHttpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = okHttpClientBuilder.build();
        retrofit = builder.client(okHttpClient).build();
        return retrofit.create(service);
    }

    private static class Constant {
        private static final String BASE_URL = "https://www.spiral-pf.com/";
    }
}
