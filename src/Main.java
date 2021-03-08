import java.util.*;

class Main {
    public static boolean containsChar(ArrayList<Pair> list, char v) {
        for (var i : list) {
            if (i.value == v)
                return true;
        }
        return false;
    }

    public static int getPos(ArrayList<Pair> list, char v) {
        for (var i : list) {
            if (i.value == v)
                return i.index;
        }
        return 0;
    }

    public static void main(String[] args) {

        String[] grammar = {"S-aF", "F-bF", "F-cD","D-a$", "S-bS", "D-cS", "D-a$"};
        int j = 0;
        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 0; i < grammar.length; i++) {

            char temp = grammar[i].charAt(0);
            if (!(containsChar(list, temp))) {
                list.add(new Pair(j, temp));
                j++;
            }
        }
       
        int m, n;
        n = list.size();
        m = list.size() + 1;
        List<Character>[][] adjacencyTable = new ArrayList[n][m];
        for (int i = 0; i < n; i++) {
            for (int z = 0; z <= n; z++) {
                adjacencyTable[i][z] = new ArrayList<>();
            }
        }
        for (int i = 0; i < grammar.length; i++) {
            if (grammar[i].charAt(grammar[i].length() - 1) == '$') {
                char temp = grammar[i].charAt(grammar[i].length() - 2);
                adjacencyTable[getPos(list, grammar[i].charAt(0))][m - 1].add(temp);
            } else {
                char temp = (grammar[i].charAt(grammar[i].length() - 2));
                adjacencyTable[getPos(list, grammar[i].charAt(0))][getPos(list, grammar[i].charAt(grammar[i].length() - 1))].add(temp);
            }
        }
        String string = "abca";
        int pas = 0;
        int y = 0;
        boolean check_point = true;
        try {
            while (pas < string.length() && (check_point == true)) {
                for (int i = 0; i < list.size() + 1; i++) {
                    if (adjacencyTable[y][i].contains(string.charAt(pas))) {
                        y = i;
                        if (pas == string.length() - 1 && i != list.size())
                            check_point = false;
                        else check_point = true;
                        pas++;
                        break;
                    } else {
                        check_point = false;
                    }

                }

            }
        } catch (IndexOutOfBoundsException e) {
            check_point = false;
        }

        if (check_point == false)
            System.out.println("\nIncorrect string");

        if (check_point == true) {
            System.out.println("\nCorrect string");
        }

    }
}

