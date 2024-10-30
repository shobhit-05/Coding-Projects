/*
@author: Shobhit Gupta
@date: April 30 2022
@description: A BMI Index Calculator
*/


import java.util.*; //imports all utility classes, no need for multiple import statements

class Main { //initializes class

    static double bmiCalc(double height, double weight) {
        //calculates BMI in function bmiCalc
        return weight / (height * height);
    }

    static void calculateBMI(double BMI) {
        //this below checks a certain threshold and determines one's physical fitness
        if (BMI < 18.5) { //BMI values were found from CDC
            System.out.println("You're underweight");
        } else if (BMI >= 18.5 && BMI <= 24.9) {
            System.out.println("You're a healthy weight");
        } else if (BMI >= 25.0 && BMI <= 29.9) {
            System.out.println("You're overweight");
        } else {
            System.out.println("You're obese");
        }
    }
    public static void main(String[] args) {
        //variable declaration and initialization 
        Scanner uI = new Scanner(System.in); //creation of scanner, which gets user input
        List < Double > numberStorage = new ArrayList < Double > (); //creation of list
        //these two are user input variables that take in user Weight and Height
        double userWeight;
        double userHeight;

        //captures user input
        System.out.println("Please enter your height in meters: ");
        userHeight = uI.nextDouble(); //input stored in userHeight
        numberStorage.add(userHeight); //input is then added to the list
        double Height = numberStorage.get(0); //height is directly accessible from list

        System.out.println("Please enter your weight in kilos: ");
        userWeight = uI.nextDouble(); //input stored in userWeight
        numberStorage.add(userWeight); //input added to list
        double Weight = numberStorage.get(1); //weight then accessible from list

        double BMI = bmiCalc(Height, Weight); //function is called and BMI is calculated with the user input values stored in the list
        System.out.printf("\nYour BMI is %.2f kg/m2\n", BMI);
        numberStorage.add(BMI); //BMI added into list
        //System.out.println(numberStorage);
        BMI = numberStorage.get(2); //BMI is now accessible from the list

        calculateBMI(BMI);

        System.out.println(numberStorage); //provides values inside list
    } //end of main
} //end of class
