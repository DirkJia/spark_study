package com.dirkjia.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

import java.util.Arrays;

public class JavaSparkLambdaDemo {

    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","E:\\hsj\\mygit\\spark_study\\win");
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[1]")
                .setAppName("lambda-spark-test");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);

        JavaRDD<String> initData = jsc.textFile("data");
        initData.flatMap(x->Arrays.asList(x.split("\t")))
                .filter(x->!x.equals("北京"))
                .mapToPair(x->new Tuple2<>(x,1))
                .reduceByKey((x,y)->x+y)
                .foreach(x-> System.out.println(x));

        jsc.close();
    }
}
