# secret-santa
Author: Gabriella Morelli

Program that determines random secret santa ordering and sends friendly emails.
References: This program modifies this example from mykyong:
https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 
To use: Edit the file secretSanta.java to be from an existing gmail account. You must input the username and password.

Program execution:
secretSanta.java takes two command line arguments: a list of names and a list of emails (in same order)
Example of program execution: java secretSanta names.txt emails.txt

Program asks for input in the form of categories. The amount of categories equals the number of participants. 
After inputting the categories, the program will create unique orderings of the list of names by incrementally shifting their position in the list.
The program will then randomly assign each participant to a list, matched with the categories, and email it to them. 
