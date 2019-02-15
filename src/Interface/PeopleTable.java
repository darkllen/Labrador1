package Interface;

import DB.Connect;
import DB.Database;
import Model.Person;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeopleTable {

    int cId;

    public static void create(int WIDTH,int HEIGHT,int cId,int number,int fId,int fNumber,ArrayList<Person>myArray,int sortColumn,int sortAD){

        PeopleTable d = new PeopleTable();
        d.createPeopleTable(WIDTH,HEIGHT,cId,number,fId,fNumber,myArray,sortColumn,sortAD);
    }

    private void createPeopleTable(int WIDTH, int HEIGHT,int cId,int number,int fId,int fNumber,ArrayList<Person>myArray,int sortColumn,int sortAD) {
        this.cId=cId;

        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());

        int cafedraSort=0;
        int facultySort=0;

        JFrame peopleTable = new JFrame("Lab 1: PEOPLE");
        peopleTable.setSize(WIDTH, HEIGHT);
        peopleTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        peopleTable.setVisible(true);


        JTable table=new JTable();
        DefaultTableModel model=new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {

                return false;
            }
        };


        ArrayList<Person> array;
        if(myArray!=null){database.sortPerson(myArray,sortColumn,sortAD);array=myArray;}
        else
       if(cId==-1)
           array=database.getPeople(sortColumn,sortAD);
       else array=database.getPeopleByCafedraId(cId,cafedraSort,sortAD);

        Object[] columns = new Object[6];
        columns[0]="Name";
        columns[1]="Surname";
        columns[2]="Fathername";
        columns[3]="Student or Teacher";
        columns[4]="Course";
        columns[5]="Group";

        JScrollPane pane = new JScrollPane(table);

        peopleTable.setLayout(null);
        pane.setBounds(0, 0, WIDTH-18, 2*HEIGHT/3); //////!!!!!!!!!!!!!!!!!!
        peopleTable.add(pane);
        Object[] row = new Object[6];
        Table.tableParameters(WIDTH,table,columns,model);

        for (int j = 0; j < array.size(); j++) {
            row[0]=array.get(j).getName();
            row[1]=array.get(j).getSurname();
            row[2]=array.get(j).getFatherName();
            row[3]=array.get(j).isATeacher();
            row[4]=array.get(j).getCourse();
            row[5]=array.get(j).getGroup();
            model.addRow(row);
        }

        TableColumn professionColumn = table.getColumnModel().getColumn(3);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Student");
        comboBox.addItem("Teacher");
        professionColumn.setCellEditor(new DefaultCellEditor(comboBox));

        if(cId!=-1) {

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, HEIGHT - 120, WIDTH / 6, 50);
            peopleTable.add(backButton);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    peopleTable.setVisible(false);

                    CafedraTable.create(WIDTH, HEIGHT,fId,fNumber);

                }
            });

            JButton newButton = new JButton("New Person");
            newButton.setBounds(30 + WIDTH / 6, HEIGHT - 120, WIDTH / 6, 50);
            peopleTable.add(newButton);


            newButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    peopleTable.setVisible(false);
                    NewOrEditWindow.createPer( WIDTH, HEIGHT,"new",cId,sortColumn, sortAD,-1,number,fId,fNumber,myArray);

//
                }
            });

            JButton editButton = new JButton("Edit person");
            editButton.setBounds(50 + 2 * WIDTH / 6, HEIGHT - 120, WIDTH / 6, 50);
            peopleTable.add(editButton);

            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int row = table.getSelectedRow();
                    if (row >= 0) {

                       peopleTable.setVisible(false);
                        NewOrEditWindow.createPer( WIDTH, HEIGHT,"edit",cId,sortColumn, sortAD,row,number,fId,fNumber,myArray);


                    } else {
                        System.out.println("Edit Error");
                    }
                }
            });

            JButton delButton = new JButton("Delete person");
            delButton.setBounds(70 + 3 * WIDTH / 6, HEIGHT - 120, WIDTH / 6, 50);
            peopleTable.add(delButton);

            delButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int i = table.getSelectedRow();
                    if (i >= 0) {

                        model.removeRow(i);
                        int pId = database.getPeopleByCafedraId(cId,sortColumn,sortAD).get(i).getId();
                        try {
                            database.deletePeople(pId);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }


                    } else {
                        System.out.println("Delete Error");
                    }
                }
            });


            peopleTable.add(StartButton.createHideButton());


        }else{


            JButton backButton = new JButton("Back");
            backButton.setBounds(WIDTH/8,HEIGHT-120,WIDTH/4,50);
            peopleTable.add(backButton);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    peopleTable.setVisible(false);

                    Menu.create(WIDTH, HEIGHT);

                }
            });

            JButton sortButton = new JButton("SortPeople");
            sortButton.setBounds(3*WIDTH/4-WIDTH/8,HEIGHT-120,WIDTH/4,50);
            peopleTable.add(sortButton);

            sortButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    peopleTable.setVisible(false);
                    SortPeople.create(WIDTH, HEIGHT,myArray);

                }
            });


        }


        }

    }

