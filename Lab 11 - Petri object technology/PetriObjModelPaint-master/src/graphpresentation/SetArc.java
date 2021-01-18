/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SetTie.java
 *
 * Created on 11.12.2011, 18:44:06
 */
package graphpresentation;

import PetriObj.*;
import graphnet.*;
import utils.Utils;

import javax.swing.*;

/**
 *
 * @author Оля
 */
public class SetArc extends javax.swing.JFrame {

    /**
     * Creates new form SetTie
     */
    public SetArc(PetriNetsPanel panel) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.panel = panel;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//<-- destroy only this frame
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTiePanel = new javax.swing.JPanel();
        quantityLabel = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        quantityTextField = new javax.swing.JTextField();
        isInfRadioButton = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        quantityLabel1 = new javax.swing.JLabel();
        infParamNameField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Link parameters");

        setTiePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Basic parameters"));

        quantityLabel.setText("Number of links");

        jRadioButton1.setText("Use the formula");
        jRadioButton1.setEnabled(false);

        isInfRadioButton.setText("Information link");

        jTextField2.setEnabled(false);

        quantityLabel1.setText("Information link (parameter name)");

        infParamNameField.setName(""); // NOI18N
        infParamNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infParamNameFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout setTiePanelLayout = new javax.swing.GroupLayout(setTiePanel);
        setTiePanel.setLayout(setTiePanelLayout);
        setTiePanelLayout.setHorizontalGroup(
            setTiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setTiePanelLayout.createSequentialGroup()
                .addGroup(setTiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(setTiePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(setTiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(setTiePanelLayout.createSequentialGroup()
                                .addComponent(quantityLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRadioButton1)
                            .addComponent(isInfRadioButton)))
                    .addGroup(setTiePanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(setTiePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(quantityLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(infParamNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setTiePanelLayout.setVerticalGroup(
            setTiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setTiePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(setTiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(isInfRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(setTiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel1)
                    .addComponent(infParamNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(setTiePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setTiePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        choosenTie = null;        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
       try{
        setQuantity();
        setIsInf();
        choosenTie = null;
        this.setVisible(false);
        panel.repaint();
       }
         catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void infParamNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infParamNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infParamNameFieldActionPerformed

    private void setQuantity() { // modified by Katya 08.12.2016
        String quantityValueStr = quantityTextField.getText();
        if (Utils.tryParseInt(quantityValueStr)) {
            choosenTie.setQuantity(Integer.valueOf(quantityValueStr));
            if (choosenTie.getClass() == GraphArcIn.class) {
                GraphArcIn chosenArc = (GraphArcIn)choosenTie;
                ArcIn arcIn = chosenArc.getArcIn();
                arcIn.setKParam(null);
            } else {
                GraphArcOut chosenArc = (GraphArcOut)choosenTie;
                ArcOut arcOut = chosenArc.getArcOut();
                arcOut.setKParam(null);
            }
        } else {
            if (choosenTie.getClass() == GraphArcIn.class) {
                GraphArcIn chosenArc = (GraphArcIn)choosenTie;
                ArcIn arcIn = chosenArc.getArcIn();
                arcIn.setKParam(quantityValueStr);
            } else {
                GraphArcOut chosenArc = (GraphArcOut)choosenTie;
                ArcOut arcOut = chosenArc.getArcOut();
                arcOut.setKParam(quantityValueStr);
            }
        }
    }

    private void getQuantity() { // modified by Katya 08.12.2016
        String quantityStr;
        if (choosenTie.getClass() == GraphArcIn.class) {
            GraphArcIn chosenArc = (GraphArcIn)choosenTie;
            ArcIn arcIn = chosenArc.getArcIn();
            quantityStr = arcIn.kIsParam()
                ? arcIn.getKParamName()
                : Integer.toString(choosenTie.getQuantity());
        } else {
            GraphArcOut chosenArc = (GraphArcOut)choosenTie;
            ArcOut arcOut = chosenArc.getArcOut();
            quantityStr = arcOut.kIsParam()
                ? arcOut.getKParamName()
                : Integer.toString(choosenTie.getQuantity());
        }
        quantityTextField.setText(quantityStr);
    }

    private void setIsInf() { // modified by Katya 08.12.2016
        if (choosenTie.getClass() == GraphArcIn.class) {
            boolean isInfValue = isInfRadioButton.isSelected();
            String isInfParamName = infParamNameField.getText();
            GraphArcIn chosenArc = (GraphArcIn)choosenTie;
            ArcIn arcIn = chosenArc.getArcIn();
            if (isInfParamName != null && !isInfParamName.isEmpty()) {
                arcIn.setInfParam(isInfParamName);
            } else {
                arcIn.setInf(isInfValue);
                arcIn.setInfParam(null);
            }
        } else {
            choosenTie.setInf(false);
        }
    }

    private void getIsInf() { // modified by Katya 08.12.2016
        if (choosenTie.getClass() == GraphArcIn.class) {
            GraphArcIn chosenArc = (GraphArcIn)choosenTie;
            ArcIn arcIn = chosenArc.getArcIn();
            isInfRadioButton.setSelected(choosenTie.getIsInf());
            infParamNameField.setText(arcIn.getInfParamName());
        } else {
            isInfRadioButton.setSelected(false);
            infParamNameField.setText(null);
        }
    }

    private void setChoosenTie(GraphArc t) {
        choosenTie = t;
    }

    public void setInfo(GraphArc t) {
        setChoosenTie(t);
        getQuantity();
        getIsInf();
    }
    private PetriNetsPanel panel;
    private GraphArc choosenTie;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton isInfRadioButton;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JLabel quantityLabel1;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JTextField infParamNameField;
    private javax.swing.JPanel setTiePanel;
    // End of variables declaration//GEN-END:variables
}