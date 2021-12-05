package ru.meow.pepeproject.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.meow.pepeproject.service.FileEntityService;
import ru.meow.pepeproject.utils.CheckSumUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Route
public class MainView extends VerticalLayout {
    @Autowired
    FileEntityService fileEntityService;


    public MainView() {
        MultiFileMemoryBuffer fileBuff = new MultiFileMemoryBuffer();

        VerticalLayout verticalLayout = new VerticalLayout();
        TextField textField = new TextField();
        com.vaadin.flow.component.upload.Upload upload = new Upload(fileBuff);
        upload.addSucceededListener(e -> {
            InputStream inputStream = fileBuff.getInputStream(e.getFileName());

            try {

                byte[] bytes = IOUtils.toByteArray(inputStream);
                ByteArrayInputStream stream =  new ByteArrayInputStream(bytes);
                Long sum = CheckSumUtil.getChecksumCRC32(stream, 1024);
                if (fileEntityService.checkEntity(sum) != null) {
                    System.out.println("File already exist");

                    return;
                }
                fileEntityService.createFileEntity(bytes, e.getFileName(), sum);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        verticalLayout.add(textField);
        verticalLayout.add(upload);
        add(verticalLayout);
    }
}
