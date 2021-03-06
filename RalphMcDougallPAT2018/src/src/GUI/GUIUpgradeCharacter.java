/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GUI;

import javax.swing.JOptionPane;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import src.Player.User;

/**
 * The GUI where the suer can upgrade their character
 * 
 * @author Ralph McDougall 14/4/2018
 */
public class GUIUpgradeCharacter extends javax.swing.JFrame {

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // How much each upgrade costs
    private int healthUpgradeCost = 0;
    private int damageUpgradeCost = 0;
    private int fireRateUpgradeCost = 0;
    private int speedUpgradeCost = 0;
    private int xp = 0;
    
    // How much each of the stats get upgraded for each upgrade
    private final int healthUpgradeAmount = 10;
    private final int damageUpgradeAmount = 1;
    private final int fireRateUpgradeAmount = 1;
    private final int speedUpgradeAmount = 1;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise the components of the upgrade screen
     */
    public GUIUpgradeCharacter() {
        GameLogger.logInfo("Initialising GUIUpgradeCharacter.");
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
        pnlButtons = new javax.swing.JPanel();
        pnlAvailableXP = new javax.swing.JPanel();
        lblXP = new javax.swing.JLabel();
        lblCurrentXP = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnUpgradeHealth = new javax.swing.JButton();
        btnUpgradeDamage = new javax.swing.JButton();
        btnUpgradeFireRate = new javax.swing.JButton();
        btnUpgradeSpeed = new javax.swing.JButton();
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
        lblScreenDescription.setText("Upgrade Character");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addComponent(lblScreenDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblScreenDescription)
                .addContainerGap())
        );

        pnlBackground.add(pnlTitle);
        pnlTitle.setBounds(30, 10, 730, 180);

        pnlButtons.setBackground(new java.awt.Color(215, 215, 215));
        pnlButtons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        pnlAvailableXP.setBackground(new java.awt.Color(222, 222, 222));

        lblXP.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblXP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblXP.setText("Available XP: ");

        lblCurrentXP.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentXP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentXP.setText("5");

        javax.swing.GroupLayout pnlAvailableXPLayout = new javax.swing.GroupLayout(pnlAvailableXP);
        pnlAvailableXP.setLayout(pnlAvailableXPLayout);
        pnlAvailableXPLayout.setHorizontalGroup(
            pnlAvailableXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableXPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXP, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCurrentXP, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAvailableXPLayout.setVerticalGroup(
            pnlAvailableXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableXPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAvailableXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXP)
                    .addComponent(lblCurrentXP)))
        );

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnUpgradeHealth.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnUpgradeHealth.setText("Upgrade Health (Cost 5)");
        btnUpgradeHealth.setMaximumSize(new java.awt.Dimension(295, 50));
        btnUpgradeHealth.setMinimumSize(new java.awt.Dimension(295, 50));
        btnUpgradeHealth.setPreferredSize(new java.awt.Dimension(295, 50));
        btnUpgradeHealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpgradeHealthActionPerformed(evt);
            }
        });

        btnUpgradeDamage.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnUpgradeDamage.setText("Upgrade Damage (Cost 10)");
        btnUpgradeDamage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpgradeDamageActionPerformed(evt);
            }
        });

        btnUpgradeFireRate.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnUpgradeFireRate.setText("Upgrade Fire Rate (Cost 20)");
        btnUpgradeFireRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpgradeFireRateActionPerformed(evt);
            }
        });

        btnUpgradeSpeed.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnUpgradeSpeed.setText("Upgrade Speed (Cost 5)");
        btnUpgradeSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpgradeSpeedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAvailableXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(btnUpgradeHealth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpgradeDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpgradeFireRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpgradeSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAvailableXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpgradeHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpgradeDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpgradeFireRate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpgradeSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addGap(24, 24, 24))
        );

        pnlBackground.add(pnlButtons);
        pnlButtons.setBounds(150, 200, 500, 360);

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
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The "Back" button was pressed so go back to the previous screen
     * 
     * @param evt "Back" button pressed
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Go back to the Character Main Menu screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.CHARACTER_MAIN_MENU);
    }//GEN-LAST:event_btnBackActionPerformed
    /**
     * Upgrade the user's health stat
     * 
     * @param evt "Upgrade Health" button pressed
     */
    private void btnUpgradeHealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpgradeHealthActionPerformed
        if (xp < healthUpgradeCost)
        {
            // The user does not have enough XP to upgrade their health
            JOptionPane.showMessageDialog(null, "You do not have enough XP to do this.");
            return;
        }
        
        // Update the user's stats to log the purchase
        User user = GameHandler.getUser();
        
        user.setNumTimesHealthUpgraded(user.getNumTimesHealthUpgraded() + 1);
        user.setXp(user.getXp() - healthUpgradeCost);
        user.setHealth(user.getHealth() + healthUpgradeAmount);
        
        xp -= healthUpgradeCost;
        
        // Increase the cost of the next upgrade
        healthUpgradeCost += 10;
        
        updateButtons();
    }//GEN-LAST:event_btnUpgradeHealthActionPerformed
    
    /**
     * Upgrade the user's damage stat
     * 
     * @param evt "Upgrade Damage" button pressed
     */
    
    private void btnUpgradeDamageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpgradeDamageActionPerformed
        if (xp < damageUpgradeCost)
        {
            // The user does not have enough XP to upgrade their health
            JOptionPane.showMessageDialog(null, "You do not have enough XP to do this.");
            return;
        }
        
        // Update the user's stats to reflect the upgrade
        User user = GameHandler.getUser();
        
        user.setNumTimesDamageUpgraded(user.getNumTimesDamageUpgraded() + 1);
        user.setXp(user.getXp() - damageUpgradeCost);
        user.setDamage(user.getDamage() + damageUpgradeAmount);
        
        xp -= damageUpgradeCost;
        
        // Increase the cost for the next upgrade
        damageUpgradeCost += 10;
        
        updateButtons();
    }//GEN-LAST:event_btnUpgradeDamageActionPerformed

    /**
     * Upgrade the user's fire rate stat
     * 
     * @param evt "Upgrade Fire Rate" button pressed
     */
    private void btnUpgradeFireRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpgradeFireRateActionPerformed
        if (xp < fireRateUpgradeCost)
        {
            // The user does not have enough XP to upgrade their health
            JOptionPane.showMessageDialog(null, "You do not have enough XP to do this.");
            return;
        }
        
        // Update the user's stats to reflect the upgrade
        User user = GameHandler.getUser();
        
        user.setNumTimesFireRateUpgraded(user.getNumTimesFireRateUpgraded() + 1);
        user.setXp(user.getXp() - fireRateUpgradeCost);
        user.setFireRate(user.getFireRate() + fireRateUpgradeAmount);
        
        xp -= fireRateUpgradeCost;
        
        // Increase the cost for the next upgrade
        fireRateUpgradeCost += 10;
        
        updateButtons();
    }//GEN-LAST:event_btnUpgradeFireRateActionPerformed

    /**
     * Upgrade the user's speed stat
     * 
     * @param evt "Upgrade Speed" button pressed
     */
    private void btnUpgradeSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpgradeSpeedActionPerformed
        if (xp < speedUpgradeCost)
        {
            // The user does not have enough XP to upgrade their health
            JOptionPane.showMessageDialog(null, "You do not have enough XP to do this.");
            return;
        }
        
        // Update the user's stats to reflect the upgrade
        User user = GameHandler.getUser();
        
        user.setNumTimesSpeedUpgraded(user.getNumTimesSpeedUpgraded() + 1);
        user.setXp(user.getXp() - speedUpgradeCost);
        user.setSpeed(user.getSpeed() + speedUpgradeAmount);
        
        xp -= speedUpgradeCost;
        
        // Increase the cost for the next upgrade
        speedUpgradeCost += 10;
        
        updateButtons();
    }//GEN-LAST:event_btnUpgradeSpeedActionPerformed

    /**
     * Update the text displayed on the buttons
     */
    private void updateButtons()
    {
        lblCurrentXP.setText("" + xp);
        btnUpgradeHealth.setText("Upgrade Health (Cost " + healthUpgradeCost + ")");
        btnUpgradeDamage.setText("Upgrade Damage (Cost " + damageUpgradeCost + ")");
        btnUpgradeFireRate.setText("Upgrade Fire Rate (Cost " + fireRateUpgradeCost + ")");
        btnUpgradeSpeed.setText("Upgrade Speed (Cost " + speedUpgradeCost + ")");
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Use the user statistics to determine what prices to ask
     * for the upgrades.
     */
    public void getUserData()
    {
        User user = GameHandler.getUser();
        healthUpgradeCost = user.getNumTimesHealthUpgraded() * 10 + 10;
        damageUpgradeCost = user.getNumTimesDamageUpgraded() * 10 + 10;
        fireRateUpgradeCost = user.getNumTimesFireRateUpgraded() * 10 + 10;
        speedUpgradeCost = user.getNumTimesSpeedUpgraded() * 10 + 10;
        
        xp = user.getXp();
        
        updateButtons();
    }

    // *****************************************************
    // GENERATED FIELDS
    // *****************************************************
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnUpgradeDamage;
    private javax.swing.JButton btnUpgradeFireRate;
    private javax.swing.JButton btnUpgradeHealth;
    private javax.swing.JButton btnUpgradeSpeed;
    private javax.swing.JLabel lblBackgroundImage;
    private javax.swing.JLabel lblCurrentXP;
    private javax.swing.JLabel lblScreenDescription;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblXP;
    private javax.swing.JPanel pnlAvailableXP;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables
}
