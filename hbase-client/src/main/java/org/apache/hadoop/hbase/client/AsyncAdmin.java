/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.client;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.apache.hadoop.hbase.classification.InterfaceAudience;
import org.apache.hadoop.hbase.classification.InterfaceStability;

/**
 *  The asynchronous administrative API for HBase.
 */
@InterfaceAudience.Public
@InterfaceStability.Unstable
public interface AsyncAdmin {

  /**
   * Turn the load balancer on or off.
   * @param on
   * @return Previous balancer value wrapped by a {@link CompletableFuture}.
   */
  CompletableFuture<Boolean> setBalancerRunning(final boolean on) throws IOException;

  /**
   * Invoke the balancer. Will run the balancer and if regions to move, it will go ahead and do the
   * reassignments. Can NOT run for various reasons. Check logs.
   * @return True if balancer ran, false otherwise. The return value will be wrapped by a
   *         {@link CompletableFuture}.
   */
  CompletableFuture<Boolean> balancer() throws IOException;

  /**
   * Invoke the balancer. Will run the balancer and if regions to move, it will go ahead and do the
   * reassignments. If there is region in transition, force parameter of true would still run
   * balancer. Can *not* run for other reasons. Check logs.
   * @param force whether we should force balance even if there is region in transition.
   * @return True if balancer ran, false otherwise. The return value will be wrapped by a
   *         {@link CompletableFuture}.
   */
  CompletableFuture<Boolean> balancer(boolean force) throws IOException;

  /**
   * Query the current state of the balancer.
   * @return true if the balancer is enabled, false otherwise.
   *         The return value will be wrapped by a {@link CompletableFuture}.
   */
  CompletableFuture<Boolean> isBalancerEnabled() throws IOException;
}
