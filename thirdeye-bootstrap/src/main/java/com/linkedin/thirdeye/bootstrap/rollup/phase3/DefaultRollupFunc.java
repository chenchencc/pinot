package com.linkedin.thirdeye.bootstrap.rollup.phase3;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkedin.thirdeye.bootstrap.DimensionKey;
import com.linkedin.thirdeye.bootstrap.MetricTimeSeries;
import com.linkedin.thirdeye.bootstrap.rollup.RollupThresholdFunc;
import com.linkedin.thirdeye.bootstrap.rollup.phase1.RollupPhaseOneJob;

/**
 * Default implementation that selects the one rolls up minimum number of
 * dimensions and clears the threshold
 * 
 * @author kgopalak
 * 
 */
public class DefaultRollupFunc implements RollupFunction {
  private static final Logger LOG = LoggerFactory
      .getLogger(DefaultRollupFunc.class);
  @Override
  public DimensionKey rollup(DimensionKey rawDimensionKey,
      Map<DimensionKey, MetricTimeSeries> possibleRollups,
      RollupThresholdFunc func) {
    int minCount = rawDimensionKey.getDimensionsValues().length + 1 ;
    DimensionKey selectedRollup = null;
    LOG.info("Start find roll up for {}", rawDimensionKey);
    for (Entry<DimensionKey, MetricTimeSeries> entry : possibleRollups
        .entrySet()) {
      DimensionKey key = entry.getKey();
      LOG.info("Trying {}", key);
      String[] dimensionsValues = key.getDimensionsValues();
      if (func.isAboveThreshold(entry.getValue())) {
        LOG.info("passedc threshold");
        int count = 0;
        for (String val : dimensionsValues) {
          if ("?".equalsIgnoreCase(val)) {
            count += 1;
          }
        }
        LOG.info("count:{} mincount:{}", count, minCount);
        if (count < minCount) {
          minCount = count;
          selectedRollup = key;
          LOG.info("setting selectedrollup:{}", selectedRollup);
        }
      }
    }
    if(selectedRollup ==null){
      StringBuilder sb = new StringBuilder();
      for (Entry<DimensionKey, MetricTimeSeries> entry : possibleRollups
          .entrySet()) {
        sb.append(entry.getKey());
        sb.append("=");
        sb.append(entry.getValue());
        sb.append("\n");
      }
      LOG.info("cannot find roll up for {} possiblerollups:{}",rawDimensionKey, sb.toString() );
    }
    return selectedRollup;
  }

}
