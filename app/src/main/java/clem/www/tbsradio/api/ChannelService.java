package clem.www.tbsradio.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by laileon on 2017/9/28.
 */

public interface ChannelService {

    @GET("indexLPSD")
    Observable<String> getByRefresh();

    @GET("indexLBCD")
    Observable<String> getByDate();

    @GET("archive/{bangumiName}")
    Observable<String> getBangumiArchive(@Path("bangumiName") String bangumiName);

    @GET("{bangumiName}")
    Observable<String> getBangumiArticle(@Path("bangumiName") String bangumiName);
}
