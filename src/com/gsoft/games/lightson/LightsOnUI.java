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
import java.util.*;
import java.io.*;

public class LightsOnUI
    extends JFrame {
  private LevelManager levelMgr = new LevelManager();
  private JPanel contentPane;
  private ObjectOutputStream oos;
  private ObjectInputStream ois;
  private Color defaultOnColor = Color.yellow;
  private Color defaultOffColor = Color.blue;
  private int totalMoves = 0;
  private ArrayList gridContainer = new ArrayList();
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jmiExit = new JMenuItem();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuHelpAbout = new JMenuItem();
  private JLabel statusBar = new JLabel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel jpCenter = new JPanel();
  private JPanel jpWest = new JPanel();
  private JButton jbRestart = new JButton();
  private GridLayout gameGridLayout = new GridLayout();
  private changeColorAdapter buttonListener = new changeColorAdapter(this);
  private JButton jbPause = new JButton();
  private JPanel jpSouth = new JPanel();
  private GameTimer gameTimer = null;
  private JButton jbStart = new JButton();
  private boolean resume = false;
  private GridLayout gridLayout1 = new GridLayout();
  private JPanel jpRight = new JPanel();
  private JPanel jlLeft = new JPanel();
  private FlowLayout flowLayout2 = new FlowLayout();
  private JLabel timerLabel = new JLabel();
  private JLabel jlTimer = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private FlowLayout flowLayout3 = new FlowLayout();
  private JLabel jlTotalMoves = new JLabel();
  private FlowLayout buttonFlowLayout = new FlowLayout();
  private JPanel jpOnOff = new JPanel();
  private JPanel jPanel1 = new JPanel();
  private GridLayout gridLayout2 = new GridLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jlWorkingLevel = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JButton jlOffColor = new JButton();
  private JButton jlOnColor = new JButton();
  private JMenu jlLandF = new JMenu();
  private JRadioButtonMenuItem jmiMetal = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jmiSystem = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jmiCrossPlatform = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jmiMotif = new JRadioButtonMenuItem();
  private Properties prop = new Properties();
  private JMenuItem jmiExportGrid = new JMenuItem();
  private int maxLevels = levelMgr.size();
  private int thisLevel = 0;
  private JMenuItem miFullRestart = new JMenuItem();

  //Construct the frame
  public LightsOnUI() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      loadPropertyFile();
      jbInit();
      buildGrid();
      gameTimer = new GameTimer(timerLabel);
      gameTimer.start();
      jbPause.setEnabled(false);
      jbRestart.setEnabled(false);
      jlWorkingLevel.setText(" 1 of " + maxLevels);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(400, 300));
    this.setResizable(false);
    this.setTitle("Lights On");
    statusBar.setText(" ");
    jMenuFile.setText("File");
    jmiExit.setText("Exit");
    jmiExit.addActionListener(new LightsOnUI_jmiExit_ActionAdapter(this));
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new LightsOnUI_jMenuHelpAbout_ActionAdapter(this));
    jbRestart.setActionCommand("RestartGame");
    jbRestart.setText("Reset");
    jbRestart.addActionListener(new LightsOnUI_jbRestart_actionAdapter(this));
    jpWest.setLayout(gridLayout2);
    jpCenter.setLayout(gameGridLayout);
    gameGridLayout.setColumns(5);
    gameGridLayout.setRows(5);
    jbPause.setText(" Pause ");
    jbPause.addActionListener(new LightsOnUI_jbPause_actionAdapter(this));
    jpSouth.setLayout(gridLayout1);
    jbStart.setText("Start");
    jbStart.addActionListener(new LightsOnUI_jbStart_actionAdapter(this));
    jpSouth.setMaximumSize(new Dimension(400, 400));
    jlLeft.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.LEFT);
    timerLabel.setToolTipText("");
    timerLabel.setText("0::00::00");
    jlTimer.setText("Game Time:    ");
    jLabel2.setRequestFocusEnabled(true);
    jLabel2.setText("Total Moves:   ");
    jpRight.setLayout(flowLayout3);
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setHgap(5);
    jlTotalMoves.setText("0");
    jlTotalMoves.setText("0");
    buttonFlowLayout.setAlignment(FlowLayout.RIGHT);

    gridLayout2.setColumns(1);
    gridLayout2.setRows(12);
    jLabel9.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel9.setForeground(Color.blue);
    jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel9.setText(" Level");
    jlWorkingLevel.setFont(new java.awt.Font("Dialog", 3, 11));
    jlWorkingLevel.setForeground(Color.blue);
    jlWorkingLevel.setHorizontalAlignment(SwingConstants.CENTER);
    jlWorkingLevel.setText("");
    jLabel7.setText("");
    jLabel1.setText("");
    jlOnColor.setBackground(defaultOnColor);
    jlOnColor.setHorizontalAlignment(SwingConstants.CENTER);
    jlOnColor.setText("--ON--");
    jlOnColor.addActionListener(new LightsOnUI_jlOnColor_actionAdapter(this));
    jlOffColor.setBackground(defaultOffColor);
    jlOffColor.setToolTipText("");
    jlOffColor.setHorizontalAlignment(SwingConstants.CENTER);
    jlOffColor.setText("-OFF-");
    jlOffColor.addActionListener(new LightsOnUI_jlOffColor_actionAdapter(this));
    jLabel6.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 11));
    jlLandF.setText("Look and Feel");
    jmiMetal.setText("Metal");
    jmiMetal.addActionListener(new LightsOnUI_jmiMetal_actionAdapter(this));
    jmiSystem.setText("System");
    jmiSystem.addActionListener(new LightsOnUI_jmiSystem_actionAdapter(this));
    jmiCrossPlatform.setText("Cross Platform");
    jmiCrossPlatform.addActionListener(new LightsOnUI_jmiCrossPlatform_actionAdapter(this));
    jmiMotif.setText("Motif");
    jmiMotif.addActionListener(new LightsOnUI_jmiMotif_actionAdapter(this));
    jmiExportGrid.setText("Export Grid");
    jmiExportGrid.addActionListener(new LightsOnUI_jmiExportGrid_actionAdapter(this));
    miFullRestart.setText("Restart Game");
    miFullRestart.addActionListener(new LightsOnUI_miFullRestart_actionAdapter(this));
    jlLeft.add(jlTimer, null);
    jlLeft.add(timerLabel, null);
    jMenuFile.add(miFullRestart);
    jMenuFile.add(jmiExportGrid);
    jMenuFile.addSeparator();
    jMenuFile.add(jmiExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jlLandF);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(statusBar, BorderLayout.EAST);
    contentPane.add(jpCenter, BorderLayout.CENTER);
    contentPane.add(jpWest, BorderLayout.WEST);
    jpWest.add(jbStart, null);
    jpWest.add(jbPause, null);
    jpWest.add(jbRestart, null);
    jpWest.add(jpOnOff, null);
    jLabel4.setText("On Color");
    jLabel6.setText("Off Color");

    jpWest.add(jLabel9, null);
    jpWest.add(jlWorkingLevel, null);
    jpWest.add(jLabel7, null);
    jpWest.add(jLabel6, null);
    jpWest.add(jlOffColor, null);
    jpWest.add(jLabel4, null);
    jpWest.add(jlOnColor, null);
    jpWest.add(jLabel1, null);
    contentPane.add(jpSouth, BorderLayout.SOUTH);
    jpSouth.add(jlLeft, null);
    jpSouth.add(jpRight, null);
    jpRight.add(jlTotalMoves, null);
    jpRight.add(jLabel2, null);
    contentPane.add(jPanel1, BorderLayout.NORTH);
    jlLandF.add(jmiMetal);
    jlLandF.add(jmiSystem);
    jlLandF.add(jmiCrossPlatform);
    jlLandF.add(jmiMotif);
    this.setSize(new Dimension(400, 400));
    contentPane.setMinimumSize(new Dimension(400, 400));
  }

  //File | Exit action performed
  public void jmiExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  private void buildGrid() {
    for (int grid = 0; grid < 25; grid++) {
      LightButton x = new LightButton(true, defaultOnColor, defaultOffColor);
      x.setActionCommand("" + grid);
      x.addActionListener(buttonListener);
      jpCenter.add(x, null);
      gridContainer.add(x);
    }
  }

  void changeLights(ActionEvent e) {
    /*
     1 2 3 4 5
     6 7 8 9 0
     1 2 3 4 5
     6 7 8 9 0
     1 2 3 4 5
     */
    totalMoves++;
    jlTotalMoves.setText("" + totalMoves);
    try {
       ( (LightButton) gridContainer.get(Integer.parseInt(e.getActionCommand()))).changeColor();
    } catch (Exception ex) {}
    if (Integer.parseInt(e.getActionCommand()) % 5 != 0) {
      try {
         ( (LightButton) gridContainer.get(Integer.parseInt(e.getActionCommand()) - 1)).changeColor();
      } catch (Exception ex) {}
    }
    if (Integer.parseInt(e.getActionCommand()) != 4 &&
        Integer.parseInt(e.getActionCommand()) != 9 &&
        Integer.parseInt(e.getActionCommand()) != 14 &&
        Integer.parseInt(e.getActionCommand()) != 19) {
      try {
         ( (LightButton) gridContainer.get(Integer.parseInt(e.getActionCommand()) + 1)).changeColor();
      } catch (Exception ex) {}
    }
    try {
       ( (LightButton) gridContainer.get(Integer.parseInt(e.getActionCommand()) - 5)).changeColor();
    } catch (Exception ex) {}
    try {
       ( (LightButton) gridContainer.get(Integer.parseInt(e.getActionCommand()) + 5)).changeColor();
    } catch (Exception ex) {}

    boolean bVictory = false;
    for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
      bVictory = ( (LightButton) itr.next()).isOn();
      if (!bVictory) {
        break;
      }
    }
    if (bVictory) {
      victory();
    }
  }

  void resetGrid(ActionEvent e) {
    gameTimer.resetTime();
    totalMoves = 0;
    jlTotalMoves.setText("0");
    jbPause.setEnabled(false);
    jbStart.setEnabled(true);
    jbPause.setText(" Pause ");
    for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
      ( (LightButton) itr.next()).refershColorSet();
    }
  }

  void pauseGame(ActionEvent e) {
    if (resume) {
      jbPause.setText(" Pause ");
      for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
        ( (LightButton) itr.next()).enableButton();
      }
      gameTimer.resumeTimer();
      resume = false;
    } else {
      gameTimer.pauseTimer();
      jbPause.setText("Resume");
      for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
        ( (LightButton) itr.next()).disableButton();
      }
      resume = true;
    }
  }

  void startGame(ActionEvent e) {
    gameTimer.resetTime();
    resume = false;
    jbPause.setEnabled(true);
    jbStart.setEnabled(false);
    jbRestart.setEnabled(true);
    try {
      String gameGrid = levelMgr.nextLevel(thisLevel);
      int cntr = 0;
      for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
        ( (LightButton) itr.next()).setState(Integer.parseInt(gameGrid.substring(cntr, (cntr + 1))));
        cntr++;
      }
    } catch (Exception ex) {
      for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
        ( (LightButton) itr.next()).enableButton();
      }
    }
    gameTimer.resumeTimer();
  }

  private void victory() {
    gameTimer.pauseTimer();
    VictoryDialog dlg = new VictoryDialog(this, timerLabel.getText(), jlTotalMoves.getText());
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
    resetGrid(null);
    thisLevel++;
    if (thisLevel >= maxLevels) {
      jlWorkingLevel.setText(" Levels Complete ");
      jbRestart.setEnabled(false);
      jbStart.setEnabled(false);
    } else
      jlWorkingLevel.setText(" " + (thisLevel + 1) + " of " + maxLevels);
  }

  void changeOnColor(ActionEvent e) {
    OnOffDialog dlg = new OnOffDialog(this, "-ON-", defaultOnColor);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
    Color newOnColor = dlg.getNewColor();
    if (newOnColor != null) {
      jlOnColor.setBackground(newOnColor);
      for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
        ( (LightButton) itr.next()).changeOnColor(newOnColor);
      }
      writeColorObject("conf/oncolor.dat", newOnColor);
    }
  }

  void changeOffColor(ActionEvent e) {
    OnOffDialog dlg = new OnOffDialog(this, "-OFF-", defaultOffColor);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
    Color newOffColor = dlg.getNewColor();
    if (newOffColor != null) {
      jlOffColor.setBackground(newOffColor);
      for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
        ( (LightButton) itr.next()).changeOffColor(newOffColor);
      }
      writeColorObject("conf/offcolor.obj", newOffColor);
    }
  }

  public void loadPropertyFile() {
    try {
      File configDir = new File("conf");
      if(!configDir.exists())
         configDir.mkdir();

      prop.load(new FileInputStream("conf/lightson.conf"));
      swapLookAndFeel(new ActionEvent(new Object(), -1, prop.getProperty("interface")));
    } catch (Exception ex) {
      prop.setProperty("interface", "System");
    } finally {
      try {
        prop.store(new FileOutputStream("conf/lightson.conf"), "Lights On Configuration");
      } catch (Exception fe) {}
    }

//======== ON COLOR =========
    try {
      defaultOnColor = readColorObject("conf/oncolor.dat");
    } catch (Exception ex) {
      writeColorObject("conf/oncolor.obj", defaultOnColor);
    }

//======== OFF COLOR =========
    try {
      defaultOffColor = readColorObject("conf/offcolor.obj");
    } catch (Exception ex) {
      writeColorObject("conf/offcolor.obj", defaultOffColor);
    }
  }

  private void writeColorObject(String file, Color xcolor) {
    try {
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(xcolor);
      oos.flush();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      try {
        oos.close();
      } catch (Exception ex) {}
    }
  }

  private Color readColorObject(String file) throws Exception {
    try {
      ois = new ObjectInputStream(new FileInputStream(file));
      return (Color) ois.readObject();
    } finally {
      try {
        ois.close();
      } catch (Exception ex) {}
    }
  }

  void swapLookAndFeel(ActionEvent e) {
    String lookandfeel = e.getActionCommand();
    jmiMetal.setSelected(false);
    jmiSystem.setSelected(false);
    jmiCrossPlatform.setSelected(false);
    jmiMotif.setSelected(false);
//    jmiGTK.setSelected(false);
    String lookAndFeelClass = UIManager.getSystemLookAndFeelClassName();
    if (lookandfeel.equals("Metal")) {
      lookAndFeelClass = "javax.swing.plaf.metal.MetalLookAndFeel";
      jmiMetal.setSelected(true);
    } else if (lookandfeel.equals("System")) {
      lookAndFeelClass = UIManager.getSystemLookAndFeelClassName();
      jmiSystem.setSelected(true);
//    } else if (lookandfeel == "GTK") {
//      lookAndFeelClass = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
//      jmiGTK.setSelected(true);
    } else if (lookandfeel.equals("Cross Platform")) {
      lookAndFeelClass = UIManager.getCrossPlatformLookAndFeelClassName();
      jmiCrossPlatform.setSelected(true);
    } else if (lookandfeel.equals("Motif")) {
      lookAndFeelClass = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
      jmiMotif.setSelected(true);
    }

    try {
      prop.setProperty("interface", lookandfeel);
    } catch (Exception ex) {} finally {
      try {
        prop.store(new FileOutputStream("conf/lightson.conf"), "Lights On Configuration");
      } catch (Exception ex) {}
    }

    try {
      UIManager.setLookAndFeel(lookAndFeelClass);
      SwingUtilities.updateComponentTreeUI(this);
    } catch (Exception ecp) {
      ecp.printStackTrace();
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.updateComponentTreeUI(this);
      } catch (Exception ex) {}
    }
  }

  void exportGrid(ActionEvent e) {
    StringBuffer buffer = new StringBuffer();
    for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
      if ( ( (LightButton) itr.next()).isOn())
        buffer.append("0");
      else
        buffer.append("1");
    }
    System.out.println(buffer.toString());

  }

  void restartGame(ActionEvent e) {
    for (Iterator itr = gridContainer.iterator(); itr.hasNext(); ) {
      ( (LightButton) itr.next()).resetButton();
    }
    jbPause.setEnabled(false);
    jbRestart.setEnabled(false);
    jbStart.setEnabled(true);
    jlWorkingLevel.setText(" 1 of " + maxLevels);
    thisLevel = 0;
    gameTimer.pauseTimer();
    timerLabel.setText("0::00::00");
    jlTotalMoves.setText("0");
  }

//************************************************************************
//************************************************************************

    //Help | About action performed
    public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
      AboutHelpDialog dlg = new AboutHelpDialog(this);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.pack();
      dlg.show();
    }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jmiExit_actionPerformed(null);
    }
  }

}

class LightsOnUI_jmiExit_ActionAdapter
    implements ActionListener {
  LightsOnUI adaptee;

  LightsOnUI_jmiExit_ActionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jmiExit_actionPerformed(e);
  }
}

class LightsOnUI_jMenuHelpAbout_ActionAdapter
    implements ActionListener {
  LightsOnUI adaptee;

  LightsOnUI_jMenuHelpAbout_ActionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class changeColorAdapter
    implements java.awt.event.ActionListener {
  LightsOnUI adaptee;

  changeColorAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.changeLights(e);
  }
}

class LightsOnUI_jbRestart_actionAdapter
    implements java.awt.event.ActionListener {
  LightsOnUI adaptee;

  LightsOnUI_jbRestart_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.resetGrid(e);
  }
}

class LightsOnUI_jbPause_actionAdapter
    implements java.awt.event.ActionListener {
  LightsOnUI adaptee;

  LightsOnUI_jbPause_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.pauseGame(e);
  }
}

class LightsOnUI_jbStart_actionAdapter
    implements java.awt.event.ActionListener {
  LightsOnUI adaptee;

  LightsOnUI_jbStart_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.startGame(e);
  }
}

class LightsOnUI_jlOnColor_actionAdapter
    implements java.awt.event.ActionListener {
  LightsOnUI adaptee;

  LightsOnUI_jlOnColor_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.changeOnColor(e);
  }
}

class LightsOnUI_jmiMetal_actionAdapter
    implements java.awt.event.ActionListener {
  private LightsOnUI adaptee;

  LightsOnUI_jmiMetal_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.swapLookAndFeel(e);
  }
}

class LightsOnUI_jmiSystem_actionAdapter
    implements java.awt.event.ActionListener {
  private LightsOnUI adaptee;

  LightsOnUI_jmiSystem_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.swapLookAndFeel(e);
  }
}

class LightsOnUI_jmiCrossPlatform_actionAdapter
    implements java.awt.event.ActionListener {
  private LightsOnUI adaptee;

  LightsOnUI_jmiCrossPlatform_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.swapLookAndFeel(e);
  }
}

class LightsOnUI_jmiMotif_actionAdapter
    implements java.awt.event.ActionListener {
  private LightsOnUI adaptee;

  LightsOnUI_jmiMotif_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.swapLookAndFeel(e);
  }
}

class LightsOnUI_jlOffColor_actionAdapter
    implements java.awt.event.ActionListener {
  private LightsOnUI adaptee;

  LightsOnUI_jlOffColor_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.changeOffColor(e);
  }
}

class LightsOnUI_jmiExportGrid_actionAdapter
    implements java.awt.event.ActionListener {
  private LightsOnUI adaptee;

  LightsOnUI_jmiExportGrid_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.exportGrid(e);
  }
}

class LightsOnUI_miFullRestart_actionAdapter
    implements java.awt.event.ActionListener {
  private LightsOnUI adaptee;

  LightsOnUI_miFullRestart_actionAdapter(LightsOnUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.restartGame(e);
  }
}