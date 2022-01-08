import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class main {

    public static void main(String args[]) {
        try {

            //Using the scanner class, we'll convert the map into a String matrix.
            Scanner sc = new Scanner(new BufferedReader(new FileReader("matrix2.txt")));

            //The id is on the first line.
            String id = sc.nextLine();

            //We can use a 2-dimensional array large enough to fit the map.
            String[][] maze = new String[100][100];
            sc.useDelimiter("\n");
            String line = sc.nextLine();
            int startrow = 0;
            int startcolumn = 0;

            //Filling our array with the map values.
            for(int row = 0; row < 100; row++) {
                if(sc.hasNextLine()) {
                    for(int column = 0; column < line.length(); column++) {
                        maze[row][column] = line.substring(column, column+1);
                        if (maze[row][column].equals("S")) {
                            startrow = row;
                            startcolumn = column;
                        }

                    }
                    line = sc.nextLine();
                }
            }

            //Moving in the matrix with four different directions and counting the steps required.
            boolean moving = true;
            int steps = 0;
            String direction = "start";
            while(moving) {
                switch (direction) {
                    case "start":
                        if(maze[startrow-1][startcolumn].equals("#") && maze[startrow-1][startcolumn+1].equals("#")) {
                            direction = "down";
                        } else if(maze[startrow-1][startcolumn].equals("#")) {
                            direction = "right";
                        } else {
                            direction = "up";
                        }
                    case "up":
                        startrow--;
                        steps++;
                        if(maze[startrow][startcolumn].equals("E")) {
                            moving = false;
                            break;
                        } else if(maze[startrow-1][startcolumn].equals("#")) {
                            direction = "right";
                            break;
                        }

                        break;
                    case "right":
                        startcolumn++;
                        steps++;
                        if(maze[startrow][startcolumn].equals("E")) {
                            moving = false;
                            break;
                        } else if(maze[startrow][startcolumn+1].equals("#")) {
                            direction = "down";
                            break;
                        }

                        break;
                    case "down":
                        startrow++;
                        steps++;
                        if(maze[startrow][startcolumn].equals("E")) {
                            moving = false;
                            break;
                        } else if(maze[startrow+1][startcolumn].equals("#")) {
                            direction = "left";
                            break;
                        }

                        break;
                    case "left":
                        startcolumn--;
                        steps++;
                        if(maze[startrow][startcolumn].equals("E")) {
                            moving = false;
                            break;
                        } else if(maze[startrow][startcolumn-1].equals("#")) {
                            direction = "up";
                            break;
                        }

                        break;
                }
            }
            String answer = Integer.toString(steps);
            System.out.println(id+":"+answer);
        } catch (FileNotFoundException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}

