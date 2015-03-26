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

/**
 * <p>Title: LightOn (Lights-Off)</p>
 * <p>Description: Simple logic game for a quick break </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: GravitySoft</p>
 * @author: Steve Gee
 * @version 1.0
 */

import javax.swing.UIManager;
import java.awt.*;

public class LightsOn {
  boolean packFrame = false;

  public LightsOn() {
    LightsOnUI frame = new LightsOnUI();
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new LightsOn();
  }
}