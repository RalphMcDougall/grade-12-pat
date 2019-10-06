/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GUI;

import src.Quest.Quest;
import src.Quest.QuestHandler;
import java.util.ArrayList;
import src.Main.GameHandler;
import src.Utility.GameLogger;

/**
 * The GUI where the character can view their active quests
 * 
 * @author Ralph McDougall 16/3/2018
 */
public class GUIActiveQuests extends javax.swing.JFrame {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The list of all current quests
    private ArrayList<Quest> quests;
    
    // The list of all strings to display in the JList
    private String[] displayStrings;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise the components of the screen
     */
    public GUIActiveQuests() {
        GameLogger.logInfo("Initialising GUIActiveQuests.");
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
        lblChooseQuest = new javax.swing.JLabel();
        scrollpaneSelectCharacter = new javax.swing.JScrollPane();
        listSelectQuest = new javax.swing.JList<>();
        lblFormatDescription = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        pnlQuest = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaDescription = new javax.swing.JTextArea();
        barProgress = new javax.swing.JProgressBar();
        lblBackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("frame"); // NOI18N
        setResizable(false);

        pnlBackground.setBackground(new java.awt.Color(0, 0, 153));
        pnlBackground.setMaximumSize(new java.awt.Dimension(1200, 900));
        pnlBackground.setMinimumSize(new java.awt.Dimension(1200, 900));
        pnlBackground.setLayout(null);

        pnlTitle.setBackground(new java.awt.Color(215, 215, 215));
        pnlTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        lblTitle.setFont(new java.awt.Font("Arial", 1, 72)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Dungeon Swarm");

        lblScreenDescription.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        lblScreenDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenDescription.setText("Active Quests");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScreenDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblScreenDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBackground.add(pnlTitle);
        pnlTitle.setBounds(40, 10, 670, 170);

        pnlSelectCharacter.setBackground(new java.awt.Color(215, 215, 215));
        pnlSelectCharacter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        lblChooseQuest.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblChooseQuest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChooseQuest.setText("Choose Quest");

        listSelectQuest.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        listSelectQuest.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Kill 10 skeletons : 2/10", "Collect 5 potions: 4/5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listSelectQuest.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listSelectQuest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSelectQuestMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listSelectQuestMouseReleased(evt);
            }
        });
        scrollpaneSelectCharacter.setViewportView(listSelectQuest);

        lblFormatDescription.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblFormatDescription.setText("Quest : Progress");

        btnBack.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectCharacterLayout = new javax.swing.GroupLayout(pnlSelectCharacter);
        pnlSelectCharacter.setLayout(pnlSelectCharacterLayout);
        pnlSelectCharacterLayout.setHorizontalGroup(
            pnlSelectCharacterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectCharacterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectCharacterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFormatDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSelectCharacterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnBack, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollpaneSelectCharacter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                    .addComponent(lblChooseQuest, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSelectCharacterLayout.setVerticalGroup(
            pnlSelectCharacterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectCharacterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChooseQuest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFormatDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpaneSelectCharacter, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );

        pnlBackground.add(pnlSelectCharacter);
        pnlSelectCharacter.setBounds(20, 200, 330, 370);

        pnlQuest.setBackground(new java.awt.Color(215, 215, 215));
        pnlQuest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 5));

        txtareaDescription.setColumns(15);
        txtareaDescription.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtareaDescription.setLineWrap(true);
        txtareaDescription.setRows(5);
        txtareaDescription.setText("The quest description is displayed here so that \nthe user can remember what they need to do.");
        txtareaDescription.setWrapStyleWord(true);
        txtareaDescription.setFocusable(false);
        jScrollPane1.setViewportView(txtareaDescription);

        barProgress.setBackground(new java.awt.Color(255, 0, 0));
        barProgress.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        barProgress.setForeground(new java.awt.Color(0, 255, 0));
        barProgress.setToolTipText("");
        barProgress.setValue(50);
        barProgress.setFocusable(false);
        barProgress.setStringPainted(true);

        javax.swing.GroupLayout pnlQuestLayout = new javax.swing.GroupLayout(pnlQuest);
        pnlQuest.setLayout(pnlQuestLayout);
        pnlQuestLayout.setHorizontalGroup(
            pnlQuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuestLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(barProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlQuestLayout.setVerticalGroup(
            pnlQuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBackground.add(pnlQuest);
        pnlQuest.setBounds(390, 200, 400, 360);

        lblBackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/BrickBackground.png"))); // NOI18N
        pnlBackground.add(lblBackgroundImage);
        lblBackgroundImage.setBounds(0, 0, 800, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The "Back" button got pressed so go back to the previous screen
     * 
     * @param evt The "Back" button was pressed
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Go back to the Character Main Menu screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.CHARACTER_MAIN_MENU);
    }//GEN-LAST:event_btnBackActionPerformed
    
    /**
     * A different quest got chosen to display
     * 
     * @param evt Mouse clicked
     */
    private void listSelectQuestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSelectQuestMouseClicked
        changeQuestDescription();
    }//GEN-LAST:event_listSelectQuestMouseClicked

    /**
     * A different quest got chosen to display
     * 
     * @param evt Mouse released
     */
    private void listSelectQuestMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSelectQuestMouseReleased
        changeQuestDescription();
    }//GEN-LAST:event_listSelectQuestMouseReleased
    
    /**
     * Change the text that gets displayed in the display box
     */
    private void changeQuestDescription()
    {
        // The currently selected quest index
        int ind = listSelectQuest.getSelectedIndex();
        
        // Set the description to the description of the currently 
        // selected quest
        txtareaDescription.setText(quests.get(ind).getQuestText());
        
        // Update the value shown in the progress bar
        barProgress.setValue((int) 100.0 * quests.get(ind).getProgress() / quests.get(ind).getDesiredTargetCount());
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Change the list of quests displayed in the list to the current player's
     * active quests
     */
    public void loadQuests()
    {
        // The active quests
        quests = new ArrayList<>();
        
        // Get the number of quests
        int size = QuestHandler.getNumQuests();
        
        if (size == 0)
        {
            // No quests, so special display
            String[] display = {"No active quests"};
            listSelectQuest.setListData(display);
            listSelectQuest.setSelectedIndex(0);
            
            txtareaDescription.setText("No active quests");
            barProgress.setValue(0);
            return;
        }
        
        // Add all of the currently active quests to the list of displayed quests
        for (int i = 0; i < size; ++i)
        {
            // Don't add quests that have already been completed
            if (!QuestHandler.getQuest(i).completed())
            {
                quests.add(QuestHandler.getQuest(i));
            }
        }
        
        size = quests.size();
        
        displayStrings = new String[size];
        
        // Compose each of the messages to be displayed for the quests
        for (int i = 0; i < size; ++i)
        {
            // Capitalise the first letter
            String str = quests.get(i).getQuestTypeDescription().toUpperCase().charAt(0) + "";
            str += quests.get(i).getQuestTypeDescription().substring(1) + " ";
            str += quests.get(i).getDesiredTargetCount() + " ";
            // Plurals get an "s"
            str += quests.get(i).getTarget() + "s";
            displayStrings[i] = str;
        }
        
        // Set the text displayed in the list to the generated text
        listSelectQuest.setListData(displayStrings);
        
        // By default the first quest is chosen
        listSelectQuest.setSelectedIndex(0);
        changeQuestDescription();
        
    }
    
    // *****************************************************
    // GENERATED FIELDS
    // *****************************************************
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barProgress;
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackgroundImage;
    private javax.swing.JLabel lblChooseQuest;
    private javax.swing.JLabel lblFormatDescription;
    private javax.swing.JLabel lblScreenDescription;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList<String> listSelectQuest;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlQuest;
    private javax.swing.JPanel pnlSelectCharacter;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JScrollPane scrollpaneSelectCharacter;
    private javax.swing.JTextArea txtareaDescription;
    // End of variables declaration//GEN-END:variables
}
