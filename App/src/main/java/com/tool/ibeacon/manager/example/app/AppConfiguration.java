package com.tool.ibeacon.manager.example.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.google.gson.GsonBuilder;
import com.logg.Logg;
import com.logg.config.LoggConfiguration;
import com.tool.common.base.delegate.ApplicationLifecycles;
import com.tool.common.di.module.AppConfigModule;
import com.tool.common.di.module.AppModule;
import com.tool.common.di.module.HttpModule;
import com.tool.common.http.NetworkHandler;
import com.tool.common.http.interceptor.LoggingInterceptor;
import com.tool.common.integration.ConfigModule;
import com.tool.common.utils.ProjectUtils;
import com.tool.ibeacon.manager.example.BuildConfig;
import com.tool.ibeacon.manager.example.app.api.Api;

import java.io.File;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * App全局配置信息在此配置，需要将此实现类声明到AndroidManifest中
 */
public class AppConfiguration implements ConfigModule {

    @Override
    public void applyOptions(final Context context, AppConfigModule.Builder builder) {
        builder
                .httpUrl(Api.APP_DOMAIN)
                .cacheFile(new File(ProjectUtils.CACHE))
                .networkHandler(new NetworkHandler() { // Http全局响应结果的处理类

                    @Override
                    public Request onHttpRequest(Interceptor.Chain chain, Request request) {
                        // 请求服务器之前可以拿到Request,做一些操作比如给Request统一添加Token或者Header，不做任务操作则直接返回Request
                        // return chain.request().newBuilder().header("token", tokenId).build();
                        return request;
                    }

                    @Override
                    public Response onHttpResponse(String result, Interceptor.Chain chain, Request request, Response response) {

                        return response;
                    }
                })
                .interceptors(new Interceptor[]
                        {
                                new LoggingInterceptor()
                        })
                .retrofitConfiguration(new HttpModule.RetrofitConfiguration() {// 扩展自定义配置Retrofit参数

                    @Override
                    public void configRetrofit(Context context, Retrofit.Builder builder) {

                    }
                })
                .okHttpConfiguration(new HttpModule.OkHttpConfiguration() {// 扩展自定义配置OkHttp参数
                    @Override
                    public void configOkHttp(Context context, OkHttpClient.Builder builder) {
                        // builder.sslSocketFactory(SSL.createSSLSocketFactory(),new TrustAllX509TrustManager());
                        // builder.hostnameVerifier(new TrustAllHostnameVerifier());
                    }
                })
                .gsonConfiguration(new AppModule.GsonConfiguration() {// 扩展自定义配置Gson参数
                    @Override
                    public void configGson(Context context, GsonBuilder builder) {
                        builder
                                .serializeNulls();// 支持序列化null的参数
                    }
                })
        ;
    }

    @Override
    public void injectAppLifecycle(Context context, List<ApplicationLifecycles> lifecycleManager) {
        // AppDelegateManager.Lifecycle的所有方法都会在基类Application对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        lifecycleManager.add(new ApplicationLifecycles() {

            @Override
            public void onCreate(Application application) {
                LoggConfiguration configuration = new LoggConfiguration.Buidler()
                        .setDebug(BuildConfig.DEBUG_FLAG)
                        .build();
                Logg.init(configuration);
            }

            @Override
            public void onTerminate(Application application) {

            }
        });
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        lifecycles.add(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {

            }
        });
    }
}