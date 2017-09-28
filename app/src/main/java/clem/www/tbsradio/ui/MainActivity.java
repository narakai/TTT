package clem.www.tbsradio.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import clem.www.tbsradio.R;
import clem.www.tbsradio.api.ChannelService;
import clem.www.tbsradio.api.RadioService;
import clem.www.tbsradio.api.RetrofitRadioWrapper;
import clem.www.tbsradio.api.RetrofitWebWrapper;
import clem.www.tbsradio.model.BangumiArchive;
import clem.www.tbsradio.model.BangumiItem;
import clem.www.tbsradio.util.HtmlParser;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.message)
    TextView mTextMessage;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private List<BangumiItem> mBangumiItems = new ArrayList<>();
    private BangumiArchive bangumiArchive;
    private String bangumiName = "";
    private String address = "";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    getBangumiItem();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    //test
                    bangumiName = mBangumiItems.get(0).getBangumiName();
                    getBangumiArchive(bangumiName);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void getBangumiItem() {
        ChannelService service = RetrofitWebWrapper.getInstance().create(ChannelService.class);
        service.getByRefresh().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        mBangumiItems = HtmlParser.getBangumiItem(s);
//                        Log.d(TAG, "onNext: " + mBangumiItems.get(0).getBangumiName());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getBangumiArchive(final String bangumiName) {
        ChannelService service = RetrofitWebWrapper.getInstance().create(ChannelService.class);
        service.getBangumiArchive(bangumiName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        bangumiArchive = HtmlParser.getBangumiArchive(s);

                        Log.d(TAG, "onNext: " + bangumiArchive.getRadioItems().get(0).getDetail());
                        getRadio(bangumiArchive.getRadioItems().get(0).getCode(), bangumiName);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getRadio(String code, String name){
        RadioService radioService = RetrofitRadioWrapper.getInstance().create(RadioService.class, name);
        radioService.getAddress(code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        int index = s.indexOf("m4a?id=");
                        String id = s.substring(index + 7, index + 20);
                        Log.d(TAG, "onNext: " + id);
                        String address = HtmlParser.HTML + id + "/audio.m4a";
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
