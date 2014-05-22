import static java.awt.EventQueue.invokeLater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
/*
 *
 * @author simonpedersen
 *
 */

public class CaseManager extends JFrame implements ActionListener
{
    JPanel mainPane = new JPanel();
    ComponentLayout componentLayout = new ComponentLayout(mainPane);
    
    CaseManager()
    {
        super("CASE MANAGER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(mainPane);
        componentLayout.InitComponent();
        InitMenu();
        pack();
    }
    
    public static void main(String args[]) 
    {
        invokeLater(new Runnable()
        {
            public void run()
            {
                new CaseManager().setVisible(true);
            }
        });
    }
    
    void InitMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Tools");
        JMenuItem menuItemAdd = new JMenuItem("Add...");
        JMenuItem menuItemEnlarge = new JMenuItem("Enlarge Text");
        JMenuItem menuItemMinimize = new JMenuItem("Minimize Text");
        JMenuItem menuItemSaveToDB = new JMenuItem("Save to Database");
        
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(menuItemAdd);
        menu.add(menuItemMinimize);
        menu.add(menuItemEnlarge);
        menu.add(menuItemSaveToDB);
        
        menuItemAdd.addActionListener(this);
        menuItemAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItemMinimize.addActionListener(this);
        menuItemMinimize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        menuItemEnlarge.addActionListener(this);
        menuItemEnlarge.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
        menuItemSaveToDB.addActionListener(this);
        menuItemSaveToDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }
    
    public void actionPerformed(ActionEvent event) 
    {   
        if (event.getActionCommand() == "Add...")
            ChangeComponent.AddNew(mainPane);
        else if (event.getActionCommand() == "Enlarge Text")
            ChangeComponent.EnlargeText(mainPane);
        else if (event.getActionCommand() == "Minimize Text")
            ChangeComponent.MinimizeText(mainPane);
        else if (event.getActionCommand() == "Save to Database")
        {
            ComponentLayout.caseDB.SaveSettings();
            ComponentLayout.caseDB.SaveValues();
        }
        
        pack();
    }
}
