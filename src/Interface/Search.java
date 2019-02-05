package Interface;

import DB.Connect;
import DB.Database;
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

            JFrame frame = new JFrame("LAB 1: SEARCH MENU");
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLayout(null);

//            JLabel label1=new JLabel();
//            label1.setText("Print name:");
//            label1.setLocation(WIDTH/2-70-label1.getWidth(),30);
//            frame.add(label1);
            JTextField[]text=new JTextField[6];

            JLabel s[] = new JLabel[6];
            for(int i=0;i<6;i++){
                s[i]=new JLabel();
            }

           // s[0].setText("Cafedra");
            s[0].setText("Name");
            s[1].setText("Surname");
            s[2].setText("FatherName");
            s[3].setText("Student or Teacher");
            s[4].setText("Course");
            s[5].setText("Group");



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
            searchButton.setBounds(WIDTH/2-WIDTH/10, HEIGHT-115, WIDTH / 5, 50);
            frame.add(searchButton);

            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    String[]text1=new String[6];
                    for(int i=3;i<6;i++){
                    if(text[i].getText().equals("")) text1[i]="0";
                    else text1[i]=text[i].getText();
                    }
                    ArrayList<Person> array=database.findPerson(0,text[0].getText().toString(),text[1].getText().toString(),text[2].getText().toString(),Integer.valueOf(text1[3]),Integer.valueOf(text1[4]),Integer.valueOf(text1[5]),);
                    PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,array);

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
