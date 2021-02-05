package com.dirkjia.spark;

import org.apache.spark.Partitioner;

public class MyParitition extends Partitioner {

    private int numpartition;

    public MyParitition(int numpartition){
        this.numpartition=numpartition;
    }

    public int numPartitions() {
        return numpartition;
    }

    public int getPartition(Object o) {
        if(o == null){
            return 0;
        }
        if(o.equals("lisi")){
            return 1;
        }
        return o.toString().hashCode() % numpartition;
    }

}
