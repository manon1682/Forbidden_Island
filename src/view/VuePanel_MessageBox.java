/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author blanquan
 */
public class VuePanel_MessageBox extends JPanel {
    
//    private final JEditorPane html ;    
//    private final JScrollPane scrollPane;
    String texte ;


    public VuePanel_MessageBox() {
        this.setBackground(Color.GREEN);
        
        JPanel caseMsgTemporaire = new JPanel();    
        JLabel msgboxTempo = new JLabel("niveau eaud");
        caseMsgTemporaire.add(msgboxTempo);

        
        /*
        à contiuer mercredi > antoine
       
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);
        
        html = new JEditorPane();
        html.setContentType("text/html");
        scrollPane = new JScrollPane(html);
        this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        
        html.setMinimumSize(new Dimension(180, 280));
        html.setPreferredSize(new Dimension(180, 280));
        scrollPane.setPreferredSize(new Dimension(180, 280));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMinimumSize(new Dimension(180, 280));
        
        html.setText("<html><h1 style=\"text-align:center; color:blue;\">Bienvenue dans<br>l'Île Interdite</h1></html>");
        mainPanel.add(scrollPane, BorderLayout.CENTER) ;
        
        this.texte = "" ;
        */
        this.add(caseMsgTemporaire);
    }
        
    
}
