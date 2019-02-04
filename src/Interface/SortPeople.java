package Interface;

import DB.Connect;
import DB.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortPeople {

    public static void create(int WIDTH,int HEIGHT){
        SortPeople d = new SortPeople();
        d.createSort(WIDTH,HEIGHT);
    }

    private void createSort(int WIDTH, int HEIGHT) {
        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());

        JFrame frame = new JFrame("LAB 1: SORT MENU");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        frame.add(StartButton.createHideButton());

        JButton Button1 = new JButton("Sort_1");
        Button1.setBounds(WIDTH/2-WIDTH/10, 30, WIDTH / 5, 50);
        frame.add(Button1);

        Button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,null);

            }
        });

        //other sort buttons


    }
}
