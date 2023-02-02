# Sorting Madness

## Contents
* [General](#general)
* [Running the application](#running-the-application)
* [Endpoints](#endpoints)

## General

All the necessary information about the project

[Backlog](https://docs.google.com/spreadsheets/d/1NBvADZVLzGd5_zMO9ObgqBY62RBQ5A0g/edit#gid=1602270925)

The application implements 6 various sorting algorithms which can be used to sort different data sets using REST API and compare their sort times.
Numeric data is sorted by standard and objects are sorted by parameter given by the user.
To design api strategy pattern was used.



## Running the application
The project is a standard Maven project. To run it from the command line, type mvn spring-boot:run then use given endpoints.



## Endpoints
- sort integers 
  - ascending localhost:9090/sort/integers
  - descending localhost:9090/sort/integers?descending=true
  
  In body of the request you have to give integers to be sorted, sorting strategies you want to use and optional iteration limits.
  ###### Body
    ```json
    {
      "toSort": [2, 6, 234, 789, 124, 45758, 2134235, 1, 0, 9],
      "sortingStrategies": ["BUBBLE_SORT", "HEAP_SORT"],
      "iterationLimits": [1, 1]
    }
   ```
- sort objects 
  - ascending localhost:9090/sort/objects
  - descending localhost:9090/sort/objects?descending=true
  
  In body of the request you have to give objects to be sorted, property on which objects should be sorted, sorting strategies you want to use and optional iteration limits.
  ###### Body
  ```json
  {
    "toSort": [
        {"name": "object1", "sort": {"key": 3}},
        {"name": "object2", "sort": {"key": 5}},
        {"name": "object3", "sort": {"key": 3234}},
        {"name": "object4", "sort": {"key": 1}} 
    ],
    "property": "/sort/key",
    "sortingStrategies":  ["BUBBLE_SORT", "MERGE_SORT"],
    "iterationLimits": [1,3]
  }
  ```
- sort integers with best strategy
  - ascending localhost:9090/sort/integers/best/strategy
  - descending localhost:9090/sort/integers/best/strategy?descending=true
  
  Using this endpoint you can sort your integers using strategy that have sorted your data the fastest

  In body of the request you have to give integers to be sorted 
  ###### Body
  ```json
  {
    [2, 6, 234, 789, 124, 45758, 2134235, 1, 0, 9]
  }
  ```
- sort objects with best strategy
  - ascending localhost:9090/sort/objects/best/strategy
  - descending localhost:9090/sort/objects/best/strategy?descending=true
  
  Using this endpoint you can sort your objects using strategy that have sorted your data the fastest

  In body of the request you have to give objects to be sorted and property on which objects should be sorted
  
  ```json
  {
    "toSort": [
        {"name": "object1", "sort": {"key": 3}},
        {"name": "object2", "sort": {"key": 5}},
        {"name": "object3", "sort": {"key": 3234}},
        {"name": "object4", "sort": {"key": 1}}
     ],
    "property": "/sort/key"
  }
  ```

