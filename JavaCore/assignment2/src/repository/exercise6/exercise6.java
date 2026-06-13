package repository.exercise6;

import entity.Account;

public class exercise6 {
    public void question1(){
        System.out.println("Số chẵn nguyên dương nhỏ hơn 10");
        for(int i=2; i<10;i+=2){
            System.out.println(i);
        }
    }

    public void question2(){
        /*in thông tin các account
        Duyệt list chứa các account, xong in ra
         */
    }
    public void question3(){
        System.out.println("Số nguyên dương nhỏ hơn 10");
        for(int i=1; i<10;i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Account account = new Account(3, "tran@gmail.com", "tl03", "Trần Đức Lương");
        System.out.println(account);
    }
}
