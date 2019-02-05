package Interface;

import DB.Connect;
import DB.Database;
import Model.Cafedra;
import Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Search {

        public static void create(int WIDTH, int HEIGHT) {
            Interface.Search d= new Interface.Search();
            d.createSearch(WIDTH,HEIGHT);
        }

        public void createSearch(int WIDTH,int HEIGHT) {
            Connect connect= new Connect();
            Database database=new Database(connect.connectToDB());

            int sortColumn=0;
            int sortAD=0;

            JFrame frame = new JFrame("LAB 1: SEARCH MENU");
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLayout(null);

//            JLabel label1=new JLabel();
//            label1.setText("Print name:");
//            label1.setLocation(WIDTH/2-70-label1.getWidth(),30);
//            frame.add(label1);
            JTextField[]text=new JTextField[7];

            JLabel s[] = new JLabel[7];
            for(int i=0;i<7;i++){
                s[i]=new JLabel();
            }

           // s[0].setText("Cafedra");
            s[0].setText("Name");
            s[1].setText("Surname");
            s[2].setText("FatherName");
            s[3].setText("Student or Teacher");
            s[4].setText("Course");
            s[5].setText("Group");
            s[6].setText("Cafedra");



            for(int i=0;i<text.length;i++){
                text[i]=new JTextField();
                text[i].setBounds(WIDTH/2-50, 20+(45*i), 100, 25);
                frame.add(text[i]);
            }


//            for (int i = 0; i < text.length; i += 1) {
//                s[i].setLocation(WIDTH/2-s[i].getWidth()-70,50*(i+1));
//                frame.add(s[i]);
//                System.out.println("LABEL");
//
//            }





            frame.add(StartButton.createHideButton());

            JButton searchButton = new JButton("Search");
            searchButton.setBounds(3*WIDTH/4-WIDTH/10, HEIGHT-115, WIDTH / 5, 50);
            frame.add(searchButton);

            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    ArrayList<Person> arrayP;
                    ArrayList<Cafedra> arrayCaf;
                    int cafId=0;
                    arrayCaf = database.getCafedrasByName(text[6].getText());
                    if (arrayCaf.size() != 0) {
                        cafId=arrayCaf.get(0).getId();
                    arrayP = database.findPerson(cafId, text[0].getText().toString(), text[1].getText().toString(), text[2].getText().toString(), text[3].getText(), text[4].getText(), text[5].getText(), sortColumn, sortAD);

                    PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,arrayP,sortColumn,sortAD);}
                    else Search.create(WIDTH,HEIGHT);

                }
            });

            JButton backButton = new JButton("Back");
            backButton.setBounds(10,HEIGHT-120,WIDTH/5,50);
            frame.add(backButton);

            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.setVisible(false);
                    Menu.create(WIDTH,HEIGHT);
                }
            });


        }
}
