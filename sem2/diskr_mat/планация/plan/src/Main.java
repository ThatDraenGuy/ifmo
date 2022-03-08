import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<Integer> history = new ArrayList<>();
    public static int resCount=1;
    public static ArrayList<String> results = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> rawResults = new ArrayList<>();
    public static void main(String ... args) {
        String str = "1\t0\t0\t0\t1\t0\t0\t0\t1\t1\t0\t0\t0\t1\t0\t0\t0\t0\t0\t1\t1\t0\n" +
                "0\t1\t0\t0\t1\t0\t0\t0\t1\t1\t1\t0\t0\t1\t1\t0\t1\t0\t0\t1\t0\t0\n" +
                "0\t0\t1\t0\t1\t0\t0\t0\t1\t1\t1\t1\t0\t1\t1\t0\t1\t1\t0\t1\t0\t0\n" +
                "0\t0\t0\t1\t1\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t0\t0\n" +
                "1\t1\t1\t1\t1\t0\t0\t0\t1\t0\t0\t0\t0\t1\t0\t0\t0\t0\t0\t1\t1\t1\n" +
                "0\t0\t0\t0\t0\t1\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t0\n" +
                "0\t0\t0\t0\t0\t0\t1\t0\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t0\t0\t0\t0\n" +
                "0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
                "1\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
                "1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t0\t0\t0\t1\t0\t0\t0\t0\t0\t1\t1\t1\n" +
                "0\t1\t1\t1\t0\t1\t1\t1\t0\t0\t1\t0\t0\t1\t0\t0\t0\t0\t0\t1\t1\t0\n" +
                "0\t0\t1\t1\t0\t1\t1\t1\t0\t0\t0\t1\t0\t1\t1\t0\t1\t0\t0\t1\t0\t0\n" +
                "0\t0\t0\t1\t0\t1\t1\t1\t0\t0\t0\t0\t1\t1\t1\t0\t1\t1\t0\t1\t0\t0\n" +
                "1\t1\t1\t1\t1\t1\t1\t0\t0\t1\t1\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\n" +
                "0\t1\t1\t1\t0\t1\t1\t0\t0\t0\t0\t1\t1\t0\t1\t0\t0\t0\t0\t1\t1\t0\n" +
                "0\t0\t0\t1\t0\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t0\t1\t0\t0\n" +
                "0\t1\t1\t1\t0\t1\t0\t0\t0\t0\t0\t1\t1\t0\t0\t1\t1\t0\t0\t1\t1\t0\n" +
                "0\t0\t1\t1\t0\t1\t0\t0\t0\t0\t0\t0\t1\t0\t0\t1\t0\t1\t0\t1\t0\t0\n" +
                "0\t0\t0\t0\t0\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t0\t0\t0\n" +
                "1\t1\t1\t0\t1\t0\t0\t0\t0\t1\t1\t1\t1\t0\t1\t1\t1\t1\t0\t1\t0\t0\n" +
                "1\t0\t0\t0\t1\t0\t0\t0\t0\t1\t1\t0\t0\t0\t1\t0\t1\t0\t0\t0\t1\t0\n" +
                "0\t0\t0\t0\t1\t0\t0\t0\t0\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\n";
        String newStr = str.replace("\t", "");
        String[] strings = newStr.split("\n");
        ArrayList<ArrayList<Integer>> table = new ArrayList<>();
        for (String s : strings) {
            table.add(convert(s));
        }
        stuff(table);
        System.out.println();
        System.out.println("Семейство максимальных внутренне устойчивых множеств ΨG' построено.\n" +
                "Это:");
        for (String res : results) {
            System.out.println(res);
        }
        System.out.println(rawResults.get(0));
        System.out.println(rawResults.get(27));
        matrix(rawResults);
        ArrayList<ArrayList<Integer>> curr;
        curr=remove(rawResults, 1, 28);
        curr=remove(curr, 4, 23);




    }
    public static ArrayList<Integer> convert(String str) {
        ArrayList<Integer> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            if (c=='1') {
                list.add(1);
            } else {
                list.add(0);
            }
        }
        return list;
    }
    public static void stuff(ArrayList<ArrayList<Integer>> table) {
        for (int i=0; i<table.size(); i++) {
            history.clear();
            work(table,table.get(i), i);
        }
    }
    public static boolean work(ArrayList<ArrayList<Integer>> table, ArrayList<Integer> curr, int border) {
        boolean status=false;
        history.add(border);
        if (check(curr)) {
            System.out.print("В строке ");
            strHistory();
            System.out.println(" все \"1\"");
            System.out.print("Построено ");
            result(history);
            System.out.println("Откатимся на шаг(и) назад и выберем из списка следующий элемент");
            history.remove(history.size()-1);
            return true;
        }
        for (int i=border; i<curr.size(); i++) {
            if (curr.get(i).equals(0)) {
                notify(i);
                ArrayList<Integer> newCurr=or(curr, table.get(i), i);
                status=work(table, newCurr, i);
            }
        }
        if (!status) {
            System.out.println("В строке остались незакрытые \"0\"");
            System.out.println("Откатимся на шаг(и) назад и выберем из списка следующий элемент");
        }
        history.remove(history.size()-1);
        return true;
    }

    public static ArrayList<Integer> or(ArrayList<Integer> base, ArrayList<Integer> addon, int strNumber) {
        ArrayList<Integer> res = new ArrayList<>();
        strHistory();
        int strInc = strNumber+1;
        System.out.print("˅r"+strInc+"={");
        for (int i=0; i<base.size(); i++) {
            res.add(or(base.get(i), addon.get(i)));
        }
        System.out.print("}.\n");
        return res;
    }
    public static int or(int base, int addon) {
        int res;
        if (base==1 || addon==1) {
            res=1;
        } else {
            res=0;
        }
        System.out.print(res);
        return res;
    }
    public static boolean check(ArrayList<Integer> arr) {
        return !arr.contains(0);
    }
    public static void notify(int i) {
        System.out.print("В строке ");
        strHistory();
        int c = i+1;
        System.out.print(" Находим m"+c+"=0. Записываем дизъюнкцию: \n");

    }
    public static void strHistory() {
        System.out.print("M");
        for (int hisInt : history) {
            int hisIntI = hisInt+1;
            System.out.print("."+hisIntI);
        }

    }
    public static void result(ArrayList<Integer> history) {
        StringBuilder builder = new StringBuilder();
        builder.append("ψ").append(resCount).append("={");
        for (int hisInt : history) {
            builder.append(decipher(hisInt)).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("}.");
        resCount++;
        String result = builder.toString();
        results.add(result);
        ArrayList<Integer> histCopy = new ArrayList<>();
        for (int i : history) {
            histCopy.add(i);
        }
        rawResults.add(histCopy);
        System.out.println(result);
    }
    public static String decipher(int i) {
        return switch (i+1) {
            case 1 -> "U1-10";
            case 2 -> "U1-9";
            case 3 -> "U1-8";
            case 4 -> "U1-7";
            case 5 -> "U2-11";
            case 6 -> "U2-6";
            case 7 -> "U2-5";
            case 8 -> "U2-4";
            case 9 -> "U3-12";
            case 10 -> "U3-11";
            case 11 -> "U3-10";
            case 12 -> "U3-9";
            case 13 -> "U3-8";
            case 14 -> "U4-12";
            case 15 -> "U4-10";
            case 16 -> "U4-8";
            case 17 -> "U5-10";
            case 18 -> "U5-9";
            case 19 -> "U5-7";
            case 20 -> "U7-12";
            case 21 -> "U9-12";
            case 22 -> "U10-12";
            default -> "error";
        };
    }


    public static void matrix(ArrayList<ArrayList<Integer>> target) {
        ArrayList<String> matrix = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (int i=1; i<=target.size(); i++) {
            builder.append(i).append("\t");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("\n");
        System.out.print(builder.toString());
        matrix.add(builder.toString());
        int j=0;
        for (ArrayList<Integer> omega : target) {
            j++;
            matrix.add(makeStr(omega, target, j));
        }
    }
    public static String makeStr(ArrayList<Integer> target, ArrayList<ArrayList<Integer>> targetList, int num) {
        StringBuilder builder = new StringBuilder();
        builder.append(num).append("\t");
        for (ArrayList<Integer> check : targetList) {
            if (target.equals(check)) {
                builder.append("0\t");
            } else {
                builder.append(count(target,check)).append("\t");
            }
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("\n");
        System.out.print(builder.toString());
        return builder.toString();
    }
    public static int count(ArrayList<Integer> one, ArrayList<Integer> two) {
        int res = one.size()+two.size();
        for (int check1 : one) {
            for (int check2 : two) {
                if (check1 == check2) res--;
            }
        }
        return res;
    }

    public static ArrayList<ArrayList<Integer>> remove(ArrayList<ArrayList<Integer>> target, int one, int two) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> first = target.get(one-1);
        ArrayList<Integer> second = target.get(two-1);
        for (ArrayList<Integer> str : target) {
            if (str.equals(first) || str.equals(second)) {}else{
                str.removeAll(first);
                str.removeAll(second);
                if (str.size() != 0 && !result.contains(str)) result.add(str);
            }
        }
        resCount=1;
        for (ArrayList<Integer> res : result) {
            //System.out.println(res);
            result(res);
        }

        System.out.println(result.size());
        matrix(result);
        return result;
    }
}
