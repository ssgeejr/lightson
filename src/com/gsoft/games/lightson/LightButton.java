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

import javax.swing.JButton;
import java.awt.Color;

public class LightButton extends JButton{

  private Color off = Color.blue;
  private Color on = Color.yellow;
  private boolean buttonOn = false;

  public void enableButton(){
    if(buttonOn){
      this.setBackground(on);
    }else{
      this.setBackground(off);
      buttonOn = false;
    }
  }

  public void disableButton(){
    this.setBackground(Color.darkGray);
    this.setEnabled(false);
  }

  public LightButton(boolean disabled) {
    if(disabled){
      buttonOn = false;
      disableButton();
    }else{
      buttonOn = true;
      refershColorSet();
    }
  }

  public void setState(int state){
    if(state == 0){
      this.setBackground(off);
      buttonOn = false;
    }else{
      this.setBackground(on);
      buttonOn = true;
    }
    this.setEnabled(true);
  }


  public LightButton(boolean disabled, Color _on, Color _off) {
    on = _on;
    off = _off;
    if(disabled){
      disableButton();
    }else{
      refershColorSet();
    }
  }

  public void resetButton(){
    buttonOn = false;
    this.setBackground(Color.darkGray);
    this.setEnabled(false);

  }

  public void refershColorSet(){
    this.setBackground(Color.darkGray);
    this.setEnabled(false);
  }

  public void setColorSet(Color isoff, Color ison){
    off = isoff;
    on = ison;
  }

  public void changeColor(){
    if(buttonOn){
       this.setBackground(off);
       buttonOn = false;
     }else{
       this.setBackground(on);
       buttonOn = true;
     }
  }

  public void changeOffColor(Color newOff){
    off = newOff;
    if(!buttonOn){
      this.setBackground(off);
    }
  }

  public void changeOnColor(Color newOn){
    on = newOn;
    if(buttonOn){
      this.setBackground(on);
    }
  }

  public boolean isOn(){  return buttonOn;}
}
