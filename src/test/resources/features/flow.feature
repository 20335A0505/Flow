Feature: Flow Controller Tests
  To ensure the Flow Controller correctly handles flow data and responds appropriately to API requests.

  Scenario: Save a Flow via POST API
    Given I have the Flow POST API endpoint "/stream"
    And I have the following valid JSON request body:
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
    When I send a POST request to the Flow POST API with the provided request body
    Then the response status code should be 200
    And the response message should be "Flow saved successfully!"
