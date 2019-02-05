package Interface;

import DB.Connect;
import DB.Database;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SortPeople {

    public static void create(int WIDTH, int HEIGHT, ArrayList<Person> array){
        SortPeople d = new SortPeople();
        d.createSort(WIDTH,HEIGHT,array);
    }

    private void createSort(int WIDTH, int HEIGHT,ArrayList<Person>array) {
        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());

        JFrame frame = new JFrame("LAB 1: SORT MENU");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        frame.add(StartButton.createHideButton());

        JButton Button01 = new JButton("Sort by name (A-Z)");
        Button01.setBounds(WIDTH/4-WIDTH/8, 10, WIDTH / 4, 40);
        frame.add(Button01);

        Button01.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,0,1);
            }
        });
        JButton Button00 = new JButton("Sort by name (Z-A)");
        Button00.setBounds(3*WIDTH/4-WIDTH/8, 10, WIDTH / 4, 40);
        frame.add(Button00);

        Button00.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,0,0);
            }
        });

        JButton Button11 = new JButton("Sort by surname (A-Z)");
        Button11.setBounds(WIDTH/4-WIDTH/8, 10+60, WIDTH / 4, 40);
        frame.add(Button11);

        Button11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,1,1);
            }
        });
        JButton Button10 = new JButton("Sort by surname (Z-A)");
        Button10.setBounds(3*WIDTH/4-WIDTH/8, 10+60, WIDTH / 4, 40);
        frame.add(Button10);

        Button10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,1,0);
            }
        });

        JButton Button21 = new JButton("Sort by fathername (A-Z)");
        Button21.setBounds(WIDTH/4-WIDTH/8, 10+120, WIDTH / 4, 40);
        frame.add(Button21);

        Button21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,2,1);
            }
        });
        JButton Button20 = new JButton("Sort by fathername (Z-A)");
        Button20.setBounds(3*WIDTH/4-WIDTH/8, 10+120, WIDTH / 4, 40);
        frame.add(Button20);

        Button20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,2,0);
            }
        });

        JButton Button31 = new JButton("Student-Teacher");
        Button31.setBounds(WIDTH/4-WIDTH/8, 10+180, WIDTH / 4, 40);
        frame.add(Button31);

        Button31.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,3,1);
            }
        });
        JButton Button30 = new JButton("Teacher-Student");
        Button30.setBounds(3*WIDTH/4-WIDTH/8, 10+180, WIDTH / 4, 40);
        frame.add(Button30);

        Button30.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,3,0);
            }
        });

        JButton Button41 = new JButton("Sort by course (0-9)");
        Button41.setBounds(WIDTH/4-WIDTH/8, 10+240, WIDTH / 4, 40);
        frame.add(Button41);

        Button41.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,4,1);
            }
        });
        JButton Button40 = new JButton("Sort by course (9-0)");
        Button40.setBounds(3*WIDTH/4-WIDTH/8, 10+240, WIDTH / 4, 40);
        frame.add(Button40);

        Button40.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,4,0);
            }
        });

        JButton Button51 = new JButton("Sort by group (0-9)");
        Button51.setBounds(WIDTH/4-WIDTH/8, 10+300, WIDTH / 4, 40);
        frame.add(Button51);

        Button51.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,5,1);
            }
        });
        JButton Button50 = new JButton("Sort by group (9-0)");
        Button50.setBounds(3*WIDTH/4-WIDTH/8, 10+300, WIDTH / 4, 40);
        frame.add(Button50);

        Button50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array,5,0);
            }
        });

        //other sort buttons


    }
}
