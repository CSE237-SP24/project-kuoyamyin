#!/bin/bash

cd src

javac bankapp/BankAccount.java
javac bankapp/Menu.java

java bankapp/Menu

cd bankapp

rm BankAccount.class

rm Menu.class
