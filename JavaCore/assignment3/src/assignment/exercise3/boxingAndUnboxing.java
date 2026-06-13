package assignment.exercise3;

public class boxingAndUnboxing {
    public void question1() {
        Integer salary = 5000;
        float salaryFloat = (float) salary;
        System.out.printf("Salary is: %.2f\n", salaryFloat);
    }

    public void question2() {
        String chuoi = "12345";
        int so = Integer.parseInt(chuoi);

        /*
        Su dung Integer.valueOf() - Tra ve doi tuong Integer
        Integer numObj = Integer.valueOf(chuoi);
         */

        System.out.println("so is: " + so);
    }

    public void question3() {
        Integer numObj = 12345;
        int num = numObj.intValue();

        System.out.println("num is: " + num);
    }
}
