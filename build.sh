#!/bin/bash

javac MainApp.java Connect.java DBOps.java SHA.java

java Connect .:mysql-connector MainApp