package Interface;

import DB.Connect;
import DB.Database;
import Model.Faculty;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class FacultyTable {
public static void create(int WIDTH,int HEIGHT){
    FacultyTable d = new FacultyTable();
    d.createFacultyTable(WIDTH,HEIGHT);
}


public void createFacultyTable(int WIDTH,int HEIGHT) {
    Connect connect= new Connect();
    Database database=new Database(connect.connectToDB());

    JFrame facultyTable = new JFrame("Lab 1: FACULTY");
    facultyTable.setSize(WIDTH, HEIGHT);
    facultyTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    facultyTable.setVisible(true);

    JTable table=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    final boolean[] newButtonPressed = {false};
    final boolean[] delButtonPressed = {false};

    ArrayList<Faculty> array=database.getFaculties();

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
    backButton.setBounds(10,HEIGHT-120,WIDTH/5,50);
    facultyTable.add(backButton);

    backButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            facultyTable.setVisible(false);
            Menu.create(WIDTH,HEIGHT);
        }
    });

    JButton newButton = new JButton("New faculty");
    newButton.setBounds(30+WIDTH/5,HEIGHT-120,WIDTH/5,50);
    facultyTable.add(newButton);


    // JTextField textId = new JTextField();
    ///textId.setBounds(20, 220, 100, 25);
    //facultyTable.add(textId);

    newButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
           database.insertNewFaculty("New Faculty!");
            newButtonPressed[0] =true;
            row[0]="New Faculty!";
            model.addRow(row);
        }
    });

    JButton delButton = new JButton("Delete faculty");
    delButton.setBounds(50+2*WIDTH/5,HEIGHT-120,WIDTH/5,50);
    facultyTable.add(delButton);

    delButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            int i = table.getSelectedRow();
            if(i >= 0){
                delButtonPressed[0]=true;
                model.removeRow(i);

                try {
                    database.deleteFaculty(database.getFaculties().get(i).getId());
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
    openButton.setBounds(70+3*WIDTH/5,HEIGHT-120,WIDTH/5,50);
    facultyTable.add(openButton);

    openButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int i = table.getSelectedRow();
            if(i>=0){
            facultyTable.setVisible(false);
            CafedraTable.create(WIDTH,HEIGHT,database.getFaculties().get(i).getId(),i);}
            else
                System.out.println("Open_Cafedras Error");

        }
    });


            facultyTable.add(StartButton.createHideButton());



    model.addTableModelListener(new TableModelListener() {

        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
        int column = e.getColumn();

        Object data=new Object();
        if(newButtonPressed[0]==true){
                newButtonPressed[0]=false;
        }else
            if(delButtonPressed[0]==true){
                delButtonPressed[0]=false;
            }
        else
            {
            data = model.getValueAt(row, column);
            database.updateFacultyById(database.getFaculties().get(row).getId(), data.toString());
        }

        }

    });


}


}

