import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * TCSS 445 - Summer 2015
 * 
 * Project Final
 *
 * Author: Casey Peterson
 * (Some code used from Professor Menaka Abraham example)
 */

/**
 * A Graphical User Interface to view the shouts and song lists.
 *
 */
public class ShoutGUI extends JFrame implements ActionListener
{
    
    private static final long serialVersionUID = 1779520078061383929L;
    private String userID = null;
    private boolean loggedIN = false;
    private JButton btnList, btnSearch, btnLogPnl, btnRegPnl, btnFav, btnClear, btnAcctPnl, btnUpdatePnl;
    private JPanel pnlNorthButtons, pnlSouthButtons, pnlContent;
    private ShoutDB db;
    private List<Shout> list;
    private String[] columnNames = {"Song",
                                    "Artist",
                                    "Album",
                                    "Length",
                                    "Genre"};
    
    private Object[][] data;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel pnlSearch;
    private JLabel lblTitle;;
    private JTextField txfTitle;
    private JButton btnTitleSearch;
    
    private JPanel pnlLogin;
    private JLabel[] logLabel = new JLabel[5];
    private JTextField[] logField = new JTextField[5];
    private JButton btnLogin;
    
    private JPanel pnlReg;
    private JLabel[] regLabel = new JLabel[5];
    private JTextField[] regField = new JTextField[5];
    private JButton btnRegister;
    
    private JPanel pnlAcct;
    private JButton btnDelete;
    
    private JPanel pnlUpdate;
    private JLabel[] updateLabel = new JLabel[5];
    private JTextField[] updateField = new JTextField[5];
    private JButton btnUpdate;
    
    
    /**
     * Creates the frame and components and launches the GUI.
     */
    public ShoutGUI() {
        super("[SHOUT!]");
        
        db = new ShoutDB();
        try
        {
            list = db.getShout();
            
            data = new Object[list.size()][columnNames.length];
            for (int i=0; i<list.size(); i++) 
            {
                data[i][0] = list.get(i).getSong();
                data[i][1] = list.get(i).getArtist();
                data[i][2] = list.get(i).getAlbum();
                data[i][3] = list.get(i).getSongLength();
                data[i][4] = list.get(i).getGenre();
            }
            
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        createComponents();
        setVisible(true);
        setSize(700, 700);
    }
    
    /**
     * Creates panels for Song list, search, login, register, and 
     * components to each panel.
     */
    private void createComponents()
    {
        pnlNorthButtons = new JPanel();
        pnlSouthButtons = new JPanel();
        btnList = new JButton("Song List");
        btnList.addActionListener(this);
        
        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);
        
        btnFav = new JButton("Favorites");
        btnFav.addActionListener(this);
        
        btnSearch = new JButton("Shout Search");
        btnSearch.addActionListener(this);
        
        btnLogPnl = new JButton("Login");
        btnLogPnl.addActionListener(this);
        
        btnRegPnl = new JButton("Register");
        btnRegPnl.addActionListener(this);
        
        btnAcctPnl = new JButton(userID + " Account");
        btnAcctPnl.addActionListener(this);
        btnAcctPnl.setVisible(false);
        
        btnUpdatePnl = new JButton("Update Account");
        btnUpdatePnl.addActionListener(this);        
        
        pnlNorthButtons.add(btnList);
        pnlNorthButtons.add(btnSearch);
        pnlNorthButtons.add(btnFav);
        pnlNorthButtons.add(btnLogPnl);
        pnlNorthButtons.add(btnAcctPnl);
        pnlSouthButtons.add(btnClear); 
        add(pnlNorthButtons, BorderLayout.NORTH);
        add(pnlSouthButtons, BorderLayout.SOUTH);
        
        
        //List Panel
        pnlContent = new JPanel();
        table = new JTable(data, columnNames);
        table.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(table);
        pnlContent.add(scrollPane);
        
        
        //Search Panel
        pnlSearch = new JPanel();
        lblTitle = new JLabel("Enter Search Word: ");
        txfTitle = new JTextField(20);
        btnTitleSearch = new JButton("Search");
        btnTitleSearch.addActionListener(this);
        pnlSearch.add(lblTitle);
        pnlSearch.add(txfTitle);
        pnlSearch.add(btnTitleSearch);
        
        
        //Login Panel
        pnlLogin = new JPanel();
        pnlLogin.setLayout(new GridLayout(6, 0));
        String labelNames[] = {"Enter userID: ", "Enter Password: "};
        for (int i=0; i<labelNames.length; i++) 
        {
            JPanel panel = new JPanel();
            logLabel[i] = new JLabel(labelNames[i]);
            logField[i] = new JTextField(10);
            panel.add(logLabel[i]);
            panel.add(logField[i]);
            pnlLogin.add(panel);
        }
        JPanel panel = new JPanel();
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        panel.add(btnLogin);
        panel.add(btnRegPnl);
        pnlLogin.add(panel);
        
        
        // Register Panel        
        pnlReg = new JPanel();
        pnlReg.setLayout(new GridLayout(6, 0));
        String regNames[] = {"Enter userID: ", "Enter First Name: ", "Enter Last Name: ", "Enter Password: "};
        for (int i=0; i<regNames.length; i++) 
        {
            JPanel panel2 = new JPanel();
            regLabel[i] = new JLabel(regNames[i]);
            regField[i] = new JTextField(10);
            panel2.add(regLabel[i]);
            panel2.add(regField[i]);
            pnlReg.add(panel2);
        }
        JPanel panel2 = new JPanel();
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this);
        panel2.add(btnRegister);
        pnlReg.add(panel2);
        
        
        // Account Panel
        pnlAcct = new JPanel();
        btnDelete = new JButton("Delete Account");
        btnDelete.addActionListener(this);
        pnlAcct.add(btnDelete);
        pnlAcct.add(btnUpdatePnl);
        
        
        // Update Panel
        pnlUpdate = new JPanel();
        pnlUpdate.setLayout(new GridLayout(6, 0));
        String updateNames[] = {"userID: ", "Update First Name: ", "Update Last Name: ", "Update Password: "};
        for (int i=0; i<updateNames.length; i++) 
        {
            JPanel panel3 = new JPanel();
            updateLabel[i] = new JLabel(updateNames[i]);
            updateField[i] = new JTextField(10);
            panel3.add(updateLabel[i]);
            panel3.add(updateField[i]);
            pnlUpdate.add(panel3);
        }
        JPanel panel3 = new JPanel();
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        panel3.add(btnUpdate);
        pnlUpdate.add(panel3);
        
        
        add(pnlContent, BorderLayout.CENTER);         
    }
    

    /**
     * Main generate Shout GUI
     * @param theArgs
     */
    public static void main(String[] theArgs)
    {
        ShoutGUI shoutGUI = new ShoutGUI();
        shoutGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    /**
     * Event handling to change the panels when different tabs are clicked,
     * login, search, register buttons are clicked on the corresponding panels.
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Song List Button
        if (e.getSource() == btnList) 
        {
            try 
            {
                list = db.getShout();
            } catch (SQLException e1) 
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            data = new Object[list.size()][columnNames.length];
            for (int i=0; i<list.size(); i++) 
            {
                data[i][0] = list.get(i).getSong();
                data[i][1] = list.get(i).getArtist();
                data[i][2] = list.get(i).getAlbum();
                data[i][3] = list.get(i).getSongLength();
                data[i][4] = list.get(i).getGenre();
            }
            loadList();
            
        } 
        // Favorite Button
        else if (e.getSource() == btnFav) 
        {
            
            if (!loggedIN) JOptionPane.showMessageDialog(null, "Please login for Favorites.");
            else
            {
                clear();
                try
                {
                    list = db.getFavorites(userID);
                }
                catch (SQLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                data = new Object[list.size()][columnNames.length];
                for (int i=0; i<list.size(); i++) 
                {
                    data[i][0] = list.get(i).getSong();
                    data[i][1] = list.get(i).getArtist();
                    data[i][2] = list.get(i).getAlbum();
                    data[i][3] = list.get(i).getSongLength();
                    data[i][4] = list.get(i).getGenre();
                }
                loadList();
                try
                {
                    callShout();
                }
                catch (SQLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
          
        // Search Panel Button
        else if (e.getSource() == btnSearch) 
        {
              reloadPnl(pnlSearch);
              pnlSouthButtons.setVisible(false);
              setTitle("[SHOUT!] Search");
        } 
        // Login Panel Button
        else if (e.getSource() == btnLogPnl) 
        {
            if (loggedIN && btnLogPnl.getText().equals("Logout"))
            {
                myLogout();
            }
            else
            {
                reloadPnl(pnlLogin);
                setTitle("[SHOUT!] Login");
            }
        } 
        
        // Register Panel Button
        else if (e.getSource() == btnRegPnl) 
        {
            reloadPnl(pnlReg);            
            setTitle("[SHOUT!] Register");
        } 
        // Account Panel Button
        else if (e.getSource() == btnAcctPnl)
        {
            reloadPnl(pnlAcct);
            setTitle("[SHOUT!] Account Management");
        }
        // Update Panel Button
        else if (e.getSource() == btnUpdatePnl)
        {
            reloadPnl(pnlUpdate);
            updateField[0].setText(userID);
            updateField[0].setEditable(false);                
            setTitle("[SHOUT!] Update Account");
        }
        // Clear Button
        else if (e.getSource() == btnClear) 
        {
            clear();
            
        } 
        // Search Button
        else if (e.getSource() == btnTitleSearch) 
        {
            String title = txfTitle.getText();
            if (title.length() > 0) 
            {
                list = db.getShout(title);
                data = new Object[list.size()][columnNames.length];
                for (int i=0; i<list.size(); i++) 
                {
                    data[i][0] = list.get(i).getSong();
                    data[i][1] = list.get(i).getArtist();
                    data[i][2] = list.get(i).getAlbum();
                    data[i][3] = list.get(i).getSongLength();
                    data[i][4] = list.get(i).getGenre();
                }
                loadList();
            }
        } 
        // Login Button
        else if (e.getSource() == btnLogin) 
        {
            
            try
            {
                if (logField[0].getText().isEmpty() || logField[1].getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Forms missing information.");
                    reloadPnl(pnlLogin); 
                    logField[0].setText("");
                    logField[1].setText("");
                }
                else
                {
                    loggedIN = db.login(logField[0].getText(), logField[1].getText());
                    if (loggedIN)
                    {
                        userID = logField[0].getText();
                        btnLogPnl.setText("Logout");
                        btnAcctPnl.setText(userID + " Account");
                        btnAcctPnl.setVisible(true);
                        setTitle("[SHOUT!] Welcome " + userID);
                        logField[0].setText("");
                        logField[1].setText("");
                        loadList();
                    }
                    else 
                    {
                        reloadPnl(pnlLogin); 
                        logField[0].setText("");
                        logField[1].setText("");
                    }
                }
            }
            catch (SQLException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }            
        }
        
        // Register Button
        else if (e.getSource() == btnRegister) 
        {
            if (regField[0].getText().isEmpty() || 
                regField[1].getText().isEmpty() || !isAlpha(regField[1].getText()) ||
                regField[2].getText().isEmpty() || !isAlpha(regField[1].getText()) || 
                regField[3].getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Forms missing or incorrect entry. (Name is alpha only)");
                reloadPnl(pnlReg);
            }
            else
            {
                Shout shout = new Shout(regField[0].getText(), regField[1].getText(),
                                        regField[2].getText(), regField[3].getText());
                db.register(shout);
                JOptionPane.showMessageDialog(null, "Registered Successfully!");
                loadList();
            }                                   
        }    
        
        // Update Button
        else if (e.getSource() == btnUpdate) 
        {
            if (updateField[0].getText().isEmpty() || 
                updateField[1].getText().isEmpty() || !isAlpha(updateField[1].getText()) ||
                updateField[2].getText().isEmpty() || !isAlpha(updateField[1].getText()) || 
                updateField[3].getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Forms missing or incorrect entry. (Name is alpha only)");
                reloadPnl(pnlUpdate);
            }
            else
            {
                Shout shout = new Shout(updateField[0].getText(), updateField[1].getText(),
                                        updateField[2].getText(), updateField[3].getText());
                db.update(shout);
                JOptionPane.showMessageDialog(null, "Updated Successfully!");
                loadList();
            }                                   
        }    
        
        // Delete Button
        else if (e.getSource() == btnDelete)
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int confirmDel = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete " + userID +
                                                            " Account?","Warning",dialogButton);
            if(confirmDel == JOptionPane.YES_OPTION)
            {
                db.delete(userID);
                myLogout();
            }
        }
    }

    /**
     * Call the shout.
     * @throws SQLException 
     * 
     */
    private void callShout() throws SQLException
    {
        List<String> shoutList = new ArrayList<String>();
        shoutList = null;
        for(int i = 0; i < list.size(); i++)
        {
            shoutList = db.shoutOut(list.get(i).getArtist());
        }
        
        for(int i = 0; i < shoutList.size(); i++)
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int confirmDel = JOptionPane.showConfirmDialog (null, "A Favorite Artist Has A [SHOUT!], check it out? ","Warning",dialogButton);
            if(confirmDel == JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(null, shoutList.get(i));
            }
        }
    }

    /**
     * Log the user out of the system.
     */
    private void myLogout()
    {
        userID = null;
        loggedIN = false;
        JOptionPane.showMessageDialog(null, "Successfully Logged Out!");
        btnLogPnl.setText("Login");
        btnAcctPnl.setVisible(false);
        loadList();        
    }

    /**
     * Reload the panel
     * @param thePanel
     */
    private void reloadPnl(JPanel thePanel)
    {
        pnlSouthButtons.setVisible(false);
        pnlContent.removeAll();
        pnlContent.add(thePanel);
        pnlContent.revalidate();
        this.repaint();        
    }

    /**
     * Load the database list
     */
    private void loadList()
    {
        pnlContent.removeAll();
        pnlSouthButtons.setVisible(true);
        table = new JTable(data, columnNames);
        table.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(table);
        pnlContent.add(scrollPane);
        pnlContent.revalidate();
        this.repaint();        
    }

    /**
     * Clear the database list
     */
    private void clear()
    {
        try 
        {
            list = db.getShout();
        } 
        catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[list.size()][columnNames.length];
        for (int i=0; i<list.size(); i++) 
        {
            data[i][0] = "";
            data[i][1] = "";
            data[i][2] = "";
            data[i][3] = "";
            data[i][4] = "";
        }
        loadList();       
    }
    
    /**
     * Check whether the string input is alpha
     * @param theString
     * @return true if alpha
     */
    public boolean isAlpha(String theString) 
    {
        char[] chars = theString.toCharArray();

        for (char c : chars) 
        {
            if(!Character.isLetter(c)) 
            {
                return false;
            }
        }
        return true;
    }
}
