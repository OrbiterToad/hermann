package ch.lebois.client.service.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Project: Hermann
 **/
public class Chat extends JDialog {

    private static Chat instance;
    protected DefaultListModel<String> listModel;
    private JList<String> list;

    private Chat() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createDashedBorder(Color.RED, 3, 2, 2, false));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
//                Console.execute("java -jar Hermann.jar");
            }
        });

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);

        add(list, BorderLayout.NORTH);
        add(new SenderField(), BorderLayout.SOUTH);
        pack();
    }


    /**
     * Lemao SINGELTONNNNN
     *
     * @return
     */
    public static Chat getInstance() {
        if (instance == null) {
            instance = new Chat();
        }
        return instance;
    }

    public void addMessage(String user, String message) {
        listModel.addElement(user + ": " + message);
        setVisible(true);
        pack();
    }
}
