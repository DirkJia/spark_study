package com.dirkjia.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

public class JavaSparkSql {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("spark-sql")
                .master("local[2]")
                .getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        JavaRDD<String> initdata = jsc.parallelize(Arrays.asList("1,lisi", "2,wangwu"));
        JavaRDD<Row> rowJavaRDD = initdata.map(new Function<String, Row>() {

            @Override
            public Row call(String v1) throws Exception {
                String[] s = v1.split(",");
                return RowFactory.create(s[0], s[1]);
            }
        });

        List<StructField> columns = Arrays.asList(DataTypes.createStructField("id",DataTypes.StringType,true),
                DataTypes.createStructField("name",DataTypes.StringType,true) );
        StructType schema = DataTypes.createStructType(columns);
        Dataset<Row> dataFrame = sparkSession.createDataFrame(rowJavaRDD, schema);
        dataFrame.show();
        dataFrame.registerTempTable("user");
        sparkSession.sql("select * from user where id = '1'").show();
        sparkSession.stop();
    }
}
