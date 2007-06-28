/*
 * ChangeMyVoiceUI.java
 *
 * Created on June 21, 2007, 7:52 AM
 */

package de.dfki.lt.signalproc.demo;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import de.dfki.lt.signalproc.process.FrameOverlapAddSource;
import de.dfki.lt.signalproc.process.InlineDataProcessor;
import de.dfki.lt.signalproc.process.LPCWhisperiser;
import de.dfki.lt.signalproc.process.Robotiser;
import de.dfki.lt.signalproc.process.Chorus;
import de.dfki.lt.signalproc.process.VocalTractScalingProcessor;
import de.dfki.lt.signalproc.process.VocalTractScalingSimpleProcessor;
import de.dfki.lt.signalproc.process.VocalTractModifier;
import de.dfki.lt.signalproc.util.AudioDoubleDataSource;
import de.dfki.lt.signalproc.util.BufferedDoubleDataSource;
import de.dfki.lt.signalproc.util.DDSAudioInputStream;
import de.dfki.lt.signalproc.util.DoubleDataSource;
import de.dfki.lt.signalproc.util.SignalProcUtils;
import de.dfki.lt.signalproc.demo.OnlineAudioEffects;

/**
 *
 * @author  oytun.turk
 */

public class ChangeMyVoiceUI extends javax.swing.JFrame {
    private int voiceIndex;
    private boolean bStarted;
    OnlineAudioEffects online;
    TargetDataLine microphone;
    SourceDataLine loudspeakers;
    VoiceModificationParameters modificationParameters;
    String [] effectNames = { "Robot", 
                              "Whisper", 
                              "Jet Pilot", 
                              "Old Radio", 
                              "Dwarf",
                              "Ogre",
                              "Giant",
                              "Mountain",
                              "Stadium",
                              "Ghost"
                              };

    String [] fsNames = { "8000", 
                          "11025", 
                          "16000", 
                          "22050", 
                          "32000",
                          "44100",
                          "48000"
    };
    
    
    /** Creates new form ChangeMyVoiceUI */
    public ChangeMyVoiceUI() {
        microphone = null;
        loudspeakers = null;
        voiceIndex = -1;
        initComponents();
        modificationParameters = new VoiceModificationParameters();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButtonStart = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jComboBoxVoice = new javax.swing.JComboBox();
        jComboBoxSamplingRate = new javax.swing.JComboBox();
        jLabelVoice = new javax.swing.JLabel();
        jLabelSamplingRate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change My Voice");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jComboBoxVoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVoiceActionPerformed(evt);
            }
        });

        jLabelVoice.setText("Voice");
        jLabelSamplingRate.setText("Sampling Rate");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(layout.createSequentialGroup()
                            .add(jLabelVoice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBoxVoice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabelSamplingRate)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBoxSamplingRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(167, 167, 167)
                            .add(jButtonExit)
                            .add(165, 165, 165)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jButtonStart, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(156, 156, 156))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(32, 32, 32)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelVoice)
                    .add(jComboBoxSamplingRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelSamplingRate)
                    .add(jComboBoxVoice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 42, Short.MAX_VALUE)
                .add(jButtonStart, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(33, 33, 33)
                .add(jButtonExit)
                .add(29, 29, 29))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
    System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jComboBoxVoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVoiceActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVoiceActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        if (!bStarted) { 
            getParameters();
            changeVoice();
            
            jButtonStart.setText("Stop");
        }
        else {
            bStarted = false;
            online.requestStop();
            
            //Close the source and the target datalines to be able to use them repeatedly
            microphone.close();
            microphone = null;
            loudspeakers.close();
            loudspeakers = null;
            //

            jButtonStart.setText("Start");
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

    /* This function gets the modification parameters from the GUI
     * and fills in the modificationParameters object
    */ 
    private void getParameters() {
        modificationParameters.fs = Float.valueOf((String)jComboBoxSamplingRate.getSelectedItem());
        voiceIndex = jComboBoxVoice.getSelectedIndex();
    }
    
    /*This function opens source and target datalines and starts real-time voice modification  
     * using the parameters in the modificationParameters object
     */ 
    private void changeVoice() {
        bStarted = true;
        int channels = 1;
        AudioFormat audioFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED, modificationParameters.fs, 16, channels, 2*channels, modificationParameters.fs,
                false);

        if (microphone != null)
            microphone.close();

        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class,
                    audioFormat);
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(audioFormat);
            System.out.println("Microphone format: " + microphone.getFormat());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (loudspeakers != null)
            loudspeakers.close();
        
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                    audioFormat);
            loudspeakers = (SourceDataLine) AudioSystem.getLine(info);
            loudspeakers.open(audioFormat);
            System.out.println("Loudspeaker format: " + loudspeakers.getFormat());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        // Choose an audio effect
        InlineDataProcessor effect = null;

        if (effectNames[voiceIndex]=="Robot")
        {  
            effect = new Robotiser.PhaseRemover(4096);
        }
        else if (effectNames[voiceIndex]=="Whisper")
        {  
            effect = new LPCWhisperiser(SignalProcUtils.getLPOrder((int)modificationParameters.fs));
        }
        else if (effectNames[voiceIndex]=="Jet Pilot")
        {  
            effect = new Robotiser.PhaseRemover(4096);
        }
        else if (effectNames[voiceIndex]=="Old Radio")
        {  
            effect = new Robotiser.PhaseRemover(4096);
        }
        else if (effectNames[voiceIndex]=="Dwarf")
        {  
            double [] vscales = {1.5};
            
            //effect = new VocalTractScalingSimpleProcessor(1024, vscales);
            
            int p = SignalProcUtils.getLPOrder((int)modificationParameters.fs);
            int fftSize = Math.max(SignalProcUtils.getDFTSize((int)modificationParameters.fs), 1024);
            effect = new VocalTractScalingProcessor(p, (int)modificationParameters.fs, fftSize, vscales);
        }
        else if (effectNames[voiceIndex]=="Ogre")
        { 
            double [] vscales = {0.9};
            
            //effect = new VocalTractScalingSimpleProcessor(1024, vscales);
            
            int p = SignalProcUtils.getLPOrder((int)modificationParameters.fs);
            int fftSize = Math.max(SignalProcUtils.getDFTSize((int)modificationParameters.fs), 1024);
            effect = new VocalTractScalingProcessor(p, (int)modificationParameters.fs, fftSize, vscales);
        }
        else if (effectNames[voiceIndex]=="Giant")
        {  
            double [] vscales = {0.8};
            
            //effect = new VocalTractScalingSimpleProcessor(1024, vscales);
            
            int p = SignalProcUtils.getLPOrder((int)modificationParameters.fs);
            int fftSize = Math.max(SignalProcUtils.getDFTSize((int)modificationParameters.fs), 1024);
            effect = new VocalTractScalingProcessor(p, (int)modificationParameters.fs, fftSize, vscales);
        }
        else if (effectNames[voiceIndex]=="Mountain")
        {  
            double [] vscales = {0.7};
            
            //effect = new VocalTractScalingSimpleProcessor(1024, vscales);
            
            int p = SignalProcUtils.getLPOrder((int)modificationParameters.fs);
            int fftSize = Math.max(SignalProcUtils.getDFTSize((int)modificationParameters.fs), 1024);
            effect = new VocalTractScalingProcessor(p, (int)modificationParameters.fs, fftSize, vscales);
        }
        else if (effectNames[voiceIndex]=="Stadium")
        {
            int [] delaysInMiliseconds = {366, 500};
            double [] amps = {0.54, -0.10};
            effect = new Chorus(delaysInMiliseconds, amps, (int)(modificationParameters.fs));
        }
        else if (effectNames[voiceIndex]=="Ghost")
        {
            int [] delaysInMiliseconds = {100, 200, 300};
            double [] amps = {0.8, 0.7, 0.9};
            effect = new Chorus(delaysInMiliseconds, amps, (int)(modificationParameters.fs));
        }
        //            

        // Create the output thread and make it run in the background:
        if (effect!=null)
        {
            online = new OnlineAudioEffects(effect, microphone, loudspeakers);
            online.start();
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        bStarted = false;
        
        //Fill-in voice combo-box
        for (int i=0; i<effectNames.length; i++) {
            jComboBoxVoice.addItem(effectNames[i]);
        }
        //
        
        //Fill-in sampling rates combo-box and make default sampling rate selection
        for (int i=0; i<fsNames.length; i++) {
            jComboBoxSamplingRate.addItem(fsNames[i]);
        }
        jComboBoxSamplingRate.setSelectedItem("16000");
        //
    }//GEN-LAST:event_formWindowOpened
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeMyVoiceUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JComboBox jComboBoxSamplingRate;
    private javax.swing.JComboBox jComboBoxVoice;
    private javax.swing.JLabel jLabelSamplingRate;
    private javax.swing.JLabel jLabelVoice;
    // End of variables declaration//GEN-END:variables
    
}
