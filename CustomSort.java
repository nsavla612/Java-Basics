import java.util.*;

public class customSort {
    public static void main(String[] args) {

        String[] strings = {"z","vvvvvvvv","c","ffff","e","gg","nnn","h","i"};
        String[] strings2 = {"z","vvvvvvvv","c","ffff","e","gg","nnn","h","i"};
        int[][] array2D = { {9,8,7,5} , {1,6,3,6} , {1,2,3,4}};
        
        Player player1 = new Player("a",4,1);
        Player player2 = new Player("b", 3, 2);
        Player player3 = new Player("c", 2, 3);
        Player player4 = new Player("d", 1, 4);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        System.out.println("original - ");
        print(strings);

        System.out.println("Sort Strings using comparator - ");
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        print(strings);

        System.out.println("Sort Strings using lambda - ");
        Arrays.sort(strings2, (a,b) -> a.length() - b.length());
        print(strings2);

        System.out.println("Sort Strings using comparingInt Reversed - ");
        Arrays.sort(strings2, Comparator.comparingInt(String::length).reversed());
        print(strings2);


        System.out.println("Sort Players using Comparable - ");
        Collections.sort(playerList);
        print(playerList);


        System.out.println("Sort Players using Comparator - ");
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.age - o2.age;
            }
        });
        print(playerList);


        System.out.println("Sort Players using Lambda - ");
        Collections.sort(playerList, (o1, o2) -> o1.score - o2.score);
        print(playerList);


        System.out.println("Sort Players using comparingInt with two values- ");
        Collections.sort(playerList, Comparator.comparingInt(o -> o.age));
        print(playerList);


        System.out.println("Sort 2D Array using Comparator ");
        Arrays.sort(array2D, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        print(array2D);


        System.out.println("Sort 2D Array using Lambda ");
        Arrays.sort(array2D, (a,b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        print(array2D);


        System.out.println("Sort 2D Array using ComparingInt with two values ");
        Arrays.sort(array2D, Comparator.comparingInt((int[] a) -> a[0]).thenComparing((int[] a) -> a[1]));
        print(array2D);


    }

    static void print(String[] strings)
    {
        System.out.println();
        for(int i = 0 ; i < strings.length; i++)
            System.out.print(strings[i] + " ");
        System.out.println() ;
    }

    static void print(List<Player> players)
    {
        System.out.println();
        for(int i = 0 ; i < players.size(); i++)
            System.out.print(players.get(i) + " ");
        System.out.println() ;
    }

    static void print(int[][] array2D)
    {
        System.out.println();
        for(int i = 0 ; i < array2D.length; i++)
            System.out.println(Arrays.toString(array2D[i]));
        System.out.println() ;
    }
}

class Player implements Comparable<Player>
{

    String name;
    int score;
    int age;

    Player(String name, int score, int age)
    {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    @Override
    public int compareTo(Player o) {
        return this.score - o.score;
    }

    @Override
    public String toString()
        {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", age=" + age +
                '}';
    }
}
