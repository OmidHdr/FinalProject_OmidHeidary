package org.example.validation;

import com.github.mfathi91.time.PersianDate;
import org.example.Main;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Validation {


    //section valid date
    public static String validDate() {
        while (true) {
            PersianDate systemDate = PersianDate.now();
            String fromSystem = String.valueOf(systemDate);
            try {
                String[] system = fromSystem.split("-");
                out.print("Enter date for example " + fromSystem + " : ");
                String dateUser = Main.scanner.nextLine();
                String[] user = dateUser.split("-");
                String systemYear = system[0];
                String systemMonth = system[1];
                String systemDay = system[2];
                String userYear = user[0];
                String userMonth = user[1];
                String userDay = user[2];
                try {
                    if (Integer.parseInt(systemYear) == Integer.parseInt(userYear)) {
                        if (Integer.parseInt(systemMonth) == Integer.parseInt(userMonth)) {
                            if (Integer.parseInt(systemDay) < Integer.parseInt(userDay)) {
                                return dateUser;
                            }
                        } else if (Integer.parseInt(systemMonth) < Integer.parseInt(userMonth)) {
                            return dateUser;
                        }
                    } else if (Integer.parseInt(systemYear) < Integer.parseInt(userYear)) {
                        return dateUser;
                    }
                } catch (Exception e) {
                }
                out.println("Today is " + fromSystem + " so you cant request before today !! ");
                out.println("Try again !! ");
            } catch (Exception e) {
            }
        }
    }

    //section valid email
    public static String validateEmail(String email) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (email.matches(regex))
            return email;
        else {
            while (!email.matches(regex)) {
                System.out.print("Enter your Email Again : ");
                email = Main.scanner.nextLine();
            }
        }
        return email;
    }


    //section valid Password
    public static String validPassword(String string) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (string.matches(regex))
            return string;
        else {
            while (!string.matches(regex)) {
                System.out.print("Enter your Password Again : ");
                string = Main.scanner.nextLine();
            }
        }
        return string;
    }


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
    public static boolean cartNumber(String cartNumber) {
        CartBankValidation cart = new CartBankValidation();
        boolean validation = cart.validation(cartNumber);
        while (!validation) {
            System.out.print("Wrong information \nTry again : ");
            validation = cart.validation(Main.scanner.nextLine());
        }
        return validation;
    }

    //section String
    public static String validString(String string) {
        boolean result = Validation.noNumber(string);
        InternationalValidation international = new InternationalValidation();
        boolean result2 = international.checkEmpty(string);
        String returnResult = string;
        while (!result || !result2) {
            System.out.println("Wrong Information ! ");
            System.out.print("Enter your Information : ");
            String name = Main.scanner.nextLine();
            result = Validation.noNumber(name);
            result2 = international.checkEmpty(name);
            returnResult = name;
        }
        return returnResult;
    }

    //section valid number
    public static long validNumber(String number) {
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
    public static String betweenShow(String number, int size) {
        Pattern pattern = Pattern.compile("^[1-" + size + "]$");
        boolean matches = number.matches(String.valueOf(pattern));
        String newNum = number;
        while (!matches) {
            System.out.printf("Enter Number Between '1 - %d'😀 : ", size);
            newNum = Main.scanner.nextLine();
            matches = newNum.matches(String.valueOf(pattern));
        }
        return newNum;
    }

    //section Role
    public static String Role(int uni) {
        if (uni == 1)
            return "customer";
        else if (uni == 2)
            return "expert";
        return null;
    }

}
