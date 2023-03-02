# Project 3.2 

Team members: Amandaliss Dropik, Heather Monteson

Java version: 11.0

Patterns found in classes/interfaces
- Observer: Observer (I), Logger (C), Tracker(C), InformationBroker (C) (only one implementation of a subject/publisher therefor no publisher interface is used)
- Decorator: AddOnDecorator (C) --> all sub classes implement, used in the Employee(C)--> Salesperson(C)
- Strategy: CleaningBehavior(I), implemented by --> Chemical(C), ElbowGrease(C), Detailed(C). Intern(C) then sets its celeaning behavior to one of the defined classes

Assumptions 
 - The Logger only subscribes to the information outlined in the bullet points thus some output from the terminal may not be exact to the txt file because not pushing redundant/unecessary info
 - Electric cars are initially set between 60 and 400 miles and then 100 can be added on, not interpreted to have an upper limit 
 - Motorcycle truncated value understood as the numeric value would be truncated to an intiger/remove decimal places but not upper limit given
 - When a vehicle wins a race interpreited as price would increase 10% each time 
 - The tracker is completely cumulative: it is a hash table with "Day", "FNCD" and "STAFF" keys in which associated values are stores and added to
