/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sailee
 */
public class VisitPatientController{

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField pid;
    @FXML
    private TextField pfnm;
    @FXML
    private TextField plnm;
    @FXML
    private TextField phone;
    @FXML
    private TextField pdate;
     @FXML
    private TextField paddr;
    @FXML
    private Button pok;
    public void actionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException, IOException
    {
       String s1= pid.getText();
       String s2= pfnm.getText();
       String s3= plnm.getText();
       String s4= phone.getText();
       String s5= pdate.getText();
       String s6=paddr.getText();
    //   System.out.println(s1+" "+s2+" "+s3+" "+s4+" "+s5+" "+s6);
        Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            PreparedStatement st=con.prepareStatement("insert into pvisited values(?,?,?,?,?,?)");
            st.setInt(1,Integer.parseInt(s1));
            st.setString(2, s2);
            st.setString(3, s3);
            st.setLong(4, Long.parseLong(s4));
            st.setString(5, s6);
            st.setString(6, s5);
            if(e.getSource()==pok)
            {
                st.executeUpdate();
                Stage stage=(Stage) pok.getScene().getWindow();
                Parent root=FXMLLoader.load(getClass().getResource("Start.fxml"));
                Scene scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            
       
    }
    
    
}
