package Interface;

import DB.Connect;
import DB.Database;
import Model.Faculty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacultyTable {
public static void create(int WIDTH,int HEIGHT){
    FacultyTable d = new FacultyTable();
    d.createFacultyTable(WIDTH,HEIGHT);
}


public void createFacultyTable(int WIDTH,int HEIGHT) {
    Connect connect= new Connect();
    Database database=new Database(connect.connectToDB());

    int facultySort=0;

    JFrame facultyTable = new JFrame("Lab 1: FACULTY");
    facultyTable.setSize(WIDTH, HEIGHT);
    facultyTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    facultyTable.setVisible(true);

    JTable table=new JTable();
    DefaultTableModel model=new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }
    };


    ArrayList<Faculty> array=database.getFaculties(facultySort);

    Object[] columns = new Object[1];
    columns[0]="Name";


    JScrollPane pane = new JScrollPane(table);

    facultyTable.setLayout(null);
    pane.setBounds(0, 0, WIDTH-18, 3*HEIGHT/5);
    facultyTable.add(pane);
    Object[] row = new Object[1];
    Table.tableParameters(WIDTH,table,columns,model);

    for (int j = 0; j < array.size(); j++) {
        row[0]=array.get(j).getName();
        model.addRow(row);
    }



    JButton backButton = new JButton("Back");
    backButton.setBounds(10,HEIGHT-120,WIDTH/6,50);
    facultyTable.add(backButton);

    backButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            facultyTable.setVisible(false);
            Menu.create(WIDTH,HEIGHT);
        }
    });

    JButton newButton = new JButton("New faculty");
    newButton.setBounds(30+WIDTH/6,HEIGHT-120,WIDTH/6,50);
    facultyTable.add(newButton);



    newButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            facultyTable.setVisible(false);
            NewOrEditWindow.createFac(WIDTH,HEIGHT,"new",facultySort,-1);

        }
    });

    JButton editButton = new JButton("Edit faculty");
    editButton.setBounds(50+2*WIDTH/6,HEIGHT-120,WIDTH/6,50);
    facultyTable.add(editButton);

    editButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            int row = table.getSelectedRow();
            if(row >= 0){

facultyTable.setVisible(false);
                NewOrEditWindow.createFac(WIDTH,HEIGHT,"edit",facultySort,row);

            }
            else{
                System.out.println("Edit Error");
            }
        }
    });

    JButton delButton = new JButton("Delete faculty");
    delButton.setBounds(70+3*WIDTH/6,HEIGHT-120,WIDTH/6,50);
    facultyTable.add(delButton);

    delButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            int i = table.getSelectedRow();
            if(i >= 0){
                model.removeRow(i);

                try {
                    database.deleteFaculty(database.getFaculties(facultySort).get(i).getId());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
            else{
                System.out.println("Delete Error");
            }
        }
    });

    JButton openButton = new JButton("Open cafedras");
    openButton.setBounds(90+4*WIDTH/6,HEIGHT-120,WIDTH/6,50);
    facultyTable.add(openButton);

    openButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int i = table.getSelectedRow();
            if(i>=0){
            facultyTable.setVisible(false);
            CafedraTable.create(WIDTH,HEIGHT,database.getFaculties(facultySort).get(i).getId(),i);}
            else
                System.out.println("Open_Cafedras Error");

        }
    });


            facultyTable.add(StartButton.createHideButton());

}

}

