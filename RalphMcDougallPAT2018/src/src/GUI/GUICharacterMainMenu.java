/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GUI;

import src.Quest.QuestHandler;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import src.Player.User;

/**
 * The GUI where the user can choose to start a new run, view active quests,
 * see help and upgrade their character
 * 
 * @author Ralph McDougall 16/3/2018
 */
public class GUICharacterMainMenu extends javax.swing.JFrame {
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise the components of the character main menu GUI
     */
    public GUICharacterMainMenu() {
        GameLogger.logInfo("Initialising GUICharacterMainMenu.");
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
        btnNewRun = new javax.swing.JButton();
        btnActiveQuests = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnUpgradeCharacter = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        pnlStats = new javax.swing.JPanel();
        pnlName = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        pnlAvailableXP = new javax.swing.JPanel();
        lblXP = new javax.swing.JLabel();
        lblCurrentXP = new javax.swing.JLabel();
        pnlHealth = new javax.swing.JPanel();
        lblHealth = new javax.swing.JLabel();
        lblCurrentHealth = new javax.swing.JLabel();
        pnlDamage = new javax.swing.JPanel();
        lblDamage = new javax.swing.JLabel();
        lblCurrentDamage = new javax.swing.JLabel();
        pnlFireRate = new javax.swing.JPanel();
        lblFireRate = new javax.swing.JLabel();
        lblCurrentFireRate = new javax.swing.JLabel();
        pnlSpeed = new javax.swing.JPanel();
        lblSpeed = new javax.swing.JLabel();
        lblCurrentSpeed = new javax.swing.JLabel();
        pnlNumDeaths = new javax.swing.JPanel();
        lblNumDeaths = new javax.swing.JLabel();
        lblCurrentNumDeaths = new javax.swing.JLabel();
        pnlNumberOfKills = new javax.swing.JPanel();
        lblNumKills = new javax.swing.JLabel();
        lblCurrentNumKills = new javax.swing.JLabel();
        pnlQuestsCompleted = new javax.swing.JPanel();
        lblQuestsCompleted = new javax.swing.JLabel();
        lblCurrentQuestsCompleted = new javax.swing.JLabel();
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
        pnlBackground.setLayout(null);

        pnlTitle.setBackground(new java.awt.Color(215, 215, 215));
        pnlTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        lblTitle.setFont(new java.awt.Font("Arial", 1, 72)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Dungeon Swarm");

        lblScreenDescription.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        lblScreenDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenDescription.setText("Main Menu");

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
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblScreenDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBackground.add(pnlTitle);
        pnlTitle.setBounds(30, 10, 740, 150);

        pnlButtons.setBackground(new java.awt.Color(215, 215, 215));
        pnlButtons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        btnNewRun.setBackground(new java.awt.Color(255, 255, 255));
        btnNewRun.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnNewRun.setText("New Run");
        btnNewRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRunActionPerformed(evt);
            }
        });

        btnActiveQuests.setBackground(new java.awt.Color(255, 255, 255));
        btnActiveQuests.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnActiveQuests.setText("View Active Quests");
        btnActiveQuests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveQuestsActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnUpgradeCharacter.setBackground(new java.awt.Color(255, 255, 255));
        btnUpgradeCharacter.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnUpgradeCharacter.setText("Upgrade Character");
        btnUpgradeCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpgradeCharacterActionPerformed(evt);
            }
        });

        btnHelp.setBackground(new java.awt.Color(255, 255, 255));
        btnHelp.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnHelp.setText("Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHelp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActiveQuests, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpgradeCharacter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewRun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNewRun)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpgradeCharacter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActiveQuests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHelp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlBackground.add(pnlButtons);
        pnlButtons.setBounds(420, 220, 370, 320);

        pnlStats.setBackground(new java.awt.Color(215, 215, 215));
        pnlStats.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        pnlName.setBackground(new java.awt.Color(222, 222, 222));

        lblName.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setText("Ralph");

        javax.swing.GroupLayout pnlNameLayout = new javax.swing.GroupLayout(pnlName);
        pnlName.setLayout(pnlNameLayout);
        pnlNameLayout.setHorizontalGroup(
            pnlNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNameLayout.setVerticalGroup(
            pnlNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAvailableXP.setBackground(new java.awt.Color(222, 222, 222));

        lblXP.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblXP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblXP.setText("XP: ");

        lblCurrentXP.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentXP.setText("57");

        javax.swing.GroupLayout pnlAvailableXPLayout = new javax.swing.GroupLayout(pnlAvailableXP);
        pnlAvailableXP.setLayout(pnlAvailableXPLayout);
        pnlAvailableXPLayout.setHorizontalGroup(
            pnlAvailableXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableXPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        pnlAvailableXPLayout.setVerticalGroup(
            pnlAvailableXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableXPLayout.createSequentialGroup()
                .addGroup(pnlAvailableXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        pnlHealth.setBackground(new java.awt.Color(222, 222, 222));

        lblHealth.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblHealth.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblHealth.setText("Health: ");

        lblCurrentHealth.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentHealth.setText("100");

        javax.swing.GroupLayout pnlHealthLayout = new javax.swing.GroupLayout(pnlHealth);
        pnlHealth.setLayout(pnlHealthLayout);
        pnlHealthLayout.setHorizontalGroup(
            pnlHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHealthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentHealth, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        pnlHealthLayout.setVerticalGroup(
            pnlHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHealthLayout.createSequentialGroup()
                .addGroup(pnlHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHealth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentHealth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        pnlDamage.setBackground(new java.awt.Color(222, 222, 222));

        lblDamage.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblDamage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblDamage.setText("Damage: ");

        lblCurrentDamage.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentDamage.setText("5");

        javax.swing.GroupLayout pnlDamageLayout = new javax.swing.GroupLayout(pnlDamage);
        pnlDamage.setLayout(pnlDamageLayout);
        pnlDamageLayout.setHorizontalGroup(
            pnlDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDamageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        pnlDamageLayout.setVerticalGroup(
            pnlDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCurrentDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlFireRate.setBackground(new java.awt.Color(222, 222, 222));

        lblFireRate.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblFireRate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblFireRate.setText("Fire Rate: ");

        lblCurrentFireRate.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentFireRate.setText("8");

        javax.swing.GroupLayout pnlFireRateLayout = new javax.swing.GroupLayout(pnlFireRate);
        pnlFireRate.setLayout(pnlFireRateLayout);
        pnlFireRateLayout.setHorizontalGroup(
            pnlFireRateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFireRateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFireRate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentFireRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        pnlFireRateLayout.setVerticalGroup(
            pnlFireRateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFireRateLayout.createSequentialGroup()
                .addGroup(pnlFireRateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFireRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentFireRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );

        pnlSpeed.setBackground(new java.awt.Color(222, 222, 222));

        lblSpeed.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblSpeed.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSpeed.setText("Speed: ");

        lblCurrentSpeed.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentSpeed.setText("3");

        javax.swing.GroupLayout pnlSpeedLayout = new javax.swing.GroupLayout(pnlSpeed);
        pnlSpeed.setLayout(pnlSpeedLayout);
        pnlSpeedLayout.setHorizontalGroup(
            pnlSpeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpeedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        pnlSpeedLayout.setVerticalGroup(
            pnlSpeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpeedLayout.createSequentialGroup()
                .addGroup(pnlSpeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pnlNumDeaths.setBackground(new java.awt.Color(222, 222, 222));

        lblNumDeaths.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblNumDeaths.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblNumDeaths.setText("Number of Deaths: ");

        lblCurrentNumDeaths.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentNumDeaths.setText("5");

        javax.swing.GroupLayout pnlNumDeathsLayout = new javax.swing.GroupLayout(pnlNumDeaths);
        pnlNumDeaths.setLayout(pnlNumDeathsLayout);
        pnlNumDeathsLayout.setHorizontalGroup(
            pnlNumDeathsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNumDeathsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNumDeaths, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentNumDeaths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        pnlNumDeathsLayout.setVerticalGroup(
            pnlNumDeathsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNumDeathsLayout.createSequentialGroup()
                .addGroup(pnlNumDeathsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumDeaths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentNumDeaths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        pnlNumberOfKills.setBackground(new java.awt.Color(222, 222, 222));

        lblNumKills.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblNumKills.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblNumKills.setText("Number of Kills: ");

        lblCurrentNumKills.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentNumKills.setText("27");

        javax.swing.GroupLayout pnlNumberOfKillsLayout = new javax.swing.GroupLayout(pnlNumberOfKills);
        pnlNumberOfKills.setLayout(pnlNumberOfKillsLayout);
        pnlNumberOfKillsLayout.setHorizontalGroup(
            pnlNumberOfKillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNumberOfKillsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNumKills, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentNumKills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        pnlNumberOfKillsLayout.setVerticalGroup(
            pnlNumberOfKillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNumberOfKillsLayout.createSequentialGroup()
                .addGroup(pnlNumberOfKillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumKills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentNumKills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        pnlQuestsCompleted.setBackground(new java.awt.Color(222, 222, 222));

        lblQuestsCompleted.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblQuestsCompleted.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblQuestsCompleted.setText("Quests Completed: ");

        lblCurrentQuestsCompleted.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCurrentQuestsCompleted.setText("2");

        javax.swing.GroupLayout pnlQuestsCompletedLayout = new javax.swing.GroupLayout(pnlQuestsCompleted);
        pnlQuestsCompleted.setLayout(pnlQuestsCompletedLayout);
        pnlQuestsCompletedLayout.setHorizontalGroup(
            pnlQuestsCompletedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestsCompletedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuestsCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentQuestsCompleted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        pnlQuestsCompletedLayout.setVerticalGroup(
            pnlQuestsCompletedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestsCompletedLayout.createSequentialGroup()
                .addGroup(pnlQuestsCompletedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuestsCompleted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentQuestsCompleted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnlStatsLayout = new javax.swing.GroupLayout(pnlStats);
        pnlStats.setLayout(pnlStatsLayout);
        pnlStatsLayout.setHorizontalGroup(
            pnlStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlFireRate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlDamage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlHealth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlAvailableXP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlQuestsCompleted, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlNumberOfKills, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlNumDeaths, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlStatsLayout.setVerticalGroup(
            pnlStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatsLayout.createSequentialGroup()
                .addComponent(pnlName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNumDeaths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNumberOfKills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQuestsCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAvailableXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDamage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFireRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBackground.add(pnlStats);
        pnlStats.setBounds(10, 200, 400, 370);

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
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * "Upgrade Character" button was pressed so go to the next screen
     * 
     * @param evt "Upgrade Character" button pressed
     */
    private void btnUpgradeCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpgradeCharacterActionPerformed
        // Go to the Upgrade Character screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.UPGRADE_CHARACTER);
    }//GEN-LAST:event_btnUpgradeCharacterActionPerformed

    /**
     * "Active Quests" button was pressed so go to the next screen
     * 
     * @param evt "Active Quests" button pressed
     */
    private void btnActiveQuestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveQuestsActionPerformed
        // Go to the Active Quests screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.ACTIVE_QUESTS);
    }//GEN-LAST:event_btnActiveQuestsActionPerformed

    /**
     * "Help" button was pressed so go to the next screen
     * 
     * @param evt "Help" button pressed 
     */
    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        // Go to the Help screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.HELP);
    }//GEN-LAST:event_btnHelpActionPerformed

    /**
     * "Back" button was pressed so go to the previous screen
     * 
     * @param evt "Back" button pressed
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        
        try {
            // Update the user's statistics before going back
            GameHandler.getUser().updateUserDBData();
        }
        catch(SQLException ex)
        {
            GameLogger.logInfo("User statistics could not be updated! \n" + ex);
            JOptionPane.showMessageDialog(this, "Unable to update user statistics! See logs for details.");
        }
        
        try {
            // Update the user's quests before going back
            QuestHandler.updateDB();
        }
        catch (SQLException ex)
        {
            GameLogger.logInfo("User active quests could not be updated! \n" + ex);
            JOptionPane.showMessageDialog(this, "Unable to update active quests in database! See logs for details.");
        }
        
        // Go back to the Character Selection screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.CHARACTER_SELECTION);
    }//GEN-LAST:event_btnBackActionPerformed
    
    /**
     * "New Run" button was pressed so open the main game window
     * 
     * @param evt "New run" button pressed
     */
    private void btnNewRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRunActionPerformed
        // Go to the game screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.GAME);
    }//GEN-LAST:event_btnNewRunActionPerformed

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Display the user's stats on the labels provided therefore.
     * 
     * Each stat has a label associated with it
     */
    public void displayUserData()
    {
        User user = GameHandler.getUser();
        
        lblName.setText(user.getName());
        lblCurrentNumDeaths.setText("" + user.getNumDeaths());
        lblCurrentNumKills.setText("" + user.getNumKills());
        lblCurrentQuestsCompleted.setText("" + user.getNumQuestsCompleted());
        lblCurrentXP.setText("" + user.getXp());
        lblCurrentHealth.setText("" + user.getHealth());
        lblCurrentDamage.setText("" + user.getDamage());
        lblCurrentFireRate.setText("" + user.getFireRate());
        lblCurrentSpeed.setText("" + user.getSpeed());
    }
    
    // *****************************************************
    // GENERATED FIELDS
    // *****************************************************
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActiveQuests;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnNewRun;
    private javax.swing.JButton btnUpgradeCharacter;
    private javax.swing.JLabel lblBackgroundImage;
    private javax.swing.JLabel lblCurrentDamage;
    private javax.swing.JLabel lblCurrentFireRate;
    private javax.swing.JLabel lblCurrentHealth;
    private javax.swing.JLabel lblCurrentNumDeaths;
    private javax.swing.JLabel lblCurrentNumKills;
    private javax.swing.JLabel lblCurrentQuestsCompleted;
    private javax.swing.JLabel lblCurrentSpeed;
    private javax.swing.JLabel lblCurrentXP;
    private javax.swing.JLabel lblDamage;
    private javax.swing.JLabel lblFireRate;
    private javax.swing.JLabel lblHealth;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumDeaths;
    private javax.swing.JLabel lblNumKills;
    private javax.swing.JLabel lblQuestsCompleted;
    private javax.swing.JLabel lblScreenDescription;
    private javax.swing.JLabel lblSpeed;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblXP;
    private javax.swing.JPanel pnlAvailableXP;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlDamage;
    private javax.swing.JPanel pnlFireRate;
    private javax.swing.JPanel pnlHealth;
    private javax.swing.JPanel pnlName;
    private javax.swing.JPanel pnlNumDeaths;
    private javax.swing.JPanel pnlNumberOfKills;
    private javax.swing.JPanel pnlQuestsCompleted;
    private javax.swing.JPanel pnlSpeed;
    private javax.swing.JPanel pnlStats;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables
}
