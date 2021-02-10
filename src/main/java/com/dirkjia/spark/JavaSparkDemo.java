package com.dirkjia.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;

public class JavaSparkDemo {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf();
        conf.setAppName("javasparkdemo").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD<String> lines = jsc.textFile("data");

        JavaRDD initData = lines.flatMap(new FlatMapFunction<String,String>() {
            public Iterable<String> call(String s) throws Exception {
                return Arrays.asList(s.split("\t",-1));
            }
        });

        JavaPairRDD<String,Integer> mapdata = initData.mapToPair(new PairFunction<String,String,Integer>() {

            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });


        JavaPairRDD<String,Integer> reduceData = mapdata.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        reduceData.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                System.out.println(stringIntegerTuple2._1 + "  "  + stringIntegerTuple2._2);
            }
        });
        jsc.close();
    }
}
