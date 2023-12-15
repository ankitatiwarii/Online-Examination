
package online.examination;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public Test() {
        initComponents();
        
        Connect();
        LoadQuestions();
    }
    int answercheck = 0;
    int marks = 0;
    
    Connection con;
   PreparedStatement pst;
   ResultSet rs;
    String cor = null;
   String answer = null;
   Statement stat;
   
   public boolean answerCheck()
   {
       
       String answerAnswer = "";
        if (r1.isSelected())
        {
            answerAnswer = r1.getText();
            
        }
        else if(r2.isSelected())
        {
        answerAnswer = r2.getText();
        }
        
        else if(r3.isSelected())
        {
        answerAnswer = r3.getText();
        }
        
        else if(r4.isSelected())
         {
         answerAnswer = r4.getText();
         }
        
        if(answer.equals(cor) && (answer==null || answer.equals(cor)))
        {
          marks = marks + 1;
          
          txtmarks.setText(String.valueOf(marks));
        
        }
        
        else if(!answerAnswer.equals(cor) && answer!=null)
        {
         //Only deduct if marks greater than zero
            if(marks > 0)
            {
                
                marks = marks - 1;
            }
            
            txtmarks.setText(String.valueOf(marks));
        
        }
        
        if(!answerAnswer.equals(""))
        {
            try {
                String query = "UPDATE question SET givenanswer = ? WHERE question = ?";
                pst = con.prepareStatement(query);
                pst.setString(1, answerAnswer);
                 pst.setString(2, jLabel2.getText());
                 pst.execute();
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            return true;
        
        }
        //when no button is selected
        
        return false;
     
   }
   
   private void NullAllGivenAnswers()
   {
       try {
           String query = "UPDATE question SET givenanswer = ?";
           pst = con.prepareStatement(query);
           pst.setString(1, null);
           pst.execute();
         
           
       } catch (Exception ex) {
           ex.printStackTrace();
       }
   
   }
   
   private boolean AlreadyAnswered()
   {
       String query = "SELECT givenanswer FROM question where question = '"+jLabel2.getText()+"'";
        try {
            stat = con.prepareStatement(query);
            
            ResultSet rs = stat.executeQuery(query);
            
            while(rs.next())
            {
              answer = rs.getString("givenanswer");
              if(answer == null)
              {
               return false;
              }
            break;
            
            }
            if(r1.getText().equals(answer))
            {
            r1.setSelected(true);
            
            }
            
            else if(r2.getText().equals(answer))
            {
            r2.setSelected(true);
            }
            else if(r3.getText().equals(answer))
            {
            r3.setSelected(true);
            }
            else if(r4.getText().equals(answer))
            {
            r4.setSelected(true);
            }
                
                
            
        } catch (SQLException ex) {
            System.out.println("Exception Caught");
            ex.printStackTrace();
        }
        return true;
   }
   
    
    
  
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/onlineexam","root","");
                    
                    } catch (ClassNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void LoadQuestions()
    {
      String query = "select * from question";
      Statement stat = null;
      
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(query);
            
            while(rs.next())
            {
              jLabel3.setText(rs.getString(1));
              jLabel2.setText(rs.getString(2));
              r1.setText(rs.getString(3));
              r2.setText(rs.getString(4));
              r3.setText(rs.getString(5));
              r4.setText(rs.getString(6));
              answer = rs.getString(7);
              
              // for one row only
              break;
              
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtmarks = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Online Test");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Questions");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(r1);
        r1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        r1.setText("jRadioButton1");

        buttonGroup1.add(r2);
        r2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        r2.setText("jRadioButton2");

        buttonGroup1.add(r3);
        r3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        r3.setText("jRadioButton3");

        buttonGroup1.add(r4);
        r4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        r4.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(r4)
                    .addComponent(r3)
                    .addComponent(r2)
                    .addComponent(r1))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(r1)
                .addGap(18, 18, 18)
                .addComponent(r2)
                .addGap(18, 18, 18)
                .addComponent(r3)
                .addGap(18, 18, 18)
                .addComponent(r4)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("No");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("Next");

        txtmarks.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtmarks.setForeground(new java.awt.Color(255, 51, 51));
        txtmarks.setText("Marks");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmarks, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(62, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmarks)
                    .addComponent(jButton1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton r4;
    private javax.swing.JLabel txtmarks;
    // End of variables declaration//GEN-END:variables
}
