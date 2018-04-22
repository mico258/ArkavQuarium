package animal;


import droppable.Koin;

public class Snail extends Animal {

//    private boolean isSearching;
    public Snail(double xPosition, double yPosition, String image) {
        super(xPosition, yPosition, image);
//        this.isSearching = false;
    }

    void doSomething() {

    }

//    bool getIsSearching(){
//        return this->isSearching;
//    }

//    void Snail::setIsSearching(bool a){
//        this->isSearching = a;
//    }
    
    void moveToCoin(Koin k){
        double x0 = this.getxPosition();

        if(k.getxPosition()<this.getxPosition()){
            this.setxPosition(x0-0.1);
        } else if(k.getxPosition()>this.getxPosition()){
            this.setxPosition(x0+0.1);
        }
    }
}
