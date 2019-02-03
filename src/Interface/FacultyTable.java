package Interface;

import DB.Connect;
import DB.Database;
import Model.Faculty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class FacultyTable {
public static void create(int WIDTH,int HEIGHT){
    FacultyTable d = new FacultyTable();
    d.createFacultyTable(WIDTH,HEIGHT);




}


public void createFacultyTable(int WIDTH,int HEIGHT) {

    JFrame facultyTable = new JFrame("Lab 1: FACULTY");
    facultyTable.setSize(WIDTH, HEIGHT);
    facultyTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    facultyTable.setVisible(true);

    JButton backButton = new JButton("Back");
    backButton.setBounds(10,HEIGHT-120,WIDTH/5,50);
    facultyTable.add(backButton);

    backButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            System.out.println("pressed Faculty");
            facultyTable.setVisible(false);
            Menu.create(WIDTH,HEIGHT);
        }
    });

    facultyTable.add(StartButton.createHideButton());


    Connect connect= new Connect();
    Database database=new Database(connect.connectToDB());
    ArrayList<Faculty> array=database.getFaculties();

    Vector<String> columns = new Vector<String>();
    columns.add("Name");

    JTable table=new JTable();


    DefaultTableModel model=new DefaultTableModel();
    JScrollPane pane = new JScrollPane(table);

    facultyTable.setLayout(null);
    pane.setBounds(0, 0, WIDTH-18, 3*HEIGHT/5);
    facultyTable.add(pane);

    Table.tableParameters(WIDTH,table,columns,model,array);

    for (int j = 0; j < array.size(); j++) {
        Vector<String> row = new Vector<String>();
        row.add((String)array.get(j).getName());
        model.addRow(row);
    }




   //facultyTable.add(table);
}
}

