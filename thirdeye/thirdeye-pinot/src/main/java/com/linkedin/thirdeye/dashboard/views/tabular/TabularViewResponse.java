/**
 * Copyright (C) 2014-2018 LinkedIn Corp. (pinot-core@linkedin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linkedin.thirdeye.dashboard.views.tabular;

import java.util.List;
import java.util.Map;

import com.linkedin.thirdeye.dashboard.views.GenericResponse;
import com.linkedin.thirdeye.dashboard.views.TimeBucket;
import com.linkedin.thirdeye.dashboard.views.ViewResponse;

public class TabularViewResponse implements ViewResponse {
  List<String> metrics;
  List<TimeBucket> timeBuckets;
  Map<String, String> summary;
  Map<String, GenericResponse> data;

  public TabularViewResponse() {
    super();
  }

  public List<String> getMetrics() {
    return metrics;
  }

  public void setMetrics(List<String> metrics) {
    this.metrics = metrics;
  }

  public void setTimeBuckets(List<TimeBucket> timeBuckets) {
    this.timeBuckets = timeBuckets;
  }

  public List<TimeBucket> getTimeBuckets() {
    return timeBuckets;
  }

  public Map<String, String> getSummary() {
    return summary;
  }

  public void setSummary(Map<String, String> summary) {
    this.summary = summary;
  }

  public Map<String, GenericResponse> getData() {
    return data;
  }

  public void setData(Map<String, GenericResponse> data) {
    this.data = data;
  }

}
