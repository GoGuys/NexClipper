/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.nexcloud.tsdb.client.adapter.influx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostDockerAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class HostDockerInfluxAdapter implements HostDockerAdapter {
	
	@Autowired private InfluxClient influxClient;
	
	public String getDockerCpuUsedByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_used_percent) AS cpu_used_percent"
			+ " FROM \"docker_container\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND container_id='" + containerId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDockerCpuUsedByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return influxClient.get(
				"SELECT mean(cpu_used_percent) AS cpu_used_percent"
				+ " FROM \"docker_container\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerMemoryAllocateByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(mem_limit) AS mem_allocate"
			+ " FROM \"docker_container\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND container_id='" + containerId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDockerMemoryUsedByteByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(mem_used) AS mem_used"
			+ " FROM \"docker_container\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND container_id='" + containerId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDockerMemoryUsedByteByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return influxClient.get(
				"SELECT mean(mem_used) AS mem_used"
				+ " FROM \"docker_container\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerMemoryUsedPercentByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(mem_used_percent) AS mem_used_percent"
			+ " FROM \"docker_container\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND container_id='" + containerId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDockerMemoryUsedPercentByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return influxClient.get(
				"SELECT mean(mem_used_percent) AS mem_used_percent"
				+ " FROM \"docker_container\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerDiskioReadByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(disk_io_read) AS disk_io_read"
			+ " FROM \"docker_container\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND container_id='" + containerId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDockerDiskioReadByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return influxClient.get(
				"SELECT mean(disk_io_read) AS disk_io_read"
				+ " FROM \"docker_container\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerDiskioWriteByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(disk_io_write) AS disk_io_write"
			+ " FROM \"docker_container\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND container_id='" + containerId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDockerDiskioWriteByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return influxClient.get(
				"SELECT mean(disk_io_write) AS disk_io_write"
				+ " FROM \"docker_container\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkRxbyteByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_bytes) AS rx_bytes"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkRxdropByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_dropped) AS rx_dropped"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkRxerrorByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_errors) AS rx_errors"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkRxpacketByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_packets) AS rx_packets"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxbyteByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_bytes) AS tx_bytes"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxdropByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_dropped) AS tx_dropped"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxerrorByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_errors) AS tx_errors"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxpacketByContainerId (String hostIp, String containerId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_packets) AS tx_packets"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND container_id='" + containerId + "'"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	/////
	public String getDockerNetworkRxbyteByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_bytes) AS rx_bytes"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkRxdropByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_dropped) AS rx_dropped"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkRxerrorByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_errors) AS rx_errors"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkRxpacketByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(rx_packets) AS rx_packets"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxbyteByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_bytes) AS tx_bytes"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxdropByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_dropped) AS tx_dropped"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxerrorByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_errors) AS tx_errors"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
	
	public String getDockerNetworkTxpacketByTaskId (String hostIp, String taskId, String startTime, String time, int limit){
		return influxClient.get(
				"SELECT mean(tx_packets) AS tx_packets"
				+ " FROM \"docker_network\""
				+ " WHERE host_ip='" + hostIp + "'"
				+ " AND time > now() - " + startTime
				+ " AND (task_id=~ /.*"+taskId+".*/)"
				+ " GROUP By time(" + time + "),task_id,interface fill(linear)"
				+ " LIMIT " + limit 
			);
	}
}
