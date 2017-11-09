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
public class AdmitPageController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField pid;
    @FXML
    private TextField admitDate;
    @FXML
    private TextField dischDate;
    @FXML
    private TextField room_no;
    @FXML
    private Button ok;
    
    public void actionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException, IOException
    {
        String s1=pid.getText();
        String s2=admitDate.getText();
        String s3=dischDate.getText();
        String s4=room_no.getText();
        Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            PreparedStatement st=con.prepareStatement("insert into padmitted values(?,?,?,?)");
            st.setInt(1,Integer.parseInt(s1));
            st.setString(2, s2);
            st.setString(3, s3);
            st.setInt(4, Integer.parseInt(s4));
            if(e.getSource()==ok)
            {
                st.executeUpdate();
                Stage stage=(Stage) ok.getScene().getWindow();
                Parent root=FXMLLoader.load(getClass().getResource("Start.fxml"));
                Scene scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
    }
    
}
