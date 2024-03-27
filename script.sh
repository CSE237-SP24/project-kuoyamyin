
#!/bin/bash

cd src

javac bankapp/*.java

java bankapp.Menu

cd bankapp

rm BankAccount.class

rm Menu.class
