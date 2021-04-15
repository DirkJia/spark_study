package com.dirkjia.spark;

import org.apache.flink.api.common.time.Time;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

public class JavaSparkStreaming {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("hadoop.home.dir","E:\\hsj\\mygit\\spark_study\\win");


        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("stream-test")
                .setMaster("local[2]");
        JavaStreamingContext jsc = new JavaStreamingContext(sparkConf, Durations.seconds(3));
        JavaReceiverInputDStream<String> lines = jsc.socketTextStream("localhost",9999, StorageLevel.MEMORY_AND_DISK_SER());

        //jsc.checkpoint("temp");

        JavaPairDStream<String, Integer> words = lines.flatMap(x -> Arrays.asList(x.split(",")).iterator())
                .filter(x -> !x.equals("北京"))
                .mapToPair(x -> new Tuple2<>(x, 1));
        //test1
        /*words.reduceByKey((x,y)->x+y).print();*/

        //test2
        /*words.updateStateByKey(new Function2<List<Integer>, Optional<Integer>, Optional<Integer>>(){
            @Override
            public Optional<Integer> call(List<Integer> v1, Optional<Integer> v2) throws Exception {
                int updateValue = 0;
                if(v2.isPresent()){
                    updateValue = v2.get();
                } else {
                    for(int i : v1){
                        updateValue+=i;
                    }
                }
                return Optional.of(updateValue);
            }
        }).print();*/

        words.reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        },Durations.seconds(15),Durations.seconds(30)).print();


        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }
}
