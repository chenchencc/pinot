package com.linkedin.thirdeye.bootstrap;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * 
 * @author kgopalak
 * 
 */
public class MetricTimeSeriesTest {
  @Test
  public void testSimple() throws Exception {
    List<String> names = Lists.newArrayList("metric1", "metric2", "metric3",
        "metric4", "metric5");
    List<MetricType> types = Lists.newArrayList(MetricType.INT, MetricType.INT,
        MetricType.INT, MetricType.INT, MetricType.INT);
    MetricSchema schema = new MetricSchema(names, types);
    MetricTimeSeries series = new MetricTimeSeries(schema);
    long startHourSinceEpoch = TimeUnit.HOURS.convert(
        System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    Random rand = new Random();
    int NUM_TIME_WINDOWS = 100;
    int[][] data = new int[NUM_TIME_WINDOWS][];
    for (int i = 0; i < NUM_TIME_WINDOWS; i++) {
      data[i] = new int[names.size()];
      for (int j = 0; j < names.size(); j++) {
        String name = names.get(j);
        int value = Math.abs(rand.nextInt(5000));
        data[i][j] = value;
        series.set(startHourSinceEpoch + i, name, value);
      }
    }
    System.out.println(series);
    // serialize to bytes
    byte[] bytes = series.toBytes();
    MetricTimeSeries newSeries = MetricTimeSeries.fromBytes(bytes, schema);
    System.out.println(newSeries);
    Assert.assertEquals(newSeries.toBytes(), bytes);

    MetricTimeSeries aggSeries = new MetricTimeSeries(schema);
    aggSeries.aggregate(series);
    aggSeries.aggregate(newSeries);
    Assert.assertEquals(aggSeries.timeseries.size(), series.timeseries.size());

    for (long timeWindow : series.timeseries.keySet()) {
      for (int j = 0; j < names.size(); j++) {
        String name = names.get(j);
        int v1 = series.get(timeWindow, name);
        int v2 = newSeries.get(timeWindow, name);
        Assert.assertEquals(v1, v2);
        int v3 = aggSeries.get(timeWindow, name);
        Assert.assertEquals(v3, v1+v2);
      }

    }

  }
}
