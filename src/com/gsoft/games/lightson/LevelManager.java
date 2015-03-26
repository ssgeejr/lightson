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

import java.util.ArrayList;

public class LevelManager extends ArrayList{

  public LevelManager(){
    initLevels();
  }

  private void initLevels(){
    this.add("1111111011100010101000100");
    this.add("1111101010010100101011111");
    this.add("0000000000011101101110001");
    this.add("0111000000000000100100000");
    this.add("0111110111110111110111110");
    this.add("1010100100111110010010101");
  }

  public String nextLevel(int level){
    return (String)this.get(level);
  }
}