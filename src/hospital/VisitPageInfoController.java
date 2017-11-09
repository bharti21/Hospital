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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sailee
 */
public class VisitPageInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Admin> data=FXCollections.observableArrayList();
    @FXML
    TableView<Admin> table;
    
    @FXML
    private TableColumn<Admin,Integer>pid;
    
    @FXML
    private TableColumn<Admin,String>pfnm;
    
    @FXML
    private TableColumn<Admin,String>plnm;
    
    @FXML
    private TableColumn<Admin,Long>pphone_no;
    
    @FXML
    private TableColumn<Admin,String>p_add;
    
    @FXML
    private TableColumn<Admin,String>p_visitDate;
    
    @FXML
    private Button show;
    
    @FXML
    private Button back;
    
    @FXML
    private Button search;
    
    @FXML
    private TextField searchfield;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     /*   pid.setCellFactory(new propertyValueFactory<>("pid"));
        pid.setCellFactory(new propertyValueFactory<>("pid"));
        pid.setCellFactory(new propertyValueFactory<>("pid"));*/
        
    } 
    
    public void loadDataBaseData() throws ClassNotFoundException, SQLException
    {
        pid.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("PID"));

        pfnm.setCellValueFactory(new PropertyValueFactory<Admin, String>("FirstName"));

        plnm.setCellValueFactory(new PropertyValueFactory<Admin, String>("LastName"));
        
        pphone_no.setCellValueFactory(new PropertyValueFactory<Admin, Long>("Phone"));
        
        p_add.setCellValueFactory(new PropertyValueFactory<Admin, String>("Address"));
        
        p_visitDate.setCellValueFactory(new PropertyValueFactory<Admin, String>("VisitDate"));
        
        System.out.println("I m n");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            Statement st=con.createStatement();
            String sql="select * from pvisited ";
            ResultSet rs=st.executeQuery(sql);
            System.out.println("I m n1");
            while(rs.next())
            {
                data.add(new Admin(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getLong(4),
                rs.getString(5),
                rs.getDate(6)+""
                ));
                System.out.println("I m n2");
                table.setItems(data);
                System.out.println("I m n3");
              //  System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getLong(4)+rs.getString(5)+rs.getDate(6));
            }
    }
    public void goBack() throws IOException
    {
          Stage stage=(Stage) back.getScene().getWindow();
            Parent root=FXMLLoader.load(getClass().getResource("Start.fxml"));
            Scene scene=new Scene(root);
             stage.setScene(scene);
            stage.show();
    }
    
    public void search()
    {
         FilteredList<Admin> filteredData = new FilteredList<>(data, p -> true);
         //searchfield.setOnKeyReleased(p->{searchfield.textProperty().addListener(observable, oldValue, newValue)});
        searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(admin -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (admin.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (admin.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Admin> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }
    
}
