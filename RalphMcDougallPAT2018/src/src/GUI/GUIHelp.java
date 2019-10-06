/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GUI;

import src.Main.GameHandler;
import src.Utility.GameLogger;

/**
 * The GUI where the user can review controls and see help
 * 
 * @author Ralph McDougall 14/4/2018
 */
public class GUIHelp extends javax.swing.JFrame {
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise all of the components of the GUI
     */
    public GUIHelp() {
        GameLogger.logInfo("Initialising GUIHelp.");
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
        pnlDisplay = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textareaHelp = new javax.swing.JTextArea();
        lblBackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        pnlBackground.setBackground(new java.awt.Color(0, 0, 153));
        pnlBackground.setMaximumSize(new java.awt.Dimension(800, 600));
        pnlBackground.setMinimumSize(new java.awt.Dimension(800, 600));
        pnlBackground.setPreferredSize(new java.awt.Dimension(800, 600));
        pnlBackground.setLayout(null);

        pnlTitle.setBackground(new java.awt.Color(215, 215, 215));
        pnlTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        lblTitle.setFont(new java.awt.Font("Arial", 1, 72)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Dungeon Swarm");

        lblScreenDescription.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        lblScreenDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenDescription.setText("Help");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblScreenDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        pnlDisplay.setBackground(new java.awt.Color(215, 215, 215));
        pnlDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        textareaHelp.setColumns(20);
        textareaHelp.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        textareaHelp.setLineWrap(true);
        textareaHelp.setRows(5);
        textareaHelp.setText("Use W, A, S, D to move your character.\nUse the arrow keys to change the direction that your character is facing and to attack.\n\nPick up items by pressing E while standing on the yellow item collection tiles.\n\nGet a new quest by pressing E whie standing on the blue NPC tiles.\n\nProgress on to the next level by pressing E while standing on the red level end tiles.\n\nYou can press ESC at any time while playing to exit back to the main menu.");
        textareaHelp.setWrapStyleWord(true);
        textareaHelp.setFocusable(false);
        jScrollPane1.setViewportView(textareaHelp);

        javax.swing.GroupLayout pnlDisplayLayout = new javax.swing.GroupLayout(pnlDisplay);
        pnlDisplay.setLayout(pnlDisplayLayout);
        pnlDisplayLayout.setHorizontalGroup(
            pnlDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDisplayLayout.setVerticalGroup(
            pnlDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlBackground.add(pnlDisplay);
        pnlDisplay.setBounds(30, 190, 730, 390);

        lblBackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/BrickBackground.png"))); // NOI18N
        pnlBackground.add(lblBackgroundImage);
        lblBackgroundImage.setBounds(0, 0, 800, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The "Back" button was pressed
     * 
     * @param evt Button pressed
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Go back to the Character Main Menu screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.CHARACTER_MAIN_MENU);
    }//GEN-LAST:event_btnBackActionPerformed

    // *****************************************************
    // GENERATED FIELDS
    // *****************************************************

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackgroundImage;
    private javax.swing.JLabel lblScreenDescription;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlDisplay;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTextArea textareaHelp;
    // End of variables declaration//GEN-END:variables
}