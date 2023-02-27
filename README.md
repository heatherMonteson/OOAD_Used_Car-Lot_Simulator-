# Project 3.2 

Team members: Amandaliss Dropik, Heather Monteson

Java version: 11.0


Assumptions 

-A note for in the AddOnDecorator: 

** return vehicle.getSalePrice()+vehicle.getSalePrice()*<add on percent>; **

will only correctly calculate the first add-on cost as getSalePrice() is returning the adjusted value for each call thus subsequent add ons
multiply that sale price miss calculates additional add-ons. Adjusted to correctly calculate setting **this.salePrice=vehicle.getSalePrice();**
in constructors.