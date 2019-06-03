package com.test.sdk.toolsoso;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.test.sdk.toolsoso.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: S
 * @date: 2019/5/29 14:48
 * @description:
 */
public class RxJavaActivity extends Activity {


    String str1 = "hello";
    String str2 = "how are you ?";
    String str3 = "i\'m fine,thank you  ";

    String[] strings = {"Hello", "RxJava", "Nice to meet you", "How are you", "i\'m fine ", " thank you "
            , " and you ", "ok ok ok ", "you can you up", "no can no BB", "diu you shi hu"};
    int[] ints = {1, 2, 3, 4, 5, 8, 6, 9, 11, 5, 77, 8666, 10, 0, 2};

    @BindView(R.id.tv)
    TextView tv;

    String StrCache = "";//模仿保存到缓存里的数据  目前为空代表没有缓存
    boolean isFromCache = false;//标记是否来自于缓存


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);


        // 创建操作符
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrCache = "jhjhjhjhjhj";
                creat();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                just();
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from();
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defer();
            }
        });


        //变换操作符
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer();
            }
        });

        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact();
            }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map();
            }
        });

        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactmap();
            }
        });

        findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatmap();
            }
        });

        findViewById(R.id.btn10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zip();
            }
        });

        findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interval();
            }
        });

        findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromIterable();
            }
        });

        findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeat();
            }
        });

        findViewById(R.id.btn14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer();
            }
        });

        findViewById(R.id.btn15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });

        findViewById(R.id.btn16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                take();
            }
        });

        findViewById(R.id.btn17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnNext();
            }
        });

        findViewById(R.id.btn18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry();
            }
        });

        findViewById(R.id.btn19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distinct();
            }
        });

        findViewById(R.id.btn20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debounce();
            }
        });

        findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all();
            }
        });

        findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeUntil();
            }
        });

        findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipUntil();
            }
        });

        findViewById(R.id.btn24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeWhile();
            }
        });

        findViewById(R.id.btn25).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contains();
            }
        });

        findViewById(R.id.btn26).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultIfEmpty();
            }
        });
    }

    /**
     * defaultIfEmpty 用法
     * 如果没有正常结束事件（onComlete执行），返回默认值
     */
    private void defaultIfEmpty() {
        tv.setText(" === defaultIfEmpty ===\n");
//        Observable.create(new ObservableOnSubscribe < Integer > () {
//            @Override
//            public void subscribe(ObservableEmitter < Integer > e) throws Exception {
//                e.onComplete();
//            }
//        })
//                .defaultIfEmpty(666)
//                .subscribe(new Consumer < Integer > () {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        tv.setText(tv.getText() + "\n" + "输出：" + integer);
//                        LogUtils.e("输出：" + integer);
//                    }
//                });


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(55);
//                emitter.onNext(11);
//                emitter.onNext(33);
                emitter.onComplete();
            }
        })
                .defaultIfEmpty(666)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        tv.setText(tv.getText() + "\n" + "输出：" + integer);
                        LogUtils.e("输出：" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        tv.setText(tv.getText() + "\n" + " === onComplete ===" );
                        LogUtils.e("=== onComplete ===" );
                    }
                });

    }

    /**
     * contains 用法
     * 是否存在特定元素
     */
    private void contains() {
        tv.setText(" === contains ===\n");
        Observable.just(1,3,5)
                .contains(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                tv.setText(tv.getText() + "\n" + "输出：" + aBoolean);
                LogUtils.e("输出：" + aBoolean);
            }
        });

    }


    /**
     * takeWhile 用法
     * 当事件满足设定的条件时，发送事件 只要false一次 就会中断
     */
    private void takeWhile() {
        tv.setText(" === takeWhile ===\n");
        Observable.just(100,3,5,7,999,58,99,100)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer>=100;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        tv.setText(tv.getText() + "\n" + "输出：" + integer);
                        LogUtils.e("输出：" + integer);
                    }
                });
    }

    /**
     * skipUntil 用法
     * 直到设定的条件事件发出之后，开始发送原始事件。
     */
    private void skipUntil() {
        tv.setText(" === skipUntil ===\n");
        Observable.intervalRange(1,10,1,1,TimeUnit.SECONDS)
                .skipUntil(Observable.intervalRange(20,3,5,1,TimeUnit.SECONDS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tv.setText(tv.getText() + "\n" + "输出：" + aLong);
                        LogUtils.e("输出：" + aLong);
                    }
                });
    }

    /**
     * takeUntil 用法
     * 当事件满足设定的条件时，该事件的下一个事件不会被发送了。包含超过临界条件的第一个事件
     */
    private void takeUntil() {
        tv.setText(" === takeUntil ===\n");
        Observable.just(1,3,5,7,999,58,99,100)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer>=100;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        tv.setText(tv.getText() + "\n" + "输出：" + integer);
                        LogUtils.e("输出：" + integer);
                    }
                });

    }

    /**
     * all 用法
     * all	判断被观察者所有事件是否满足某个事件，如果全部满足则返回true，否则返回false
     * takeUntil	当事件满足设定的条件时，该事件的下一个事件不会被发送了。包含超过临界条件的第一个事件
     * takeWhile	当事件满足设定的条件时，发送事件
     * skipUntil	直到设定的条件事件发出之后，开始发送原始事件。
     * skipWhile	跳过while范围内事件
     * amb	多个Observable序列中，只发送第一个
     * contains	是否存在特定元素
     * exists	是否满足特定条件
     * DefaultIfEmpty	如果没有正常结束事件（onComlete执行），返回默认值
     * SequenceEqual	判断两个事件序列是否是相同的数据，相同的顺序，相同的终止状态
     */
    private void all() {
        tv.setText(" === all ===\n");
        Observable.just(1,3,5)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 1;
                    }
                })
                .subscribeOn(Schedulers.io()) // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        tv.setText(tv.getText() + "\n" + "输出：" + aBoolean);
                        LogUtils.e("输出：" + aBoolean);
                    }
                });
    }

    /**
     * debounce 用法
     * 当一个事件发送出来之后，在约定时间内没有再次发送这个事件，则发射这个事件，如果再次触发了，则重新计算时间
     * 需求：在Edittext上添加监听，当里面输入的内容变化后进行搜索。换句话说就是当用户的输入操作停止几秒钟之后再去搜索。
     */
    private void debounce() {
        tv.setText(" === debounce ===\n");

        /**
         * 设置时间为500毫秒
         *  1 发送出去后 过来400毫秒2就又发射了，所以1 被遗弃 500毫秒重新计数
         *  然后过了505毫秒 3才发送出来，因2保留了超过500毫秒 视为有效发送就发送出去了 onNext 2 并重新计数
         *  3保留了100毫秒就 发送了4 ，3同1 保留时间短 3倍遗弃，定位到4
         *  发送了4 过了605毫秒超过500毫秒 4同2 视为有效发送就发送出去了 onNext 4 并重新计数
         *  发送5后 后面没有操作 ，onNext 5
         */

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time waite
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        })

                .debounce(500, TimeUnit.MILLISECONDS) // Run on a background thread
                .subscribeOn(Schedulers.io()) // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        tv.setText(tv.getText() + "\n" + "真正的输出：" + integer);
                        LogUtils.e("真正的输出：" + integer);
                    }
                });


    }

    /**
     * distinct 去重
     */
    private void distinct() {
        tv.setText(" === etry ===\n");
        List<String> list = new ArrayList<>();
        list.add("100");
        list.add("595");
        list.add("sddd");
        list.add("2");
        list.add("2");
        list.add("595");
        Observable.just(list)
                .flatMap(new Function<List<String>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<String> strings) throws Exception {
                        return Observable.fromIterable(strings);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        tv.setText(tv.getText() + "\n" + "真正的输出：" + o);
                        LogUtils.e("真正的输出：" + o);
                    }
                });
    }

    /**
     * retry 用法
     * etry() ： 出现错误时，让被观察者重新发送数据。若错误一直发生，则一直重新发送
     * retry(long time)：与retry不同的书，若错误一直发生，被观察者则一直重新发送数据，但这持续重新发送有次数限制
     * retry(Predicate predicate) ： 出现错误时，根据指定逻辑(可以捕获到发生的错误)决定是否让被观察者重新发送数据
     * retryUntil 遇到错误时根据制定规则选择是否重发
     * retryWhen 遇到错误时，将发生的错误传递给一个新的被观察者(Observable)，并决定是否需要重新订阅原始被观察者(Observable)
     * repeat 重复发射 observable的数据序列，可以使无限次也可以是指定次数.不传时为重复无限次。
     * <p>
     * repeatWhen 遇到错误选择返回object给新观察者或中止事件
     * 返回参数选择：
     * Observable.empty()； 发送Complete事件，但不会回调观察者的Complete（）
     * onComplete（）  直接完成。
     * Observable.error(new Throwable("不再重新订阅事件"));
     * Observable.just(1);   继续发送事件。
     */
    private void retry() {
        tv.setText(" === etry ===\n");
        List<String> list = new ArrayList<>();
        list.add("100");
        list.add("595");
        list.add("sddd");
        list.add("2");
        list.add("3gg");
        list.add("9999");
        Observable.just(list)
                .retry()
                .flatMap(new Function<List<String>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<String> strings) throws Exception {
                        return Observable.fromIterable(strings);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        tv.setText(tv.getText() + "\n" + "额外的工作：" + o);
                        LogUtils.e("额外的工作：" + o);
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        tv.setText(tv.getText() + "\n" + "真正的输出：" + o);
                        LogUtils.e("真正的输出：" + o);
                    }
                });
    }

    /**
     * doOnNext 用法
     * 输出结果前 可额外干些事情
     * doOnEach() :当Observable每发送一次事件就会调用一次(包含onNext()，onError()，onComplete())
     * doOnNext(): 执行 onNext()前调用
     * doAfterNext()： 执行onNext()后调用
     * doOnComplete()：执行onComplete()前调用
     * doOnError():执行 onError()前调用
     * doOnTerminate(): 执行终止(无论正常发送完毕/异常终止)
     * doFinally(): 最后执行
     * doOnSubscribe() ：观察者订阅是调用
     * doOnUnScbscribe()： 观察者取消订阅时调用
     */
    private void doOnNext() {
        tv.setText(" === doOnNext ===\n");
        List<String> list = new ArrayList<>();
        list.add(str1);
        list.add(str2);
        list.add(str3);
        Observable.just(list)
                .flatMap(new Function<List<String>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<String> strings) throws Exception {
                        return Observable.fromIterable(strings);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        tv.setText(tv.getText() + "\n" + "额外的工作：" + o);
                        LogUtils.e("额外的工作：" + o);
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        tv.setText(tv.getText() + "\n" + "真正的输出：" + o);
                        LogUtils.e("真正的输出：" + o);
                    }
                });


    }

    /**
     * take 用法
     * 输出指定数量的结果
     * filter	自定义筛选条件，返回boolean
     * distinct	去重
     * distinctUntilChanged	过滤连续相同事件
     * skip，skipLast	跳过前n个事件或最后n个
     * take和takeLast	只接收前n个事件或最后n个
     * elementAt和elementAtOrError	前者只发送第n个，可设置默认值，不抛异常；后者越界抛异常。
     * ignoreElements	只接收完成和报错信息
     * distinct	去重
     * ofType	指定接收数据类型
     * throttleFirst/throttleLast	只接收指定时间内第一个或最后一个事件
     */
    private void take() {
        tv.setText(" === take ===\n");
        List<String> list = new ArrayList<>();
        list.add(str1);
        list.add(str2);
        list.add(str3);
        Observable.just(list)
                .flatMap(new Function<List<String>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<String> strings) throws Exception {
                        return Observable.fromIterable(strings);
                    }
                })
                .take(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        tv.setText(tv.getText() + "\n" + "输出：" + o);
                        LogUtils.e("输出：" + o);
                    }
                });
    }

    /**
     * filter 用法
     * 过滤
     */
    private void filter() {
        tv.setText(" === filter ===\n");
        List<String> list = new ArrayList<>();
        list.add(str1);
        list.add(str2);
        list.add(str3);
        Observable.just(list)
                .flatMap(new Function<List<String>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<String> strings) throws Exception {
                        return Observable.fromIterable(strings);
                    }
                })
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        String str = (String) o;
                        if (str.equals(str2)) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        tv.setText(tv.getText() + "\n" + "满足要求的是：" + o);
                        LogUtils.e("满足要求的是" + o);
                    }
                });


    }

    /**
     * timer
     * 定时器
     */
    private void timer() {
        tv.setText(" === timer ===\n");
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tv.setText(tv.getText() + "\n" + "三秒后输出");
                        LogUtils.e("strings内容为：三秒后输出");
                    }
                });
    }

    /**
     * repeat 用法
     */
    private void repeat() {
        tv.setText(" === repeat ===\n");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(99999);
                for (int i = 0; i < 4; i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        })
                .repeat(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        tv.setText(tv.getText() + "\n" + integer);
                        LogUtils.e("strings内容为：" + integer);
                    }
                });
    }

    /**
     * fromIterable 用法
     * 将list item逐个发射
     */
    private void fromIterable() {
        tv.setText(" === fromIterable ===\n");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        Observable.fromIterable(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        tv.setText(tv.getText() + "\n" + s);
                        LogUtils.e("strings内容为：" + s);
                    }
                });
    }

    /**
     * interval 用法
     * 轮询 按固定时间间隔发射
     */
    private void interval() {
        tv.setText(" === interval ===\n");
        Disposable mDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tv.setText(tv.getText() + "\n" + aLong);
                        LogUtils.e("内容为：" + aLong);
                    }
                });


        /**
         * 销毁时停止心跳
         */

        /*
        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (mDisposable != null){
                mDisposable.dispose();
            }
        }
        */
    }

    /**
     * zip 用法
     * 合并后一起发射
     */
    private void zip() {
        tv.setText(" === zip ===\n");
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("哈哈");
                emitter.onNext("嘿嘿");
                emitter.onComplete();
            }
        });

        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);//多出来的不会组合发射 直接无视
                emitter.onComplete();
            }
        });
        Observable.zip(observable1, observable2, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return "合并后==>" + s + "---" + integer;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(tv.getText() + "\n" + s);
                        LogUtils.e("内容为：" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });
    }

    /**
     * contactmap 用法
     * 依次发射
     */
    private void contactmap() {
        tv.setText(" === contactmap ===\n");

        //实现多个网络请求依次依赖 模仿先获取到列表 然后根据列表的信息再去获取每一项的详情
        Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter) throws Exception {
                List<Student> students = new ArrayList<Student>();

                Student s1 = new Student(101);
                s1.setName("臭臭");
                s1.setAge(18);
                s1.setSex("男");
                Student s2 = new Student(102);
                s2.setName("香香");
                s2.setAge(22);
                s2.setSex("女");
                students.add(s1);
                students.add(s2);
                for (Student s : students) {
                    emitter.onNext(s);//发送多个事件
                }
                emitter.onComplete();
            }
        })

                .concatMap(new Function<Student, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Student student) throws Exception {
                        List<String> lists = new ArrayList<String>();
                        lists.add(student.getName());
                        lists.add(student.getSex());
                        return Observable.fromIterable(lists).delay(10, TimeUnit.NANOSECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(tv.getText() + "\n" + s);
                        LogUtils.e("内容为：" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });

    }


    /**
     * flatmap 用法
     * 依次发射
     */
    private void flatmap() {
        tv.setText(" === flatmap ===\n");

        //实现多个网络请求依次依赖 模仿先获取到列表 然后根据列表的信息再去获取每一项的详情
        Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter) throws Exception {
                List<Student> students = new ArrayList<Student>();

                Student s1 = new Student(101);
                s1.setName("臭臭");
                s1.setAge(18);
                s1.setSex("男");
                Student s2 = new Student(102);
                s2.setName("香香");
                s2.setAge(22);
                s2.setSex("女");
                students.add(s1);
                students.add(s2);
                for (Student s : students) {
                    emitter.onNext(s);//发送多个事件
                }
                emitter.onComplete();
            }
        })

                .flatMap(new Function<Student, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Student student) throws Exception {
                        List<String> lists = new ArrayList<String>();
                        lists.add(student.getName());
                        lists.add(student.getSex());
                        return Observable.fromIterable(lists).delay(10, TimeUnit.NANOSECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(tv.getText() + "\n" + s);
                        LogUtils.e("内容为：" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });
    }

    /**
     * map 用法
     * 转换格式
     */
    private void map() {
        tv.setText(" === map ===\n");
        Observable.just(12, 5, 8, 9, 7, 5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {

                        LogUtils.e("数字为：" + integer);
                        return "数字为：" + integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(tv.getText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });

    }

    /**
     * contact
     * 先执行如果true则下一个不执行
     */
    private void contact() {
        tv.setText(" === contact ===\n");
        Observable<String> cache = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                /*
                ......
                做读取缓存的操作,
                如果缓存为空则onComplete告诉订阅者执行下一个Observable（这里只指从网络获取）
                如果缓存不为空则onNext告诉订阅者不需要执行下一个Observable（这里只指从网络获取）
                 */

                if (StringUtils.isSpace(StrCache)) { //缓存为空
                    isFromCache = false;
                    emitter.onComplete();
                } else {
                    isFromCache = true;
                    emitter.onNext(StrCache);
                }
            }
        });

        Observable<String> network = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                /*
                ......
                读取网络数据 并保存到缓存里
                 */
                LogUtils.e("3242424=========");
                emitter.onNext("数据数据数据数据");
                emitter.onComplete();
            }
        });

        Observable.concat(cache, network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (!isFromCache) {//false  说明这数据不是来自于网络
                            StrCache = s;
                            tv.setText(tv.getText() + "\n" + "网络：");
                        } else {
                            tv.setText(tv.getText() + "\n" + "缓存：");
                        }
                        tv.setText(tv.getText() + "\n" + StrCache);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        if (!isFromCache){//false  说明这数据不是来自于网络
//                            StrCache = s;
//                            tv.setText(tv.getText() + "\n" + "网络：");
//                        }else{
//                            tv.setText(tv.getText() + "\n" + "缓存：");
//                        }
//                        tv.setText(tv.getText() + "\n" + StrCache);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        LogUtils.e(" == 获取数据失败！！！！==");
//                        tv.setText(tv.getText() + "\n" + throwable.toString());
//                    }
//                });

    }

    /**
     * buffer 用法
     * 分批发送事件
     */
    private void buffer() {
        tv.setText(" === buff ===\n");
        Observable.fromArray(1, 2, 5, 8, 99, 0, 35, 33)
                .buffer(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        String s = "";
                        for (int i = 0; i < integers.size(); i++) {
                            LogUtils.e("TAG", "onNext=========" + s);
                            s += "【" + integers.get(i) + "】";
                        }
                        tv.setText(tv.getText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("TAG", "onError=========" + e.toString());
                        tv.setText(tv.getText() + "\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("TAG", "onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });
    }

    /**
     * defer 用法
     */
    private void defer() {
        tv.setText("=== defer ===\n");
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                SystemClock.sleep(5000);
                return Observable.fromArray(strings);
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e("TAG", "onNext=========" + s);
                        tv.setText(tv.getText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("TAG", "onError=========" + e.toString());
                        tv.setText(tv.getText() + "\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("TAG", "onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });
    }

    /**
     * from 用法
     */
    private void from() {
        tv.setText("=== from ===\n");

        Observable.fromArray(strings)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e("TAG", "onNext=========" + s);
                        tv.setText(tv.getText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("TAG", "onError=========" + e.toString());
                        tv.setText(tv.getText() + "\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("TAG", "onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });
    }

    /**
     * just 用法
     */
    private void just() {

//        List<String> list = new ArrayList<>();
//        list.add(str1);
//        list.add(str2);
//        list.add(str3);

        tv.setText("=== just ===\n");
        Observable.just(str1, str2, str3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e("TAG", "onNext=========" + s);
                        tv.setText(tv.getText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("TAG", "onError=========" + e.toString());
                        tv.setText(tv.getText() + "\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("TAG", "onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });

    }

    /**
     * creat
     */
    private void creat() {

        tv.setText(" === creat ===\n");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(str1);
                emitter.onNext(str2);
                emitter.onNext(str3);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e("onSubscribe=========");
                    }

                    public void onNext(String s) {
                        LogUtils.e("onNext=========" + s);
                        tv.setText(tv.getText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError=========" + e.toString());
                        tv.setText(tv.getText() + "\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete=========");
                        tv.setText(tv.getText() + "\n" + "== onComplete ==");
                    }
                });
    }

}
