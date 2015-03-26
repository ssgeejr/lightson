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

public class OnOffDialog extends JDialog implements ActionListener {
  private JPanel panel1 = new JPanel();
  private JPanel insetsPanel1 = new JPanel();
  private JButton jbSelect = new JButton();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JButton jbCancel = new JButton();
  private JLabel jlMessage = new JLabel();
  private JColorChooser jcc = new JColorChooser();
  private FlowLayout flowLayout1 = new FlowLayout();
  private Color newColor = null;
  public OnOffDialog(Frame parent, String type, Color initColor) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
      jlMessage.setText(jlMessage.getText() + type + " Color");
      jcc.setColor(initColor);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    this.setTitle("Color Chooser");
    panel1.setLayout(borderLayout1);
    jbSelect.setText("Select");
    jbSelect.addActionListener(this);
    jbCancel.setText("Cancel");
    jbCancel.addActionListener(this);
    jlMessage.setFont(new java.awt.Font("Dialog", 1, 11));
    jlMessage.setForeground(Color.blue);
    jlMessage.setHorizontalAlignment(SwingConstants.CENTER);
    jlMessage.setText("Select your ");
    insetsPanel1.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    this.getContentPane().add(panel1, null);
    insetsPanel1.add(jbSelect, null);
    insetsPanel1.add(jbCancel, null);
    panel1.add(jcc, BorderLayout.CENTER);
    panel1.add(jlMessage, BorderLayout.NORTH);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
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

  public void setColor(){
    newColor = jcc.getColor();
    cancel();
  }

  public Color getNewColor(){
    return newColor;
  }

  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jbSelect) {
      setColor();
    }else if(e.getSource() == jbCancel) {
      cancel();
    }
  }
}
