package com.gsoft.games.lightson;
/*************************************************************************
     Copyright (C) 2005  Steve Gee
     ioexcept@yahoo.com
     This program is free software; you can redistribute it and/or
     modify it under the terms of the GNU General Public License
     as published by the Free Software Foundation; either version 2
     of the License, or (at your option) any later version.
     This program is distributed in the hope that it will be useful,
     but WITHOUT ANY WARRANTY; without even the implied warranty of
     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     GNU General Public License for more details.
     You should have received a copy of the GNU General Public License
     along with this program; if not, write to the Free Software
     Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*************************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class VictoryDialog extends JDialog implements ActionListener {

  private JPanel panel1 = new JPanel();
  private JPanel panel2 = new JPanel();
  private JPanel insetsPanel1 = new JPanel();
  private JButton button1 = new JButton();
  private ImageIcon image1 = new ImageIcon();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JLabel imageLabel = new JLabel();
  private JLabel jLabel1 = new JLabel();
  private GridLayout gridLayout1 = new GridLayout();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel initialMessage = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jlComplete = new JLabel();
  private FlowLayout flowLayout1 = new FlowLayout();
  public VictoryDialog(Frame parent, String time, String moves) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
      initialMessage.setText(initialMessage.getText() + " " + time + "    ");
      jlComplete.setText(" and " + moves + " moves");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(com.gsoft.games.lightson.LightsOnUI.class.getResource("victory.jpg"));
    this.setTitle("Victory!!!");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(gridLayout1);
    button1.setText("Ok");
    button1.addActionListener(this);
    imageLabel.setIcon(image1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(6);
    jLabel1.setText("");
    jLabel5.setText("");
    jlComplete.setForeground(Color.blue);
    jlComplete.setHorizontalAlignment(SwingConstants.CENTER);
    jlComplete.setText("");
    initialMessage.setForeground(Color.blue);
    initialMessage.setToolTipText("");
    initialMessage.setHorizontalAlignment(SwingConstants.CENTER);
    initialMessage.setText("    You completed the game in");
    jLabel3.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel3.setForeground(Color.blue);
    jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel3.setText("Congratulations!!!!");
    jLabel2.setText("");
    insetsPanel1.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    this.getContentPane().add(panel1, null);
    panel1.add(imageLabel,  BorderLayout.WEST);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.CENTER);
    panel2.add(jLabel1, null);
    panel2.add(jLabel2, null);
    panel2.add(jLabel3, null);
    panel2.add(initialMessage, null);
    panel2.add(jlComplete, null);
    panel2.add(jLabel5, null);
    setResizable(true);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }
  //Close the dialog
  void cancel() {
    dispose();
  }
  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}
