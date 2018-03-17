package Calculator;

/**
 * Created by Dylan Galea on 17/03/2018.
 */

/**
 * The SimpleCalculator class computes all operations a basic calculator needs to perform , this performs integer
 * addition , subtraction ,multiplication and division.
 */

public class SimpleCalculator {
    int IntegerAddition(int a, int b){
        return a+b;
    }
    int IntegerSubtraction(int a, int b){
        return a-b;
    }
   int IntegerMultiplication(int a,int b){
        return a*b;
    }
    int IntegerDivision(int a,int b){
        if(a>b && b != 0){ //to always get a positive number not equal to 0 since we have integer division .. since 5/6 =0
                           // in integer division
            return a/b;
        }else if(b>a && b!= 0){
            return b/a;
        }else{
            return -1; //Means that an error occurs
        }
    }
}
