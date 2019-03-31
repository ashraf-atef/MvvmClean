package com.example.mvvmclean.common.datalayer.remote.rxHelper;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BackgroundSchedulers<Upstream, Downstream> implements SingleTransformer<Upstream, Downstream> {
    private Scheduler subscribeScheduler;
    private Scheduler observeScheduler;

    public BackgroundSchedulers(Scheduler subscribeScheduler, Scheduler observeScheduler) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
    }

    public BackgroundSchedulers() {
      this(Schedulers.io(), AndroidSchedulers.mainThread());
    }

    @Override
    public SingleSource<Downstream> apply(Single<Upstream> upstream) {
        return (SingleSource<Downstream>) upstream.subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler);
    }
}
