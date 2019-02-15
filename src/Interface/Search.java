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
            JComboBox comboBox = new JComboBox();
            comboBox.addItem("Everyone");
            comboBox.addItem("Student");
            comboBox.addItem("Teacher");
            comboBox.setBounds(WIDTH/2-75, 20+45*3+50, 150, 25);
            frame.add(comboBox);

//            JLabel s[] = new JLabel[7];
//            for(int i=0;i<7;i++){
//                s[i]=new JLabel();
//            }





            for(int i=0;i<text.length;i++){
                text[i]=new JTextField();
                text[i].setBounds(WIDTH/2-75, 20+(45*i)+50, 150, 25);
                if(i!=3)frame.add(text[i]);
            }

//            text[0].setText("Name");
//            text[1].setText("Surname");
//            text[2].setText("FatherName");
//            text[3].setText("Student or Teacher");
//            text[4].setText("Course");
//            text[5].setText("Group");
//            text[6].setText("Cafedra");

            Label[] labels=new Label[7];
            labels[0]=new Label("Name:");
            labels[1]=new Label("Surname:");
            labels[2]=new Label("FatherName:");
            labels[3]=new Label("ST/TEACH:");
            labels[4]=new Label("Course:");
            labels[5]=new Label("Group:");
            labels[6]=new Label("Cafedra:");

            for(int i=0;i<7;i++){labels[i].setBounds(WIDTH/2-75-75,20+45*i+50,75,25);
                frame.add(labels[i]);}



//            for (int i = 0; i < text.length; i += 1) {
//                s[i].setLocation(WIDTH/2-s[i].getWidth()-70,50*(i+1));
//                frame.add(s[i]);
//                System.out.println("LABEL");
//
//            }





            frame.add(StartButton.createHideButton());

            JButton searchButton = new JButton("Search");
            searchButton.setBounds(3*WIDTH/4-WIDTH/8, HEIGHT-150, WIDTH / 4, 50);
            frame.add(searchButton);

            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    text[3].setText(comboBox.getSelectedItem().toString());

                   // if(text[0].getText().equals("Name"))text[0].setText("");
                    //if(text[1].getText().equals("Surname"))text[1].setText("");
                    //if(text[2].getText().equals("FatherName"))text[2].setText("");
                    //if(text[4].getText().equals("Course"))text[4].setText("");
                    //if(text[5].getText().equals("Group"))text[5].setText("");
                    //if(text[6].getText().equals("Cafedra"))text[6].setText("");
                    if(comboBox.getSelectedItem().equals("Everyone")) text[3].setText("");

                    frame.setVisible(false);
                    ArrayList<Person> arrayP;
                    ArrayList<Cafedra> arrayCaf;
                    int cafId=-1;
                    if(text[6].getText().equals("")) {arrayCaf=database.getCafedras(0);cafId=0;}else
                    arrayCaf = database.getCafedrasByName(text[6].getText());
                   // if (arrayCaf.size() != 0)
                          //  ||(text[6].getText().equals(""))
                     //{
                        if((arrayCaf.size()!=0)&&(cafId!=0))cafId=arrayCaf.get(0).getId();
                    arrayP = database.findPerson(cafId, text[0].getText().toString(), text[1].getText().toString(), text[2].getText().toString(), text[3].getText(), text[4].getText(), text[5].getText(), sortColumn, sortAD);

                    PeopleTable.create(WIDTH,HEIGHT,-2,-1,-1,-1,arrayP,sortColumn,sortAD);
                     //}
                    //else Search.create(WIDTH,HEIGHT);

                }
            });

            JButton backButton = new JButton("Back");
            backButton.setBounds(WIDTH/8,HEIGHT-150,WIDTH/4,50);
            frame.add(backButton);

            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.setVisible(false);
                    Menu.create(WIDTH,HEIGHT);
                }
            });


        }
}
