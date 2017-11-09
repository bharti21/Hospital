/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author sailee
 */

public class StartController  {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button newvisit;
    
    @FXML
    private Button newadmit;
    
    @FXML
    private Button visitinfo;
    
    @FXML
    private Button admitinfo;
    
    @FXML
    private Button specialinfo;
    
    @FXML
    private Button generalinfo;
    
    @FXML
    private Button Logout;
    
    
    
    public void actionPerformed1(ActionEvent event) throws IOException, ClassNotFoundException, SQLException
    {
        if(event.getSource()==Logout)
        {
            Stage stage=(Stage) Logout.getScene().getWindow();
            Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene=new Scene(root);
             stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==visitinfo)
        {
            Stage stage=(Stage) visitinfo.getScene().getWindow();
            Parent root=FXMLLoader.load(getClass().getResource("VisitPageInfo.fxml"));
            Scene scene=new Scene(root);
             stage.setScene(scene);
            stage.show();
        }
        
        if(event.getSource()==admitinfo)
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            Statement st=con.createStatement();
            String sql="select * from padmitted ";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getInt(4));
            }
        }
        if(event.getSource()==newvisit)
        {
              Stage stage=(Stage) newvisit.getScene().getWindow();
            Parent root=FXMLLoader.load(getClass().getResource("VisitPatient.fxml"));
            Scene scene=new Scene(root);
             stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==specialinfo)
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            Statement st=con.createStatement();
            String sql="select count(pid) from padmitted full outer join room on padmitted.room_no=room.room_no where room_cat='S'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Room Information");
            alert.setHeaderText(null);
            alert.setContentText("Rooms Available for special category: "+(10-rs.getInt(1)));
            alert.showAndWait();
            }
        }
        if(event.getSource()==generalinfo)
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            Statement st=con.createStatement();
            String sql="select count(pid) from padmitted full outer join room on padmitted.room_no=room.room_no where room_cat='G'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Room Information");
            alert.setHeaderText(null);
            alert.setContentText("Rooms Available for General category: "+(10-rs.getInt(1)));
            alert.showAndWait();
            }
        }
         if(event.getSource()==newadmit)
        {
              Stage stage=(Stage) newadmit.getScene().getWindow();
            Parent root=FXMLLoader.load(getClass().getResource("AdmitPage.fxml"));
            Scene scene=new Scene(root);
             stage.setScene(scene);
            stage.show();
        }
    }
    
    
    
}
