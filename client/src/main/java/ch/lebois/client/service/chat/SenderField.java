package ch.lebois.client.service.chat;

import ch.lebois.client.handler.ResponseSender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Project: Hermann
 **/
public class SenderField extends JPanel {

    public SenderField() {
        setLayout(new BorderLayout());
        final TextField textField = new TextField("");
        textField.setColumns(30);
        Button button = new Button("Send");
        button.setSize(100, 20);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();

                if (!text.equals("")) {
                    Chat.getInstance().addMessage("me", text);
                    new ResponseSender("message", "client: " + text);
                }
                textField.setText("");
                textField.requestFocus();
            }
        });

        add(textField, BorderLayout.WEST);
        add(button, BorderLayout.EAST);
    }
}
