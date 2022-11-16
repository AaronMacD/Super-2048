package Project3;

import java.security.InvalidParameterException;

public class Tile {
    private int value;

    public Tile(){
        this.value = 2;
    }
    public Tile(int value){
        if(power2(value)){
            this.value = value;
        }
        else{
            throw new InvalidParameterException("Invalid value, not a factor of 2");
        }
    }

    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        if(power2(value)){
            this.value = value;
        }
        else{
            throw new InvalidParameterException("Invalid value, not a factor of 2");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    private boolean power2(double value){
        if(value % 2 != 0){
            return false;
        }
        if(value / 2 > 1){
            power2(value/2);
        }
        return true;
    }


}
