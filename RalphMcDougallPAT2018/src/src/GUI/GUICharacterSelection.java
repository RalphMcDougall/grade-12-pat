/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import src.Player.User;

/**
 * The GUI where the user can select their character, make a new character
 * or delete a character
 * 
 * @author Ralph McDougall 16/3/2018
 */
public class GUICharacterSelection extends javax.swing.JFrame {

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The list of the IDs of all of the players that have been saved
    private ArrayList<Integer> ID_LIST;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise all of the components of the character selection GUI
     */
    public GUICharacterSelection() {
        GameLogger.logInfo("Initialising GUICharacterSelection.");
        initComponents();
        this.setLocationRelativeTo(null);
    }

    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblScreenDescription = new javax.swing.JLabel();
        pnlSelectCharacter = new javax.swing.JPanel();
        lblSelectCharacter = new javax.swing.JLabel();
        scrollpaneSelectCharacter = new javax.swing.JScrollPane();
        listSelectCharacter = new javax.swing.JList<>();
        lblFormatDescription = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        btnLoadCharacter = new javax.swing.JButton();
        btnNewCharacter = new javax.swing.JButton();
        btnDeleteCharacter = new javax.swing.JButton();
        btnCredits = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblBackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("frame"); // NOI18N
        setResizable(false);

        pnlBackground.setBackground(new java.awt.Color(0, 0, 153));
        pnlBackground.setMaximumSize(new java.awt.Dimension(800, 600));
        pnlBackground.setMinimumSize(new java.awt.Dimension(800, 600));
        pnlBackground.setPreferredSize(new java.awt.Dimension(800, 600));
        pnlBackground.setLayout(null);

        pnlTitle.setBackground(new java.awt.Color(204, 204, 204));
        pnlTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        lblTitle.setFont(new java.awt.Font("Arial", 1, 72)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Dungeon Swarm");

        lblScreenDescription.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        lblScreenDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenDescription.setText("Character Selection");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScreenDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblScreenDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBackground.add(pnlTitle);
        pnlTitle.setBounds(30, 10, 730, 160);

        pnlSelectCharacter.setBackground(new java.awt.Color(204, 204, 204));
        pnlSelectCharacter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        lblSelectCharacter.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblSelectCharacter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectCharacter.setText("Select a character:");

        listSelectCharacter.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        listSelectCharacter.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Person 1 : 16/3/2018", "Person 2 : 15/2/2018", "Person 3: 1/1/2018", "Person 4: 31/12/2017", "Person 5: 1/1/2000" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listSelectCharacter.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollpaneSelectCharacter.setViewportView(listSelectCharacter);

        lblFormatDescription.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblFormatDescription.setText("Character name : Last time used");

        javax.swing.GroupLayout pnlSelectCharacterLayout = new javax.swing.GroupLayout(pnlSelectCharacter);
        pnlSelectCharacter.setLayout(pnlSelectCharacterLayout);
        pnlSelectCharacterLayout.setHorizontalGroup(
            pnlSelectCharacterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectCharacterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectCharacterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFormatDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(scrollpaneSelectCharacter)
                    .addComponent(lblSelectCharacter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSelectCharacterLayout.setVerticalGroup(
            pnlSelectCharacterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectCharacterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelectCharacter)
                .addGap(2, 2, 2)
                .addComponent(lblFormatDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpaneSelectCharacter, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262))
        );

        pnlBackground.add(pnlSelectCharacter);
        pnlSelectCharacter.setBounds(20, 190, 400, 380);

        pnlButtons.setBackground(new java.awt.Color(204, 204, 204));
        pnlButtons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        btnLoadCharacter.setBackground(new java.awt.Color(255, 255, 255));
        btnLoadCharacter.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnLoadCharacter.setText("Load Character");
        btnLoadCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadCharacterActionPerformed(evt);
            }
        });

        btnNewCharacter.setBackground(new java.awt.Color(255, 255, 255));
        btnNewCharacter.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnNewCharacter.setText("New Character");
        btnNewCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCharacterActionPerformed(evt);
            }
        });

        btnDeleteCharacter.setBackground(new java.awt.Color(255, 255, 255));
        btnDeleteCharacter.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnDeleteCharacter.setText("Delete Character");
        btnDeleteCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCharacterActionPerformed(evt);
            }
        });

        btnCredits.setBackground(new java.awt.Color(255, 255, 255));
        btnCredits.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnCredits.setText("Credits");
        btnCredits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditsActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(btnCredits, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteCharacter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(btnNewCharacter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoadCharacter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoadCharacter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewCharacter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteCharacter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCredits)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlBackground.add(pnlButtons);
        pnlButtons.setBounds(430, 210, 360, 320);

        lblBackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/BrickBackground.png"))); // NOI18N
        pnlBackground.add(lblBackgroundImage);
        lblBackgroundImage.setBounds(0, 0, 800, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * "Exit" button was pressed so exit the game
     * 
     * @param evt "Exit" button pressed
     */
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // Exit
        GameHandler.exit();
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * The "New Character" button was pressed so change to the next screen
     * 
     * @param evt "New Character" button was pressed
     */
    private void btnNewCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCharacterActionPerformed
        // Go to the New Character screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.NEW_CHARACTER);
    }//GEN-LAST:event_btnNewCharacterActionPerformed
    
    /**
     * The "Load Character" button was pressed so load the character
     * and change to the next screen
     * 
     * @param evt "Load Character" button was pressed
     */
    private void btnLoadCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadCharacterActionPerformed
        // Get the index of the desired character
        int ind = getSelectedCharacterIndex();
        if (ind == -1)
        {
            // User didn't select a character
            JOptionPane.showMessageDialog(this, "No character was selected.");
            return;
        }
        
        // Attempt to change the current user to that of the chosen character
        try {
            GameHandler.setUser(new User(ID_LIST.get(ind)) );
        } catch (SQLException ex) {
            GameLogger.logError("Unable to load character statistics! " + ex);
            JOptionPane.showMessageDialog(this, "Unable to load player statistics! View logs for details.");
            
            // Don't change the screen
            return;
        }
        
        // Go to the Character Main Menu screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.CHARACTER_MAIN_MENU);
    }//GEN-LAST:event_btnLoadCharacterActionPerformed
    
    /**
     * The "Credits" button was pressed so go to the next screen
     * 
     * @param evt "Credits" button pressed
     */
    private void btnCreditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditsActionPerformed
        // Go to the Credits screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.CREDITS);
    }//GEN-LAST:event_btnCreditsActionPerformed

    /**
     * Delete the selected character from the database
     * 
     * @param evt "Delete" button pressed
     */
    private void btnDeleteCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCharacterActionPerformed
        // Get the index of the desired character
        int ind = getSelectedCharacterIndex();
        if (ind == -1)
        {
            // No character was selected
            JOptionPane.showMessageDialog(this, "No character was selected.");
            return;
        }
        
        int ans = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this character?", "Delete character", JOptionPane.YES_NO_OPTION);
        
        // Make sure that the user is sure they want to delete this character
        if (ans == JOptionPane.YES_OPTION)
        {
            // Delete the character
            DBBridge db = GameHandler.getPersistentDB();
            
            int userID = ID_LIST.get(ind);
            try {
                // Clear all of their quests first from the active quest table
                // in the database
                String deleteQuests = "DELETE * FROM tblActiveQuests WHERE UserID = " + userID + ";";
                GameLogger.logInfo("Deleting all active quests for user.");
                db.update(deleteQuests);
                GameLogger.logInfo("User quest data deleted.");
                
                // Delete the user from the users table in the database
                String deleteUser = "DELETE * FROM tblUsers WHERE UserID = " + userID + ";";
                GameLogger.logInfo("Deleting user.");
                db.update(deleteUser);
                GameLogger.logInfo("User deleted.");
            } catch (SQLException ex) {
                // Something went wrong :(
                GameLogger.logError("Unable to delete user data! " + ex);
                JOptionPane.showMessageDialog(this, "User data could not be deleted! Check logs for details.");
                // Go back
                return;
            }
            
            // Update the list of characters display
            try
            {
                loadCharacterList();
            }
            catch (SQLException ex)
            {
                GameLogger.logError("Unable to load character list! " + ex);
                JOptionPane.showMessageDialog(this, "Unable to reload character list! See log for details.");
            }
        }
    }//GEN-LAST:event_btnDeleteCharacterActionPerformed

    /**
     * Return the index of the character that the user chose in the list
     * 
     * @return Integer index of the character that was selected
     */
    private int getSelectedCharacterIndex()
    {
        return listSelectCharacter.getSelectedIndex();
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the list of characters that the user can choose from
     * 
     * @throws SQLException SQL could not retrieve the characters from
     *          the database
     */
    public void loadCharacterList() throws SQLException
    {
        // Get the characters from the database
        DBBridge db = GameHandler.getPersistentDB();
        String query = "SELECT UserID, UserName, DAY(LastPlay) + '/' + MONTH(lastPlay) + '/' + YEAR(lastPlay) FROM tblUsers ORDER BY LastPlay DESC;";
        ResultSet rs = db.query(query);
        ArrayList< ArrayList< String > > data = DBBridge.processResultSet(rs);
        
        ID_LIST = new ArrayList<Integer>();
        
        // Get all of the character IDs and add the displays to the array
        // of strings to display in the list
        String[] displayData = new String[data.size()];
        for (int i = 0; i < data.size(); ++i)
        {
            ID_LIST.add(Integer.parseInt(data.get(i).get(0)));
            
            String display = data.get(i).get(1) + " : " + data.get(i).get(2);
            
            displayData[i] = display;
        }
        listSelectCharacter.setListData(displayData);
    }
    
    // *****************************************************
    // GENERATED FIELDS
    // *****************************************************

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCredits;
    private javax.swing.JButton btnDeleteCharacter;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLoadCharacter;
    private javax.swing.JButton btnNewCharacter;
    private javax.swing.JLabel lblBackgroundImage;
    private javax.swing.JLabel lblFormatDescription;
    private javax.swing.JLabel lblScreenDescription;
    private javax.swing.JLabel lblSelectCharacter;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList<String> listSelectCharacter;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlSelectCharacter;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JScrollPane scrollpaneSelectCharacter;
    // End of variables declaration//GEN-END:variables
}
