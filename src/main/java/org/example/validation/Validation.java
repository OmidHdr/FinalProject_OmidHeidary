package org.example.validation;

import org.example.Main;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class Validation {

    //section Number
    public static boolean noNumber(String string) {
        AtomicBoolean flag = new AtomicBoolean(true);
        Arrays.stream(string.split("")).forEach(s -> {
            if (s.matches("[0-9]"))
                flag.set(false);
        });
        return flag.get();
    }
    //section cart
    public static boolean cartNumber(String cartNumber){
        CartBankValidation cart = new CartBankValidation();
        boolean validation = cart.validation(cartNumber);
        while (!validation){
            System.out.print("Wrong information \nTry again : ");
            validation = cart.validation(Main.scanner.nextLine());
        }
        return validation;
    }

    //section String
    public static String validString(String string) {
        boolean result = Validation.noNumber(string);
        String returnResult = string;
        while (!result) {
            System.out.println("Wrong Information ! ");
            System.out.print("Enter your Information : ");
            String name = Main.scanner.nextLine();
            result = Validation.noNumber(name);
            returnResult = name;
        }
        return returnResult;
    }

    //section valid number
    public static long validNumber(String number){
        InternationalValidation validateInternation = new InternationalValidation();
        boolean b = validateInternation.validNumber(number);
        String result = number;
        while (!b) {
            System.out.print("Wrong Number !\nEnter again : ");
            String n = Main.scanner.nextLine();
            b = validateInternation.validNumber(n);
            result = n;
        }
        return Long.parseLong(result);
    }


    //section international
    public static String validInternational(String international) {
        InternationalValidation validateInternation = new InternationalValidation();
        boolean binternational = validateInternation.checkMeli(international);
        String resultInformational = international;
        while (!binternational) {
            System.out.println("Wrong International ! ");
            System.out.print("Enter Your Information again : ");
            String meli = Main.scanner.nextLine();
            binternational = validateInternation.checkMeli(meli);
            resultInformational = meli;
        }
        return resultInformational;
    }

    //section year
    public static String validYear(String year) {
        Pattern pattern = Pattern.compile("^\\d{4}$");
        boolean matches = year.matches(String.valueOf(pattern));
        String newYear = year;
        while (!matches) {
            System.out.print("Wrong information Enter again : ");
            newYear = Main.scanner.nextLine();
            matches = newYear.matches(String.valueOf(pattern));
        }
        return newYear;
    }

    //section valid date
    public static String validDate(String date) throws DataFormatException {
        boolean check = DateValidation.check(date);
        String newDate = date;
        while (!check) {
            System.out.print("Wrong Information !! \n" +
                    "try Again : ");
            newDate = Main.scanner.nextLine();
            check = DateValidation.check(newDate);
        }
        return newDate;
    }

    //section between
    public static String between(String number) {
        Pattern pattern = Pattern.compile("^[1-2]$");
        boolean matches = number.matches(String.valueOf(pattern));
        String newNum = number;
        while (!matches) {
            System.out.print("Enter Number Between '1-2' : ");
            newNum = Main.scanner.nextLine();
            matches = newNum.matches(String.valueOf(pattern));
        }
        return newNum;
    }
    //section between
    public static String betweenShow(String number,int size) {
        Pattern pattern = Pattern.compile("^[1-"+size+"]$");
        boolean matches = number.matches(String.valueOf(pattern));
        String newNum = number;
        while (!matches) {
            System.out.printf("Enter Number Between '1 - %d'😀 : ",size);
            newNum = Main.scanner.nextLine();
            matches = newNum.matches(String.valueOf(pattern));
        }
        return newNum;
    }

    public static String numberOne(String number) {
        Pattern pattern = Pattern.compile("^\\d+$");
        boolean matches = number.matches(String.valueOf(pattern));
        String newNum = number;
        while (!matches) {
            System.out.print("Enter Number again : ");
            newNum = Main.scanner.nextLine();
            matches = newNum.matches(String.valueOf(pattern));
        }
        return newNum;
    }
    //section Role
    public static String Role(int uni){
        if (uni == 1)
            return "customer";
        else if (uni == 2)
            return "expert";
        return null;
    }

}
