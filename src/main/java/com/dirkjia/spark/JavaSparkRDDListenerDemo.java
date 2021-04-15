package com.dirkjia.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerJobEnd;
import org.apache.spark.scheduler.SparkListenerJobStart;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class JavaSparkRDDListenerDemo {

    private static class MySparkListener extends SparkListener {
        @Override
        public void onJobStart(SparkListenerJobStart jobStart) {
            super.onJobStart(jobStart);
            System.out.println("==================start==========================");
            System.out.println(jobStart.jobId());
            System.out.println(jobStart.stageIds());
        }

        @Override
        public void onJobEnd(SparkListenerJobEnd jobEnd) {
            super.onJobEnd(jobEnd);
            System.out.println("====================end===========================");
        }
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("sparkRddTest").setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        jsc.setLogLevel("WARN");

        jsc.setJobGroup("1","jobid");
        System.out.println("app ID :  "+jsc.getConf().getAppId());


        jsc.sc().addSparkListener(new MySparkListener());

        JavaRDD<String> parallelize = jsc.parallelize(Arrays.asList("zhangsan", "lisi", "xiaoming", "zhangsan"));
        JavaRDD<String> parallelize2 = jsc.parallelize(Arrays.asList("zhangsan", "lisi", "xiaoming", "hanmeimei"));

        JavaPairRDD<String, Integer> pairRDD = parallelize.flatMapToPair(new PairFlatMapFunction<String, String, Integer>() {
            @Override
            public Iterator<Tuple2<String, Integer>> call(String s) throws Exception {
                return Arrays.asList(new Tuple2<>(s, 1)).iterator();
            }
        });
        JavaPairRDD<String, Integer> reduceByKeyRDD = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        reduceByKeyRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                System.out.println("key: " + stringIntegerTuple2._1+ " value: " +stringIntegerTuple2._2);
            }
        });
        JavaPairRDD<String, Integer> load1 = parallelize.flatMapToPair(new PairFlatMapFunction<String, String, Integer>() {
            @Override
            public Iterator<Tuple2<String, Integer>> call(String s) throws Exception {
                return Arrays.asList(new Tuple2<>(s, 1)).iterator();
            }
        });
        JavaPairRDD<String, Integer> load2 = parallelize2.flatMapToPair(new PairFlatMapFunction<String, String, Integer>() {
            @Override
            public Iterator<Tuple2<String, Integer>> call(String s) throws Exception {
                return Arrays.asList(new Tuple2<>(s, 2)).iterator();
            }
        });
        load2.aggregateByKey("", new Function2<String, Integer, String>() {
            @Override
            public String call(String v1, Integer v2) throws Exception {
                return null;
            }
        }, new Function2<String, String, String>() {
            @Override
            public String call(String v1, String v2) throws Exception {
                return null;
            }
        });
        //缓存
        //load1.persist(StorageLevel.MEMORY_AND_DISK_2());
        load1.leftOuterJoin(load2).foreach(new VoidFunction<Tuple2<String, Tuple2<Integer, Optional<Integer>>>>() {
            @Override
            public void call(Tuple2<String, Tuple2<Integer, Optional<Integer>>> stringTuple2Tuple2) throws Exception {
                Thread.sleep(1000);
                System.out.println(stringTuple2Tuple2._1 + "---------" +stringTuple2Tuple2._2._1 + "-------"+ stringTuple2Tuple2._2._2.get());
            }
        });

        jsc.stop();
    }
}
