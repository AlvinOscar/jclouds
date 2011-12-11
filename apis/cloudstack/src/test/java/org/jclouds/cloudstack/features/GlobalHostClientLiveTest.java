/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.cloudstack.features;

import org.jclouds.cloudstack.domain.Host;
import org.jclouds.logging.Logger;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Tests behavior of {@code GlobalHostClient}
 *
 * @author Andrei Savu
 */
@Test(groups = "live", singleThreaded = true, testName = "GlobalHostClientLiveTest")
public class GlobalHostClientLiveTest extends BaseCloudStackClientLiveTest {

   @Test(groups = "live", enabled = true)
   public void testListHosts() throws Exception {
      assertTrue(globalAdminEnabled, "Test cannot run without global admin identity and credentials");

      Set<Host> hosts = globalAdminClient.getHostClient().listHosts();
      assert hosts.size() > 0 : hosts;

      for(Host host : hosts) {
         checkHost(host);
      }
   }

   private void checkHost(Host host) {
      if (host.getType().equals("Routing")) {
         assert host.getCpuNumber() > 0;
         assert host.getAverageLoad() >= 0;
         assert host.getHypervisor() != null;
      }
      assert host.getAllocationState() != null;
      assert host.getEvents() != null;
      if (host.getType().equals("SecondaryStorageVM")) {
         assert host.getName().startsWith("s-");
      }
      if (host.getType().equals("ConsoleProxy")) {
         assert host.getName().startsWith("v-");
      }
   }

}