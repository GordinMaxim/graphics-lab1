package ru.nsu.gordin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel{

    protected Action aboutAction, exitAction;

    public MainPanel() {
        super(new BorderLayout());

        aboutAction = new AboutAction("About",
                                    createToolIcon("info_icon&16"),
                                    "About lab1",
                                    new Integer(KeyEvent.VK_H));
        exitAction = new ExitAction("Exit",
                                    createToolIcon("on-off_icon&16"),
                                    "Quit the application",
                                    new Integer(KeyEvent.VK_E));
    }

    protected static ImageIcon createToolIcon(String imageName) {
        String imgLocation = "/res/icon/"
                + imageName
                + ".png";
        java.net.URL imageURL = MainPanel.class.getResource(imgLocation);
        System.out.println();
        if (imageURL == null) {
            System.err.println("Resource not found: "
                    + imgLocation);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createHelpMenu());
        return menuBar;
    }

    public JMenu createFileMenu() {
        JMenuItem menuItem = null;
        JMenu fileMenu = new JMenu("File");

        Action[] actions = {exitAction};
        for (int i = 0; i < actions.length; i++) {
            menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null); //arbitrarily chose not to use icon
            fileMenu.add(menuItem);
        }

        return fileMenu;
    }

    public JMenu createHelpMenu() {
        JMenuItem menuItem = null;
        JMenu helpMenu = new JMenu("Help");

        Action[] actions = {aboutAction};
        for (int i = 0; i < actions.length; i++) {
            menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null);
            helpMenu.add(menuItem);
        }

        return helpMenu;
    }

    public void createToolBar() {
        JButton button = null;

        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.PAGE_START);

        button = new JButton(aboutAction);
        if (button.getIcon() != null) {
            button.setText("");
        }
        toolBar.add(button);

        button = new JButton(exitAction);
        if (button.getIcon() != null) {
            button.setText("");
        }
        toolBar.add(button);
    }

    public class AboutAction extends AbstractAction {

        public AboutAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ExitAction extends AbstractAction {

        public ExitAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("lab #1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel demo = new MainPanel();
        frame.setJMenuBar(demo.createMenuBar());
        demo.createToolBar();
        demo.add(new DrawPanel());
        demo.setOpaque(true);
        frame.setContentPane(demo);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
