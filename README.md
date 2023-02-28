# Project 3.2 

Team members: Amandaliss Dropik, Heather Monteson

Java version: 11.0

Patterns found in classes/interfaces
- Observer: Observer (I), Logger (C), Tracker(C), InformationBroker (C) (only one implementation of a subject/publisher therefor no publisher interface is used)
- Decorator: AddOnDecorator (C) --> all sub classes implement, used in the Employee(C)--> Salesperson(C)
- Strategy: 

Assumptions 
 - The Logger only subscribes to the information outlined in the bullet points thus some output from the terminal may not be exact to the txt file 
_A note for in the AddOnDecorator:_

**return vehicle.getSalePrice()+vehicle.getSalePrice() \*\_add-on percent_;**

will only correctly calculate the first add-on cost as getSalePrice() is returning the adjusted value for each call thus subsequent add-ons
multiply that sale price miscalculates additional add-ons. Adjusted to correctly calculate setting **this.salePrice=vehicle.getSalePrice();**
in constructors nd used return **vehicle.getSalePrice()+vehicle.salePrice\*\_add-on percent_;**. 
