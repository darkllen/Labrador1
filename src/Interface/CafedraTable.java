package Interface;

import DB.Connect;
import DB.Database;
import Model.Cafedra;
import Model.Faculty;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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

        JFrame cafedraTable = new JFrame("Lab 1: CAFEDRAS OF \"" + database.getFaculties().get(number).getName()+"\"");
        cafedraTable.setSize(WIDTH, HEIGHT);
        cafedraTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cafedraTable.setVisible(true);


        JTable table=new JTable();
        DefaultTableModel model=new DefaultTableModel();
        final boolean[] newButtonPressed = {false};
        final boolean[] delButtonPressed = {false};

        ArrayList<Cafedra> array=database.getCafedrasByFacultyId(fId);

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
        backButton.setBounds(10,HEIGHT-120,WIDTH/5,50);
        cafedraTable.add(backButton);

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cafedraTable.setVisible(false);
                FacultyTable.create(WIDTH,HEIGHT);
            }
        });

        JButton newButton = new JButton("New Cafedra");
        newButton.setBounds(30+WIDTH/5,HEIGHT-120,WIDTH/5,50);
        cafedraTable.add(newButton);


        // JTextField textId = new JTextField();
        ///textId.setBounds(20, 220, 100, 25);
        //facultyTable.add(textId);

        newButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                database.insertNewCafedra(fId,"New Cafedra!");
                newButtonPressed[0] =true;
                row[0]="New Cafedra!";
                model.addRow(row);
            }
        });

        JButton delButton = new JButton("Delete cafedra");
        delButton.setBounds(50+2*WIDTH/5,HEIGHT-120,WIDTH/5,50);
        cafedraTable.add(delButton);

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                if(i >= 0){
                    delButtonPressed[0]=true;
                    model.removeRow(i);
                    int cId=database.getCafedrasByFacultyId(fId).get(i).getFacultyId();
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
        openButton.setBounds(70+3*WIDTH/5,HEIGHT-120,WIDTH/5,50);
        cafedraTable.add(openButton);

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if(i>=0){
                    cafedraTable.setVisible(false);
                    PeopleTable.create(WIDTH,HEIGHT,database.getCafedrasByFacultyId(fId).get(i).getId(),i,fId,number,null);
                    }
                else
                    System.out.println("Open_People Error");

            }
        });

        cafedraTable.add(StartButton.createHideButton());



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
                    database.updateCafedra(database.getCafedrasByFacultyId(fId).get(row).getId(),fId,data.toString());
                }

            }

        });
    }
}
