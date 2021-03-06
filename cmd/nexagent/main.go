/*
Copyright 2019 NexClipper.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package main

import (
	"fmt"
	"github.com/NexClipper/NexClipper/pkg/nexagent"
	"github.com/urfave/cli"
	"log"
	"os"
)

func main() {
	app := cli.NewApp()
	app.Version = nexagent.NexAgentVersion
	app.Name = nexagent.AppName
	app.Description = nexagent.AppDescription
	app.Flags = []cli.Flag{
		cli.StringFlag{
			Name:     "config, c",
			Usage:    "Config file path",
			EnvVar:   "NEXAGENT_CONFIG_PATH",
			Required: false,
			Value:    "",
		},
		cli.StringFlag{
			Name:     "server, s",
			Usage:    "NexServer address",
			EnvVar:   "NEXAGENT_SERVER_ADDRESS",
			Required: false,
			Value:    "",
		},
		cli.IntFlag{
			Name:   "api",
			Usage:  "Listening port for REST API",
			EnvVar: "NEXAGENT_API_PORT",
			Value:  18002,
		},
		cli.StringFlag{
			Name:   "k8s.cluster",
			Usage:  "Name of Kubernetes cluster",
			EnvVar: "NEXAGENT_KUBERNETES_NAME",
			Value:  "default",
		},
		cli.StringFlag{
			Name:   "k8s.namespace",
			Usage:  "Name of Kubernetes namespace",
			EnvVar: "NEXAGENT_KUBERNETES_NAMESPACE",
			Value:  "nexclipper",
		},
		cli.BoolFlag{
			Name:   "tls",
			Usage:  "Use TLS secure communication channel",
			EnvVar: "NEXAGENT_TLS_USE",
		},
		cli.StringFlag{
			Name:   "tls.key",
			Usage:  "Path of TLS key file",
			EnvVar: "NEXAGENT_TLS_KEY_PATH",
		},
		cli.StringFlag{
			Name:   "tls.cert",
			Usage:  "Path of TLS cert file",
			EnvVar: "NEXAGENT_TLS_CERT_PATH",
		},
		cli.IntFlag{
			Name:   "agent.interval",
			Usage:  "Metric report interval for agent status and metrics (seconds)",
			EnvVar: "NEXAGENT_REPORT_INTERVAL",
			Value:  5,
		},
		cli.StringFlag{
			Name:   "agent.cluster",
			Usage:  "Cluster name of agent",
			EnvVar: "NEXAGENT_CLUSTER",
			Value:  "default",
		},
	}

	app.Action = func(c *cli.Context) error {
		nexAgent := nexagent.NewNexAgent()
		configPath := c.String("config")

		if configPath != "" {
			err := nexAgent.LoadConfig(configPath)
			if err != nil {
				return fmt.Errorf("failed to load config: %v\n", err)
			}
		} else {
			serverAddress := c.String("server")
			if serverAddress == "" {
				return fmt.Errorf("failed to start agent: missing server address")
			}

			k8sCluster := c.String("k8s.cluster")
			k8sNamespace := c.String("k8s.namespace")
			agentCluster := c.String("agent.cluster")
			reportInterval := c.Int("agent.interval")
			apiPort := c.Int("api")

			nexAgent.InitWithDefault()

			nexAgent.SetAgentCluster(agentCluster)
			nexAgent.SetServerAddress(serverAddress)
			nexAgent.SetK8sCluster(k8sCluster)
			nexAgent.SetK8sNamespace(k8sNamespace)
			nexAgent.SetApiPort(apiPort)
			nexAgent.SetReportInterval(reportInterval)
		}

		if err := nexAgent.Start(); err != nil {
			return fmt.Errorf("stopped Agent: %v\n", err)
		}

		return nil
	}

	err := app.Run(os.Args)
	if err != nil {
		log.Fatal(err)
	}
}
