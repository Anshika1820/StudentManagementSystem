package sms;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;

public class StudentManagementSystem extends javax.swing.JFrame {
    Connection conn;
    Statement stmt;
    public StudentManagementSystem() {
        initComponents();  
        connectDatabase();
    }
    //to initialize the attributs of a particular student 
    public class Student {
    private String name;
    private String grade;
    private String rollNumber;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;      
    }
    public String toString() {
       return  "\nName: " + name + "\nRoll Number: " + rollNumber + "\nGrade: " + grade + "\n";  //data will be print in this pattern
        
    }
}

//to connect the database to my application
  private void connectDatabase(){
    try{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/smsdb", "root", "anshika");
        stmt=conn.createStatement();
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(this,"Connection not established properly with database!!!");
    }
}
 
  //this is a method for adding a student details
  private void addStudent() {
    try {
        if (txtname.getText().isEmpty() || txtgrade.getText().isEmpty() || txtroll.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill each field \n Every field is compulsory!!!");
            return;
        }
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (name, grade, roll_number) VALUES (?, ?, ?)");
        stmt.setString(1, txtname.getText());
        stmt.setString(2, txtgrade.getText());
        stmt.setInt(3, Integer.parseInt(txtroll.getText()));
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Student successfully added");
        txtname.setText("");
        txtgrade.setText("");
        txtroll.setText("");
    } 
    catch (HeadlessException | SQLException e) {
        e.printStackTrace();
    }
}

  //method to modify the details of a student
private void modifyStudent() {
    try {
        PreparedStatement stmt = conn.prepareStatement("UPDATE student SET name=?, grade=? WHERE roll_number=?");
        stmt.setString(1, txtname.getText());
        stmt.setString(2, txtgrade.getText());
        stmt.setInt(3, Integer.parseInt(txtroll.getText()));
        int rows = stmt.executeUpdate();
        if (rows > 0) {
            JOptionPane.showMessageDialog(this, "Successfully updated");
        } else {
            JOptionPane.showMessageDialog(this, "No student found with this roll number");
        }
        txtname.setText("");
        txtgrade.setText("");
        txtroll.setText("");
    } catch (HeadlessException | SQLException e) {
        e.printStackTrace();
    }
}

//to delete the data of the student
private void deleteStudent() {
    try {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE roll_number=?");
        stmt.setInt(1, Integer.parseInt(txtroll.getText()));
        int rows = stmt.executeUpdate();
        if (rows > 0) {
            JOptionPane.showMessageDialog(this, "Successfully deleted");
        } else {
            JOptionPane.showMessageDialog(this, "No student found with this roll number");
        }
        txtname.setText("");
        txtgrade.setText("");
        txtroll.setText("");
    } catch (HeadlessException | SQLException e) {
        e.printStackTrace();
    }
}

//to display the record of the students
private void displayStudent() {
    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");
        displayarea.setText("");  // Clear display

        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            Student student = new Student(
                
                rs.getString("name"),
                rs.getString("grade"),
                rs.getString("roll_number")
               
            );
            sb.append(student.toString()).append("___________________________________________________________________________________");
        }

        if (sb.length() == 0) {
            displayarea.setText("No students found.");
        } else {
            displayarea.setText(sb.toString());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

//to the display the student's data tha has been searched
private void searchStudent() {
    try {
        String search = txtsearch.getText().trim();
        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter the roll number or name of the student");
            return;
        }
        String sql = "SELECT * FROM student WHERE roll_number = ? OR name LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setString(1, search);  
        } catch (SQLException e) {
            stmt.setNull(1, java.sql.Types.VARCHAR);
        }
        stmt.setString(2, "%" + search + "%");  
        ResultSet rs = stmt.executeQuery();
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        while (rs.next()) {
            found = true;
            String name = rs.getString("name");
            String grade = rs.getString("grade");
            String roll_number = rs.getString("roll_number");
            sb.append("Name: ").append(name).append("\n")
              .append("Grade: ").append(grade).append("\n")
              .append("Roll Number: ").append(roll_number).append("\n")
              .append("___________________________________________________________________________________");
        }
        if (!found) {
            displayarea.setText("No student found.");
        } else {
            displayarea.setText(sb.toString());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayarea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtgrade = new javax.swing.JTextField();
        txtroll = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnmodify = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btndetail = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();

        jTextField3.setBackground(new java.awt.Color(0, 0, 51));
        jTextField3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel1.setFont(new java.awt.Font("Script MT Bold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Management System");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        displayarea.setEditable(false);
        displayarea.setBackground(new java.awt.Color(0, 0, 51));
        displayarea.setColumns(20);
        displayarea.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        displayarea.setForeground(new java.awt.Color(255, 255, 255));
        displayarea.setRows(5);
        jScrollPane1.setViewportView(displayarea);

        jLabel2.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Grade");

        jLabel4.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Roll Number");

        txtgrade.setBackground(new java.awt.Color(0, 0, 51));
        txtgrade.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtgrade.setForeground(new java.awt.Color(255, 255, 255));

        txtroll.setBackground(new java.awt.Color(0, 0, 51));
        txtroll.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtroll.setForeground(new java.awt.Color(255, 255, 255));

        txtname.setBackground(new java.awt.Color(0, 0, 51));
        txtname.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtname.setForeground(new java.awt.Color(255, 255, 255));

        btnadd.setBackground(new java.awt.Color(0, 0, 51));
        btnadd.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        btnadd.setForeground(new java.awt.Color(255, 255, 255));
        btnadd.setText("ADD");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnmodify.setBackground(new java.awt.Color(0, 0, 51));
        btnmodify.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        btnmodify.setForeground(new java.awt.Color(255, 255, 255));
        btnmodify.setText("MODIFY");
        btnmodify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifyActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(0, 0, 51));
        btndelete.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        btndelete.setForeground(new java.awt.Color(255, 255, 255));
        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btndetail.setBackground(new java.awt.Color(0, 0, 51));
        btndetail.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        btndetail.setForeground(new java.awt.Color(255, 255, 255));
        btndetail.setText("SHOW ALL STUDENTS");
        btndetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndetailActionPerformed(evt);
            }
        });

        btnexit.setBackground(new java.awt.Color(255, 0, 0));
        btnexit.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        btnexit.setForeground(new java.awt.Color(255, 255, 255));
        btnexit.setText("EXIT");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        txtsearch.setBackground(new java.awt.Color(0, 0, 51));
        txtsearch.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtsearch.setForeground(new java.awt.Color(255, 255, 255));

        btnsearch.setBackground(new java.awt.Color(0, 0, 51));
        btnsearch.setFont(new java.awt.Font("Gabriola", 1, 14)); // NOI18N
        btnsearch.setForeground(new java.awt.Color(255, 255, 255));
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtgrade, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btnmodify)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btndelete)
                                .addGap(32, 32, 32))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtroll, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(btndetail, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtgrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnadd)
                            .addComponent(btnmodify)
                            .addComponent(btndelete))
                        .addGap(32, 32, 32)
                        .addComponent(btndetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnexit)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsearch))
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//calling the methods in each action performed on the buttons
    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        addStudent();
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnmodifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifyActionPerformed
       modifyStudent();
    }//GEN-LAST:event_btnmodifyActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        deleteStudent();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btndetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndetailActionPerformed
        displayStudent();
    }//GEN-LAST:event_btndetailActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        System.exit(1);
    }//GEN-LAST:event_btnexitActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        searchStudent();
    }//GEN-LAST:event_btnsearchActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btndetail;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnmodify;
    private javax.swing.JButton btnsearch;
    private javax.swing.JTextArea displayarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField txtgrade;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtroll;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
