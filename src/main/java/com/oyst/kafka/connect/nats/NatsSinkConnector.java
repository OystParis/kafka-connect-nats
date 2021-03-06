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
package com.oyst.kafka.connect.nats;

import com.oyst.kafka.connect.nats.sink.NatsSinkConnectorConfig;
import com.oyst.kafka.connect.nats.sink.NatsSinkTask;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NatsSinkConnector extends SinkConnector {
  private static final Logger LOG = LoggerFactory.getLogger(NatsSourceConnector.class);
  private Map<String, String> mConfigProperties;

  @Override
  public void start(Map<String, String> props) {
    LOG.info("Start the NATS Sink Connector with the next properties : {}", props);
    this.mConfigProperties = props;
  }

  @Override
  public void stop() {
    LOG.info("Stop the Nats Sink Connector");
  }

  @Override
  public List<Map<String, String>> taskConfigs(int capacity) {
    List<Map<String, String>> taskConfigs = new ArrayList<>(capacity);
    Map<String, String> taskProps = new HashMap<>(mConfigProperties);
    for (int i = 0; i < capacity; i++){
      taskConfigs.add(taskProps);
    }
    return taskConfigs;
  }

  @Override
  public Class<? extends Task> taskClass() {
    return NatsSinkTask.class;
  }

  @Override
  public ConfigDef config() {
    return NatsSinkConnectorConfig.config;
  }
  @Override
  public String version() {
    return AppInfoParser.getVersion();
  }

}
