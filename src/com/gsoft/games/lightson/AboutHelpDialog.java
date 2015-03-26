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


public class AboutHelpDialog extends JDialog implements ActionListener {

  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JPanel insetsPanel2 = new JPanel();
  JPanel insetsPanel3 = new JPanel();
  JButton button1 = new JButton();
  JLabel imageLabel = new JLabel();
  JLabel label1 = new JLabel();
  JLabel label2 = new JLabel();
  JLabel label3 = new JLabel();
  JLabel label4 = new JLabel();
  ImageIcon image1 = new ImageIcon();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout();
  private JPanel jPanel1 = new JPanel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private BorderLayout borderLayout3 = new BorderLayout();
  private JPanel jPanel2 = new JPanel();
  private GridLayout gridLayout2 = new GridLayout();
  private JLabel firstImage = new JLabel();
  private JLabel thirdImage = new JLabel();
  private JLabel secondImage = new JLabel();
  private JTextArea txHelp = new JTextArea();
  public AboutHelpDialog(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(com.gsoft.games.lightson.LightsOnUI.class.getResource("logo.gif"));
    imageLabel.setIcon(image1);
    this.setTitle("About");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    insetsPanel1.setLayout(flowLayout1);
    insetsPanel2.setLayout(flowLayout1);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    gridLayout1.setRows(4);
    gridLayout1.setColumns(1);
    label1.setText("Lights On");
    label3.setText("Copyright (c) 2006");
    label4.setText("Vesrsion 1.3.1");
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
    button1.setText("Close");
    button1.addActionListener(this);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    jPanel1.setLayout(borderLayout3);
    jPanel2.setLayout(gridLayout2);
    firstImage.setMaximumSize(new Dimension(65, 55));
    firstImage.setMinimumSize(new Dimension(65, 55));
    firstImage.setHorizontalAlignment(SwingConstants.CENTER);
    firstImage.setIcon(new ImageIcon(com.gsoft.games.lightson.LightsOnUI.class.getResource("newGrid.jpg")));
    firstImage.setIconTextGap(4);
    secondImage.setHorizontalAlignment(SwingConstants.CENTER);
    secondImage.setIcon(new ImageIcon(com.gsoft.games.lightson.LightsOnUI.class.getResource("firstClick.jpg")));
    thirdImage.setHorizontalAlignment(SwingConstants.CENTER);
    thirdImage.setIcon(new ImageIcon(com.gsoft.games.lightson.LightsOnUI.class.getResource("secondClick.jpg")));
    txHelp.setEnabled(false);
    txHelp.setRequestFocusEnabled(true);
    txHelp.setEditable(false);
    txHelp.setText("Lights-On is a game that reqiures the player to turn\n"
                   +"all of the lights (cells) -ON-. This is done by clicking\n"
                   +"a light, the light to it's left and right and the lights\n"
                   +"above and below are swapped.  In the first image, you have\n"
                   +"an intial game board.  Clicking the 'light' colored cell\n"
                   +"[second image] changes the colors around the cell. The last image\n"
                   +"shows how clicking the 2nd cell down changes the colors\n"
                   +"around it. Once all of the cells are -On- you will be victorious!!");
    txHelp.setWrapStyleWord(true);
    gridLayout2.setColumns(0);
    gridLayout2.setHgap(0);
    gridLayout2.setVgap(5);
    label2.setText("Author:  Steve Gee");
    insetsPanel2.add(imageLabel, null);
    panel2.add(insetsPanel2, BorderLayout.WEST);
    this.getContentPane().add(panel1, null);
    insetsPanel3.add(label1, null);
    insetsPanel3.add(label2, null);
    insetsPanel3.add(label3, null);
    insetsPanel3.add(label4, null);
    panel1.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jScrollPane1,  BorderLayout.CENTER);
    jPanel1.add(jPanel2,  BorderLayout.SOUTH);
    jPanel2.add(firstImage, null);
    jPanel2.add(secondImage, null);
    jPanel2.add(thirdImage, null);
    panel2.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.NORTH);
    jScrollPane1.getViewport().add(txHelp, null);
    setResizable(false);
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