package com.georgiancollege.test1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, Integer> employeeIdColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private TableColumn<Employee, String> cityColumn;

    @FXML
    private TableColumn<Employee, String> provinceColumn;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private CheckBox ontarioOnlyCheckBox;

    @FXML
    private ComboBox<String> areaCodeComboBox;
    //dsadsadsadsad
    @FXML
    private Label noOfEmployeesLabel;
    List<Employee> employees;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        areaCodeComboBox.getItems().add("All");
        employees = DbUtility.getEmployees();
        tableView.getItems().addAll(employees);
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        Set<String> areaCodes = new TreeSet<>();
        for (Employee employee : employees) {
            String areaCode = employee.getPhoneNo().substring(0, 3);
            areaCodes.add(areaCode);
        }
        areaCodeComboBox.getItems().addAll(areaCodes);

        updateNumOfEmployeesLabel(employees.size());
    }

    private void updateNumOfEmployeesLabel(int numEmployees) {
        noOfEmployeesLabel.setText("Number of Employees: " + numEmployees);
    }


    @FXML
    void ontarioOnlyCheckBox_OnClick(ActionEvent event) {
        List<Employee> filteredEmployees;
        if (ontarioOnlyCheckBox.isSelected()) {
            filteredEmployees = tableView.getItems().stream()
                    .filter(employee -> employee.getProvince().equalsIgnoreCase("ON"))
                    .collect(Collectors.toList());
            noOfEmployeesLabel.setText("Number of Employees: " + filteredEmployees.size());
            areaCodeComboBox.getSelectionModel().clearAndSelect(0);
        } else {
            filteredEmployees = DbUtility.getEmployees();
            updateNumOfEmployeesLabel(filteredEmployees.size());
        }
        tableView.getItems().clear();
        tableView.getItems().addAll(filteredEmployees);
    }


    @FXML
    void areaCodeComboBox_OnClick(ActionEvent event) {
        String selectedAreaCode = areaCodeComboBox.getSelectionModel().getSelectedItem();
        if (selectedAreaCode.equals("All")) {
            ontarioOnlyCheckBox.setSelected(false);
            ontarioOnlyCheckBox_OnClick(null);
        } else {
            String regex = "^" + selectedAreaCode + ".*";
            List<Employee> allEmployees = DbUtility.getEmployees();
            List<Employee> filteredEmployees = allEmployees.stream()
                    .filter(employee -> employee.getPhoneNo().matches(regex))
                    .collect(Collectors.toList());
            tableView.getItems().clear();
            tableView.getItems().addAll(filteredEmployees);
            updateNumOfEmployeesLabel(filteredEmployees.size());
        }
    }
}
