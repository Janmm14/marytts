/**
 * Copyright 2007 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * Permission is hereby granted, free of charge, to use and distribute
 * this software and its documentation without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of this work, and to
 * permit persons to whom this work is furnished to do so, subject to
 * the following conditions:
 * 
 * 1. The code must retain the above copyright notice, this list of
 *    conditions and the following disclaimer.
 * 2. Any modifications must be clearly marked as such.
 * 3. Original authors' names are not deleted.
 * 4. The authors' names are not used to endorse or promote products
 *    derived from this software without specific prior written
 *    permission.
 *
 * DFKI GMBH AND THE CONTRIBUTORS TO THIS WORK DISCLAIM ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL DFKI GMBH NOR THE
 * CONTRIBUTORS BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS
 * ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */
package de.dfki.lt.mary.recsessionmgr.gui;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 *
 * @author  Mat Wilson <matwils@gmail.com>
 */
public class SpeakerWindow extends javax.swing.JFrame {
    
    private String promptText;
    private boolean showPromptCount;
    private Font defaultPromptFont;
    
    /** Creates new form SpeakerWindow */
    public SpeakerWindow() {
        
        initComponents();  // Auto-generated in NetBeans
        
        defaultPromptFont = jTextPane_PromptDisplay.getFont();
        
        // Set icon image in upper left corner to the 16 x 16 pixel image
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(IconSet.LOGO_16x16_URL));         
        
    }
    
    public void setShowPromptCount(boolean flag) {
        showPromptCount = flag;
        showOrHidePromptCount();
    }
   
    /** Updates the prompt display with the current prompt text
     *  @param promptText The current prompt text for the speaker to read
     */
    public void updatePromptDisplay(String text) {
        this.promptText = text;  // Store for later use during resize events
        jTextPane_PromptDisplay.setFont(defaultPromptFont);
        if (this.isVisible())
            LookAndFeel.centerPromptText(this.jTextPane_PromptDisplay, text);
    }

    /** Updates instructions in Speaker window
     *  @param text The instructions to the speaker for the current recording session
     */
    public void updateInstructions(String text) {
        jEditorPane_Instructions.setText(text);
    }
    
    /** Updates status icon in Speaker window
     *  @param statusIcon The icon to use (play, record, stop)
     */
    public void updateSessionStatus(ImageIcon statusIcon) {
        jLabel_SessionStatus.setText("");
        jLabel_SessionStatus.setIcon(statusIcon);
    }
    
    public void showOrHidePromptCount() {
        
        // Display accordingly
        this.jProgressBar_SpeakerProgress.setVisible(showPromptCount);
        this.jLabel_PromptCount.setVisible(showPromptCount);
        this.jLabel_PromptTotal.setVisible(showPromptCount);        
                
    }   
    
    public void updatePromptCount(int promptCount) {
                
        this.showOrHidePromptCount();
        
        // Update the count
        String countString = String.valueOf(promptCount);
        jLabel_PromptCount.setText(countString);
        
    }
    
    public void updatePromptTotal(int promptTotal) {
        String totalString = "/ " + String.valueOf(promptTotal);
        jLabel_PromptTotal.setText(totalString);
    }
    
    public void setupProgressBar(int promptTotal) {
        this.jProgressBar_SpeakerProgress.setMaximum(promptTotal);
        this.updatePromptTotal(promptTotal);
    }
        
    public void updateProgressBar(int promptCount) {
        this.jProgressBar_SpeakerProgress.setValue(promptCount);       
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel_SpeakerWindow = new javax.swing.JPanel();
        jTextPane_PromptDisplay = new javax.swing.JTextPane();
        jScrollPane_Instructions = new javax.swing.JScrollPane();
        jEditorPane_Instructions = new javax.swing.JEditorPane();
        jLabel_SessionStatus = new javax.swing.JLabel();
        jProgressBar_SpeakerProgress = new javax.swing.JProgressBar();
        jLabel_PromptCount = new javax.swing.JLabel();
        jLabel_PromptTotal = new javax.swing.JLabel();

        setTitle("Redstart - Speaker Window");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jTextPane_PromptDisplay.setEditable(false);
        jTextPane_PromptDisplay.setFont(new java.awt.Font("Tahoma", 1, 36));
        jTextPane_PromptDisplay.setAutoscrolls(false);

        jScrollPane_Instructions.setBorder(null);
        jEditorPane_Instructions.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        jEditorPane_Instructions.setBorder(null);
        jEditorPane_Instructions.setEditable(false);
        jEditorPane_Instructions.setFont(new java.awt.Font("Tahoma", 0, 18));
        jScrollPane_Instructions.setViewportView(jEditorPane_Instructions);

        jLabel_SessionStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/dfki/lt/mary/recsessionmgr/gui/icons/stopped_64x64.png")));

        jProgressBar_SpeakerProgress.setFocusable(false);

        jLabel_PromptCount.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel_PromptCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PromptCount.setText("1999");
        jLabel_PromptCount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel_PromptCount.setPreferredSize(new java.awt.Dimension(64, 64));

        jLabel_PromptTotal.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel_PromptTotal.setForeground(java.awt.Color.gray);
        jLabel_PromptTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PromptTotal.setText("/ 2012");
        jLabel_PromptTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel_PromptTotal.setPreferredSize(new java.awt.Dimension(64, 64));

        org.jdesktop.layout.GroupLayout jPanel_SpeakerWindowLayout = new org.jdesktop.layout.GroupLayout(jPanel_SpeakerWindow);
        jPanel_SpeakerWindow.setLayout(jPanel_SpeakerWindowLayout);
        jPanel_SpeakerWindowLayout.setHorizontalGroup(
            jPanel_SpeakerWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTextPane_PromptDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel_SpeakerWindowLayout.createSequentialGroup()
                .add(jPanel_SpeakerWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel_SpeakerWindowLayout.createSequentialGroup()
                        .add(jProgressBar_SpeakerProgress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 622, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 68, Short.MAX_VALUE)
                        .add(jLabel_PromptCount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel_PromptTotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane_Instructions, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE))
                .add(21, 21, 21)
                .add(jLabel_SessionStatus))
        );
        jPanel_SpeakerWindowLayout.setVerticalGroup(
            jPanel_SpeakerWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel_SpeakerWindowLayout.createSequentialGroup()
                .add(jTextPane_PromptDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 286, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel_SpeakerWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel_SessionStatus)
                    .add(jScrollPane_Instructions, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 181, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel_SpeakerWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel_SpeakerWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel_PromptCount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel_PromptTotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jProgressBar_SpeakerProgress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(28, 28, 28))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel_SpeakerWindow, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel_SpeakerWindow, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        this.updatePromptDisplay(this.promptText);
    }//GEN-LAST:event_formComponentResized

    public void setVisible(boolean visible) 
    {
        super.setVisible(visible);
        if (visible) {
            jTextPane_PromptDisplay.setFont(defaultPromptFont);
            LookAndFeel.centerPromptText(this.jTextPane_PromptDisplay, promptText);
        }
    }

    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // Call method to deselect Speaker Window in Window menu of Admin Window
        // How do I reference the instance of AdminWindow that created the Speaker Window object?
        // AdminWindow.deselectSpeakerWindow();
        // Or do we need to do this through a listener in AdminWindow?
    }//GEN-LAST:event_formWindowClosing
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpeakerWindow().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane_Instructions;
    private javax.swing.JLabel jLabel_PromptCount;
    private javax.swing.JLabel jLabel_PromptTotal;
    private javax.swing.JLabel jLabel_SessionStatus;
    private javax.swing.JPanel jPanel_SpeakerWindow;
    private javax.swing.JProgressBar jProgressBar_SpeakerProgress;
    private javax.swing.JScrollPane jScrollPane_Instructions;
    private javax.swing.JTextPane jTextPane_PromptDisplay;
    // End of variables declaration//GEN-END:variables
    
}
