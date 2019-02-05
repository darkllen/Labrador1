package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    public static void create(int WIDTH, int HEIGHT) {
        Menu d= new Menu();
    d.createMenu(WIDTH,HEIGHT);
    }

   public void createMenu(int WIDTH,int HEIGHT) {


       JFrame menu=new JFrame("Lab 1: MENU");
       menu.setSize(WIDTH,HEIGHT);
       menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       menu.setVisible(true);

        JButton facultyButton = new JButton("Faculty");
//        facultyButton.setSize(new Dimension(2*WIDTH/7,HEIGHT/5));
//        facultyButton.setLocation(WIDTH/7,HEIGHT/5);
        facultyButton.setBounds(WIDTH/7,HEIGHT/5,2*WIDTH/7,HEIGHT/5);
        menu.add(facultyButton);

        JButton peopleButton = new JButton("People");
        peopleButton.setBounds(4*WIDTH/7,HEIGHT/5,2*WIDTH/7,HEIGHT/5);
        menu.add(peopleButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(2*WIDTH/7,3*HEIGHT/5,3*WIDTH/7,HEIGHT/5);
        menu.add(searchButton);


        menu.add(StartButton.createHideButton());

       facultyButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               System.out.println("pressed Faculty");
               menu.setVisible(false);
               FacultyTable.create(WIDTH,HEIGHT);
           }
       });

       peopleButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){

               System.out.println("pressed People");
               menu.setVisible(false);
               PeopleTable.create(WIDTH,HEIGHT,-1,-1,-1,-1,null,0,0);
           }
       });

       searchButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){

               System.out.println("pressed Search");
               menu.setVisible(false);
               Search.create(WIDTH,HEIGHT);
           }
       });





   }

}
