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
package com.linkedin.pinot.core.common.predicate;

import java.util.List;


/**
 * This class implements the IN predicate.
 */
public class InPredicate extends BaseInPredicate {

  /**
   * Constructor for the class
   * @param lhs LHS for the IN predicate (column name)
   * @param rhs RHS for the IN predicate (list of values)
   */
  public InPredicate(String lhs, List<String> rhs) {
    super(lhs, Type.IN, rhs);
  }
}
