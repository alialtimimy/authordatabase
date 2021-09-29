import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import com.k33ptoo.components.KButton;
import java.awt.MouseInfo;
//Deliver it with how to use it (user and technical application) (how to run it).doc file
//The code itself
//how many hours and what was those hours spent on

public class gui extends Main implements MouseListener, ActionListener {
    private static JFrame frame;
    private static JFrame frame2;
    private static JPanel panel2;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static JPanel panel;
    private static ArrayList<String> authorNames;
    private static JLabel name;
    private static ArrayList<JLabel> array;
    private static KButton backButton;
    private static String nameClicked;

    public gui() throws Exception {
        frame = new JFrame("BioWindow"); // create a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        makePanel(); // create the panel
        frame.setVisible(true);
    }

    public void makePanel() throws Exception {
        panel = new JPanel();
        panel.setBackground(new Color(17, 44, 80));
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        panel.setLayout(null);
        authorNames = get();
        array = new ArrayList<>();
        JLabel title = new JLabel("Authors:");
        title.setForeground(new Color(255, 199, 0));
        title.setFont(new Font("Serif", Font.BOLD, 35));
        title.setBounds(350, 15, title.getMaximumSize().width, title.getMaximumSize().height);
        int y = 40;
        for (int i = 0; i < 10; i++) {
            name = new JLabel(authorNames.get(i));
            y = y + 50;
            // jLabelArr[r].addMouseListener(this);
            name.setForeground(new Color(255, 199, 0));
            name.setFont(new Font("Serif", Font.BOLD, 25));
            name.setBounds(340, y, name.getMaximumSize().width, name.getMaximumSize().height);
            array.add(name);
        }
        for (int i = 0; i < 10; i++) {
            array.get(i).addMouseListener(this);
            panel.add(array.get(i));
        }
        panel.add(title);
        frame.add(panel);
    }

    public void makePopUpWindow() throws Exception {
        frame2 = new JFrame(nameClicked);
        frame2.setSize(800, 600);
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        frame2.setLocation((int) mousePosition.getX(), (int) mousePosition.getY() + 10);
        panel2 = new JPanel();
        panel2.setBackground(new Color(17, 44, 80));
        panel2.setBounds(0, 0, WIDTH, HEIGHT);
        panel2.setLayout(null);
        frame2.add(panel2);
        ArrayList<String> bio = getBio(nameClicked).getList();
        JLabel title2 = new JLabel("Bio:");
        title2.setForeground(new Color(255, 199, 0));
        title2.setFont(new Font("Impact", Font.BOLD, 35));
        title2.setBounds(350, 15, title2.getMaximumSize().width, title2.getMaximumSize().height);
        for (int i = 0; i < 7; i++) {
            JLabel bioInfo = new JLabel(bio.get(i));
            bioInfo.setForeground(new Color(255, 199, 0));
            bioInfo.setFont(new Font("Serif", Font.BOLD, 25));
            bioInfo.setBounds(260, 70 + 50 * i, bioInfo.getMaximumSize().width, bioInfo.getMaximumSize().height);
            panel2.add(bioInfo);
        }
        backButton = new KButton();
        backButton.setBounds(10, 10, 50, 30);
        backButton.setFont(new Font("Arial Black", Font.PLAIN, 15));
        backButton.setText("<-");
        backButton.addActionListener(this);
        backButton.setFocusable(true);
        backButton.setkBackGroundColor(new Color(255, 203, 0));
        backButton.setkBorderRadius(0);
        backButton.setkBackGroundColor(new Color(255, 203, 0));
        backButton.setkStartColor(new Color(255, 203, 0));
        backButton.setkEndColor(new Color(255, 203, 0));
        backButton.setkForeGround(Color.BLACK);
        backButton.setkHoverForeGround(new Color(255, 203, 0));
        backButton.setkHoverEndColor(new Color(255, 203, 0));
        backButton.setkHoverColor(new Color(255, 203, 0));
        backButton.setkHoverStartColor(new Color(255, 203, 0));
        backButton.setBorder(BorderFactory.createEtchedBorder());
        panel2.add(backButton);
        panel2.add(title2);
        frame2.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new gui();
        getConnection();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < array.size(); i++) {
            if (e.getSource() == array.get(i)) {
                nameClicked = ((JLabel) e.getSource()).getText();
                try {
                    makePopUpWindow();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame2.setVisible(false);
        }
    }
}