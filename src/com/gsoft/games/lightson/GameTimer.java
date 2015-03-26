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

public class GameTimer extends Thread {
  private int seconds = 0;
  private int minutes = 0;
  private int hours = 0;
  private boolean runTimer = false;
  private Thread timerThread = new Thread();

  private javax.swing.JLabel timerLabel = null;
  public GameTimer(javax.swing.JLabel lbl){timerLabel = lbl;}
  public void run(){ startTimer();}

  private void startTimer(){
    String timerMsg = "";
      while (true) {
        try {
          timerThread.sleep(1000);
          if(runTimer){
            seconds++;
            if (seconds == 60) {
              seconds = 0;
              minutes++;
              timerMsg = "::00";
            }else {
              timerMsg = "::" + checkLen(seconds);
            }
            if (minutes == 60) {
              minutes = 0;
              hours++;
              timerMsg = "00" + timerMsg;
            }else {
              timerMsg = checkLen(minutes) + timerMsg;
            }
            timerLabel.setText(hours + "::" + timerMsg);
          }
        }catch (Exception ex) {
          ex.printStackTrace();
        }
      } //end while
  }

  private String checkLen(int in){
    if (new Integer(in).toString().length() == 1) {
      return "0" + in;
    }else {
      return "" + in;
    }
  }

  public void resetTime(){
    runTimer = false;
    seconds = 0;
    minutes = 0;
    hours = 0;
    timerLabel.setText("0::00::00");
  }
  public void resumeTimer(){
    runTimer = true;
  }

  public void pauseTimer(){
    runTimer = false;
  }


}