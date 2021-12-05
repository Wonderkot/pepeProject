package ru.meow.pepeproject.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;



@Route
public class MainView  extends VerticalLayout {
    public MainView() {
        VerticalLayout verticalLayout = new VerticalLayout();
        TextField textField = new TextField();
        com.vaadin.flow.component.upload.Upload upload = new Upload();
        Button btn = new Button();

        verticalLayout.add(textField);
        verticalLayout.add(upload);
        add(verticalLayout);
    }
}
