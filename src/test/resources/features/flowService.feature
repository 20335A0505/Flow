Feature: Flow Service - Initialization and ID assignment

  Scenario: Initialize FlowService with last Flow ID from the database
    Given the FlowRepo contains flows with the last flowId from the database
    When the last flowId is not null
    Then the FlowService should set the id to the last flowId
    When the last flowId is null
    Then the FlowService should set the id to 1000

 Scenario: Save a flow with valid details
    Given I have the following flow details inMap<String, Object>
      """
      {
          "Inbound": {
            "topic": "", 
            "queue": "ACTIVEMQ.SAMPLE.MU.IN",
            "brokerurl": "tcp://172.17.1.25:61616",
            "username": "admin",
            "password": "admin"
          },
          "Outbound": {
            "topic": "",
            "queue": "ACTIVEMQ.SAMPLE.MU.OUT",
            "brokerurl": "tcp://172.17.1.25:61616",
            "username": "admin",
            "password": "admin"
          },
          "stages": "INGATE.OUTGATE",
          "flownodes": [
            {
              "id": "dndnode_0",
              "type": "Amq",
              "position": {"x": -128.203125, "y": 157},
              "data": {"label": "Amq"}
            },
            {
              "id": "dndnode_1",
              "type": "Ingate",
              "position": {"x": -34.8046875, "y": 156.75},
              "data": {"label": "Ingate"}
            },
            {
              "id": "dndnode_2",
              "type": "Outgate",
              "position": {"x": 52.1953125, "y": 157.25},
              "data": {"label": "Outgate"}
            },
            {
              "id": "dndnode_3",
              "type": "Amqout",
              "position": {"x": 190, "y": 158.25},
              "data": {"label": "Amqout"}
            }
          ],
          "flowedges": [
            {
              "source": "dndnode_0",
              "target": "dndnode_1",
              "type": "straight"
            },
            {
              "source": "dndnode_1",
              "target": "dndnode_2",
              "type": "straight"
            },
            {
              "source": "dndnode_2",
              "target": "dndnode_3",
              "type": "straight"
            }
          ]
      }
      """
    When the id value is incremented by 1
    Then the flowId value should be "FLOW" followed by the incremented id
    When the Inbound key "queue" is present
    Then the queue value should be assigned from inbound object queue value
    When the Inbound key "topic" is present
    Then the queue value should be assigned from inbound object topic value
    When the Inbound key "url" is present
    Then the queue value should be assigned from inbound object URL value
    When the Inbound key "queuename" is present
    Then the queue value should be assigned from inbound object queuename value
    When all the flowDetails values are processed and set
    Then the Flow object should have correct values for Inbound, Outbound, FlowName, Region, Stages, Edges, and Nodes
    When the service is executed with the provided flowDetails
    Then the Flow object should be saved successfully with correct values
