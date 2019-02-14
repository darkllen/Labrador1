package Interface;

import DB.Connect;
import DB.Database;
import Model.Cafedra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CafedraTable {

    public static void create(int WIDTH,int HEIGHT,int fId,int number){
        CafedraTable d = new CafedraTable();
        d.createCafedraTable(WIDTH,HEIGHT,fId,number);
    }

    private void createCafedraTable(int WIDTH, int HEIGHT,int fId,int number) {
        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());

        int facultySort=0;
        int cafedraSort=0;
        int sortColumn=0;
        int sortAD=0;

        JFrame cafedraTable = new JFrame("Lab 1: CAFEDRAS OF \"" + database.getFaculties(facultySort).get(number).getName()+"\"");
        cafedraTable.setSize(WIDTH, HEIGHT);
        cafedraTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cafedraTable.setVisible(true);


        JTable table=new JTable();
        DefaultTableModel model=new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };


        ArrayList<Cafedra> array=database.getCafedrasByFacultyId(fId,cafedraSort);

        Object[] columns = new Object[1];
        columns[0]="Name";

        JScrollPane pane = new JScrollPane(table);

        cafedraTable.setLayout(null);
        pane.setBounds(0, 0, WIDTH-18, 3*HEIGHT/5);
        cafedraTable.add(pane);
        Object[] row = new Object[1];
        Table.tableParameters(WIDTH,table,columns,model);

        for (int j = 0; j < array.size(); j++) {
            row[0]=array.get(j).getName();
            model.addRow(row);
        }


        //buttons
        JButton backButton = new JButton("Back");
        backButton.setBounds(10,HEIGHT-120,WIDTH/6,50);
        cafedraTable.add(backButton);

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cafedraTable.setVisible(false);
                FacultyTable.create(WIDTH,HEIGHT);
            }
        });

        JButton newButton = new JButton("New Cafedra");
        newButton.setBounds(30+WIDTH/6,HEIGHT-120,WIDTH/6,50);
        cafedraTable.add(newButton);


        newButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                cafedraTable.setVisible(false);
                NewOrEditWindow.createCaf(WIDTH,HEIGHT,"new",facultySort,0,cafedraSort,fId,number);

            }
        });

        JButton editButton = new JButton("Edit cafedra");
        editButton.setBounds(50+2*WIDTH/6,HEIGHT-120,WIDTH/6,50);
        cafedraTable.add(editButton);

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if(row >= 0){
                    cafedraTable.setVisible(false);

                    NewOrEditWindow.createCaf(WIDTH,HEIGHT,"edit",facultySort,row,cafedraSort,fId,number);


                }
                else{
                    System.out.println("Edit Error");
                }
            }
        });

        JButton delButton = new JButton("Delete cafedra");
        delButton.setBounds(70+3*WIDTH/6,HEIGHT-120,WIDTH/6,50);
        cafedraTable.add(delButton);

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                if(i >= 0){

                    model.removeRow(i);
                    int cId=database.getCafedrasByFacultyId(fId,cafedraSort).get(i).getFacultyId();
                    try {
                        database.deleteСafedra(cId);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });


        JButton openButton = new JButton("Open people");
        openButton.setBounds(90+4*WIDTH/6,HEIGHT-120,WIDTH/6,50);
        cafedraTable.add(openButton);

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if(i>=0){
                    cafedraTable.setVisible(false);
                    PeopleTable.create(WIDTH,HEIGHT,database.getCafedrasByFacultyId(fId,cafedraSort).get(i).getId(),i,fId,number,null,sortColumn,sortAD);
                    }
                else
                    System.out.println("Open_People Error");

            }
        });

        cafedraTable.add(StartButton.createHideButton());




    }
}
