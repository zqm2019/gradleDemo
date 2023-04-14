//package com.zqm.controller.flink;
//
///**
// * @describe:
// * @author:zqm
// */
//public class FlinkSource implements SourceFunction<Integer> {
//    int i = 0;
//
//    @Override
//    public void run(SourceContext ctx) throws Exception {
//        while (true) {
//            ctx.collect(i++);
//            Thread.sleep(1000);
//        }
//    }
//
//    @Override
//    public void cancel() {
//
//
//}
