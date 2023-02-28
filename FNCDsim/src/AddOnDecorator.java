package FNCDsim.src;

import java.util.Objects;

//DECORATOR: Decorator class to add items to purchased vehicles. The vehicle item starts in the salesperson class
//and the vehicle methods getAddOnsDes() and getSalePrice() decorate the item. getAddOnsDes() starts as returning an empty
//string in the vehicle class.

public abstract class AddOnDecorator extends Vehicle{
    Vehicle vehicle;
}
/////////////////////////////////////////////////////
class ExtendedWarranty extends AddOnDecorator{

    ExtendedWarranty(Vehicle vehicle){
        this.vehicle=vehicle;
        this.salePrice=vehicle.getSalePrice();
    }

    public String getAddOnDes() {
        if(Objects.equals(vehicle.getAddOnDes(), ""))
            return vehicle.getAddOnDes()+ "Add-ons: Extended Warranty for $" + Utility.format(vehicle.salePrice*.2);
        return vehicle.getAddOnDes()+ " Extended Warranty for $" + Utility.format(vehicle.salePrice*.2);
    }
    public double getSalePrice(){
        return Utility.format(vehicle.getSalePrice()+vehicle.salePrice*.20);
    }
}


/////////////////////////////////////////////////////
class Undercoating extends AddOnDecorator{

    Undercoating(Vehicle vehicle){
        this.vehicle=vehicle;
        this.salePrice=vehicle.getSalePrice();
    }
    public String getAddOnDes() {
        if(Objects.equals(vehicle.getAddOnDes(), ""))
            return vehicle.getAddOnDes()+ "Add-ons: Undercoating for $" + Utility.format(vehicle.salePrice*.05);
        return vehicle.getAddOnDes()+ " Undercoating for $" + Utility.format(vehicle.salePrice*.05);
    }
    public double getSalePrice(){
        return Utility.format(vehicle.getSalePrice()+vehicle.salePrice*.05);    }
}

/////////////////////////////////////////////////////
class RoadRescueCoverage extends AddOnDecorator{
    RoadRescueCoverage(Vehicle vehicle){
        this.vehicle=vehicle;
        this.salePrice=vehicle.getSalePrice();
    }
    public String getAddOnDes() {
        if(Objects.equals(vehicle.getAddOnDes(), ""))
            return vehicle.getAddOnDes()+ "Add-ons Road Rescue Coverage for $" + Utility.format(vehicle.salePrice*.02);
        return vehicle.getAddOnDes()+ " Road Rescue Coverage for $" + Utility.format(vehicle.salePrice*.02);
    }

    public double getSalePrice(){
        return Utility.format(vehicle.getSalePrice()+vehicle.salePrice*.02);    }
}

/////////////////////////////////////////////////////
class SatelliteRadio extends AddOnDecorator{
    SatelliteRadio(Vehicle vehicle){
        this.vehicle=vehicle;
        this.salePrice=vehicle.getSalePrice();
    }
    public String getAddOnDes() {
        if(Objects.equals(vehicle.getAddOnDes(), ""))
            return vehicle.getAddOnDes()+ "Add-ons: Satellite Radio for $" + Utility.format(vehicle.salePrice*.05);
        return vehicle.getAddOnDes()+ " Satellite Radio for $" + Utility.format(vehicle.salePrice*.05);
    }

    public double getSalePrice(){
        return Utility.format(vehicle.getSalePrice()+vehicle.salePrice*.05);
    }
}