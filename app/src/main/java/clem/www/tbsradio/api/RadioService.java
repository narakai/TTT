package clem.www.tbsradio.api;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by laileon on 2017/9/28.
 */

public interface RadioService {
    @POST("embed/{code}")
    Observable<String> getAddress(@Path("code") String code);
}
